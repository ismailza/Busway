package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conducteurs")
public class Conducteur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prenom;
	private String nom;
	@OneToMany(mappedBy = "conducteur")
	private List<Bus> buses;

	public Conducteur() {
		this.buses = new ArrayList<>();
	}

	public Conducteur(String prenom, String nom) {
		this();
		this.prenom = prenom;
		this.nom = nom;
	}

	public Conducteur(Long id, String prenom, String nom) {
		this(prenom, nom);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
