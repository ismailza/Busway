package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Conducteur;
import ma.fstm.ilisi.busway.dto.ConducteurDTO;

public interface ConducteurServiceInterface extends ServiceInterface<ConducteurDTO> {
    ConducteurDTO findById(Long id);
    Conducteur mapToConducteur(ConducteurDTO conducteurDTO);
    ConducteurDTO mapToConducteurDTO(Conducteur conducteur);
}
