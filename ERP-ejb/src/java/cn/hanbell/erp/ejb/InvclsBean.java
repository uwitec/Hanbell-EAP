/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invcls;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Stateless
@LocalBean
public class InvclsBean extends SuperEJBForERP<Invcls> {

    public InvclsBean() {
        super(Invcls.class);
    }

    public Invcls findByItcls(String itcls) {
        Query query = getEntityManager().createNamedQuery("Invcls.findByItcls");
        query.setParameter("itcls", itcls);
        try {
            return (Invcls) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

  

}