package ma.fstm.ilisi.busway.service;

import java.util.List;

public interface ServiceInterface<T> {
    List<T> retreive();
    boolean create(T t);
    boolean update(T t);
    boolean delete(T t);
}
