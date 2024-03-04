package ma.fstm.ilisi.busway.view;

import javax.swing.*;
import java.awt.*;

public class authentifier extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public authentifier(){
        setTitle("Authentication");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création d'un conteneur JPanel pour l'arrière-plan avec une couleur spécifique
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(240, 240, 240)); // Couleur gris clair

        // Création des composants de l'interface
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Création d'un gestionnaire de disposition BorderLayout pour positionner les composants
        backgroundPanel.setLayout(new BorderLayout());

        // Création d'un JPanel pour contenir les champs d'authentification
        JPanel authPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        authPanel.add(usernameLabel);
        authPanel.add(usernameField);
        authPanel.add(passwordLabel);
        authPanel.add(passwordField);

        // Ajout du JPanel d'authentification au centre du conteneur principal
        backgroundPanel.add(authPanel, BorderLayout.CENTER);

        // Création d'un JPanel pour contenir le bouton de connexion
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);

        // Ajout du JPanel du bouton au bas du conteneur principal
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du conteneur JPanel à la fenêtre
        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	authentifier authUI = new authentifier();
            authUI.setVisible(true);
        });
    }
}
