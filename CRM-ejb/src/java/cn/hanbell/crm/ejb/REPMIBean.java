/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.crm.ejb;

import cn.hanbell.crm.comm.SuperEJBForCRM;
import cn.hanbell.crm.entity.REPMI;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class REPMIBean extends SuperEJBForCRM<REPMI> {

    public REPMIBean() {
        super(REPMI.class);
    }

    public REPMI findByPK(String mi001, String mi002) {
        Query query = this.getEntityManager().createNamedQuery("REPMI.findByPK");
        query.setParameter("mi001", mi001);
        query.setParameter("mi002", mi002);
        try {
            Object o = query.getSingleResult();
            return (REPMI) o;
        } catch (Exception e) {
            return null;
        }
    }
    
    //根据制造号码查询
    public REPMI findByMI002(String mi002) {
        Query query = this.getEntityManager().createNamedQuery("REPMI.findByMi002");
        query.setParameter("mi002", mi002);
        try {
            Object o = query.getSingleResult();
            return (REPMI) o;
        } catch (Exception e) {
            return null;
        }
    }

}
