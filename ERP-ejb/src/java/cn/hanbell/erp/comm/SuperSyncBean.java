/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.comm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

/**
 *
 * @author C0160
 */
public abstract class SuperSyncBean implements Serializable {

    public SuperSyncBean() {

    }

    public abstract EntityManager getEntityManager();

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persist(Object entity, HashMap<SuperEJBForERP, List<?>> detailAdded) {
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Object entity, HashMap<SuperEJBForERP, List<?>> detailAdded, HashMap<SuperEJBForERP, List<?>> detailEdited, HashMap<SuperEJBForERP, List<?>> detailDeleted) {
        try {
            getEntityManager().merge(entity);
            if (detailEdited != null && !detailEdited.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailEdited.entrySet()) {
                    for (Object o : entry.getValue()) {
                        getEntityManager().merge(o);
                    }
                }
            }
            if (detailDeleted != null && !detailDeleted.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailDeleted.entrySet()) {
                    for (Object o : entry.getValue()) {
                        if (getEntityManager().contains(o)) {
                            getEntityManager().remove(o);
                        } else {
                            getEntityManager().remove(getEntityManager().merge(o));
                        }
                    }
                }
            }
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

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Object entity, HashMap<SuperEJBForERP, List<?>> detailEdited) {
        try {
            getEntityManager().merge(entity);
            if (detailEdited != null && !detailEdited.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailEdited.entrySet()) {
                    for (Object o : entry.getValue()) {
                        getEntityManager().merge(o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void delete(Object entity, HashMap<SuperEJBForERP, List<?>> detailDeleted) {
        try {
            if (getEntityManager().contains(entity)) {
                getEntityManager().remove(entity);
            } else {
                getEntityManager().remove(getEntityManager().merge(entity));
            }
            if (detailDeleted != null && !detailDeleted.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailDeleted.entrySet()) {
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

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void syncPersist(Object entity, HashMap<SuperEJBForERP, List<?>> detailAdded) {
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

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void syncUpdate(Object entity, HashMap<SuperEJBForERP, List<?>> detailEdited) {
        try {
            getEntityManager().merge(entity);
            if (detailEdited != null && !detailEdited.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailEdited.entrySet()) {
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
    public void syncDelete(Object entity, HashMap<SuperEJBForERP, List<?>> detailDeleted) {
        try {
            if (getEntityManager().contains(entity)) {
                getEntityManager().remove(entity);
            } else {
                getEntityManager().remove(getEntityManager().merge(entity));
            }
            if (detailDeleted != null && !detailDeleted.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailDeleted.entrySet()) {
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
