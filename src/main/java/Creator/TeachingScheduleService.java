package Creator;


import Entity.Subject;
import Entity.Teacher;
import Entity.TeachingSchedule;
import Repository.TeachingScheduleRepository;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeachingScheduleService {
    private static final TeachingScheduleRepository teachingScheduleDAO = new TeachingScheduleRepository();
    private static final List<TeachingSchedule> listTeachingScheduleTemp = new ArrayList<>();

    public TeachingSchedule inputInfo() throws IOException {
        try {
            TeachingSchedule teachingSchedule = new TeachingSchedule();
            System.out.println("-------------------------");

            System.out.print("Input teacher id: ");
            String t_id = new Scanner(System.in).nextLine();
            Teacher teacher = MainRun.teacherList.stream().filter(item -> t_id.equals(item.getIdString())).findFirst().get();
            teachingSchedule.setTeacher(teacher);

            System.out.print("Input subject id: ");
            String s_id = new Scanner(System.in).nextLine();
            Subject subject = MainRun.subjectList.stream().filter(item -> s_id.equals(item.getIdString())).findFirst().get();
            teachingSchedule.setSubject(subject);

            System.out.print("Input number of class (1~3): ");
            int choice = 0;
            while (true) {
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Number of class must between 1 and 3!");
                    System.out.println("Your choice: ");
                    continue;
                }
                break;
            }
            int tt = teacher.getTeachingSchedules().stream()
                    .mapToInt(tc -> tc.getSubject().getTotalLesson()).sum();
            System.out.println("Total lesson [" + tt + "]");
            if (teacher.getTeachingSchedules().stream()
                    .mapToInt(tc -> tc.getSubject().getTotalLesson()).sum() > 200) {
                System.out.println("Total lesson cannot over 200 [" + tt + "]");
                return null;
            }

            teachingSchedule.setTotalClass(choice);
            return teachingSchedule;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createTeachingSchedule() {
        listTeachingScheduleTemp.clear();
        do {
            try {
                CollectionUtil.addIfNotNull(listTeachingScheduleTemp, this.inputInfo());
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        } while (MenuUtil.Continue());

        if (!CollectionUtil.isEmpty(listTeachingScheduleTemp)) {
            teachingScheduleDAO.addNew(listTeachingScheduleTemp);
            MainRun.teachingScheduleList.addAll(listTeachingScheduleTemp);
        }
    }
}
