package ma.fstm.ilisi.busway.bo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Arrete {
    private Station station;
    private LocalTime heureArrete;
    private List<Voyage> voyages;

    public Arrete(Station station, LocalTime heureArrete) {
        this.station = station;
        this.heureArrete = heureArrete;
        this.voyages = new ArrayList<>();
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
