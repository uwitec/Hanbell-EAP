/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.ejb.CdrcusBean;
import cn.hanbell.erp.entity.Cdrcus;
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
@Path("shberp.cdrcus")
@javax.enterprise.context.RequestScoped
public class CdrcusFacadeREST extends SuperRESTForERP<Cdrcus> {

    @Inject
    private CdrcusBean cdrcusBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return cdrcusBean;
    }

    public CdrcusFacadeREST() {
        super(Cdrcus.class);
    }

    @GET
    @Path("man/{man}")
    @Produces({"application/json"})
    public List<Cdrcus> findByMan(@PathParam("man") String man) {
        return cdrcusBean.findByMan(man);
    }

}
