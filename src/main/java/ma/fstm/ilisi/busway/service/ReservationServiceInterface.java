package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Reservation;
import ma.fstm.ilisi.busway.dto.ReservationDTO;

public interface ReservationServiceInterface extends ServiceInterface<ReservationDTO> {
    ReservationDTO mapToReservation(ReservationDTO reservationDTO);
    ReservationDTO mapToReservationDTO(Reservation reservation);
}
