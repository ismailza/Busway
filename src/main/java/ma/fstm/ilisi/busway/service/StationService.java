package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Station;

import java.util.ArrayList;
import java.util.List;

public class StationService {
    private static final List<Station> stations;

    static {
        stations = new ArrayList<>(List.of(
                new Station("Station 1"),
                new Station("Station 2"),
                new Station("Station 3"),
                new Station("Station 4"),
                new Station("Station 5"),
                new Station("Station 6"),
                new Station("Station 7"),
                new Station("Station 8"),
                new Station("Station 9")
        ));
    }

    public Station findByName(String name) {
        for (Station station : stations)
            if (station.getNom().equalsIgnoreCase(name))
                return station;
        return null;
    }

    public List<Station> retreive() {
        return stations;
    }
}
