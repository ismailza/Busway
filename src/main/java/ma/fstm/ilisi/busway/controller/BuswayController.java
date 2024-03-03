package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.bo.*;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.service.StationService;
import ma.fstm.ilisi.busway.service.VoyageService;

import java.io.IOException;
import java.util.List;

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
        switch (req.getServletPath()) {
            case "/search" -> {
                req.getRequestDispatcher("availableBus.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/search" -> {
                String depart = req.getParameter("depart");
                String arrivee = req.getParameter("arrivee");
                List<Voyage> voyagesDisponibles = this.consulterVoyagesDisponibles(depart, arrivee);
                if (voyagesDisponibles == null)
                    req.setAttribute("danger", "Une ou plusieurs stations spécifiées sont introuvables.");
                else
                    req.setAttribute("results", voyagesDisponibles);
                req.getRequestDispatcher("availableBus.jsp").forward(req, resp);
            }
        }
    }

    /**
     * Consulter les voyages disponibles
     * @param depart La station de départ
     * @param arrivee La station d'arrivée
     */
    public List<Voyage> consulterVoyagesDisponibles(String depart, String arrivee) {
        Station stationDepart = this.stationService.findByName(depart);
        Station stationArrivee = this.stationService.findByName(arrivee);
        if (stationDepart == null || stationArrivee == null)
            return null;
        return this.voyageService.trouverVoyagesDisponibles(stationDepart, stationArrivee);
    }

}
