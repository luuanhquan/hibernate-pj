package DAO;

import Entity.Teacher;

import java.util.List;

public interface TeacherDAO{

    public List<Teacher> getAll();

    public boolean addNew(Teacher teacher);

    Teacher findById(int id);

    boolean update(Teacher student);

    boolean delete(Teacher student);
}
