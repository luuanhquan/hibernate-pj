package Creator;

import Entity.Subject;
import Entity.TeachingSchedule;
import run.MainRun;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeachingScheduleSortingAndCalculating {
    private static List<TeachingSchedule> teachingScheduleByName;
    private static List<TeachingSchedule> teachingScheduleByNumber;

    public void sortByTeacherName() {
        teachingScheduleByName= new ArrayList<>(MainRun.teachingScheduleList);
        teachingScheduleByName.stream()
                .sorted((o1, o2) -> o1.getTeacher().getName().compareToIgnoreCase(o2.getTeacher().getName()))
                .forEach(System.out::println);
        ;
    }

    public void sortByLessonNumber() {
        teachingScheduleByNumber= new ArrayList<>(MainRun.teachingScheduleList);
        teachingScheduleByNumber.stream()
                .sorted((o1, o2) -> getSoTiet(o1)-getSoTiet(o2))
                .forEach(System.out::println);
    }

    public void salaryCaltulate() {
        MainRun.teacherList.stream().forEach(teacher -> {
            double salary= teacher.getTeachingSchedules()
                    .stream()
                    .mapToDouble(item -> {
                        int totalLesson= item.getSubject().getTotalLesson();
                        int theoryLesson = item.getSubject().getTotalTheoryLesson();
                        return item.getSubject().getTheoryLessonCost()* item.getTotalClass()*(0.7*totalLesson+0.3*theoryLesson);
                    }).sum();
            System.out.printf(teacher.getName()+" \t%.0f\n",salary);
        });
    }

    public int getSoTiet(TeachingSchedule teachingSchedule){
        Subject subject= teachingSchedule.getSubject();
        return teachingSchedule.getTotalClass()*subject.getTotalLesson();
    }


}
