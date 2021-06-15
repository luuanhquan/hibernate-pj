package Service;

import Repository.DAO;

import java.util.List;

public interface Service<T> {
    DAO dao = new DAO() {
        };
    default List<T> findAll() {
        return dao.getAll();
    }

    default T find(Class<T> tClass, int id) {
        return (T) dao.findById(tClass, id);
    }

    default boolean addNew(T item) {
        return dao.addNew(item);
    }

    default boolean delete(Class<T> tClass, int id) {
        return dao.delete(tClass, id);
    }

}
