package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.dto.StationDTO;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;
import ma.fstm.ilisi.busway.service.StationService;

import java.io.IOException;

public class StationController extends HttpServlet {
    private StationService stationService;

    @Override
    public void init() throws ServletException {
        this.stationService = new StationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/stations" -> {
                req.setAttribute("stations", this.stationService.retreive());
                req.getRequestDispatcher("stations.jsp").forward(req, resp);
            }
            case "/newStation" -> {
                req.getRequestDispatcher("stationForm.jsp").forward(req, resp);
            }
            case "/editStation" -> {
                try {
                    req.setAttribute("station", this.stationService.findById(Long.parseLong(req.getParameter("id"))));
                    req.getRequestDispatcher("stationForm.jsp").forward(req, resp);
                } catch (StationNotFoundException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                    resp.sendRedirect(req.getContextPath() + "/stations");
                }
            }
            default -> resp.sendRedirect(req.getContextPath() + "/stations");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/saveStation" -> {
                try {
                    if (this.stationService.create(this.constructStationDTO(req)))
                        req.getSession().setAttribute("success", "Station ajoutée avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (StationNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
            }
            case "/updateStation" -> {
                try {
                    if (this.stationService.update(this.constructStationDTO(req)))
                        req.getSession().setAttribute("success", "Station modifiée avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (StationNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
            }
            case "/deleteStation" -> doDelete(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/stations");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (this.stationService.delete(this.stationService.findById(Long.parseLong(req.getParameter("id")))))
                req.getSession().setAttribute("success", "Station supprimée avec succès");
            else
                req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
        } catch (StationNotFoundException | NumberFormatException e) {
            req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
        }
    }

    private StationDTO constructStationDTO(HttpServletRequest req) throws StationNotFoundException, NumberFormatException {
        String id = req.getParameter("id");
        String nom = req.getParameter("nom");
        StationDTO stationDTO = new StationDTO(nom);
        if (id != null && !id.isEmpty())
            stationDTO.setId(Long.parseLong(id));
        return stationDTO;
    }
}
