package ma.fstm.ilisi.busway.controller;

import ma.fstm.ilisi.busway.model.Bus;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.view.Main;

public class BusWayController {
    private final Main mainView;

    private final BusService busService;

    public BusWayController() {
        mainView = new Main(this);
        this.busService = new BusService();

        displayBuses();
    }

    public void addBus(int busNumber, int capacity) {
        Bus bus = new Bus(busNumber, capacity);
        busService.addBus(bus);
        mainView.getBusListPanel().addBus(String.valueOf(bus.getBusNumber()), bus.getCapacity());
    }

    public void displayBuses() {
        mainView.getBusListPanel().setTableData(busService.getBuses());
    }

    public void showAvailableTrips(String startStation, String endStation) {

    }





    public static void main(String[] args) {
        new BusWayController();
    }

}
