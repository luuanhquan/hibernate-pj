package run;

import Creator.SubjectService;
import Creator.TeacherService;
import Creator.TeachingScheduleService;
import Creator.TeachingScheduleSortingAndCalculating;
import DAO.DAO;
import Entity.Subject;
import Entity.Teacher;
import Entity.TeachingSchedule;
import Repository.SubjectRepository;
import Repository.TeacherRepository;
import Repository.TeachingScheduleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainRun {

    //    static Logger logger = Logger.getLogger(MainRun.class);
    static final Scanner scanner = new Scanner(System.in);
    static private final DAO<Subject> subjectDAO = new SubjectRepository();
    static private final DAO<Teacher> teacherDAO = new TeacherRepository();
    static private final DAO<TeachingSchedule> teachingScheduleDAO = new TeachingScheduleRepository();
    static private final SubjectService subjectService = new SubjectService();
    static private final TeacherService teacherCreator = new TeacherService();
    static private final TeachingScheduleService teachingScheduleCreator = new TeachingScheduleService();
    static private final TeachingScheduleSortingAndCalculating teachingScheduleSortingAndCalculating = new TeachingScheduleSortingAndCalculating();
    static public List<Subject> subjectList = new ArrayList<>();
    static public List<Teacher> teacherList = new ArrayList<>();
    static public List<TeachingSchedule> teachingScheduleList = new ArrayList<>();

    public static void main(String[] args) {
        init();
        while (true) {
            showMenu();
        }
    }

    public static void init() {
        subjectList = subjectDAO.getAll();
        teacherList = teacherDAO.getAll();
        teachingScheduleList = teachingScheduleDAO.getAll();
    }

    private static void showMenu() {
        System.out.println("-------------------------");
        System.out.println("QUẢN LÝ TRẢ LƯƠNG CHO GIẢNG VIÊN THỈNH GIẢNG");
        System.out.println("1.Nhập danh sách môn học");
        System.out.println("2.In danh sách môn học");
        System.out.println("3.Nhập danh sách giảng viên");
        System.out.println("4.In danh sách giảng viên");
        System.out.println("5.Thêm kế hoạch giảng dạy");
        System.out.println("6.In bảng kê khai giảng dạy");
        System.out.println("7.Sắp xếp bảng kê khai giảng dạy theo tên GV");
        System.out.println("8.Sắp xếp bảng kê khai giảng dạy theo số tiết");
        System.out.println("9.In bảng tính tiền công");
        System.out.println("0.Thoát");
        System.out.print("Chọn chức năng: ");
        switch (scanner.nextLine()) {
            case "1":
                subjectService.createSubject();
                break;
            case "2":
                printSubject();
                break;
            case "3":
                teacherCreator.createTeacher();
                break;
            case "4":
                printTeacher();
                break;
            case "5":
                teachingScheduleCreator.createTeachingSchedule();
                break;
            case "6":
                printSchedule();
                break;
            case "7":
                teachingScheduleSortingAndCalculating.sortByTeacherName();
                break;
            case "8":
                teachingScheduleSortingAndCalculating.sortByLessonNumber();
                break;
            case "9":
                teachingScheduleSortingAndCalculating.salaryCaltulate();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Wrong choice!");
        }
    }

    private static void printSchedule() {
        System.out.println("\t SCHEDULE");
        System.out.println("Teacher\tSubject\tTotal Class");
        teachingScheduleList.forEach(System.out::println);
    }

    private static void printTeacher() {
        System.out.println("\t TEACHER LIST");
        System.out.println("ID\t\tNAME\tLEVEL\tPHONE\t\t\tADDRESS");
        teacherList.forEach(System.out::println);
    }

    private static void printSubject() {
        System.out.println("\t SUBJECT LIST");
        System.out.println("ID\t\tNAME\t\tTHEORY\tPRACTICAL\tCOST");
        subjectList.forEach(System.out::println);
    }
}
