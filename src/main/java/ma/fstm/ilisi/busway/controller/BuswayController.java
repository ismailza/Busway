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
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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
        setupSomeTrips();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/install" -> {
                req.setAttribute("buses", this.busService.retreive());
                req.getRequestDispatcher("installInfoBus.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Installer infos busway
     * @param numBus Numéro du bus
     * @param placesLimite Nombre de places limite du bus
     * @param tarif Le tarif du voyage
     * @param prenomConducteur Prénom du conducteur
     * @param nomConducteur Nom du conducteur
     * @param heureDepart L'heure de départ
     * @param heureArrivee L'heure d'arrivée
     * @param depart La station de départ
     * @param arrivee La station d'arrivée
     * @param arretes Les stations d'arrêtes
     */
    public void installerInfosBusway(int numBus, int placesLimite, float tarif,
                                     String prenomConducteur, String nomConducteur,
                                     LocalTime heureDepart, LocalTime heureArrivee,
                                     String depart, String arrivee, Map<String, LocalTime> arretes) {
        Station stationDepart = this.stationService.findByName(depart);
        Station stationArrivee = this.stationService.findByName(arrivee);
        if (stationDepart == null || stationArrivee == null)
            return;
        Bus bus = new Bus(numBus, placesLimite, new Conducteur(prenomConducteur, nomConducteur));
        this.busService.create(bus);
        Voyage voyage = new Voyage(tarif, bus, heureDepart, heureArrivee, stationDepart, stationArrivee);
        for (Map.Entry<String, LocalTime> entry : arretes.entrySet()) {
            Station stationArrete = this.stationService.findByName(entry.getKey());
            if (stationArrete != null) {
                voyage.addArrete(new Arrete(stationArrete, entry.getValue()));
            }
        }
        this.voyageService.create(voyage);
    }

    private void setupSomeTrips() {
        Map<String, LocalTime> arretes = new HashMap<>();
        arretes.put("Station 2", LocalTime.of(7,15));
        arretes.put("Station 3", LocalTime.of(7, 30));
        arretes.put("Station 4", LocalTime.of(7, 45));
        this.installerInfosBusway(1, 80, 6, "Ismail", "ZAHIR", LocalTime.of(7,0), LocalTime.of(8,0), "Station 1", "Station 5", arretes);
    }

}
