package ma.fstm.ilisi.busway.controller;

import ma.fstm.ilisi.busway.exception.StationNotFoundException;
import ma.fstm.ilisi.busway.model.Bus;
import ma.fstm.ilisi.busway.model.Station;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.service.ReservationService;
import ma.fstm.ilisi.busway.service.StationService;
import ma.fstm.ilisi.busway.service.TripService;
import ma.fstm.ilisi.busway.view.Main;

import javax.swing.*;

public class BusWayController {
    private final Main mainView;
    private final BusService busService;
    private final StationService stationService;
    private final ReservationService reservationService;
    private final TripService tripService;

    public BusWayController() {
        mainView = new Main(this);
        this.busService = new BusService();
        this.stationService = new StationService();
        this.reservationService = new ReservationService();
        this.tripService = new TripService();

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

    public void showAvailableTrips(String from, String to) {
        try {
            Station fromStation = stationService.getStationByName(from);
            Station toStation = stationService.getStationByName(to);
            if (fromStation == null || toStation == null)
                throw new StationNotFoundException();
            // TODO: Implement this method
            tripService.getAvailableTrips(fromStation, toStation);

        } catch (StationNotFoundException e) {
            JOptionPane.showMessageDialog(mainView, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }





    public static void main(String[] args) {
        new BusWayController();
    }

}
