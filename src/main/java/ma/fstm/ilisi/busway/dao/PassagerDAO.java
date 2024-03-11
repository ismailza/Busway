package ma.fstm.ilisi.busway.dao;

import ma.fstm.ilisi.busway.bo.Passager;
import ma.fstm.ilisi.busway.util.HibernateUtil;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;

public class PassagerDAO implements PassagerDAOInterface {

	@Override
    public Passager findById(Long id) {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Passager passager = session.find(Passager.class, id);
            transaction.commit();
            session.close();
            return passager;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Passager> retreive() {
        return null;
    }

    @Override
    public boolean create(Passager passager) {
    	Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(passager);
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
    public boolean update(Passager passager) {
        // TODO
        return false;
    }

    @Override
    public boolean delete(Passager passager) {
       Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(passager);
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

    

}
