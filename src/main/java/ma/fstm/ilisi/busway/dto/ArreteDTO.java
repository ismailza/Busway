package ma.fstm.ilisi.busway.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ArreteDTO {
    private Long id;
    private StationDTO station;
    private LocalTime heureArrete;
    private List<VoyageDTO> voyages;

    public ArreteDTO() {
        this.voyages = new ArrayList<>();
    }

    public ArreteDTO(StationDTO station, LocalTime heureArrete) {
        this();
        this.station = station;
        this.heureArrete = heureArrete;
    }

    public ArreteDTO(Long id, StationDTO station, LocalTime heureArrete) {
        this(station, heureArrete);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StationDTO getStation() {
        return station;
    }

    public void setStation(StationDTO station) {
        this.station = station;
    }

    public LocalTime getHeureArrete() {
        return heureArrete;
    }

    public void setHeureArrete(LocalTime heureArrete) {
        this.heureArrete = heureArrete;
    }

    public List<VoyageDTO> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<VoyageDTO> voyages) {
        this.voyages = voyages;
    }

    public void addVoyage(VoyageDTO voyage) {
        this.voyages.add(voyage);
    }
}
