package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Station;
import ma.fstm.ilisi.busway.dto.StationDTO;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;

public interface StationServiceInterface extends ServiceInterface<StationDTO> {
    StationDTO findById(Long id) throws StationNotFoundException;
    Station mapToStation(StationDTO stationDTO);
    StationDTO mapToStationDTO(Station station);
}
