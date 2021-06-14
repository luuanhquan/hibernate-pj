package Repository;

import DAO.DAO;
import Entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;


public class SubjectRepository implements DAO<Subject> {


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
            e.printStackTrace();
            return null;
        }
    }
}
