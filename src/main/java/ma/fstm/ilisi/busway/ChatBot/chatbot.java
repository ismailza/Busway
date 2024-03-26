package ma.fstm.ilisi.busway.ChatBot;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;

public class chatbot {
    public static String getResponse(String userInput) {
        String resourcesPath = "C:/Users/gx178/eclipse-workspace/Busway/src/main/java/ma/fstm/ilisi/busway/ChatBot";
        String botResponse = "";

        try {
            MagicBooleans.trace_mode = false;
            Bot bot = new Bot("Reservation_Bot", resourcesPath);
            Chat chatSession = new Chat(bot);
            bot.brain.nodeStats();

            if ((userInput == null) || (userInput.length() < 1)) {
                userInput = MagicStrings.null_input;
            }

            if (userInput.equals("exit")) {
                System.exit(0);
            } else if (userInput.equals("bye")) {
                bot.writeQuit();
                System.exit(0);
            } else {
                String request = userInput.toLowerCase(); // Convertir la saisie en minuscules pour la comparaison

                if (MagicBooleans.trace_mode) {
                    System.out.println(
                            "STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
                                    + ":TOPIC=" + chatSession.predicates.get("topic"));
                }

                if (request.equals("station2") || request.equals("station 2")) {
                    botResponse = "2"; // Répondre avec "2" pour "station2" ou "station 2"
                } else if (request.contains("valide")) {
                    botResponse = request;
                } else {
                    botResponse = chatSession.multisentenceRespond(request); // Répondre par défaut avec le bot ALICE
                    while (botResponse.contains("&lt;")) {
                        botResponse = botResponse.replace("&lt;", "<");
                    }
                    while (botResponse.contains("&gt;")) {
                        botResponse = botResponse.replace("&gt;", ">");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return botResponse;
    }


}