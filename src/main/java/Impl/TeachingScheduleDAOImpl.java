package Impl;

import DAO.TeachingScheduleDAO;
import Entity.TeachingSchedule;
//import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;


public class TeachingScheduleDAOImpl implements TeachingScheduleDAO {
    Transaction tx= null;

//    Logger logger = Logger.getLogger(TeachingScheduleDAOImpl.class);
    @Override
    public List<TeachingSchedule> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<TeachingSchedule> TeachingScheduleList = session.createQuery("from Teaching_Schedule").list();
            return TeachingScheduleList;
        } catch (HibernateException e) {
//            logger.error(e);
        }
        return null;
    }

    @Override
    public boolean addNew(TeachingSchedule TeachingSchedule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx= session.beginTransaction();
            session.save(TeachingSchedule);
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
    public TeachingSchedule findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.load(TeachingSchedule.class,id);
        } catch (HibernateException e) {
//            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean update(TeachingSchedule TeachingSchedule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx= session.beginTransaction();
            session.update(TeachingSchedule);
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
    public boolean delete(TeachingSchedule TeachingSchedule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx= session.beginTransaction();
            session.delete(TeachingSchedule);
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
