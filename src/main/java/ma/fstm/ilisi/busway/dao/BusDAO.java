package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Bus;
import ma.fstm.ilisi.busway.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BusDAO implements BusDAOInterface {

    @Override
    public Collection<Bus> retreive() {
        List<Bus> buses = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Bus> query = session.createQuery("from Bus", Bus.class);
            buses.addAll(query.list());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }
        return buses;
    }

    @Override
    public boolean create(Bus bus) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(bus);
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
    public boolean update(Bus bus) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(bus);
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
    public boolean delete(Bus bus) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(bus);
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
    public Bus findById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Bus bus = session.find(Bus.class, id);
            transaction.commit();
            session.close();
            return bus;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bus findByBusNum(int numBus) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Bus bus = session.find(Bus.class, numBus);
            transaction.commit();
            session.close();
            return bus;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
