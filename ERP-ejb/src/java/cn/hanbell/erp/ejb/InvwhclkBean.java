/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invwhclk;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class InvwhclkBean extends SuperEJBForERP<Invwhclk> {

    public InvwhclkBean() {
        super(Invwhclk.class);
    }

    public List<Invwhclk> findByWareh(String wareh) {
        Query query = getEntityManager().createNamedQuery("Invwhclk.findByWareh");
        query.setParameter("wareh", wareh);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }

    }

}
