package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Bus;
import ma.fstm.ilisi.busway.dto.BusDTO;
import ma.fstm.ilisi.busway.exception.BusNotFoundException;

public interface BusServiceInterface extends ServiceInterface<BusDTO> {
    BusDTO findById(Long id) throws BusNotFoundException;
    BusDTO findByBusNum(int numBus) throws BusNotFoundException;
    Bus mapToBus(BusDTO busDTO);
    BusDTO mapToBusDTO(Bus bus);
}
