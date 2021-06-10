package Impl;

import DAO.DAO;
import Entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;


public class SubjectDAOImpl implements DAO<Subject> {
    Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();


    @Override
    public List<Subject> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Subject").list();
        } catch (HibernateException e) {
            e.printStackTrace();
//            logger.error(e);
        }
        return null;
    }


    @Override
    public Subject findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Subject.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }
}
