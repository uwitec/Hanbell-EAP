/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.ItemKindBean;
import cn.hanbell.erp.entity.ItemKind;
import cn.hanbell.util.SuperEJB;
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
@Path("shberp.itemkind")
@javax.enterprise.context.RequestScoped
public class ItemKindFacadeREST extends AbstractFacade<ItemKind> {

    @Inject
    private ItemKindBean itemKindBean;

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public ItemKindFacadeREST() {
        super(ItemKind.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemKind entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    @Override
//    public void edit(@PathParam("id") String id, ItemKind entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
