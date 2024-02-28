package ma.fstm.ilisi.busway.view;

import javax.swing.*;

public class TripPanel extends JPanel {

    public TripPanel() {
        setBorder(BorderFactory.createTitledBorder("Ajouter Voyage"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Ajouter Voyage"));

    }
}
