package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    private Double longitude;
    private Double latitude;
    @OneToMany(mappedBy = "depart")
    private List<Reservation> reservationsDepart;
    @OneToMany(mappedBy = "arrivee")
    private List<Reservation> reservationsArrivee;
    @OneToMany(mappedBy = "depart")
    private List<Voyage> voyagesDepart;
    @OneToMany(mappedBy = "arrivee")
    private List<Voyage> voyagesArrivee;
    @OneToMany(mappedBy = "station")
    private List<Arrete> arretes;

    public Station() {
        super();
        this.reservationsDepart = new ArrayList<>();
        this.reservationsArrivee = new ArrayList<>();
        this.voyagesDepart = new ArrayList<>();
        this.voyagesArrivee = new ArrayList<>();
        this.arretes = new ArrayList<>();
    }

    public Station(String nom) {
        this.nom = nom;
    }

    public Station(Long id, String nom) {
        this(nom);
        this.id = id;
    }
    public Station(Long id, String nom,double latitude,double longitude) {
        this(id,nom);
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public double getLatitude() {
        return latitude;

    };
    public void setLatitude(double lat) {
         latitude =lat;

    };
    public double getLongitude() {
        return this.longitude;

    };

    public void setLongitude(double lon) {
         longitude=lon;

    };
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public List<Reservation> getReservationsDepart() {
        return reservationsDepart;
    }

    public List<Reservation> getReservationsArrivee() {
        return reservationsArrivee;
    }

    public List<Voyage> getVoyagesArrivee() {
        return voyagesArrivee;
    }

    public List<Voyage> getVoyagesDepart() {
        return voyagesDepart;
    }

    public List<Arrete> getArretes() {
        return arretes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setReservationsDepart(List<Reservation> reservationsDepart) {
        this.reservationsDepart = reservationsDepart;
    }

    public void setReservationsArrivee(List<Reservation> reservationsArrivee) {
        this.reservationsArrivee = reservationsArrivee;
    }

    public void setVoyagesDepart(List<Voyage> voyagesDepart) {
        this.voyagesDepart = voyagesDepart;
    }

    public void setVoyagesArrivee(List<Voyage> voyagesArrivee) {
        this.voyagesArrivee = voyagesArrivee;
    }

    public void setArretes(List<Arrete> arretes) {
        this.arretes = arretes;
    }

    public void addReservationDepart(Reservation reservation) {
        this.reservationsDepart.add(reservation);
    }

    public void addReservationArrivee(Reservation reservation) {
        this.reservationsArrivee.add(reservation);
    }

}
