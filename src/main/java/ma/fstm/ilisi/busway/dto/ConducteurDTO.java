package ma.fstm.ilisi.busway.dto;

import java.util.ArrayList;
import java.util.List;

public class ConducteurDTO {
    private Long id;
    private String prenom;
    private String nom;
    private List<BusDTO> buses;

    public ConducteurDTO() {
        this.buses = new ArrayList<>();
    }

    public ConducteurDTO(String prenom, String nom) {
        this();
        this.prenom = prenom;
        this.nom = nom;
    }

    public ConducteurDTO(Long id, String prenom, String nom) {
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

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<BusDTO> getBuses() {
        return buses;
    }

    public void setBuses(List<BusDTO> buses) {
        this.buses = buses;
    }

    public void addBus(BusDTO bus) {
        this.buses.add(bus);
    }
}
