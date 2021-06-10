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
    private List<Subject> listSubjectTemp = new ArrayList<>();

    public void createSubject() {
        while (true) {
            try {
                Subject subject = new Subject();
                subject.inputInfo();
                listSubjectTemp.add(subject);
                if (new MenuUtil().Continue()) continue;
                else break;
            } catch (Exception e) {
                System.out.println("Wrong data input!");
            }
        }
        if (!CollectionUtil.isEmpty(listSubjectTemp)) {
            subjectDAO.addNew(listSubjectTemp);
            MainRun.subjectList.addAll(listSubjectTemp);
        }
    }
}
