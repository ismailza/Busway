package ma.fstm.ilisi.busway.view;

import ma.fstm.ilisi.busway.controller.BusWayController;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private BusPanel busPanel;
    private BusListPanel busListPanel;
    BusWayController busWayController;

    public Main(BusWayController busWayController) {
        setTitle("BusWay");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.busWayController = busWayController;
        busPanel = new BusPanel();
        busListPanel = new BusListPanel();

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
            setContentPane(new TripPanel());
            revalidate();
            repaint();
        });
        tripMenu.add(addTripMenuItem);
        JMenuItem listTripMenuItem = new JMenuItem("List trips");
        listTripMenuItem.addActionListener(e -> {
            // setContentPane(new TripListPanel());
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
}
