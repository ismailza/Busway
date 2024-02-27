package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.model.Station;

import java.util.ArrayList;
import java.util.List;

public class StationService {
    private final List<Station> stations;
    public StationService() {
        this.stations = new ArrayList<>();
        this.setupStations();
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    public Station getStationByName(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }

    public void setupStations() {
        Station station1 = new Station("Station 1", "Address 1");
        Station station2 = new Station("Station 2", "Address 2");
        Station station3 = new Station("Station 3", "Address 3");
        Station station4 = new Station("Station 4", "Address 4");
        Station station5 = new Station("Station 5", "Address 5");
        Station station6 = new Station("Station 6", "Address 6");
        Station station7 = new Station("Station 7", "Address 7");
        Station station8 = new Station("Station 8", "Address 8");
        Station station9 = new Station("Station 9", "Address 9");
        Station station10 = new Station("Station 10", "Address 10");
        this.addStation(station1);
        this.addStation(station2);
        this.addStation(station3);
        this.addStation(station4);
        this.addStation(station5);
        this.addStation(station6);
        this.addStation(station7);
        this.addStation(station8);
        this.addStation(station9);
        this.addStation(station10);
    }

}
