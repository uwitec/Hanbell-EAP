/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Cdrcus;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class CdrcusBean_JN extends SuperEJBForERP<Cdrcus> {

    @PersistenceContext(unitName = "PU_jnerp")
    private EntityManager em;

    public CdrcusBean_JN() {
        super(Cdrcus.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sync(Cdrcus entity, HashMap<SuperEJBForERP, List<?>> detailAdded) {
        try {
            getEntityManager().persist(entity);
            if (detailAdded != null && !detailAdded.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailAdded.entrySet()) {
                    for (Object o : entry.getValue()) {
                        getEntityManager().persist(o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
