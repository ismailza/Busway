package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "passagers")
public class Passager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String prenom;
	private String nom;
	@Column(unique = true)
	private String email;
	@ManyToMany
	@JoinTable(name = "reservation_passager",
			joinColumns = @JoinColumn(name = "reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "passager_id"))
	private Set<Reservation> reservations;

	public Passager() {
		this.reservations = new HashSet<>();
	}

	public Passager(String prenom, String nom, String email) {
		this();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
	}

	public Passager(Long id, String prenom, String nom, String email) {
		this(prenom, nom, email);
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

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}

}
