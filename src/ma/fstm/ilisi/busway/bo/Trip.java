package ma.fstm.ilisi.busway.bo;

import java.time.LocalDateTime;
import java.util.*;

public class Trip {
	private LocalDateTime date;
	private float tarif;
	private Bus bus;
	private List<Driver> drivers;
	private Set<Reservation> reservations;
	private StartStation startStation;
	private EndStation endStation;
	private List<StopStation> stopStations;

	public Trip(LocalDateTime date, float tarif) {
		this.date = date;
		this.tarif = tarif;
		this.drivers = new ArrayList<>();
		this.reservations = new HashSet<>();
		this.startStation = null;
		this.stopStations = new ArrayList<>();
		this.endStation = null;
	}

	public Trip(LocalDateTime date, float tarif, Bus bus, StartStation startStation, EndStation endStation) {
		this(date, tarif);
		this.bus = bus;
		this.startStation = startStation;
		this.endStation = endStation;
	}

	public Trip(LocalDateTime date, float tarif, Bus bus, List<Driver> drivers, TreeSet<Reservation> reservations, StartStation startStation, EndStation endStation, List<StopStation> stopStations) {
		this(date, tarif, bus, startStation, endStation);
		this.drivers = drivers;
		this.reservations = reservations;
		this.stopStations = stopStations;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public float getTarif() {
		return tarif;
	}

	public Bus getBus() {
		return bus;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public StartStation getStartStation() {
		return startStation;
	}

	public EndStation getEndStation() {
		return endStation;
	}

	public List<StopStation> getStopStations() {
		return stopStations;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public void setStartStation(StartStation startStation) {
		this.startStation = startStation;
	}

	public void setEndStation(EndStation endStation) {
		this.endStation = endStation;
	}

	public void addDriver(Driver driver) {
		this.drivers.add(driver);
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

	public void addStopStation(StopStation stopStation) {
		this.stopStations.add(stopStation);
	}

	public boolean isAvailable(Station from, Station to) {
		if (startStation.getStation().equals(from))
			return reservations.size() < bus.getCapacity();
		// TODO: Implement this method
		return reservations.size() < bus.getCapacity();

		 // return false;
	}

	public boolean passByStation(Station station) {
		if (startStation.getStation().equals(station) || endStation.getStation().equals(station))
			return true;
		for (StopStation stopStation : stopStations) {
			if (stopStation.getStation().equals(station))
				return true;
		}
		return false;
	}


}
