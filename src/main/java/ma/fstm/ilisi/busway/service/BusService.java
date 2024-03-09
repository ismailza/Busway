package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Bus;
import ma.fstm.ilisi.busway.dao.BusDAO;
import ma.fstm.ilisi.busway.dto.BusDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BusService implements BusServiceInterface {

    private BusDAO busDAO;

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
    public BusDTO findById(Long id) {
        return this.mapToBusDTO(this.busDAO.findById(id));
    }

    @Override
    public BusDTO findByBusNum(int numBus) {
        return this.mapToBusDTO(this.busDAO.findByBusNum(numBus));
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
