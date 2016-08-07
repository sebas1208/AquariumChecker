/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Galeria;
import ec.epn.aqchecker.jdbc.GaleriaService;
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

/**
 *
 * @author sebastian
 */
@Path("galeria")
public class GaleriaREST {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Galeria get(@PathParam("id") Integer id) {
        GaleriaService service = new GaleriaService();
        return service.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Galeria> getAll() {
        GaleriaService service = new GaleriaService();
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Galeria galeria) {
        GaleriaService service = new GaleriaService();
        return service.create(galeria);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Galeria galeria) {
        GaleriaService service = new GaleriaService();
        return service.edit(galeria);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        GaleriaService service = new GaleriaService();
        return service.remove(id);
    }
}
