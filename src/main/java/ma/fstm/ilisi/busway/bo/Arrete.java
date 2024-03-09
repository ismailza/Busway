package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "arretes")
public class Arrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
    private LocalTime heureArrete;
    @ManyToMany(mappedBy = "arretes")
    private List<Voyage> voyages;

    public Arrete() {
        this.voyages = new ArrayList<>();
    }

    public Arrete(Station station, LocalTime heureArrete) {
        this();
        this.station = station;
        this.heureArrete = heureArrete;
    }

    public Arrete(Long id, Station station, LocalTime heureArrete) {
        this(station, heureArrete);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public Station getStation() {
        return station;
    }

    public LocalTime getHeureArrete() {
        return heureArrete;
    }

    public List<Voyage> getVoyage() {
        return voyages;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setHeureArrete(LocalTime heureArrete) {
        this.heureArrete = heureArrete;
    }

    public void addVoyage(Voyage voyage) {
        this.voyages.add(voyage);
    }

}
