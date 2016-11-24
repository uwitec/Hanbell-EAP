/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs.erp;

import cn.hanbell.jrs.comm.AbstractFacade;
import cn.hanbell.erp.ejb.ItemModelBean;
import cn.hanbell.erp.entity.ItemModel;
import cn.hanbell.util.SuperEJB;
import java.util.List;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("shberp.itemmodel")
@javax.enterprise.context.RequestScoped
public class ItemModelFacadeREST extends AbstractFacade<ItemModel> {

    @Inject
    private ItemModelBean itemModelBean;

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public ItemModelFacadeREST() {
        super(ItemModel.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemModel entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    @Override
//    public void edit(@PathParam("id") String id, ItemModel entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{model}")
    @Produces({"application/xml", "application/json"})
    @Override
    public ItemModel findById(@PathParam("model") PathSegment model) {
        Query query = em.createNativeQuery("SELECT cmcmodel,itnbr FROM cdrdmmodel WHERE cmcmodel = '" + model.getPath() + "' ORDER BY cmcmodel ");
        Object[] row = (Object[]) query.getSingleResult();
        ItemModel newEntity = new ItemModel(row[0].toString(), row[1].toString());
        return newEntity;
    }

    @GET
    @Path("kind/{kind}")
    @Produces({"application/json"})
    public List<ItemModel> findByKind(@PathParam("kind") String kind) {
        return itemModelBean.findByKind(kind);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
