package Impl;

import DAO.DAO;
import Entity.TeachingSchedule;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;


public class TeachingScheduleDAOImpl implements DAO<TeachingSchedule> {


    @Override
    public List<TeachingSchedule> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Teaching_Schedule").list();
        } catch (HibernateException e) {
            e.printStackTrace();
//            logger.error(e);
        }
        return null;
    }


    @Override
    public TeachingSchedule findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TeachingSchedule.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

}
