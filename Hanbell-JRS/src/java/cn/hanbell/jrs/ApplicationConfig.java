/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

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
        resources.add(cn.hanbell.jrs.ArmhadFacadeREST.class);
        resources.add(cn.hanbell.jrs.ArmhadSumFacadeREST.class);
        resources.add(cn.hanbell.jrs.CORSFilter.class);
        resources.add(cn.hanbell.jrs.CdrcusFacadeREST.class);
        resources.add(cn.hanbell.jrs.CdrdmasFacadeREST.class);
        resources.add(cn.hanbell.jrs.CdrscheduleFacadeREST.class);
        resources.add(cn.hanbell.jrs.CompanyFacadeREST.class);
        resources.add(cn.hanbell.jrs.InvbalFacadeREST.class);
        resources.add(cn.hanbell.jrs.InvwhFacadeREST.class);
        resources.add(cn.hanbell.jrs.ItemCategoryFacadeREST.class);
        resources.add(cn.hanbell.jrs.ItemFaultFacadeREST.class);
        resources.add(cn.hanbell.jrs.ItemKindFacadeREST.class);
        resources.add(cn.hanbell.jrs.ItemMasterFacadeREST.class);
        resources.add(cn.hanbell.jrs.ItemModelFacadeREST.class);
        resources.add(cn.hanbell.jrs.PricingFacadeREST.class);
        resources.add(cn.hanbell.jrs.PricingGroupFacadeREST.class);
        resources.add(cn.hanbell.jrs.PricingPolicyFacadeREST.class);
        resources.add(cn.hanbell.jrs.SecuserFacadeREST.class);
    }
    
}
