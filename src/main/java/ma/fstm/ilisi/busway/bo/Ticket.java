package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;
import java.io.Serializable;

public class Ticket implements Serializable {
    private Long id;

    private String qrCodeData;

    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrCodeData() {
        return qrCodeData;
    }

    public void setQrCodeData(String qrCodeData) {
        this.qrCodeData = qrCodeData;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
