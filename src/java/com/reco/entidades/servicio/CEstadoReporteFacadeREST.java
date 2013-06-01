/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reco.entidades.servicio;

import com.reco.entidades.CEstadoReporte;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Hugo
 */
@javax.ejb.Stateless
@Path("com.reco.entidades.cestadoreporte")
public class CEstadoReporteFacadeREST extends AbstractFacade<CEstadoReporte> {
    @PersistenceContext(unitName = "ReCoPU")
    private EntityManager em;

    public CEstadoReporteFacadeREST() {
        super(CEstadoReporte.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(CEstadoReporte entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(CEstadoReporte entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public CEstadoReporte find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<CEstadoReporte> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<CEstadoReporte> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
