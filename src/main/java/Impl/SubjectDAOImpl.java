package Impl;

import DAO.SubjectDAO;
import Entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;


public class SubjectDAOImpl implements SubjectDAO {
    Transaction tx= null;
    Session session = HibernateUtil.getSessionFactory().openSession();


    //    Logger logger = Logger.getLogger(SubjectDAOImpl.class);
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
    public boolean addNew(List<Subject> subjectList) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            subjectList.forEach(session::save);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            tx.commit();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Subject findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.load(Subject.class, id);
        } catch (HibernateException e) {
//            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean update(List<Subject> subjectList) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            subjectList.forEach(session::update);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            tx.commit();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(List<Subject> subjectList) {
        Transaction tx = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            subjectList.forEach(session::delete);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            tx.commit();
            e.printStackTrace();
            return false;
        }
    }
}
