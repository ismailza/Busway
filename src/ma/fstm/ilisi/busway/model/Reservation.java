package ma.fstm.ilisi.busway.model;

import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime date;
	private Trip trip;
	private Passenger passenger;
	private Station from;
	private Station to;

	public Reservation(LocalDateTime date, Trip trip, Passenger passenger, Station from, Station to) {
		this.date = date;
		this.trip = trip;
		this.passenger = passenger;
		this.from = from;
		this.to = to;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Trip getTrip() {
		return trip;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public Station getFrom() {
		return from;
	}

	public Station getTo() {
		return to;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public void setFrom(Station from) {
		this.from = from;
	}

	public void setTo(Station to) {
		this.to = to;
	}
}
