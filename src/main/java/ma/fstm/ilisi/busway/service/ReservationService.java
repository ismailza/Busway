package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Reservation;
import ma.fstm.ilisi.busway.dto.ReservationDTO;
import ma.fstm.ilisi.busway.dto.StationDTO;

import java.util.List;

public class ReservationService implements ReservationServiceInterface {

    @Override
    public List<ReservationDTO> retreive() {
        return null;
    }

    @Override
    public boolean create(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean update(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public boolean delete(ReservationDTO reservationDTO) {
        return false;
    }

    @Override
    public Reservation mapToReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public ReservationDTO mapToReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setDate(reservation.getDate());
        reservationDTO.setDepart(new StationService().mapToStationDTO(reservation.getDepart()));
        reservationDTO.setArrivee(new StationService().mapToStationDTO(reservation.getArrivee()));
        // Set passenger
        return reservationDTO;
    }
}
