package ma.fstm.ilisi.busway.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private LocalDateTime date;
    private VoyageDTO voyage;
    private PassagerDTO passager;
    private StationDTO depart;
    private StationDTO arrivee;

    public ReservationDTO() {
        this.date = LocalDateTime.now();
    }

    public ReservationDTO(LocalDateTime date, VoyageDTO voyage, PassagerDTO passager, StationDTO depart, StationDTO arrivee) {
        this();
        this.date = date;
        this.voyage = voyage;
        this.passager = passager;
        this.depart = depart;
        this.arrivee = arrivee;
    }

    public ReservationDTO(Long id, LocalDateTime date, VoyageDTO voyage, PassagerDTO passager, StationDTO depart, StationDTO arrivee) {
        this(date, voyage, passager, depart, arrivee);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public VoyageDTO getVoyage() {
        return voyage;
    }

    public PassagerDTO getPassager() {
        return passager;
    }

    public StationDTO getDepart() {
        return depart;
    }

    public StationDTO getArrivee() {
        return arrivee;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setVoyage(VoyageDTO voyage) {
        this.voyage = voyage;
    }

    public void setPassager(PassagerDTO passager) {
        this.passager = passager;
    }

    public void setDepart(StationDTO depart) {
        this.depart = depart;
    }

    public void setArrivee(StationDTO arrivee) {
        this.arrivee = arrivee;
    }

}
