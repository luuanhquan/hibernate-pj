package Impl;

import DAO.TeacherDAO;
import Entity.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;
//import org.apache.log4j.Logger;


public class TeacherDAOImpl implements TeacherDAO {
    Transaction tx= null;

//    Logger logger = Logger.getLogger(TeacherDAOImpl.class);
    @Override
    public List<Teacher> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Teacher> TeacherList = session.createQuery("from Teacher").list();
            return TeacherList;
        } catch (HibernateException e) {
//            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean addNew(Teacher Teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx= session.beginTransaction();
            session.save(Teacher);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null)
            tx.rollback();
//            logger.error(e);
            return false;
        }
    }

    @Override
    public Teacher findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.load(Teacher.class,id);
        } catch (HibernateException e) {
//            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean update(Teacher Teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx= session.beginTransaction();
            session.update(Teacher);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null)
            tx.rollback();
//            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Teacher Teacher) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx= session.beginTransaction();
            session.delete(Teacher);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null)
            tx.rollback();
//            logger.error(e);
            return false;
        }
    }
}
