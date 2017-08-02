/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.crm.ejb;

import cn.hanbell.crm.comm.SuperEJBForCRM;
import cn.hanbell.crm.entity.SERBQ;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C1587
 */
@Stateless
@LocalBean
public class SERBQBean extends SuperEJBForCRM<SERBQ> {

    public SERBQBean() {
        super(SERBQ.class);
    }

    public SERBQ findByBq001(String bq001) {
        Query query = this.getEntityManager().createNamedQuery("SERBQ.findByBq001");
        query.setParameter("bq001", bq001);
        try {
            Object o = query.getSingleResult();
            return (SERBQ) o;
        } catch (Exception ex) {
            return null;
        }
    }
}
