package Creator;


import Entity.Teacher;
import Entity.TeachingSchedule;
import Impl.TeacherDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class TeacherCreator {
    private static final TeacherDAOImpl teacherDAO = new TeacherDAOImpl();
    private static final List<Teacher> listTeacherTemp= new ArrayList<>();

    public void createTeacher() {
        listTeacherTemp.clear();
        do {
            try {
                CollectionUtil.addIfNotNull(listTeacherTemp, new Teacher().inputInfo());
            }catch (Exception e){
                System.out.println("Wrong input");
            }
        } while (MenuUtil.Continue());
        if (!CollectionUtil.isEmpty(listTeacherTemp)) {
            teacherDAO.addNew(listTeacherTemp);
            MainRun.teacherList.addAll(listTeacherTemp);
        }
    }
}
