package ma.fstm.ilisi.busway.view;

import ma.fstm.ilisi.busway.model.Bus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BusListPanel extends JPanel {
    private JTable busTable;
    private DefaultTableModel tableModel;

    public BusListPanel() {
        String[] columnNames = {"Numéro du Bus", "Capacité"};

        tableModel = new DefaultTableModel(columnNames, 0);
        busTable = new JTable(tableModel);

        busTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScroller = new JScrollPane(busTable);
        tableScroller.setPreferredSize(getPreferredSize());
        tableScroller.setPreferredSize(new Dimension(360, 500));
        add(tableScroller);
    }

    public JTable getBusTable() {
        return busTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void addBus(String busNumber, int capacity) {
        tableModel.addRow(new Object[]{busNumber, capacity});
    }

    public void removeSelectedBus() {
        int selectedRow = busTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        }
    }

    public void setTableData(List<Bus> data) {
        tableModel.setRowCount(0);

        for (Bus bus : data) {
            tableModel.addRow(new Object[]{bus.getBusNumber(), bus.getCapacity()});
        }
    }
}
