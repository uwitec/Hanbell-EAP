/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.comm;

import cn.hanbell.util.SuperEJB;
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
