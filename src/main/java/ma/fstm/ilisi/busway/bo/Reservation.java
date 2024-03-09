package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime date;
	@ManyToOne
	@JoinColumn(name = "voyage_id")
	private Voyage voyage;
	@ManyToOne
	@JoinColumn(name = "passager_id")
	private Passager passager;
	@ManyToOne
	@JoinColumn(name = "depart_id")
	private Station depart;
	@ManyToOne
	@JoinColumn(name = "arrivee_id")
	private Station arrivee;

	public Reservation() {
		this.date = LocalDateTime.now();
	}

	public Reservation(LocalDateTime date, Voyage voyage, Passager passager, Station depart, Station arrivee) {
		this();
		this.date = date;
		this.voyage = voyage;
		this.passager = passager;
		this.depart = depart;
		this.arrivee = arrivee;
	}

	public Reservation(Long id, LocalDateTime date, Voyage voyage, Passager passager, Station depart, Station arrivee) {
		this(date, voyage, passager, depart, arrivee);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
