package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusService {
    private final List<Bus> buses;

    public BusService() {
        this.buses = new ArrayList<>();
        this.setupBuses();
    }

    public void addBus(Bus bus) {
        this.buses.add(bus);
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public Bus getBusByNumber(int busNumber) {
        for (Bus bus : buses) {
            if (bus.getBusNumber() == busNumber) {
                return bus;
            }
        }
        return null;
    }

    public void setupBuses() {
        Bus bus1 = new Bus(1, 50);
        Bus bus2 = new Bus(2, 50);
        Bus bus3 = new Bus(3, 50);
        Bus bus4 = new Bus(4, 50);
        Bus bus5 = new Bus(5, 50);
        Bus bus6 = new Bus(6, 50);
        Bus bus7 = new Bus(7, 50);
        Bus bus8 = new Bus(8, 50);
        Bus bus9 = new Bus(9, 50);
        Bus bus10 = new Bus(10, 50);
        this.addBus(bus1);
        this.addBus(bus2);
        this.addBus(bus3);
        this.addBus(bus4);
        this.addBus(bus5);
        this.addBus(bus6);
        this.addBus(bus7);
        this.addBus(bus8);
        this.addBus(bus9);
        this.addBus(bus10);
    }
}
