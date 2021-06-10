package Creator;


import Entity.Subject;
import Impl.SubjectDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectCreator {
    private static SubjectDAOImpl subjectDAO = new SubjectDAOImpl();
    private static List<Subject> listSubjectTemp= new ArrayList<>();

    public void createSubject() {
        listSubjectTemp.clear();
        while (true) {
            try {
                listSubjectTemp.add(new Subject().inputInfo());
                if (!MenuUtil.Continue()) break;
            } catch (Exception e) {
                System.out.println("Wrong data input!");
            }
        }
        if (!CollectionUtil.isEmpty(listSubjectTemp)) {
            subjectDAO.addNew(listSubjectTemp);
            System.out.println(listSubjectTemp);
            MainRun.subjectList.addAll(listSubjectTemp);
        }
    }
}
