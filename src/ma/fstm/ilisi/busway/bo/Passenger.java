package ma.fstm.ilisi.busway.bo;

import java.util.HashSet;
import java.util.Set;

public class Passenger {
	private String lastname;
	private String firstname;
	private String email;
	private Set<Reservation> reservations;

	public Passenger(String lastname, String firstname, String email) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.reservations = new HashSet<>();
	}

	public String getLastname() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getEmail() {
		return email;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

}
