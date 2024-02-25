package ma.fstm.ilisi.busway.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EndStation {
    private LocalTime time;
    private List<Trip> trips;

    public EndStation(LocalTime time) {
        this.time = time;
        this.trips = new ArrayList<>();
    }

    public LocalTime getTime() {
        return time;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }
}
