package ma.fstm.ilisi.busway.dto;

import java.util.HashSet;
import java.util.Set;

public class BusDTO {
    private Long id;
    private int numBus;
    private int placesLimite;
    private ConducteurDTO conducteur;
    private Set<VoyageDTO> voyages;

    public BusDTO() {
        super();
        this.voyages = new HashSet<>();
    }

    public BusDTO(int numBus, int placesLimite) {
        this();
        this.numBus = numBus;
        this.placesLimite = placesLimite;
    }

    public BusDTO(int numBus, int placesLimite, ConducteurDTO conducteur) {
        this(numBus, placesLimite);
        this.conducteur = conducteur;
    }

    public BusDTO(Long id, int numBus, int placesLimite) {
        this(numBus, placesLimite);
        this.id = id;
    }

    public BusDTO(Long id, int numBus, int placesLimite, ConducteurDTO conducteur) {
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

    public void setNumBus(int numBus) {
        this.numBus = numBus;
    }

    public int getPlacesLimite() {
        return placesLimite;
    }

    public void setPlacesLimite(int placesLimite) {
        this.placesLimite = placesLimite;
    }

    public ConducteurDTO getConducteur() {
        return conducteur;
    }

    public void setConducteur(ConducteurDTO conducteur) {
        this.conducteur = conducteur;
    }

    public Set<VoyageDTO> getVoyages() {
        return voyages;
    }

    public void setVoyages(Set<VoyageDTO> voyages) {
        this.voyages = voyages;
    }

    public void addVoyage(VoyageDTO voyage) {
        this.voyages.add(voyage);
    }
}
