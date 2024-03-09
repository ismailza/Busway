package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Conducteur;
import ma.fstm.ilisi.busway.dao.ConducteurDAO;
import ma.fstm.ilisi.busway.dto.ConducteurDTO;
import ma.fstm.ilisi.busway.exception.DriverNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ConducteurService implements ConducteurServiceInterface {
    private ConducteurDAO conducteurDAO;

    public ConducteurService() {
        this.conducteurDAO = new ConducteurDAO();
    }

    public ConducteurService(ConducteurDAO conducteurDAO) {
        this.conducteurDAO = conducteurDAO;
    }

    @Override
    public List<ConducteurDTO> retreive() {
        return this.conducteurDAO.retreive().stream().map(this::mapToConducteurDTO).collect(Collectors.toList());
    }

    @Override
    public boolean create(ConducteurDTO conducteurDTO) {
        return this.conducteurDAO.create(this.mapToConducteur(conducteurDTO));
    }

    @Override
    public boolean update(ConducteurDTO conducteurDTO) {
        return this.conducteurDAO.update(mapToConducteur(conducteurDTO));
    }

    @Override
    public boolean delete(ConducteurDTO conducteurDTO) {
        return this.conducteurDAO.delete(mapToConducteur(conducteurDTO));
    }

    @Override
    public ConducteurDTO findById(Long id) throws DriverNotFoundException {
        Conducteur conducteur = this.conducteurDAO.findById(id);
        if (conducteur == null)
            throw new DriverNotFoundException("Driver not found!");
        return this.mapToConducteurDTO(conducteur);
    }

    @Override
    public Conducteur mapToConducteur(ConducteurDTO conducteurDTO) {
        return new Conducteur(
                conducteurDTO.getId(),
                conducteurDTO.getPrenom(),
                conducteurDTO.getNom()
        );
    }

    @Override
    public ConducteurDTO mapToConducteurDTO(Conducteur conducteur) {
        return new ConducteurDTO(
                conducteur.getId(),
                conducteur.getPrenom(),
                conducteur.getNom()
        );
    }
}
