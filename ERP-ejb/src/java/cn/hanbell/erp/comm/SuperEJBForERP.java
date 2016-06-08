/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.comm;

import cn.hanbell.erp.ejb.MiscodeBean;
import cn.hanbell.util.SuperEJB;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.EJB;
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

    @EJB
    public MiscodeBean miscodeBean;

    protected String company;

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
        return getEntityManager(getCompany());
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
                    entry.getKey().setCompany(company);
                    for (Object o : entry.getValue()) {
                        entry.getKey().persist(o);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected EntityManager getEntityManager(String facno) {
        switch (facno) {
            case "C":
                return em_shberp;
            case "G":
                return em_gzerp;
            case "J":
                return em_jnerp;
            case "N":
                return em_njerp;
            default:
                return em_default;
        }
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

}
