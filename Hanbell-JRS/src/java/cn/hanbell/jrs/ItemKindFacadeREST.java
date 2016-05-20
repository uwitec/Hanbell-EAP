/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.entity.ItemKind;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
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
@Stateless
@Path("shberp.itemkind")
public class ItemKindFacadeREST extends AbstractFacade<ItemKind> {

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

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, ItemKind entity) {
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
    public ItemKind find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<ItemKind> findAll() {
        Query query = em.createNativeQuery("SELECT distinct kind FROM cdrdmmodel ORDER BY kind ");
        List result = query.getResultList();
        List<ItemKind> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemKind kind;
            for (Object o : result) {
                kind = new ItemKind(o.toString());
                dataList.add(kind);
            }
        }
        return dataList;
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
