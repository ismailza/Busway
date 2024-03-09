package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Voyage;
import ma.fstm.ilisi.busway.dto.VoyageDTO;

public interface VoyageServiceInterface extends ServiceInterface<VoyageDTO> {
    VoyageDTO findById(Long id);
    Voyage mapToVoyage(VoyageDTO voyageDTO);
    VoyageDTO mapToVoyageDTO(Voyage voyage);
}
