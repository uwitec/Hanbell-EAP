/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Accacr;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class AccacrBean extends SuperEJBForERP<Accacr> {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public AccacrBean() {
        super(Accacr.class);
    }
    
    public Short getConfig(String facno, String sysno ,String kind ,String rkd ,String accno ,Character dcratr) {
        Query query = getEntityManager().createNamedQuery("Accacr.findByPK");
        query.setParameter("facno", facno);
        query.setParameter("sysno", sysno);
        query.setParameter("kind", kind);
        query.setParameter("rkd", rkd);
        query.setParameter("accno", accno);
        query.setParameter("dcratr", dcratr);

        try {
            Accacr accacr = (Accacr) query.getSingleResult();
            return accacr.getConfig();
        } catch (Exception ex) {
            return null;
        }
    }
    

    
}
