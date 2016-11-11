/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.PricingPolicyBean;
import cn.hanbell.erp.entity.PricingPolicy;
import cn.hanbell.util.SuperEJB;
import javax.inject.Inject;
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
//
//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    @Override
//    public void edit(@PathParam("id") String id, PricingPolicy entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @Override
    protected SuperEJB getSuperEJB() {
        return pricingPolicyBean;
    }

}
