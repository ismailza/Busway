package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Conducteur;

public interface ConducteurDAOInterface extends IDAO<Conducteur> {
    Conducteur findById(Long id);

}
