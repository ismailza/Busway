package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private final List<Trip> trips;

    public TripService() {
        this.trips = new ArrayList<>();
    }
    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

}
