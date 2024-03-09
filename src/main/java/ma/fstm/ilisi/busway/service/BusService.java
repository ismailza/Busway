package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Bus;
import ma.fstm.ilisi.busway.dao.BusDAO;
import ma.fstm.ilisi.busway.dto.BusDTO;
import ma.fstm.ilisi.busway.exception.BusNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class BusService implements BusServiceInterface {

    private BusDAO busDAO;

    public BusService() {
        this.busDAO = new BusDAO();
    }

    public BusService(BusDAO busDAO) {
        this.busDAO = busDAO;
    }

    @Override
    public List<BusDTO> retreive() {
        return this.busDAO.retreive().stream().map(this::mapToBusDTO).collect(Collectors.toList());
    }

    @Override
    public boolean create(BusDTO bus) {
        return this.busDAO.create(this.mapToBus(bus));
    }

    @Override
    public boolean update(BusDTO bus) {
        return this.busDAO.update(this.mapToBus(bus));
    }

    @Override
    public boolean delete(BusDTO bus) {
        return this.busDAO.delete(this.mapToBus(bus));
    }

    @Override
    public BusDTO findById(Long id) throws BusNotFoundException {
        Bus bus = this.busDAO.findById(id);
        if (bus == null)
            throw new BusNotFoundException("Bus not found!");
        return this.mapToBusDTO(bus);
    }

    @Override
    public BusDTO findByBusNum(int numBus) throws BusNotFoundException {
        Bus bus = this.busDAO.findByBusNum(numBus);
        if (bus == null)
            throw new BusNotFoundException("Bus not found!");
        return this.mapToBusDTO(bus);
    }

    @Override
    public Bus mapToBus(BusDTO busDTO) {
        return new Bus(
                busDTO.getId(),
                busDTO.getNumBus(),
                busDTO.getPlacesLimite()
        );
    }

    @Override
    public BusDTO mapToBusDTO(Bus bus) {
        return new BusDTO(
                bus.getId(),
                bus.getNumBus(),
                bus.getPlacesLimite()
        );
    }

}
