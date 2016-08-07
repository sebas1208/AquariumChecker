/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Recordatorio;
import ec.epn.aqchecker.jdbc.RecordatorioService;
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
@Path("recordatorio")
public class RecordatorioREST {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Recordatorio get(@PathParam("id") Integer id) {
        RecordatorioService service = new RecordatorioService();
        return service.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Recordatorio> getAll() {
        RecordatorioService service = new RecordatorioService();
        return service.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Recordatorio recordatorio) {
        RecordatorioService service = new RecordatorioService();
        return service.create(recordatorio);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Recordatorio recordatorio) {
        RecordatorioService service = new RecordatorioService();
        return service.edit(recordatorio);
    }

    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        RecordatorioService service = new RecordatorioService();
        return service.remove(id);
    }
}
