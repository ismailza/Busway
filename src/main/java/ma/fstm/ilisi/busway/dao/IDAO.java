package ma.fstm.ilisi.busway.dao;

import java.util.Collection;

public interface IDAO<T> {
    Collection<T> retreive();
    boolean create(T t);
    boolean update(T t);
    boolean delete(T t);

}
