package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Passager;

public interface PassagerDAOInterface extends IDAO<Passager> {
    Passager findById(Long id);
}
