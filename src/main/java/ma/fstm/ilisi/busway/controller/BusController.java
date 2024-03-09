package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.dto.BusDTO;
import ma.fstm.ilisi.busway.exception.BusNotFoundException;
import ma.fstm.ilisi.busway.exception.DriverNotFoundException;
import ma.fstm.ilisi.busway.service.BusService;
import ma.fstm.ilisi.busway.service.ConducteurService;

import java.io.IOException;

public class BusController extends HttpServlet {
    private BusService busService;

    @Override
    public void init() throws ServletException {
        this.busService = new BusService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/buses" -> {
                req.setAttribute("buses", this.busService.retreive());
                req.getRequestDispatcher("buses.jsp").forward(req, resp);
            }
            case "/newBus" -> {
                req.setAttribute("conducteurs", new ConducteurService().retreive());
                req.getRequestDispatcher("busForm.jsp").forward(req, resp);
            }
            case "/editBus" -> {
                req.setAttribute("conducteurs", new ConducteurService().retreive());
                try {
                    req.setAttribute("bus", this.busService.findById(Long.parseLong(req.getParameter("id"))));
                    req.getRequestDispatcher("busForm.jsp").forward(req, resp);
                } catch (BusNotFoundException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                    resp.sendRedirect(req.getContextPath() + "/buses");
                }
            }
            default -> resp.sendRedirect(req.getContextPath() + "/buses");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/saveBus" -> {
                try {
                    if (this.busService.create(this.constructBusDTO(req)))
                        req.getSession().setAttribute("success", "Bus ajouté avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (DriverNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
            }
            case "/updateBus" -> {
                try {
                    if (this.busService.update(this.constructBusDTO(req)))
                        req.getSession().setAttribute("success", "Bus modifié avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (DriverNotFoundException | NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
            }
            case "/deleteBus" -> doDelete(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/buses");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (this.busService.delete(this.busService.findById(Long.parseLong(req.getParameter("id")))))
                req.getSession().setAttribute("success", "Bus supprimé avec succès");
            else
                req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
        } catch (BusNotFoundException | NumberFormatException e) {
            req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
        }
    }

    private BusDTO constructBusDTO(HttpServletRequest req) throws DriverNotFoundException, NumberFormatException {
        String id = req.getParameter("id");
        String busNum = req.getParameter("numBus");
        String capacity = req.getParameter("placesLimite");
        String driver = req.getParameter("conducteur");
        BusDTO busDTO = new BusDTO(Integer.parseInt(busNum), Integer.parseInt(capacity), new ConducteurService().findById(Long.parseLong(driver)));
        if (id != null && !id.isEmpty())
            busDTO.setId(Long.parseLong(id));
        return busDTO;
    }
}
