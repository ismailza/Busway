package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<Reservation> reservationss;

    public ReservationService() {
        this.reservationss = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        this.reservationss.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservationss;
    }

    public List<Reservation> getReservationss(LocalDateTime date) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : reservationss) {
            if (reservation.getDate().equals(date)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

}
