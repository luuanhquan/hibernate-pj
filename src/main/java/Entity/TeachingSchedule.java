package Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import run.MainRun;

import javax.persistence.*;
import java.util.Scanner;

@Entity(name = "Teaching_Schedule")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeachingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "total_class")
    int totalClass;

    @ManyToOne
    @JoinColumn(name = "subjectid")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacherid")
    private Teacher teacher;

    @Transient
    private double totalLesson;

    public double getTotalLesson() {
        return this.totalClass * this.getSubject().getTotalLesson();
    }

    @Override
    public String toString() {
        return teacher.getName() +
                "\t" + subject.getName() +
                "\t" + totalClass;
    }

    public TeachingSchedule inputInfo() {
        System.out.println("-------------------------");

        System.out.print("Input teacher id: ");
        String t_id = new Scanner(System.in).nextLine();
        Teacher teacher = MainRun.teacherList.stream().filter(item -> t_id.equals(item.getIdString())).findFirst().get();
        this.setTeacher(teacher);

        System.out.print("Input subject id: ");
        String s_id = new Scanner(System.in).nextLine();
        Subject subject = MainRun.subjectList.stream().filter(item -> s_id.equals(item.getIdString())).findFirst().get();
        this.setSubject(subject);

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

        if (teacher.getTeachingSchedules().stream()
                .mapToInt(tc -> tc.getTotalClass()).sum() > 200) {
            System.out.println("Total lesson cannot over 200");
            return null;
        };

        this.setTotalClass(choice);
        return this;
    }
}
