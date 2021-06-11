package Creator;


import Entity.Subject;
import Entity.Teacher;
import Impl.SubjectDAOImpl;
import run.MainRun;
import utils.CollectionUtil;
import utils.MenuUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectCreator {
    private static final SubjectDAOImpl subjectDAO = new SubjectDAOImpl();
    private static final List<Subject> listSubjectTemp= new ArrayList<>();

    public void createSubject() {
        listSubjectTemp.clear();
        do {
            try {
                CollectionUtil.addIfNotNull(listSubjectTemp, new Subject().inputInfo());
            }catch (Exception e){
                System.out.println("Wrong input");
            }
        } while (MenuUtil.Continue());

        if (!CollectionUtil.isEmpty(listSubjectTemp)) {
            subjectDAO.addNew(listSubjectTemp);
            MainRun.subjectList.addAll(listSubjectTemp);
        }
    }
}
