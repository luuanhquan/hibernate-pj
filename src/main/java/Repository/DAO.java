package Repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public interface DAO<T> {
    default List<T> getAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Subject").list();
        } catch (HibernateException e) {
            e.printStackTrace();
//            logger.error(e);
        }
        return null;
    }

    default T findById(Class<T> tClass, int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(tClass, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    default boolean addNew(List<T> list) {
        Transaction tx = null;
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            list.forEach(session::save);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    default boolean addNew(T item) {
        Transaction tx = null;
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    default boolean update(List<T> list) {
        Transaction tx = null;
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            list.forEach(session::update);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    default boolean delete(List<T> list) {
        Transaction tx = null;
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            list.forEach(session::delete);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    default boolean delete(Class<T> tClass, int id){
        Transaction tx = null;
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.remove(findById(tClass,id));
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
