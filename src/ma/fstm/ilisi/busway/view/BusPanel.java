package ma.fstm.ilisi.busway.view;

import javax.swing.*;
import java.awt.*;

public class BusPanel extends JPanel {
    private JTextField busNumberField;
    private JTextField capacityField;
    private JButton addButton;

    public BusPanel() {
        setBorder(BorderFactory.createTitledBorder("Ajouter Bus"));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        busNumberField = new JTextField(10);
        capacityField = new JTextField(10);
        addButton = new JButton("Ajouter bus");

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Numéro du bus:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(busNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Capacité:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(capacityField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);
    }

    public JTextField getBusNumberField() {
        return busNumberField;
    }

    public JTextField getCapacityField() {
        return capacityField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void clearFields() {
        busNumberField.setText("");
        capacityField.setText("");
    }
}
