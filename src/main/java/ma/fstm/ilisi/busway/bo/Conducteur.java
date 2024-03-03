package ma.fstm.ilisi.busway.bo;

import java.util.ArrayList;
import java.util.List;

public class Conducteur {

	private String prenom;
	private String nom;
	private List<Bus> buses;

	public Conducteur(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
		this.buses = new ArrayList<>();
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public List<Bus> getBuses() {
		return buses;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addBus(Bus bus) {
		this.buses.add(bus);
	}

}
