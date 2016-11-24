/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs.erp;

import cn.hanbell.jrs.comm.AbstractFacade;
import cn.hanbell.erp.ejb.ItemFaultBean;
import cn.hanbell.erp.entity.ItemFault;
import cn.hanbell.util.SuperEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("shberp.itemfault")
@javax.enterprise.context.RequestScoped
public class ItemFaultFacadeREST extends AbstractFacade<ItemFault> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    @Inject
    private ItemFaultBean itemFaultBean;

    public ItemFaultFacadeREST() {
        super(ItemFault.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemFault entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    public void edit(@PathParam("id") String id, ItemFault entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }


    @GET
    @Override
    @Produces({"application/json"})
    public List<ItemFault> findAll() {
        return itemFaultBean.findAll();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
