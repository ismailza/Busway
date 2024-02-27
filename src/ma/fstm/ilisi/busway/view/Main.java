package ma.fstm.ilisi.busway.view;

import ma.fstm.ilisi.busway.controller.BusWayController;
import ma.fstm.ilisi.busway.model.Trip;

import javax.swing.*;
import java.util.List;

public class Main extends JFrame {
    private final BusPanel busPanel;
    private final BusListPanel busListPanel;
    private final TripPanel tripPanel;
    private final TripListPanel tripListPanel;

    public Main(BusWayController busWayController) {
        setTitle("BusWay");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.busPanel = new BusPanel();
        this.busListPanel = new BusListPanel();
        this.tripPanel = new TripPanel();
        this.tripListPanel = new TripListPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu busMenu = new JMenu("Bus");
        menuBar.add(busMenu);
        JMenuItem addBusMenuItem = new JMenuItem("Add bus");
        addBusMenuItem.addActionListener(e -> {
            busPanel.getAddButton().addActionListener(e1 -> {
                try {
                    int busNumber = Integer.parseInt(busPanel.getBusNumberField().getText());
                    int capacity = Integer.parseInt(busPanel.getCapacityField().getText());
                    busWayController.addBus(busNumber, capacity);
                    JOptionPane.showMessageDialog(this, "Bus added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    busPanel.clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            setContentPane(busPanel);
            revalidate();
            repaint();
        });
        busMenu.add(addBusMenuItem);
        JMenuItem listBusMenuItem = new JMenuItem("List buses");
        listBusMenuItem.addActionListener(e -> {
            setContentPane(busListPanel);
            revalidate();
            repaint();
        });
        busMenu.add(listBusMenuItem);

        JMenu tripMenu = new JMenu("Trip");
        menuBar.add(tripMenu);
        JMenuItem addTripMenuItem = new JMenuItem("Add trip");
        addTripMenuItem.addActionListener(e -> {
            setContentPane(tripPanel);
            revalidate();
            repaint();
        });
        tripMenu.add(addTripMenuItem);
        JMenuItem listTripMenuItem = new JMenuItem("Show available trips");
        listTripMenuItem.addActionListener(e -> {
            setContentPane(tripListPanel);
            tripListPanel.getSearchButton().addActionListener(e1 -> {
                busWayController.showAvailableTrips(tripListPanel.getFromField().getText(), tripListPanel.getToField().getText());
            });
            revalidate();
            repaint();
        });
        tripMenu.add(listTripMenuItem);

        setJMenuBar(menuBar);

        setVisible(true);
    }

    public BusListPanel getBusListPanel() {
        return busListPanel;
    }

    public void setAvailableTrips(List<Trip> data) {
        tripListPanel.setTableData(data);
    }
}
