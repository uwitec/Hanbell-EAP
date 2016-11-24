/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs.comm;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author C0160
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cn.hanbell.jrs.comm.CORSFilter.class);
        resources.add(cn.hanbell.jrs.erp.ArmhadFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.ArmhadSumFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.CdrcusFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.CdrdmasFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.CdrscheduleFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.CompanyFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.InvbalFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.InvwhFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.ItemCategoryFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.ItemFaultFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.ItemKindFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.ItemMasterFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.ItemModelFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.PricingFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.PricingGroupFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.PricingPolicyFacadeREST.class);
        resources.add(cn.hanbell.jrs.erp.SecuserFacadeREST.class);
    }
    
}
