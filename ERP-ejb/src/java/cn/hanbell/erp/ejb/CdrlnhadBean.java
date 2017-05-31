/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Cdrlnhad;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class CdrlnhadBean extends SuperEJBForERP<Cdrlnhad> {

    public CdrlnhadBean() {
        super(Cdrlnhad.class);
    }

    public Cdrlnhad findByPK(String facno, String trno) {
        Query q = this.getEntityManager().createNamedQuery("Cdrlnhad.findByPK");
        q.setParameter("facno", facno);
        q.setParameter("trno", trno);
        try {
            Object o = q.getSingleResult();
            return (Cdrlnhad) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
