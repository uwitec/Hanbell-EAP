/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.ItemMasterBean;
import cn.hanbell.erp.entity.ItemMaster;
import cn.hanbell.util.SuperEJB;
import java.util.List;
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
@Path("shberp.servitemmaster")
@javax.enterprise.context.RequestScoped
public class ItemMasterFacadeREST extends AbstractFacade<ItemMaster> {

    @Inject
    private ItemMasterBean itemMasterBean;

    public ItemMasterFacadeREST() {
        super(ItemMaster.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemMaster entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    @Override
//    public void edit(@PathParam("id") String id, ItemMaster entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{category}/list")
    @Produces({"application/json"})
    public List<ItemMaster> findByCategory(@PathParam("category") String category) {
        return itemMasterBean.findByCategory(category);
    }

    @GET
    @Path("{model}/{category}")
    @Produces({"application/json"})
    public List<ItemMaster> findByModelAndCategory(@PathParam("model") String model, @PathParam("category") String category) {
        return itemMasterBean.findByModelAndCategory(model, category);
    }

    @Override
    protected SuperEJB getSuperEJB() {
        return itemMasterBean;
    }

}
