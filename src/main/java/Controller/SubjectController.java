package Controller;

import Entity.Subject;
import Service.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/subjects")
public class SubjectController {
    static private final Service<Subject> subjectService= new Service<Subject>() {
    };

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subject> getListSubject() {
        return subjectService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Subject getSubject(@PathParam("id") int id) {
        return subjectService.find(Subject.class,id);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNew(Subject subject) {
        return subjectService.addNew(subject) ? "Thành công" : "Thất bại";
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(@PathParam("id")int id){
        return subjectService.delete(Subject.class, id)?"Thành công":"Thất bại";
    }
}
