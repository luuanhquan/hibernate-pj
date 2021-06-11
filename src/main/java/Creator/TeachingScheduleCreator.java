package Creator;


import Entity.TeachingSchedule;
import Impl.TeachingScheduleDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class TeachingScheduleCreator {
    private static final TeachingScheduleDAOImpl teachingScheduleDAO = new TeachingScheduleDAOImpl();
    private static final List<TeachingSchedule> listTeachingScheduleTemp = new ArrayList<>();

    public void createTeachingSchedule() {
        listTeachingScheduleTemp.clear();
        do {
            try {
                CollectionUtil.addIfNotNull(listTeachingScheduleTemp, new TeachingSchedule().inputInfo());
            }catch (Exception e){
                System.out.println("Wrong input");
            }
        } while (MenuUtil.Continue());

        if (!CollectionUtil.isEmpty(listTeachingScheduleTemp)) {
            teachingScheduleDAO.addNew(listTeachingScheduleTemp);
            MainRun.teachingScheduleList.addAll(listTeachingScheduleTemp);
        }
    }
}
