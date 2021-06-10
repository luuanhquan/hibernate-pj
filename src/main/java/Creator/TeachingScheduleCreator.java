package Creator;


import Entity.TeachingSchedule;
import Impl.TeachingScheduleDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeachingScheduleCreator {
    private List<TeachingSchedule> listTeachingScheduleTemp = new ArrayList<>();
    private static TeachingScheduleDAOImpl TeachingScheduleDAO = new TeachingScheduleDAOImpl();

    public void createTeachingSchedule() {
        while (true) {
            try {
                TeachingSchedule TeachingSchedule = new TeachingSchedule();
                TeachingSchedule.inputInfo();
                listTeachingScheduleTemp.add(TeachingSchedule);
                if (new MenuUtil().Continue()) continue;
                else break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Wrong data input!");
            }
        }
        if (!CollectionUtil.isEmpty(listTeachingScheduleTemp)) {
            listTeachingScheduleTemp.forEach(TeachingScheduleDAO::addNew);
            MainRun.teachingScheduleList.addAll(listTeachingScheduleTemp);
        }
    }
}
