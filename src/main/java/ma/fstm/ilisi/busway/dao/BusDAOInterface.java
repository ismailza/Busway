package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Bus;

public interface BusDAOInterface extends IDAO<Bus> {
    Bus findById(Long id);
    Bus findByBusNum(int numBus);
}
