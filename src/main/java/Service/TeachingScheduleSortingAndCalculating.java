package Creator;

import Entity.Subject;
import Entity.Teacher;
import Entity.TeachingSchedule;
import Service.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TeachingScheduleSortingAndCalculating {
    Service<TeachingSchedule> scheduleService = new Service<TeachingSchedule>() {
    };

    public List sortByTeacherName() {
        return scheduleService.findAll().stream()
                .sorted((o1, o2) -> o1.getTeacher().getName().compareToIgnoreCase(o2.getTeacher().getName()))
                .collect(Collectors.toList());
    }

    public void sortByLessonNumber() {
        scheduleService.findAll().stream()
                .sorted(Comparator.comparingInt(this::getSoTiet))
                .forEach(System.out::println);
    }

    public void salaryCaltulate() {
        new Service<Teacher>() {
        }.findAll().forEach(teacher -> {
            double salary = teacher.getTeachingSchedules()
                    .stream()
                    .mapToDouble(item -> {
                        int totalLesson = item.getSubject().getTotalLesson();
                        int theoryLesson = item.getSubject().getTotalTheoryLesson();
                        return item.getSubject().getTheoryLessonCost() * item.getTotalClass() * (0.7 * totalLesson + 0.3 * theoryLesson);
                    }).sum();
            System.out.printf(teacher.getName() + " \t%.0f\n", salary);
        });
    }

    public int getSoTiet(TeachingSchedule teachingSchedule) {
        Subject subject = teachingSchedule.getSubject();
        return teachingSchedule.getTotalClass() * subject.getTotalLesson();
    }


}
