package Creator;


import Entity.Subject;
import Repository.SubjectRepository;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectService {
    private static final SubjectRepository subjectDAO = new SubjectRepository();
    private static final List<Subject> listSubjectTemp = new ArrayList<>();

    public Subject inputInfo() {
        Subject subject = null;
        try {
            subject = new Subject();
            System.out.println("-------------------------");
            System.out.print("Input subject name: ");
            subject.setName(new Scanner(System.in).nextLine());
            System.out.print("Input total lesson: ");
            subject.setTotalLesson(new Scanner(System.in).nextInt());
            System.out.print("Input total theory lesson: ");
            subject.setTotalTheoryLesson(new Scanner(System.in).nextInt());
            System.out.print("Input theo lesson's cost: ");
            subject.setTheoryLessonCost(new Scanner(System.in).nextDouble());
            return subject;
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }

    public void createSubject() {
        listSubjectTemp.clear();
        do {
            CollectionUtil.addIfNotNull(listSubjectTemp, this.inputInfo());
        } while (MenuUtil.Continue());

        if (!CollectionUtil.isEmpty(listSubjectTemp)) {
            subjectDAO.addNew(listSubjectTemp);
            MainRun.subjectList.addAll(listSubjectTemp);
        }
    }
}
