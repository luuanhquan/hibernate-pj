package Controller;

import DAO.DAO;
import Entity.Subject;
import Repository.SubjectRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/subjects")
public class SubjectController {
    static private final DAO<Subject> subjectDAO = new SubjectRepository();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subject> getListStudent() {
        return subjectDAO.getAll();
    }
}
