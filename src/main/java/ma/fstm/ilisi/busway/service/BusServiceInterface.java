package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Bus;
import ma.fstm.ilisi.busway.dto.BusDTO;

public interface BusServiceInterface extends ServiceInterface<BusDTO> {
    BusDTO findById(Long id);
    BusDTO findByBusNum(int numBus);
    Bus mapToBus(BusDTO busDTO);
    BusDTO mapToBusDTO(Bus bus);
}
