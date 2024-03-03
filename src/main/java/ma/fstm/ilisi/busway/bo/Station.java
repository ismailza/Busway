package ma.fstm.ilisi.busway.bo;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String nom;
    private List<Reservation> reservationsDepart;
    private List<Reservation> reservationArrivee;

    public Station(String nom) {
        this.nom = nom;
        this.reservationsDepart = new ArrayList<>();
        this.reservationArrivee = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Reservation> getReservationsDepart() {
        return reservationsDepart;
    }

    public List<Reservation> getReservationArrivee() {
        return reservationArrivee;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addReservationDepart(Reservation reservation) {
        this.reservationsDepart.add(reservation);
    }

    public void addReservationArrivee(Reservation reservation) {
        this.reservationArrivee.add(reservation);
    }

}
