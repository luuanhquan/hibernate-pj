package Creator;


import Entity.TeachingSchedule;
import Impl.TeachingScheduleDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class TeachingScheduleCreator {
    private static TeachingScheduleDAOImpl teachingScheduleDAO = new TeachingScheduleDAOImpl();
    private static List<TeachingSchedule> listTeachingScheduleTemp= new ArrayList<>();

    public void createTeachingSchedule() {
        listTeachingScheduleTemp.clear();
        while (true) {
            try {
                listTeachingScheduleTemp.add(new TeachingSchedule().inputInfo());
                if (!MenuUtil.Continue()) break;
            } catch (Exception e) {
                System.out.println("Wrong data input!");
            }
        }
        if (!CollectionUtil.isEmpty(listTeachingScheduleTemp)) {
            teachingScheduleDAO.addNew(listTeachingScheduleTemp);
            System.out.println(listTeachingScheduleTemp);
            MainRun.teachingScheduleList.addAll(listTeachingScheduleTemp);
        }
    }
}
