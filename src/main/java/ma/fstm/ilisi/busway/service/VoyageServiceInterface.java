package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Voyage;
import ma.fstm.ilisi.busway.dto.VoyageDTO;
import ma.fstm.ilisi.busway.exception.VoyageNotFoundException;

public interface VoyageServiceInterface extends ServiceInterface<VoyageDTO> {
    VoyageDTO findById(Long id) throws VoyageNotFoundException;
    Voyage mapToVoyage(VoyageDTO voyageDTO);
    VoyageDTO mapToVoyageDTO(Voyage voyage);
}
