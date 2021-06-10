package Entity;

import Impl.SubjectDAOImpl;
import Impl.TeacherDAOImpl;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
        return this.subject.getTheoryLessonCost() * this.totalClass * (0.7 * this.subject.getTotalLesson() + 0.3 * this.subject.getTotalTheoryLesson());
    }

    public void inputInfo() {
        System.out.println("-------------------------");
        System.out.print("Input teacher id: ");
        this.setTeacher(new TeacherDAOImpl().findById(new Scanner(System.in).nextInt()));
        System.out.print("Input subject id: ");
        this.setSubject(new SubjectDAOImpl().findById(new Scanner(System.in).nextInt()));
        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.print("Input number of class (1~3): ");
            choice = new Scanner(System.in).nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Number of class must between 1 and 3!");
            }
            this.setTotalClass(new Scanner(System.in).nextInt());
        }
    }
}
