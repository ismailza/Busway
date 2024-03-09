package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Voyage;
import ma.fstm.ilisi.busway.util.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VoyageDAO implements VoyageDAOInterface {
    @Override
    public Collection<Voyage> retreive() {
        List<Voyage> voyages = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Voyage> query = session.createQuery("from Voyage", Voyage.class);
            voyages.addAll(query.list());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            System.err.println(e.getMessage());
        }
        return voyages;
    }

    @Override
    public boolean create(Voyage voyage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(voyage);
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
    public boolean update(Voyage voyage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(voyage);
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
    public boolean delete(Voyage voyage) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(voyage);
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
    public Voyage findById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Voyage voyage = session.find(Voyage.class, id);
            transaction.commit();
            session.close();
            return voyage;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
