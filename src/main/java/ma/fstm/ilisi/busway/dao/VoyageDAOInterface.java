package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Voyage;

public interface VoyageDAOInterface extends IDAO<Voyage> {
    Voyage findById(Long id);
}
