package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "buses")
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numBus;
	private int placesLimite;
	@ManyToOne
	@JoinColumn(name = "conducteur_id")
	private Conducteur conducteur;
	@OneToMany(mappedBy = "bus")
	private Set<Voyage> voyages;

	public Bus() {
		this.voyages = new HashSet<>();
	}

	public Bus(int numBus, int placesLimite) {
		this();
		this.numBus = numBus;
		this.placesLimite = placesLimite;
	}

	public Bus(int numBus, int placesLimite, Conducteur conducteur) {
		this(numBus, placesLimite);
		this.conducteur = conducteur;
	}

	public Bus(Long id, int numBus, int placesLimite) {
		this(numBus, placesLimite);
		this.id = id;
	}

	public Bus(Long id, int numBus, int placesLimite, Conducteur conducteur) {
		this(numBus, placesLimite, conducteur);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
