package ma.fstm.ilisi.busway.dto;

import java.util.ArrayList;
import java.util.List;

public class StationDTO {
    private Long id;
    private String nom;
    private List<ReservationDTO> reservationsDepart;
    private List<ReservationDTO> reservationsArrivee;
    private List<VoyageDTO> voyagesDepart;
    private List<VoyageDTO> voyagesArrivee;
    private List<ArreteDTO> arretes;

    public StationDTO() {
        super();
        this.reservationsDepart = new ArrayList<>();
        this.reservationsArrivee = new ArrayList<>();
        this.voyagesDepart = new ArrayList<>();
        this.voyagesArrivee = new ArrayList<>();
        this.arretes = new ArrayList<>();
    }

    public StationDTO(String nom) {
        this.nom = nom;
    }

    public StationDTO(Long id, String nom) {
        this(nom);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public List<ReservationDTO> getReservationsDepart() {
        return reservationsDepart;
    }

    public List<ReservationDTO> getReservationsArrivee() {
        return reservationsArrivee;
    }

    public List<VoyageDTO> getVoyagesArrivee() {
        return voyagesArrivee;
    }

    public List<VoyageDTO> getVoyagesDepart() {
        return voyagesDepart;
    }

    public List<ArreteDTO> getArretes() {
        return arretes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setReservationsDepart(List<ReservationDTO> reservationsDepart) {
        this.reservationsDepart = reservationsDepart;
    }

    public void setReservationsArrivee(List<ReservationDTO> reservationsArrivee) {
        this.reservationsArrivee = reservationsArrivee;
    }

    public void setVoyagesDepart(List<VoyageDTO> voyagesDepart) {
        this.voyagesDepart = voyagesDepart;
    }

    public void setVoyagesArrivee(List<VoyageDTO> voyagesArrivee) {
        this.voyagesArrivee = voyagesArrivee;
    }

    public void setArretes(List<ArreteDTO> arretes) {
        this.arretes = arretes;
    }

    public void addReservationDepart(ReservationDTO reservation) {
        this.reservationsDepart.add(reservation);
    }

    public void addReservationArrivee(ReservationDTO reservation) {
        this.reservationsArrivee.add(reservation);
    }

    @Override
    public String toString() {
        return "Station " + nom;
    }
}
