package ma.fstm.ilisi.busway.dto;

import java.util.HashSet;
import java.util.Set;

public class PassagerDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private Set<ReservationDTO> reservations;

    public PassagerDTO() {
        this.reservations = new HashSet<>();
    }

    public PassagerDTO(String prenom, String nom, String email) {
        this();
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public PassagerDTO(Long id, String prenom, String nom, String email) {
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

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(ReservationDTO reservation) {
        this.reservations.add(reservation);
    }
}
