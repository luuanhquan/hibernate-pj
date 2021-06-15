package Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String address;

    @Column
    int phone;
    @Column(name = "teacher_level")
    int teacherLevel;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<TeachingSchedule> teachingSchedules;

    public Level getTeacherLevel() {
        switch (teacherLevel) {
            case 0:
                return Level.PROFESSOR;
            case 1:
                return Level.ASSOCIATE_PROFESSOR;
            case 2:
                return Level.LECTURER;
            case 3:
                return Level.MASTER;
        }
        return null;
    }

    public void setTeacherLevel(Level level) {
        switch (level) {
            case PROFESSOR:
                teacherLevel = 0;
            case ASSOCIATE_PROFESSOR:
                teacherLevel = 1;
            case LECTURER:
                teacherLevel = 2;
            case MASTER:
                teacherLevel = 3;
        }
    }

    @Override
    public String toString() {
        return getIdString() +
                "\t\t" + name +
                "\t\t" + teacherLevel +
                "\t" + phone +
                "\t\t" + address;
    }

    public String getIdString() {
        if (id < 10)
            return "00" + id;
        else if (id < 100)
            return "0" + id;
        else return String.valueOf(id);
    }

    public enum Level {PROFESSOR, ASSOCIATE_PROFESSOR, LECTURER, MASTER}
}
