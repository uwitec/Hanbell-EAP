/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.PricingPolicyBean;
import cn.hanbell.erp.entity.PricingPolicy;
import javax.inject.Inject;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("shberp.pricingpolicy")
@javax.enterprise.context.RequestScoped
public class PricingPolicyFacadeREST extends AbstractFacade<PricingPolicy> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    @Inject
    private PricingPolicyBean pricingPolicyBean;

    public PricingPolicyFacadeREST() {
        super(PricingPolicy.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(PricingPolicy entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, PricingPolicy entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public PricingPolicy find(@PathParam("id") String id) {
        return pricingPolicyBean.findById(id);
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
