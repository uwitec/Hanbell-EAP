/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.ejb.ItemFaultBean;
import cn.hanbell.erp.entity.ItemFault;
import cn.hanbell.jrs.SuperRESTForERP;
import cn.hanbell.util.SuperEJB;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author C0160
 */
@Path("shberp/itemfault")
@javax.enterprise.context.RequestScoped
public class ItemFaultFacadeREST extends SuperRESTForERP<ItemFault> {

    @Inject
    private ItemFaultBean itemFaultBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return this.itemFaultBean;
    }

    public ItemFaultFacadeREST() {
        super(ItemFault.class);
    }

}
