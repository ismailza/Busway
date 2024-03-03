package ma.fstm.ilisi.busway.service;

import ma.fstm.ilisi.busway.bo.Station;
import ma.fstm.ilisi.busway.bo.Voyage;

import java.util.ArrayList;
import java.util.List;

public class VoyageService {
    private static final List<Voyage> voyages;

    static {
        voyages = new ArrayList<>();
    }

    public void create(Voyage voyage) {
        voyages.add(voyage);
    }


}
