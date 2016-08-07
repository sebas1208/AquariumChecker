/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Plantas;
import ec.epn.aqchecker.jdbc.PlantasService;
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
@Path("plantas")
public class PlantasREST {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Plantas get(@PathParam("id") Integer id) {
        PlantasService service = new PlantasService();
        return service.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plantas> getAll() {
        PlantasService service = new PlantasService();
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Plantas plantas) {
        PlantasService service = new PlantasService();
        return service.create(plantas);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Plantas plantas) {
        PlantasService service = new PlantasService();
        return service.edit(plantas);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        PlantasService service = new PlantasService();
        return service.remove(id);
    }
}
