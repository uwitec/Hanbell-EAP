/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.ejb.ItemCategoryBean;
import cn.hanbell.erp.entity.ItemCategory;
import cn.hanbell.jrs.SuperRESTForERP;
import cn.hanbell.util.SuperEJB;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author C0160
 */
@Path("shberp/itemcategory")
@javax.enterprise.context.RequestScoped
public class ItemCategoryFacadeREST extends SuperRESTForERP<ItemCategory> {

    @Inject
    private ItemCategoryBean itemCategoryBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return itemCategoryBean;
    }

    public ItemCategoryFacadeREST() {
        super(ItemCategory.class);
    }

}
