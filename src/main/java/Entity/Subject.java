package Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<TeachingSchedule> teachingSchedules;

    @Override
    public String toString() {
        return getIdString() +
                "    \t" + name +
                "    \t\t" + totalTheoryLesson +
                "    \t\t" + (totalLesson - totalTheoryLesson) +
                "    \t" + theoryLessonCost;
    }

    public String getIdString() {
        if (id < 10)
            return "00" + id;
        else if (id < 100)
            return "0" + id;
        else return String.valueOf(id);
    }
}
