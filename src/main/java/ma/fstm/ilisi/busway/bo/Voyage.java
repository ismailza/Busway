package ma.fstm.ilisi.busway.bo;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "voyages")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float tarif;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    @ManyToOne
    @JoinColumn(name = "depart_id")
    private Station depart;
    @ManyToOne
    @JoinColumn(name = "arrivee_id")
    private Station arrivee;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "voyage_arrete",
            joinColumns = @JoinColumn(name = "voyage_id"),
            inverseJoinColumns = @JoinColumn(name = "arrete_id"))
    private List<Arrete> arretes;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
    @OneToMany(mappedBy = "voyage", fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    public Voyage() {
        super();
    }

    public Voyage(float tarif, Bus bus) {
        this();
        this.tarif = tarif;
        this.bus = bus;
        this.arretes = new ArrayList<>();
        this.reservations = new HashSet<>();
    }

    public Voyage(float tarif, Bus bus, LocalTime heureDepart, LocalTime heureArrivee, Station depart, Station arrivee) {
        this(tarif, bus);
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.depart = depart;
        this.arrivee = arrivee;
        this.arretes = new ArrayList<>();
        this.reservations = new HashSet<>();
    }

    public Voyage(float tarif, Bus bus, LocalTime heureDepart, LocalTime heureArrivee, Station depart, Station arrivee, List<Arrete> arretes) {
        this(tarif, bus, heureDepart, heureArrivee, depart, arrivee);
        this.arretes = arretes;
        this.reservations = new HashSet<>();
    }

    public Voyage(Long id, float tarif, Bus bus, LocalTime heureDepart, LocalTime heureArrivee, Station depart, Station arrivee, List<Arrete> arretes) {
        this(tarif, bus, heureDepart, heureArrivee, depart, arrivee, arretes);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTarif() {
        return tarif;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public Station getDepart() {
        return depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public List<Arrete> getArretes() {
        return arretes;
    }

    public Bus getBus() {
        return bus;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public void setDepart(Station depart) {
        this.depart = depart;
    }

    public void setArrivee(Station arrivee) {
        this.arrivee = arrivee;
    }

    public void setArretes(List<Arrete> arretes) {
        this.arretes = arretes;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addArrete(Arrete arrete) {
        this.arretes.add(arrete);
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    /**
     * estDisponible: verifier si le voyage est disponible pour être réservé
     * @param stationDepart La station de départ de passager
     * @param stationArrivee La station d'arrivee de passager
     * @return true si le voyage est disponible, false sinon
     */
    public LocalTime estDisponible(Station stationDepart, Station stationArrivee) {
        LocalTime heureDepart = null;

        if (this.depart.getId().equals(stationDepart.getId())) {
            heureDepart = this.heureDepart;
        } else {
            for (Arrete arrete : arretes) {
                if (arrete.getStation().getId().equals(stationDepart.getId())) {
                    heureDepart = arrete.getHeureArrete();
                    break;
                }
            }
        }
        // If departure station is not found or its departure time is before current time
        if (heureDepart == null || LocalTime.now().isAfter(heureDepart))
            return null;
        // Check if the arrival station is reachable after the departure
        if (this.arrivee.getId().equals(stationArrivee.getId())) {
            if (this.getNombreReservations(stationDepart, stationArrivee) < this.bus.getPlacesLimite())
                return heureDepart;
            return null;
        }
        for (Arrete arrete : arretes) {
            if (arrete.getStation().getId().equals(stationArrivee.getId())) {
                if (arrete.getHeureArrete().isAfter(heureDepart) &&
                        this.getNombreReservations(stationDepart, stationArrivee) < this.bus.getPlacesLimite())
                    return heureDepart;
                return null;
            }
        }
        return null;
    }

    private int getNombreReservations(Station depart, Station arrivee) {
        int count = 0;
        int indiceDepart = this.getIndexOfStation(depart);
        int indiceArrivee = this.getIndexOfStation(arrivee);
        for (Reservation reservation : reservations) {
            if ((this.getIndexOfStation(reservation.getDepart()) < indiceArrivee) &&
                (indiceDepart < this.getIndexOfStation(reservation.getArrivee())))
                count++;
        }
        return count;
    }

    private int getIndexOfStation(Station station) {
        if (depart.getId().equals(station.getId()))
            return 0;
        int index = 1;
        for (Arrete arrete : arretes) {
            if (arrete.getStation().getId().equals(station.getId()))
                return index;
            index++;
        }
        if (arrivee.getId().equals(station.getId()))
            return index;
        return -1;
    }


}
