package Creator;


import Entity.Teacher;
import Repository.TeacherRepository;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherService {
    private static final TeacherRepository teacherDAO = new TeacherRepository();
    private static final List<Teacher> listTeacherTemp = new ArrayList<>();

    private Teacher inputInfo() {
        Teacher teacher = new Teacher();
        try {
            System.out.println("-------------------------");
            System.out.print("Input teacher name: ");
            teacher.setName(new Scanner(System.in).nextLine());
            System.out.print("Input address: ");
            teacher.setAddress(new Scanner(System.in).nextLine());
            System.out.print("Input phone: ");
            teacher.setPhone(new Scanner(System.in).nextInt());

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
                switch (choice) {
                    case 1:
                        teacher.setTeacherLevel(Teacher.Level.PROFESSOR);
                        break;
                    case 2:
                        teacher.setTeacherLevel(Teacher.Level.ASSOCIATE_PROFESSOR);
                        break;
                    case 3:
                        teacher.setTeacherLevel(Teacher.Level.LECTURER);
                        break;
                    case 4:
                        teacher.setTeacherLevel(Teacher.Level.MASTER);
                        break;
                    default:
                        System.out.print("Nhập số trong khoảng từ 1 đến 4! Vui lòng thử lại: ");
                        continue;
                }
                break;
            }
            return teacher;
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }

    public void createTeacher() {
        listTeacherTemp.clear();
        do {
            CollectionUtil.addIfNotNull(listTeacherTemp, this.inputInfo());
        } while (MenuUtil.Continue());

        if (!CollectionUtil.isEmpty(listTeacherTemp)) {
            teacherDAO.addNew(listTeacherTemp);
            MainRun.teacherList.addAll(listTeacherTemp);
        }
    }
}
