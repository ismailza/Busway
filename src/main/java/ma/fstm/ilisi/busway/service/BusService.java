package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusService {
    private static final List<Bus> buses;

    static {
        buses = new ArrayList<>(List.of(
                new Bus(1, 90),
                new Bus(2, 120),
                new Bus(3, 110),
                new Bus(4, 60),
                new Bus(5, 30)
        ));
    }

    public void create(Bus bus) {
        buses.add(bus);
    }

    public Bus findByNum(int numBus) {
        for (Bus bus : buses) {
            if (bus.getNumBus() == numBus)
                return bus;
        }
        return null;
    }

    public List<Bus> retreive() {
        return buses;
    }

}
