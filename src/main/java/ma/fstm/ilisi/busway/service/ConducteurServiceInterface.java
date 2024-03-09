package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Conducteur;
import ma.fstm.ilisi.busway.dto.ConducteurDTO;
import ma.fstm.ilisi.busway.exception.DriverNotFoundException;

public interface ConducteurServiceInterface extends ServiceInterface<ConducteurDTO> {
    ConducteurDTO findById(Long id) throws DriverNotFoundException;
    Conducteur mapToConducteur(ConducteurDTO conducteurDTO);
    ConducteurDTO mapToConducteurDTO(Conducteur conducteur);
}
