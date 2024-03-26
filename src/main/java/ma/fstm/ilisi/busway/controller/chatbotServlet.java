package ma.fstm.ilisi.busway.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi.busway.ChatBot.chatbot;
import ma.fstm.ilisi.busway.dto.PassagerDTO;
import ma.fstm.ilisi.busway.dto.ReservationDTO;
import ma.fstm.ilisi.busway.dto.VoyageDTO;
import ma.fstm.ilisi.busway.exception.StationNotFoundException;
import ma.fstm.ilisi.busway.exception.VoyageNotFoundException;
import ma.fstm.ilisi.busway.service.VoyageService;

@WebServlet("/chatbotServlet")
public class chatbotServlet extends HttpServlet {

    private VoyageService voyageService;

    @Override
    public void init() throws ServletException {
        this.voyageService = new VoyageService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String userInput = req.getParameter("userInput");
        String botResponse = chatbot.getResponse(userInput);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");


        if (botResponse.equals("2")) {

            Map<VoyageDTO, LocalTime> voyagesDisponibles = null;

            try {
                voyagesDisponibles = this.voyageService.trouverVoyagesDisponibles(Long.valueOf(1), Long.parseLong(botResponse));
            } catch (StationNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (voyagesDisponibles.isEmpty()) {
                    botResponse="Aucun voyage disponible.";
                    response.getWriter().write(botResponse);
                } else {
                    botResponse="Oui, il y a des voyages disponibles.\nDonnez votre nom, prénom, puis terminez par 'valider' pour confirmer votre réservation.";
                    response.getWriter().write( botResponse);
                }

        } else if (botResponse.contains("valide")) {
            PassagerDTO passagerDTO = new PassagerDTO();
            passagerDTO.setId(1L);
            try {
                ReservationDTO reservation = this.voyageService.reserverVoyage(Long.valueOf(1), Long.valueOf(1), Long.valueOf(2), passagerDTO);
                if (reservation != null) {
                    botResponse="Réservation effectuée avec succès";
                    response.getWriter().write( botResponse);
                } else {
                    botResponse="Réservation non effectuée";
                    response.getWriter().write( botResponse);
                }
            } catch (VoyageNotFoundException | StationNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().write(botResponse);
        }
    }
}
