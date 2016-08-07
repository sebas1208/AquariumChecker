/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Peces;
import ec.epn.aqchecker.jdbc.PecesService;
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
@Path("peces")
public class PecesREST {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Peces get(@PathParam("id") Integer id) {
        PecesService service = new PecesService();
        return service.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Peces> getAll() {
        PecesService service = new PecesService();
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Peces peces) {
        PecesService service = new PecesService();
        return service.create(peces);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Peces peces) {
        PecesService service = new PecesService();
        return service.edit(peces);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        PecesService service = new PecesService();
        return service.remove(id);
    }
}
