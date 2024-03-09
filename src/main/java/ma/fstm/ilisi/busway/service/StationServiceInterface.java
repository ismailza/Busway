package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Station;
import ma.fstm.ilisi.busway.dto.StationDTO;

public interface StationServiceInterface extends ServiceInterface<StationDTO> {
    StationDTO findById(Long id);
    Station mapToStation(StationDTO stationDTO);
    StationDTO mapToStationDTO(Station station);
}
