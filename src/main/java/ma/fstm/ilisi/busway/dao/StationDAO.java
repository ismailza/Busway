package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Station;
import ma.fstm.ilisi.busway.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StationDAO implements StationDAOInterface {
    @Override
    public Collection<Station> retreive() {
        List<Station> stations = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Station> query = session.createQuery("from Station ", Station.class);
            stations.addAll(query.list());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }
        return stations;
    }

    @Override
    public boolean create(Station station) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(station);
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
    public boolean update(Station station) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(station);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Station station) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(station);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Station findById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Station station = session.find(Station.class, id);
            transaction.commit();
            session.close();
            return station;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
