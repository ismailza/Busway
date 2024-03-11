package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Passager;
import ma.fstm.ilisi.busway.dto.PassagerDTO;

import java.util.List;

public class PassagerService implements PassagerServiceInterface {
    @Override
    public PassagerDTO findById(Long id) {
        return null;
    }

    @Override
    public Passager mapToPassager(PassagerDTO passagerDTO) {
        return new Passager(passagerDTO.getId(), passagerDTO.getPrenom(), passagerDTO.getNom(), passagerDTO.getEmail());
    }

    @Override
    public PassagerDTO mapToPassagerDTO(Passager passager) {
        return null;
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
