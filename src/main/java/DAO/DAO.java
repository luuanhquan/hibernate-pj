package DAO;

import Entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public interface DAO<T>{
     List<T> getAll();

    default boolean addNew(List<T> list){
        Transaction tx= null;
        Session session;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            list.forEach(session::save);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    T findById(int id);

    default boolean update(List<T> list){
        Transaction tx = null;
        Session session;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            list.forEach(session::update);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            tx.commit();
            e.printStackTrace();
            return false;
        }
    }

    default boolean delete(List<T> list){
        Transaction tx = null;
        Session session;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            list.forEach(session::delete);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            tx.commit();
            e.printStackTrace();
            return false;
        }
    }
}
