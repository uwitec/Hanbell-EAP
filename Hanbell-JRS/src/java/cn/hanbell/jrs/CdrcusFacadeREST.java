/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.CdrcusBean;
import cn.hanbell.erp.entity.Cdrcus;
import cn.hanbell.util.SuperEJB;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("shberp.cdrcus")
@javax.enterprise.context.RequestScoped
public class CdrcusFacadeREST extends AbstractFacade<Cdrcus> {
    
    
    @Inject
    private CdrcusBean cdrcusBean;

    public CdrcusFacadeREST() {
        super(Cdrcus.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Cdrcus entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    public void edit(@PathParam("id") String id, Cdrcus entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

//    @GET
//    @Path("{id}")
//    @Produces({"application/json"})
//    public Cdrcus find(@PathParam("id") String id) {
//        return cdrcusBean.findById(id);
//    }

    @GET
    @Path("man/{man}")
    @Produces({"application/json"})
    public List<Cdrcus> findByMan(@PathParam("man") String man) {
        return cdrcusBean.findByMan(man);
    }

    @Override
    protected SuperEJB getSuperEJB() {
        return this.cdrcusBean;
    }



}
