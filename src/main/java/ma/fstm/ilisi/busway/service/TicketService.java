package ma.fstm.ilisi.busway.service;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import ma.fstm.ilisi.busway.bo.Ticket;
import ma.fstm.ilisi.busway.dto.ReservationDTO;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;

public class TicketService {

    public TicketService() {

    }

    public Ticket createTicket(ReservationDTO reservation) {
        try {
            Gson gson = new Gson();
            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("reservationId", "reservation.getId().toString()");
            dataMap.put("voyageId", "reservation.getVoyage().getId().toString()");
            dataMap.put("depart", "reservation.getDepart().getNom()");
            dataMap.put("arrivee", "reservation.getArrivee().getNom()");
            dataMap.put("heureDepart", "reservation.getVoyage().getHeureDepart().toString()");
            dataMap.put("heureArrivee", "reservation.getVoyage().getHeureArrivee().toString()");
            dataMap.put("nomPassager", "reservation.getPassager().getNom() reservation.getPassager().getPrenom()");
            String qrCodeData = gson.toJson(dataMap);


            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 80, 80);
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] qrCodeImageBytes = pngOutputStream.toByteArray();

            String qrCodeImageBase64 = Base64.getEncoder().encodeToString(qrCodeImageBytes);

            Ticket ticket = new Ticket();
            ticket.setQrCodeData(qrCodeImageBase64);
            ticket.setReservation(new ReservationService().mapToReservation(reservation));

            // Sauvegardez le ticket dans la base de données
            // ticketRepository.save(ticket);

            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
