/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.comm;

import cn.hanbell.util.SuperEJB;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperEJBForERP<T> extends SuperEJB<T> {

    protected EntityManager em;

    @PersistenceContext(unitName = "ERP-ejbPU")
    private EntityManager em_default;

    @PersistenceContext(unitName = "PU_shberp")
    private EntityManager em_shberp;

    @PersistenceContext(unitName = "PU_gzerp")
    private EntityManager em_gzerp;

    @PersistenceContext(unitName = "PU_jnerp")
    private EntityManager em_jnerp;

    @PersistenceContext(unitName = "PU_njerp")
    private EntityManager em_njerp;

    public SuperEJBForERP(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return em != null ? em : em_default;
    }

    public Boolean initByOAPSN(String psn) {
        return true;
    }

    //新增一笔资料,一个表头多个明细
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void persist(T entity, HashMap<SuperEJBForERP, List<?>> detailAdded) {
        try {
            getEntityManager().persist(entity);
            if (detailAdded != null && !detailAdded.isEmpty()) {
                for (Entry<SuperEJBForERP, List<?>> entry : detailAdded.entrySet()) {
                    entry.getKey().setEntityManager(getEntityManager());
                    for (Object o : entry.getValue()) {
                        entry.getKey().persist(o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setEntityManagerByCompany(String facno) {
        switch (facno) {
            case "C":
                setEntityManager(em_shberp);
                break;
            case "G":
                setEntityManager(em_gzerp);
                break;
            case "J":
                setEntityManager(em_jnerp);
                break;
            case "N":
                setEntityManager(em_njerp);
                break;
            default:
                setEntityManager(em_default);
        }
    }

    /**
     * @param em the em to set
     */
    protected void setEntityManager(EntityManager em) {
        this.em = em;
    }

}
