/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.ejb.ItemModelBean;
import cn.hanbell.erp.entity.ItemModel;
import cn.hanbell.jrs.SuperRESTForERP;
import cn.hanbell.util.SuperEJB;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author C0160
 */
@Path("shberp.itemmodel")
@javax.enterprise.context.RequestScoped
public class ItemModelFacadeREST extends SuperRESTForERP<ItemModel> {

    @Inject
    private ItemModelBean itemModelBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return itemModelBean;
    }

    public ItemModelFacadeREST() {
        super(ItemModel.class);
    }

    @GET
    @Path("{model}")
    @Produces({"application/xml", "application/json"})
    @Override
    public ItemModel findById(@PathParam("model") PathSegment model) {
        Query query = itemModelBean.getEntityManager().createNativeQuery("SELECT cmcmodel,itnbr FROM cdrdmmodel WHERE cmcmodel = '" + model.getPath() + "' ORDER BY cmcmodel ");
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
    
    @GET
    @Path("kind/{kind}/{offset}/{pageSize}")
    @Produces({"application/json"})
    public List<ItemModel> findByKind(@PathParam("kind") String kind,@PathParam("offset") int from,@PathParam("pageSize") int pageSize) {
        return itemModelBean.findByKind(kind,from,pageSize);
    }

}
