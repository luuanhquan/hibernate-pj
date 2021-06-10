package Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "Subject")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "total_lesson", nullable = false)
    int totalLesson;

    @Column(name = "total_theory_lesson", nullable = false)
    int totalTheoryLesson;

    @Column(name = "theory_lesson_cost", nullable = false)
    double theoryLessonCost;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<TeachingSchedule> teachingSchedules;

    @Override
    public String toString() {
        return getIdString() +
                "    \t" + name +
                "    \t\t" + theoryLessonCost +
                "    \t\t" + (totalLesson - theoryLessonCost) +
                "    \t" + theoryLessonCost;
    }

    public Subject inputInfo() {
        System.out.println("-------------------------");
        System.out.print("Input subject name: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Input total lesson: ");
        this.setTotalLesson(new Scanner(System.in).nextInt());
        System.out.print("Input total theory lesson: ");
        this.setTotalTheoryLesson(new Scanner(System.in).nextInt());
        System.out.print("Input theo lesson's cost: ");
        this.setTheoryLessonCost(new Scanner(System.in).nextDouble());
        return this;
    }

    public String getIdString() {
        if (id < 10)
            return "00" + id;
        else if (id < 100)
            return "0" + id;
        else return String.valueOf(id);
    }
}
