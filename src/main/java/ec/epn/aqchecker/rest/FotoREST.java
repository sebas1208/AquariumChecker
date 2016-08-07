/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.rest;

import ec.epn.aqchecker.entity.Foto;
import ec.epn.aqchecker.jdbc.FotoService;
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
@Path("foto")
public class FotoREST {
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Foto get(@PathParam("id") Integer id) {
        FotoService service = new FotoService();
        return service.find(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Foto> getAll() {
        FotoService service = new FotoService();
        return service.findAll();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Foto foto) {
        FotoService service = new FotoService();
        return service.create(foto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Foto foto) {
        FotoService service = new FotoService();
        return service.edit(foto);
    }
    
    @DELETE
    @Path("{id}")
    public String delete(@PathParam("id") Integer id) {
        FotoService service = new FotoService();
        return service.remove(id);
    }
}
