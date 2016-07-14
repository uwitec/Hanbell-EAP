/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.crm.ejb;

import cn.hanbell.crm.comm.SuperEJBForCRM;
import cn.hanbell.crm.entity.CRMGG;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class CRMGGBean extends SuperEJBForCRM<CRMGG> {

    public CRMGGBean() {
        super(CRMGG.class);
    }

    public CRMGG findByERPCusno(String cusno) {
        Query query = getEntityManager().createNamedQuery("CRMGG.findByERPCusno");
        query.setParameter("cusno", cusno);
        try {
            Object o = query.getSingleResult();
            return (CRMGG) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
