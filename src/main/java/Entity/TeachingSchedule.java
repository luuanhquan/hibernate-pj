package Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Arrays;

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


}
