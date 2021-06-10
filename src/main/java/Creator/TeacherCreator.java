package Creator;


import Entity.Teacher;
import Impl.TeacherDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherCreator {
    private List<Teacher> listTeacherTemp = new ArrayList<>();
    private static TeacherDAOImpl TeacherDAO = new TeacherDAOImpl();

    public void createTeacher() {
        while (true) {
            try {
                Teacher Teacher = new Teacher();
                Teacher.inputInfo();
                listTeacherTemp.add(Teacher);
                if (new MenuUtil().Continue()) continue;
                else break;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Wrong data input!");
            }
        }
        if (!CollectionUtil.isEmpty(listTeacherTemp)) {
            listTeacherTemp.forEach(TeacherDAO::addNew);
            MainRun.teacherList.addAll(listTeacherTemp);
        }
    }
}
