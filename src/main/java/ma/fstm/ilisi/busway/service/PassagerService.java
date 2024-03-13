package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Passager;
import ma.fstm.ilisi.busway.dao.PassagerDAO;
import ma.fstm.ilisi.busway.dto.PassagerDTO;
import ma.fstm.ilisi.busway.exception.PassengerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class PassagerService implements PassagerServiceInterface {
    private PassagerDAO passagerDAO;

    public PassagerService() {
        this.passagerDAO = new PassagerDAO();
    }
    @Override
    public PassagerDTO findById(Long id) throws PassengerNotFoundException {
        Passager passager = this.passagerDAO.findById(id);
        if (passager == null)
            throw new PassengerNotFoundException("Passenger Not Found!");
        return this.mapToPassagerDTO(passager);
    }

    @Override
    public Passager mapToPassager(PassagerDTO passagerDTO) {
        return new Passager(passagerDTO.getId(), passagerDTO.getPrenom(), passagerDTO.getNom(), passagerDTO.getEmail());
    }

    @Override
    public PassagerDTO mapToPassagerDTO(Passager passager) {
        PassagerDTO passagerDTO = new PassagerDTO();
        passagerDTO.setId(passager.getId());
        passagerDTO.setPrenom(passager.getPrenom());
        passagerDTO.setNom(passager.getNom());
        passagerDTO.setReservations(passager.getReservations().stream().map(reservation -> new ReservationService().mapToReservationDTO(reservation)).collect(Collectors.toSet()));
        return passagerDTO;
    }

    @Override
    public List<PassagerDTO> retreive() {
        return null;
    }

    @Override
    public boolean create(PassagerDTO passagerDTO) {
        return false;
    }

    @Override
    public boolean update(PassagerDTO passagerDTO) {
        return false;
    }

    @Override
    public boolean delete(PassagerDTO passagerDTO) {
        return false;
    }
}
