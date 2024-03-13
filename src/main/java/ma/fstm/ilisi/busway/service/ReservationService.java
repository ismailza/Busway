package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Reservation;
import ma.fstm.ilisi.busway.dao.ReservationDAO;
import ma.fstm.ilisi.busway.dto.ReservationDTO;

import java.util.List;

public class ReservationService implements ReservationServiceInterface {
    private ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    @Override
    public List<ReservationDTO> retreive() {
        return null;
    }

    @Override
    public boolean create(ReservationDTO reservationDTO) {
        return this.reservationDAO.create(this.mapToReservation(reservationDTO));
    }

    @Override
    public boolean update(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean delete(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public Reservation mapToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setDate(reservationDTO.getDate());
        reservation.setVoyage(new VoyageService().mapToVoyage(reservationDTO.getVoyage()));
        reservation.setPassager(new PassagerService().mapToPassager(reservationDTO.getPassager()));
        reservation.setDepart(new StationService().mapToStation(reservationDTO.getDepart()));
        reservation.setArrivee(new StationService().mapToStation(reservationDTO.getArrivee()));
        reservation.setQrCodeData(reservationDTO.getQrCodeData());
        return reservation;
    }

    @Override
    public ReservationDTO mapToReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setDate(reservation.getDate());
        reservationDTO.setDepart(new StationService().mapToStationDTO(reservation.getDepart()));
        reservationDTO.setArrivee(new StationService().mapToStationDTO(reservation.getArrivee()));
        reservationDTO.setQrCodeData(reservation.getQrCodeData());
        reservationDTO.setPassager(new PassagerService().mapToPassagerDTO(reservation.getPassager()));
        return reservationDTO;
    }
}
