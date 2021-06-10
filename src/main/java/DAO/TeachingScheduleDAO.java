package DAO;

import Entity.TeachingSchedule;

import java.util.List;

public interface TeachingScheduleDAO{

    public List<TeachingSchedule> getAll();

    public boolean addNew(TeachingSchedule TeachingSchedule);

    TeachingSchedule findById(int id);

    boolean update(TeachingSchedule student);

    boolean delete(TeachingSchedule student);
}
