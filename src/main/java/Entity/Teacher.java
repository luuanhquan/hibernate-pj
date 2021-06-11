package Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

    enum Level {PROFESSOR,ASSOCIATE_PROFESSOR,LECTURER,MASTER}
    @Column(name="teacher_level")
    int teacherLevel;

    public Level getTeacherLevel() {
        switch (teacherLevel){
            case 0: return Level.PROFESSOR;
            case 1: return Level.ASSOCIATE_PROFESSOR;
            case 2: return Level.LECTURER;
            case 3: return Level.MASTER;
        }
        return null;
    }

    public void setTeacherLevel(Level level) {
        switch (level){
            case PROFESSOR: teacherLevel=0;
            case ASSOCIATE_PROFESSOR: teacherLevel=1;
            case LECTURER: teacherLevel=2;
            case MASTER: teacherLevel=3;
        }
    }

    @OneToMany(mappedBy = "teacher", fetch=FetchType.EAGER)
    private List<TeachingSchedule> teachingSchedules;

    public Teacher inputInfo() throws IOException{
        System.out.println("-------------------------");
        System.out.print("Input teacher name: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Input address: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Input phone: ");
        this.setPhone(new Scanner(System.in).nextInt());

        System.out.println("Nhập trình độ giảng viên: ");
        System.out.println("1. Giáo sư - Tiến sĩ");
        System.out.println("2. Phó giáo sư - Tiến sĩ");
        System.out.println("3. Giảng viên chính");
        System.out.println("4. Thạc sĩ");
        System.out.print("Xin hãy lựa chọn: ");
        while (true) {
            int choice;
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Vui lòng thử lại: ");
                continue;
            }
            if (choice <= 0 || choice > 4) {
                System.out.print("Nhập số trong khoảng từ 1 đến 4! Vui lòng thử lại: ");
                continue;
            }

            switch (choice) {
                case 1:
                    this.setTeacherLevel(Level.PROFESSOR);
                    break;
                case 2:
                    this.setTeacherLevel(Level.ASSOCIATE_PROFESSOR);
                    break;
                case 3:
                    this.setTeacherLevel(Level.LECTURER);
                    break;
                case 4:
                    this.setTeacherLevel(Level.MASTER);
                    break;
            }
            return this;
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
}
