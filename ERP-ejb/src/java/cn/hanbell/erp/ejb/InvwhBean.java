/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invwh;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class InvwhBean extends SuperEJBForERP<Invwh> {

    public InvwhBean() {
        super(Invwh.class);
    }

    public Invwh findByWareh(String wareh) {
        Query query = getEntityManager().createNamedQuery("Invwh.findByWareh");
        query.setParameter("wareh", wareh);
        try {
            Object o = query.getSingleResult();
            return (Invwh) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
