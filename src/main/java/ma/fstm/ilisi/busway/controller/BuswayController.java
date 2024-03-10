package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.dto.StationDTO;
import ma.fstm.ilisi.busway.dto.VoyageDTO;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.service.StationService;
import ma.fstm.ilisi.busway.service.VoyageService;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
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
        setupSomeTrips();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/install" -> {
                req.setAttribute("buses", this.busService.retreive());
                req.setAttribute("stations", this.stationService.retreive());
                req.getRequestDispatcher("installInfoBus.jsp").forward(req, resp);
            }
            case "/voyages" -> {
                req.setAttribute("voyages", this.voyageService.retreive());
                req.getRequestDispatcher("voyages.jsp").forward(req, resp);
            }
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
                try {
                    Map<VoyageDTO, LocalTime> voyagesDisponibles = this.consulterVoyagesDisponibles(Long.parseLong(depart), Long.parseLong(arrivee));
                    if (voyagesDisponibles == null)
                        req.setAttribute("danger", "Une ou plusieurs stations spécifiées sont introuvables.");
                    else
                        req.setAttribute("results", voyagesDisponibles);
                    req.getRequestDispatcher("availableBus.jsp").forward(req, resp);
                } catch (StationNotFoundException e) {
                    req.getSession().setAttribute("danger", e.getMessage());
                    resp.sendRedirect(req.getContextPath() + "/search");
                }
            }
            case "/install" -> {
                try {
                    int numBus = Integer.parseInt(req.getParameter("numBus"));
                    int placesLimite = Integer.parseInt(req.getParameter("placesLimite"));

                    String prenomConducteur = req.getParameter("prenomConducteur");
                    String nomConducteur = req.getParameter("nomConducteur");

                    float tarif = Float.parseFloat(req.getParameter("tarif"));
                    LocalTime heureDepart = LocalTime.parse(req.getParameter("heureDepart"));
                    LocalTime heureArrivee = LocalTime.parse(req.getParameter("heureArrivee"));
                    String stationDepart = req.getParameter("stationDepart");
                    String stationArrivee = req.getParameter("stationArrivee");

                    Map<String, String[]> params = req.getParameterMap();
                    Map<String, LocalTime> arretes = new HashMap<>();
                    for (int i = 1; params.containsKey("stationsArret[" + i + "][nom]"); i++) {
                        String nomStation = req.getParameter("stationsArret[" + i + "][nom]");
                        String heureArret = req.getParameter("stationsArret[" + i + "][heure]");
                        if (nomStation != null && heureArret != null) {
                            arretes.put(nomStation, LocalTime.parse(heureArret));
                        }
                    }
                    this.installerInfosBusway(numBus, placesLimite, tarif, prenomConducteur, nomConducteur, heureDepart, heureArrivee, stationDepart, stationArrivee, arretes);
                    req.getSession().setAttribute("sucess", "Opération effectuée avec succès");
                } catch (NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Erreurs dans le format des données!");
                }
                resp.sendRedirect(req.getContextPath() + "/voyages");
            }
        }
    }
    /**
     * Consulter les voyages disponibles
     * @param depart La station de départ
     * @param arrivee La station d'arrivée
     */
    public Map<VoyageDTO, LocalTime> consulterVoyagesDisponibles(Long depart, Long arrivee) throws StationNotFoundException {
        StationDTO stationDepart = this.stationService.findById(depart);
        StationDTO stationArrivee = this.stationService.findById(arrivee);
        if (stationDepart == null || stationArrivee == null)
            return null;
        return this.voyageService.trouverVoyagesDisponibles(depart, arrivee);
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
        // TODO implement this method
    }

    private void setupSomeTrips() {
        Map<String, LocalTime> arretes1 = new HashMap<>();
        arretes1.put("Station 2", LocalTime.of(7,15));
        arretes1.put("Station 3", LocalTime.of(7, 30));
        arretes1.put("Station 4", LocalTime.of(7, 45));
        this.installerInfosBusway(1, 80, 6, "Ismail", "ZAHIR", LocalTime.of(7,0), LocalTime.of(8,0), "Station 1", "Station 5", arretes1);

        Map<String, LocalTime> arretes2 = new HashMap<>();
        arretes1.put("Station 2", LocalTime.of(17,15));
        arretes1.put("Station 3", LocalTime.of(17, 30));
        arretes1.put("Station 4", LocalTime.of(17, 45));
        this.installerInfosBusway(1, 80, 6, "Ismail", "ZAHIR", LocalTime.of(17,0), LocalTime.of(18,0), "Station 1", "Station 5", arretes1);
    }

}
