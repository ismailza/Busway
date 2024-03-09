package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Station;
import ma.fstm.ilisi.busway.dao.StationDAO;
import ma.fstm.ilisi.busway.dto.StationDTO;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class StationService implements StationServiceInterface {
    private StationDAO stationDAO;

    public StationService() {
        this.stationDAO = new StationDAO();
    }

    public StationService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    @Override
    public List<StationDTO> retreive() {
        return this.stationDAO.retreive().stream().map(this::mapToStationDTO).collect(Collectors.toList());
    }

    @Override
    public boolean create(StationDTO stationDTO) {
        return this.stationDAO.create(this.mapToStation(stationDTO));
    }

    @Override
    public boolean update(StationDTO stationDTO) {
        return this.stationDAO.update(this.mapToStation(stationDTO));
    }

    @Override
    public boolean delete(StationDTO stationDTO) {
        return this.stationDAO.delete(this.mapToStation(stationDTO));
    }

    @Override
    public StationDTO findById(Long id) throws StationNotFoundException {
        Station station = this.stationDAO.findById(id);
        if (station == null)
            throw new StationNotFoundException("Station not found!");
        return this.mapToStationDTO(station);
    }

    @Override
    public Station mapToStation(StationDTO stationDTO) {
        return new Station(
                stationDTO.getId(),
                stationDTO.getNom()
        );
    }

    @Override
    public StationDTO mapToStationDTO(Station station) {
        return new StationDTO(
                station.getId(),
                station.getNom()
        );
    }

}
