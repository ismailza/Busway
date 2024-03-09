package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Station;

public interface StationDAOInterface extends IDAO<Station> {

    Station findById(Long id);
}
