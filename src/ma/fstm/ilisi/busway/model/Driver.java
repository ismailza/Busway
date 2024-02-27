package ma.fstm.ilisi.busway.model;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	private String lastname;
	private String firstname;
	private List<Trip> trips;

	public Driver(String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.trips = new ArrayList<>();
	}

	public String getLastname() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void addTrip(Trip trip) {
		this.trips.add(trip);
	}

}
