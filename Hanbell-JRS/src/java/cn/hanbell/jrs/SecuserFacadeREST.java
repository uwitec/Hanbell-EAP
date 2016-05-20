/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.entity.Secuser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.secuser")
public class SecuserFacadeREST extends AbstractFacade<Secuser> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    EntityManager em;

    public SecuserFacadeREST() {
        super(Secuser.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Secuser entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Secuser entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}/{opwd}/{npwd}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, @PathParam("opwd") String opwd, @PathParam("npwd") String npwd, Secuser entity) {
        Query query = getEntityManager().createNamedQuery("Secuser.findByUsernoAndPwd");
        query.setParameter("userno", id);
        query.setParameter("pwd", opwd);
        try {
            Secuser u = (Secuser) query.getSingleResult();
            u.setSigntext(npwd);
            getEntityManager().merge(u);
        } catch (Exception  e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Secuser find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Path("{id}/{pwd}")
    @Produces({"application/json"})
    public Secuser findByUsernoAndPwd(@PathParam("id") String id, @PathParam("pwd") String pwd) {
        Query query = getEntityManager().createNamedQuery("Secuser.findByUsernoAndPwd");
        query.setParameter("userno", id);
        query.setParameter("pwd", pwd);
        return (Secuser) query.getSingleResult();
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
