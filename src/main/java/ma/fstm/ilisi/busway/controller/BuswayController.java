package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.service.StationService;
import ma.fstm.ilisi.busway.service.VoyageService;

import java.io.IOException;

public class BuswayController extends HttpServlet {
    private BusService busService;
    private VoyageService voyageService;
    private StationService stationService;

    @Override
    public void init() throws ServletException {
        super.init();
        busService = new BusService();
        voyageService = new VoyageService();
        stationService = new StationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
