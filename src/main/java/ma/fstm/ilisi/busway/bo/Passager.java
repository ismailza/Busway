package ma.fstm.ilisi.busway.bo;

import java.util.HashSet;
import java.util.Set;

public class Passager {
	private String prenom;
	private String nom;
	private String email;
	private Set<Reservation> reservations;

	public Passager(String prenom, String nom, String email) {
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.reservations = new HashSet<>();
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getEmail() {
		return email;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

}
