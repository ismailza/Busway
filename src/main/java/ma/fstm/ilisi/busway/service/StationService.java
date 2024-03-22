package ma.fstm.ilisi.busway.service;

import jakarta.validation.constraints.Null;
import ma.fstm.ilisi.busway.bo.Station;
import ma.fstm.ilisi.busway.dao.StationDAO;
import ma.fstm.ilisi.busway.dto.StationDTO;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;

import java.util.ArrayList;
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
    public Long switchCoordinates(Double lat, Double lon) {

        Double minDistance = Double.MAX_VALUE;
        Long nearestStationId = null;

        final int R = 6371; // Radius of the Earth in kilometers

        // Convert latitude and longitude to radians
        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon);

        for (Station station :  this.stationDAO.retreive()) {
            // Testing On the current position
            System.out.println("gpslat " + latRad);
            System.out.println("gps2lon " +lonRad);
            System.out.println("gps2lon " +station.getLatitude());



            // Convert station coordinates to radians
            double stationLatRad = Math.toRadians(station.getLatitude());
            double stationLonRad = Math.toRadians(station.getLongitude());

            // Calculate distance between current station and provided coordinates
            double dLat = stationLatRad - latRad;
            double dLon = stationLonRad - lonRad;
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(latRad) * Math.cos(stationLatRad) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c; // Distance in kilometers

            // Update minimum distance and nearest station id if a closer station is found
            if (distance < minDistance) {
                minDistance = distance;
                nearestStationId = station.getId();
            }
        }
System.out.println("id"+nearestStationId);
        return nearestStationId;
    }

    @Override
    public StationDTO mapToStationDTO(Station station) {
        return new StationDTO(
                station.getId(),
                station.getNom()
        );
    }

}
