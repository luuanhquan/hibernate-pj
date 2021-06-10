package DAO;

import Entity.Subject;

import java.util.List;

public interface SubjectDAO{
    public List<Subject> getAll();

    public boolean addNew(List<Subject> subjectList);

    Subject findById(int id);

    boolean update(List<Subject> subjectList);

    boolean delete(List<Subject> subjectList);
}
