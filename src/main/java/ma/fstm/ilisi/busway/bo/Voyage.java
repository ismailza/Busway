package ma.fstm.ilisi.busway.bo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Voyage {
    private float tarif;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private Station depart;
    private Station arrivee;
    private List<Arrete> arretes;
    private Bus bus;
    private Set<Reservation> reservations;

    public Voyage(float tarif, Bus bus) {
        this.tarif = tarif;
        this.bus = bus;
        this.arretes = new ArrayList<>();
        this.reservations = new HashSet<>();
    }

    public Voyage(float tarif, Bus bus, LocalTime heureDepart, LocalTime heureArrivee, Station depart, Station arrivee) {
        this(tarif, bus);
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.depart = depart;
        this.arrivee = arrivee;
        this.arretes = new ArrayList<>();
        this.reservations = new HashSet<>();
    }

    public Voyage(float tarif, Bus bus, LocalTime heureDepart, LocalTime heureArrivee, Station depart, Station arrivee, List<Arrete> arretes) {
        this(tarif, bus, heureDepart, heureArrivee, depart, arrivee);
        this.arretes = arretes;
        this.reservations = new HashSet<>();
    }

    public float getTarif() {
        return tarif;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public Station getDepart() {
        return depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public List<Arrete> getArretes() {
        return arretes;
    }

    public Bus getBus() {
        return bus;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }

    public void setArrivee(Station arrivee) {
        this.arrivee = arrivee;
    }

    public void setArretes(List<Arrete> arretes) {
        this.arretes = arretes;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addArrete(Arrete arrete) {
        this.arretes.add(arrete);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

}