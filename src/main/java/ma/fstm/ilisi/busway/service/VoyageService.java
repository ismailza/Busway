package ma.fstm.ilisi.busway.service;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import ma.fstm.ilisi.busway.bo.Arrete;
import ma.fstm.ilisi.busway.bo.Voyage;
import ma.fstm.ilisi.busway.dao.VoyageDAO;
import ma.fstm.ilisi.busway.dto.*;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;
import ma.fstm.ilisi.busway.exception.VoyageNotFoundException;

import java.io.ByteArrayOutputStream;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class VoyageService implements VoyageServiceInterface {
    private VoyageDAO voyageDAO;

    public VoyageService() {
        this.voyageDAO = new VoyageDAO();
    }

    public VoyageService(VoyageDAO voyageDAO) {
        this.voyageDAO = voyageDAO;
    }

    @Override
    public List<VoyageDTO> retreive() {
        return this.voyageDAO.retreive().stream().map(this::mapToVoyageDTO).collect(Collectors.toList());
    }

    @Override
    public boolean create(VoyageDTO voyageDTO) {
        return this.voyageDAO.create(this.mapToVoyage(voyageDTO));
    }

    @Override
    public boolean update(VoyageDTO voyageDTO) {
        return this.voyageDAO.update(this.mapToVoyage(voyageDTO));
    }

    @Override
    public boolean delete(VoyageDTO voyageDTO) {
        return this.voyageDAO.delete(this.mapToVoyage(voyageDTO));
    }

    @Override
    public VoyageDTO findById(Long id) throws VoyageNotFoundException {
        Voyage voyage = this.voyageDAO.findById(id);
        if (voyage == null)
            throw new VoyageNotFoundException("Voyage not found!");
        return this.mapToVoyageDTO(voyage);
    }

    @Override
    public Voyage mapToVoyage(VoyageDTO voyageDTO) {
        return new Voyage(
                voyageDTO.getId(),
                voyageDTO.getTarif(),
                new BusService().mapToBus(voyageDTO.getBus()),
                voyageDTO.getHeureDepart(),
                voyageDTO.getHeureArrivee(),
                new StationService().mapToStation(voyageDTO.getDepart()),
                new StationService().mapToStation(voyageDTO.getArrivee()),
                new ArrayList<>(voyageDTO.getArretes().stream().map(this::mapToArrete).collect(Collectors.toList()))
        );
    }

    @Override
    public VoyageDTO mapToVoyageDTO(Voyage voyage) {
        VoyageDTO voyageDTO = new VoyageDTO(
                voyage.getId(),
                voyage.getTarif(),
                new BusService().mapToBusDTO(voyage.getBus()),
                voyage.getHeureDepart(),
                voyage.getHeureArrivee(),
                new StationService().mapToStationDTO(voyage.getDepart()),
                new StationService().mapToStationDTO(voyage.getArrivee()),
                new ArrayList<>(voyage.getArretes().stream().map(this::mapToArreteDTO).collect(Collectors.toList())));
        voyageDTO.setReservations(voyage.getReservations().stream().map(reservation -> new ReservationService().mapToReservationDTO(reservation)).collect(Collectors.toSet()));
        return voyageDTO;
    }

    public Arrete mapToArrete(ArreteDTO arreteDTO) {
        return new Arrete(
                new StationService().mapToStation(arreteDTO.getStation()),
                arreteDTO.getHeureArrete()
        );
    }

    public ArreteDTO mapToArreteDTO(Arrete arrete) {
        return new ArreteDTO(
                new StationService().mapToStationDTO(arrete.getStation()),
                arrete.getHeureArrete()
        );
    }




    public Map<VoyageDTO, LocalTime> trouverVoyagesDisponibles(Long depart_id, Long arrivee_id) throws StationNotFoundException {
        StationService stationService = new StationService();
        StationDTO depart = stationService.findById(depart_id);
        StationDTO arrivee = stationService.findById(arrivee_id);
        Map<VoyageDTO, LocalTime> voyages = new HashMap<>();
        for (VoyageDTO voyageDTO : this.retreive()) {
            LocalTime departTime = this.mapToVoyage(voyageDTO).estDisponible(stationService.mapToStation(depart), stationService.mapToStation(arrivee));
            if (departTime != null)
                voyages.put(voyageDTO, departTime);
        }
        return voyages;
    }

    public ReservationDTO reserverVoyage(Long id, Long depart_id, Long arrivee_id, PassagerDTO passagerDTO) throws VoyageNotFoundException, StationNotFoundException {
        VoyageDTO voyageDTO = this.findById(id);
        if (voyageDTO == null)
            throw new VoyageNotFoundException("Voyage not found!");
        StationDTO stationDepart = new StationService().findById(depart_id);
        StationDTO stationArrivee = new StationService().findById(arrivee_id);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setVoyage(voyageDTO);
        reservationDTO.setPassager(passagerDTO);
        reservationDTO.setDepart(stationDepart);
        reservationDTO.setArrivee(stationArrivee);
        reservationDTO.setQrCodeData(this.createQRCode(reservationDTO));
        if (new ReservationService().create(reservationDTO))
            return reservationDTO;
        return null;
    }

    public String createQRCode(ReservationDTO reservation) {
        try {
            Gson gson = new Gson();
            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("Reservation", reservation.toString());
            String qrCodeData = gson.toJson(dataMap);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 80, 80);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] qrCodeImageBytes = pngOutputStream.toByteArray();

            return Base64.getEncoder().encodeToString(qrCodeImageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
