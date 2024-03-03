package ma.fstm.ilisi.busway.bo;

import java.time.LocalDateTime;

public class Reservation {
	private LocalDateTime date;
	private Voyage voyage;
	private Passager passager;
	private Station depart;
	private Station arrivee;

	public Reservation(LocalDateTime date, Voyage voyage, Passager passager, Station depart, Station arrivee) {
		this.date = date;
		this.voyage = voyage;
		this.passager = passager;
		this.depart = depart;
		this.arrivee = arrivee;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Voyage getVoyage() {
		return voyage;
	}

	public Passager getPassager() {
		return passager;
	}

	public Station getDepart() {
		return depart;
	}

	public Station getArrivee() {
		return arrivee;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}

	public void setPassager(Passager passager) {
		this.passager = passager;
	}

	public void setDepart(Station depart) {
		this.depart = depart;
	}

	public void setArrivee(Station arrivee) {
		this.arrivee = arrivee;
	}
}
