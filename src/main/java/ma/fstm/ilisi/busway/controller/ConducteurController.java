package ma.fstm.ilisi.busway.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.dto.ConducteurDTO;
import ma.fstm.ilisi.busway.exception.DriverNotFoundException;
import ma.fstm.ilisi.busway.service.ConducteurService;

import java.io.IOException;

public class ConducteurController extends HttpServlet {
    private ConducteurService conducteurService;

    @Override
    public void init() throws ServletException {
        this.conducteurService = new ConducteurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/conducteurs" -> {
                req.setAttribute("conducteurs", this.conducteurService.retreive());
                req.getRequestDispatcher("conducteurs.jsp").forward(req, resp);
            }
            case "/newConducteur" -> {
                req.getRequestDispatcher("conducteurForm.jsp").forward(req, resp);
            }
            case "/editConducteur" -> {
                try {
                    req.setAttribute("conducteur", this.conducteurService.findById(Long.parseLong(req.getParameter("id"))));
                    req.getRequestDispatcher("conducteurForm.jsp").forward(req, resp);
                } catch (DriverNotFoundException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                    resp.sendRedirect(req.getContextPath() + "/conducteurs");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/saveConducteur" -> {
                try {
                    if (this.conducteurService.create(this.constructConducteurDTO(req)))
                        req.getSession().setAttribute("success", "Conducteur ajouté avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
            }
            case "/updateConducteur" -> {
                try {
                    if (this.conducteurService.update(this.constructConducteurDTO(req)))
                        req.getSession().setAttribute("success", "Conducteur modifié avec succès");
                    else
                        req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
                } catch (NumberFormatException e) {
                    req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
                }
            }
            case "/deleteConducteur" -> {
                doDelete(req, resp);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/conducteurs");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (this.conducteurService.delete(this.conducteurService.findById(Long.parseLong(req.getParameter("id")))))
                req.getSession().setAttribute("success", "Bus supprimé avec succès");
            else
                req.getSession().setAttribute("danger", "Echec!, une erreur s'est produit!");
        } catch (DriverNotFoundException | NumberFormatException e) {
            req.getSession().setAttribute("danger", "Echec!, " + e.getMessage());
        }
    }

    private ConducteurDTO constructConducteurDTO(HttpServletRequest req) throws NumberFormatException {
        String id = req.getParameter("id");
        String firstname = req.getParameter("prenom");
        String lastname = req.getParameter("nom");
        ConducteurDTO conducteurDTO = new ConducteurDTO(firstname, lastname);
        if (id != null && !id.isEmpty())
            conducteurDTO.setId(Long.parseLong(id));
        return conducteurDTO;
    }
}
