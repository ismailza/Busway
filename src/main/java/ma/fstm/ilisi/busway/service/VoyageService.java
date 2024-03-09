package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Voyage;
import ma.fstm.ilisi.busway.dao.VoyageDAO;
import ma.fstm.ilisi.busway.dto.StationDTO;
import ma.fstm.ilisi.busway.dto.VoyageDTO;
import ma.fstm.ilisi.busway.exception.VoyageNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class VoyageService implements VoyageServiceInterface {
    private VoyageDAO voyageDAO;

    public VoyageService() {
        this.voyageDAO = new VoyageDAO();
    }

    public VoyageService(VoyageDAO voyageDAO) {
        this.voyageDAO = voyageDAO;
    }

    @Override
    public List<VoyageDTO> retreive() {
        return this.voyageDAO.retreive().stream().map(this::mapToVoyageDTO).collect(Collectors.toList());
    }

    @Override
    public boolean create(VoyageDTO voyageDTO) {
        return this.voyageDAO.create(this.mapToVoyage(voyageDTO));
    }

    @Override
    public boolean update(VoyageDTO voyageDTO) {
        return this.voyageDAO.update(this.mapToVoyage(voyageDTO));
    }

    @Override
    public boolean delete(VoyageDTO voyageDTO) {
        return this.voyageDAO.delete(this.mapToVoyage(voyageDTO));
    }

    @Override
    public VoyageDTO findById(Long id) throws VoyageNotFoundException {
        Voyage voyage = this.voyageDAO.findById(id);
        if (voyage == null)
            throw new VoyageNotFoundException("Voyage not found!");
        return this.mapToVoyageDTO(voyage);
    }

    @Override
    public Voyage mapToVoyage(VoyageDTO voyageDTO) {
        return new Voyage();
    }

    @Override
    public VoyageDTO mapToVoyageDTO(Voyage voyage) {
        return new VoyageDTO();
    }

    public List<VoyageDTO> trouverVoyagesDisponibles(StationDTO depart, StationDTO arrivee) {
        return null;
    }
}
