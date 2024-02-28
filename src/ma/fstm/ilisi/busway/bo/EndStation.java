package ma.fstm.ilisi.busway.bo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EndStation {
    private Station station;
    private LocalTime time;
    private List<Trip> trips;

    public EndStation(Station station, LocalTime time) {
        this.station = station;
        this.time = time;
        this.trips = new ArrayList<>();
    }

    public Station getStation() {
        return station;
    }

    public LocalTime getTime() {
        return time;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }
}
