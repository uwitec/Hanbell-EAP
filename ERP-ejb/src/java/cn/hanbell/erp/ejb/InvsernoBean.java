/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invserno;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C1491
 */
@Stateless
@LocalBean
public class InvsernoBean extends SuperEJBForERP<Invserno> {

    public InvsernoBean() {
        super(Invserno.class);
    }

    public Invserno findByPK(String facno, String trtype, String keyformat) {
        Query q = this.getEntityManager().createNamedQuery("Invserno.findByPK");
        q.setParameter("facno", facno);
        q.setParameter("trtype", trtype);
        q.setParameter("keyformat", keyformat);
        try {
            Object o = q.getSingleResult();
            return (Invserno) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
