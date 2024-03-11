package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Reservation;
import ma.fstm.ilisi.busway.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

public class ReservationDAO implements ReservationIDAOInterface {
    @Override
    public Collection<Reservation> retreive() {
        return null;
    }

    @Override
    public boolean create(Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) {
        return false;
    }
}
