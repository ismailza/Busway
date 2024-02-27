package ma.fstm.ilisi.busway.model;

import java.util.ArrayList;
import java.util.List;

public class Bus {
	private int busNumber;
	private int capacity;
	private List<Trip> trips;

	public Bus(int busNumber, int capacity) {
		this.busNumber = busNumber;
		this.capacity = capacity;
		this.trips = new ArrayList<>();
	}

	public int getBusNumber() {
		return busNumber;
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void addTrip(Trip trip) {
		this.trips.add(trip);
	}

	@Override
	public String toString() {
		return "busNumber=" + busNumber + ", capacity=" + capacity;
	}
}
