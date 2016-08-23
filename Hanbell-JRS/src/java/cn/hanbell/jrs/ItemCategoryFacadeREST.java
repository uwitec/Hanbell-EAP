/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.ItemCategoryBean;
import cn.hanbell.erp.entity.ItemCategory;
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
@Path("shberp.itemcategory")
@javax.enterprise.context.RequestScoped
public class ItemCategoryFacadeREST extends AbstractFacade<ItemCategory> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    @Inject
    private ItemCategoryBean itemCategoryBean;

    public ItemCategoryFacadeREST() {
        super(ItemCategory.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemCategory entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, ItemCategory entity) {
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
    public ItemCategory find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<ItemCategory> findAll() {
        return itemCategoryBean.findAll();
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
