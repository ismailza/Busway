package ma.fstm.ilisi.busway.bo;

import java.util.HashSet;
import java.util.Set;

public class Bus {
	private int numBus;
	private int placesLimite;
	private Conducteur conducteur;
	private Set<Voyage> voyages;

	public Bus(int numBus, int placesLimite) {
		this.numBus = numBus;
		this.placesLimite = placesLimite;
		this.voyages = new HashSet<>();
	}

	public Bus(int numBus, int placesLimite, Conducteur conducteur) {
		this(numBus, placesLimite);
		this.conducteur = conducteur;
	}

	public int getNumBus() {
		return numBus;
	}

	public int getPlacesLimite() {
		return placesLimite;
	}

	public Set<Voyage> getVoyages() {
		return voyages;
	}

	public void setNumBus(int numBus) {
		this.numBus = numBus;
	}

	public void setPlacesLimite(int placesLimite) {
		this.placesLimite = placesLimite;
	}

	public Conducteur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	public void setVoyages(Set<Voyage> voyages) {
		this.voyages = voyages;
	}

	public void addVoyage(Voyage voyage) {
		this.voyages.add(voyage);
	}

}
