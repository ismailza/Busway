package ma.fstm.ilisi.busway.model;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String name;
    private String address;
    private List<Reservation> startReservations;
    private List<Reservation> endReservations;

    public Station(String name, String address) {
        this.name = name;
        this.address = address;
        this.startReservations = new ArrayList<>();
        this.endReservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Reservation> getStartReservations() {
        return startReservations;
    }

    public List<Reservation> getEndReservations() {
        return endReservations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addStartReservation(Reservation reservation) {
        this.startReservations.add(reservation);
    }

    public void addEndReservation(Reservation reservation) {
        this.endReservations.add(reservation);
    }
}
