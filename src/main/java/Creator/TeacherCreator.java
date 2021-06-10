package Creator;


import Entity.Teacher;
import Impl.TeacherDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class TeacherCreator {
    private static TeacherDAOImpl teacherDAO = new TeacherDAOImpl();
    private static List<Teacher> listTeacherTemp= new ArrayList<>();

    public void createTeacher() {
        listTeacherTemp.clear();
        while (true) {
            try {
                listTeacherTemp.add(new Teacher().inputInfo());
                if (!MenuUtil.Continue()) break;
            } catch (Exception e) {
                System.out.println("Wrong data input!");
            }
        }
        if (!CollectionUtil.isEmpty(listTeacherTemp)) {
            teacherDAO.addNew(listTeacherTemp);
            System.out.println(listTeacherTemp);
            MainRun.teacherList.addAll(listTeacherTemp);
        }
    }
}
