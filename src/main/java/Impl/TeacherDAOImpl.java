package Impl;

import DAO.DAO;
import Entity.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;


public class TeacherDAOImpl implements DAO<Teacher> {

    @Override
    public List<Teacher> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Teacher").list();
        } catch (HibernateException e) {
            e.printStackTrace();
//            logger.error(e);
        }
        return null;
    }
    @Override
    public Teacher findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Teacher.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

}
