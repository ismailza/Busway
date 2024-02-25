package ma.fstm.ilisi.busway.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StopStation {
    private LocalTime time;
    private List<Trip> trips;

    public StopStation(LocalTime time) {
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
