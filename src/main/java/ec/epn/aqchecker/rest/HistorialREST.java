/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Historial;
import ec.epn.aqchecker.jdbc.HistorialService;
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

/**
 *
 * @author sebastian
 */
@Path("historial")
public class HistorialREST {
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Historial get(@PathParam("id") Integer id) {
        HistorialService service = new HistorialService();
        return service.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Historial> getAll() {
        HistorialService service = new HistorialService();
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Historial historial) {
        HistorialService service = new HistorialService();
        return service.create(historial);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Historial historial) {
        HistorialService service = new HistorialService();
        return service.edit(historial);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        HistorialService service = new HistorialService();
        return service.remove(id);
    }
}
