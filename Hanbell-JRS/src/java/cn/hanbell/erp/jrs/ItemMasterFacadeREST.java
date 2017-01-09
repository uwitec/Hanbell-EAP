/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.ejb.ItemMasterBean;
import cn.hanbell.erp.entity.ItemMaster;
import cn.hanbell.jrs.SuperRESTForERP;
import cn.hanbell.util.SuperEJB;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author C0160
 */
@Path("shberp.servitemmaster")
@javax.enterprise.context.RequestScoped
public class ItemMasterFacadeREST extends SuperRESTForERP<ItemMaster> {

    @Inject
    private ItemMasterBean itemMasterBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return itemMasterBean;
    }

    public ItemMasterFacadeREST() {
        super(ItemMaster.class);
    }

    @GET
    @Path("list/{category}")
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

}
