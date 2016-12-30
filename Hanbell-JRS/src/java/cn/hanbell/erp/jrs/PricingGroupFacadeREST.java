/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.ejb.PricingGroupBean;
import cn.hanbell.erp.entity.PricingGroup;
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
@Path("shberp.pricinggroup")
@javax.enterprise.context.RequestScoped
public class PricingGroupFacadeREST extends SuperRESTForERP<PricingGroup> {

    @Inject
    private PricingGroupBean pricinGroupBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return pricinGroupBean;
    }

    public PricingGroupFacadeREST() {
        super(PricingGroup.class);
    }

    @GET
    @Path("{userno}/list")
    @Produces({"application/json"})
    public List<PricingGroup> find(@PathParam("userno") String userno) {
        return pricinGroupBean.findByUserno(userno);
    }

    @GET
    @Path("{userno}/{id}")
    @Produces({"application/json"})
    public PricingGroup find(@PathParam("userno") String userno, @PathParam("id") String id) {
        return pricinGroupBean.findByUsernoAndGroupId(userno, id);
    }

}
