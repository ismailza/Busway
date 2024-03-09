package ma.fstm.ilisi.busway.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.configure();
                sessionFactory = config.buildSessionFactory();
            } catch (HibernateException e) {
                System.err.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
