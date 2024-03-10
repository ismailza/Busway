package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.dto.ArreteDTO;
import ma.fstm.ilisi.busway.dto.VoyageDTO;
import ma.fstm.ilisi.busway.exception.BusNotFoundException;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;
import ma.fstm.ilisi.busway.exception.VoyageNotFoundException;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.service.StationService;
import ma.fstm.ilisi.busway.service.VoyageService;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VoyageController extends HttpServlet {
    private VoyageService voyageService;

    @Override
    public void init() throws ServletException {
        this.voyageService = new VoyageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/" -> {
                req.setAttribute("stations", new StationService().retreive());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
            case "/voyages" -> {
                req.setAttribute("voyages", this.voyageService.retreive());
                req.getRequestDispatcher("voyages.jsp").forward(req, resp);
            }
            case "/newVoyage" -> {
                req.setAttribute("buses", new BusService().retreive());
                req.setAttribute("stations", new StationService().retreive());
                req.getRequestDispatcher("voyageForm.jsp").forward(req, resp);
            }
            case "/editVoyage" -> {
                try {
                    req.setAttribute("voyage", this.voyageService.findById(Long.parseLong(req.getParameter("id"))));
                    req.setAttribute("buses", new BusService().retreive());
                    req.setAttribute("stations", new StationService().retreive());
                    req.getRequestDispatcher("voyageForm.jsp").forward(req, resp);
                } catch (VoyageNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                    resp.sendRedirect(req.getContextPath() + "/voyages");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/search" -> {
                try {
                    Long depart = Long.parseLong(req.getParameter("depart"));
                    Long arrivee = Long.parseLong(req.getParameter("arrivee"));
                    Map<VoyageDTO, LocalTime> voyagesDisponibles = this.voyageService.trouverVoyagesDisponibles(depart, arrivee);
                    if (voyagesDisponibles.isEmpty())
                        req.getSession().setAttribute("danger", "Aucun voyages disponible.");
                    else
                        req.setAttribute("results", voyagesDisponibles);
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                } catch (StationNotFoundException e) {
                    req.getSession().setAttribute("danger", e.getMessage());
                    resp.sendRedirect(req.getContextPath() + "/");
                }
            }
            case "/saveVoyage" -> {
                try {
                    if (this.voyageService.create(this.constructVoyageDTO(req)))
                        req.getSession().setAttribute("success", "Voyage ajouté avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (StationNotFoundException | BusNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
                resp.sendRedirect(req.getContextPath() + "/voyages");
            }
            case "/updateVoyage" -> {
                try {
                    if (this.voyageService.update(this.constructVoyageDTO(req)))
                        req.getSession().setAttribute("success", "Voyage ajouté avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (StationNotFoundException | BusNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
                resp.sendRedirect(req.getContextPath() + "/voyages");
            }
            case "/deleteVoyage" -> {
                doDelete(req, resp);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (this.voyageService.delete(this.voyageService.findById(Long.parseLong(req.getParameter("id")))))
                req.getSession().setAttribute("success", "Voyage supprimée avec succès");
            else
                req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
        } catch (VoyageNotFoundException | NumberFormatException e) {
            req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
        }
    }

    private VoyageDTO constructVoyageDTO(HttpServletRequest req) throws BusNotFoundException, StationNotFoundException, NumberFormatException {
        String id = req.getParameter("id");
        Long bus = Long.parseLong(req.getParameter("bus"));
        float tarif = Float.parseFloat(req.getParameter("tarif"));
        LocalTime heureDepart = LocalTime.parse(req.getParameter("heureDepart"));
        LocalTime heureArrivee = LocalTime.parse(req.getParameter("heureArrivee"));
        Long stationDepart = Long.parseLong(req.getParameter("stationDepart"));
        Long stationArrivee = Long.parseLong(req.getParameter("stationArrivee"));

        StationService stationService = new StationService();

        Map<String, String[]> params = req.getParameterMap();
        List<ArreteDTO> arretes = new ArrayList<>();
        for (int i = 1; params.containsKey("stationsArret[" + i + "][nom]"); i++) {
            Long stationArrete = Long.parseLong(req.getParameter("stationsArret[" + i + "][nom]"));
            LocalTime heureArret = LocalTime.parse(req.getParameter("stationsArret[" + i + "][heure]"));
            arretes.add(new ArreteDTO(stationService.findById(stationArrete), heureArret));
        }
        VoyageDTO voyageDTO = new VoyageDTO(tarif, new BusService().findById(bus), heureDepart, heureArrivee, stationService.findById(stationDepart), stationService.findById(stationArrivee), arretes);
        if (id != null && !id.isEmpty())
            voyageDTO.setId(Long.parseLong(id));
        return voyageDTO;
    }

}
