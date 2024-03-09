package ma.fstm.ilisi.busway.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoyageDTO {
    private Long id;
    private float tarif;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private StationDTO depart;
    private StationDTO arrivee;
    private List<ArreteDTO> arretes;
    private BusDTO bus;
    private Set<ReservationDTO> reservations;

    public VoyageDTO() {
        super();
    }

    public VoyageDTO(float tarif, BusDTO bus) {
        this();
        this.tarif = tarif;
        this.bus = bus;
        this.arretes = new ArrayList<>();
        this.reservations = new HashSet<>();
    }

    public VoyageDTO(float tarif, BusDTO bus, LocalTime heureDepart, LocalTime heureArrivee, StationDTO depart, StationDTO arrivee) {
        this(tarif, bus);
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.depart = depart;
        this.arrivee = arrivee;
        this.arretes = new ArrayList<>();
        this.reservations = new HashSet<>();
    }

    public VoyageDTO(float tarif, BusDTO bus, LocalTime heureDepart, LocalTime heureArrivee, StationDTO depart, StationDTO arrivee, List<ArreteDTO> arretes) {
        this(tarif, bus, heureDepart, heureArrivee, depart, arrivee);
        this.arretes = arretes;
        this.reservations = new HashSet<>();
    }

    public VoyageDTO(Long id, float tarif, BusDTO bus, LocalTime heureDepart, LocalTime heureArrivee, StationDTO depart, StationDTO arrivee, List<ArreteDTO> arretes) {
        this(tarif, bus, heureDepart, heureArrivee, depart, arrivee, arretes);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StationDTO getDepart() {
        return depart;
    }

    public StationDTO getArrivee() {
        return arrivee;
    }

    public List<ArreteDTO> getArretes() {
        return arretes;
    }

    public BusDTO getBus() {
        return bus;
    }

    public Set<ReservationDTO> getReservations() {
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

    public void setDepart(StationDTO depart) {
        this.depart = depart;
    }

    public void setArrivee(StationDTO arrivee) {
        this.arrivee = arrivee;
    }

    public void setArretes(List<ArreteDTO> arretes) {
        this.arretes = arretes;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public void addArrete(ArreteDTO arrete) {
        this.arretes.add(arrete);
    }

    public void addReservation(ReservationDTO reservation) {
        this.reservations.add(reservation);
    }
}
