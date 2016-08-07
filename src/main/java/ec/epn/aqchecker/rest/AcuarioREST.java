package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Acuario;
import ec.epn.aqchecker.jdbc.AcuarioService;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("acuario")
public class AcuarioREST {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Acuario get(@PathParam("id") Integer id) {
        AcuarioService service = new AcuarioService();
        return service.find(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Acuario> getAll() {
        AcuarioService service = new AcuarioService();
        return service.findAll();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Acuario acuario) throws SQLException {
        AcuarioService service = new AcuarioService();
        return service.create(acuario);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Acuario acuario) {
        AcuarioService service = new AcuarioService();
        return service.edit(acuario);
    }
    
    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        AcuarioService service = new AcuarioService();
        return service.remove(id);
    }
    
    @GET
    @Path("hola")
    public String holaMundo() {
        return "Hola Mundo";
    }
    
}
