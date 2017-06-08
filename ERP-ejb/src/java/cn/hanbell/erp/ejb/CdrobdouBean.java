/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Cdrobdou;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class CdrobdouBean extends SuperEJBForERP<Cdrobdou> {

    public CdrobdouBean() {
        super(Cdrobdou.class);
    }

    public Cdrobdou findByCdrobtype(String cdrobtype) {
        Query query = this.getEntityManager().createNamedQuery("Cdrobdou.findByCdrobtype");
        query.setParameter("cdrobtype", cdrobtype);
        try {
            Object o = query.getSingleResult();
            return (Cdrobdou) o;
        } catch (Exception ex) {
            return null;
        }

    }

}
