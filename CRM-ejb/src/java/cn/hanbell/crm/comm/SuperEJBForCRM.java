/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.crm.comm;

import cn.hanbell.util.SuperEJB;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperEJBForCRM<T> extends SuperEJB<T> {

    @PersistenceContext(unitName = "CRM-ejbPU")
    private EntityManager em;

    public SuperEJBForCRM(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void syncPersist(Object entity, HashMap<SuperEJBForCRM, List<?>> detailAdded) {
        try {
            getEntityManager().persist(entity);
            if (detailAdded != null && !detailAdded.isEmpty()) {
                for (Map.Entry<SuperEJBForCRM, List<?>> entry : detailAdded.entrySet()) {
                    for (Object o : entry.getValue()) {
                        getEntityManager().persist(o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void syncUpdate(Object entity, HashMap<SuperEJBForCRM, List<?>> detailEdited) {
        try {
            getEntityManager().merge(entity);
            if (detailEdited != null && !detailEdited.isEmpty()) {
                for (Map.Entry<SuperEJBForCRM, List<?>> entry : detailEdited.entrySet()) {
                    for (Object o : entry.getValue()) {
                        getEntityManager().merge(o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void syncDelete(Object entity, HashMap<SuperEJBForCRM, List<?>> detailDeleted) {
        try {
            if (getEntityManager().contains(entity)) {
                getEntityManager().remove(entity);
            } else {
                getEntityManager().remove(getEntityManager().merge(entity));
            }
            if (detailDeleted != null && !detailDeleted.isEmpty()) {
                for (Map.Entry<SuperEJBForCRM, List<?>> entry : detailDeleted.entrySet()) {
                    for (Object o : entry.getValue()) {
                        if (getEntityManager().contains(o)) {
                            getEntityManager().remove(o);
                        } else {
                            getEntityManager().remove(getEntityManager().merge(o));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
