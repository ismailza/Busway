package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Passager;
import ma.fstm.ilisi.busway.dto.PassagerDTO;
import ma.fstm.ilisi.busway.exception.PassengerNotFoundException;

public interface PassagerServiceInterface extends ServiceInterface<PassagerDTO> {
    PassagerDTO findById(Long id) throws PassengerNotFoundException;
    Passager mapToPassager(PassagerDTO passagerDTO);
    PassagerDTO mapToPassagerDTO(Passager passager);
}
