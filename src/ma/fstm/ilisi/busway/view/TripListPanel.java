package ma.fstm.ilisi.busway.view;

import ma.fstm.ilisi.busway.bo.Trip;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TripListPanel extends JPanel {
    private JTextField fromField;
    private JTextField toField;
    private JButton searchButton;
    private final JTable table;
    private final JScrollPane scrollPane;
    private final String[] columnNames = {"From", "To", "Departure", "Arrival", "Price"};

    public TripListPanel() {
        setBorder(BorderFactory.createTitledBorder("Trips"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        fromField = new JTextField();
        fromField.setBorder(BorderFactory.createTitledBorder("From"));
        add(fromField);

        toField = new JTextField();
        toField.setBorder(BorderFactory.createTitledBorder("To"));
        add(toField);

        searchButton = new JButton("Search");
        add(searchButton);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        add(scrollPane);
    }

    public void setTableData(List<Trip> data) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        for (Trip trip : data) {
            model.addRow(new Object[]{trip.getStartStation().getStation().getName(), trip.getEndStation().getStation().getName(), trip.getStartStation().getTime(), trip.getEndStation().getTime(), trip.getTarif()});
        }
        table.setModel(model);
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextField getFromField() {
        return fromField;
    }

    public JTextField getToField() {
        return toField;
    }

}
