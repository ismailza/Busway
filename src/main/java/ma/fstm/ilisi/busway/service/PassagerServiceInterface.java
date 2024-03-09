package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Passager;
import ma.fstm.ilisi.busway.dto.PassagerDTO;

public interface PassagerServiceInterface extends ServiceInterface<PassagerDTO> {
    PassagerDTO findById(Long id);
    Passager mapToPassager(PassagerDTO passagerDTO);
    PassagerDTO mapToPassagerDTO(Passager passager);
}
