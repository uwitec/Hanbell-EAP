/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.BudgetDetail;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class BudgetDetailBean extends SuperEJBForERP<BudgetDetail> {

    public BudgetDetailBean() {
        super(BudgetDetail.class);
    }

    public BudgetDetail findBudgetDetail(BudgetDetail entity) {
        return findBudgetDetail(entity.getBudgetDetailPK().getFacno(), entity.getBudgetDetailPK().getPeriod(), entity.getBudgetDetailPK().getCenterid(), entity.getBudgetDetailPK().getBudgetacc());
    }

    public BudgetDetail findBudgetDetail(String facno, String period, String centerid, String budgetacc) {
        Query query = getEntityManager().createNamedQuery("BudgetDetail.findBudgetDetail");
        query.setParameter("facno", facno);
        query.setParameter("period", period);
        query.setParameter("centerid", centerid);
        query.setParameter("budgetacc", budgetacc);
        try {
            return (BudgetDetail) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(BudgetDetail entity) throws RuntimeException {
        try {
            BudgetDetail e = findBudgetDetail(entity);
            if (e != null) {
                e.setAddamts(e.getAddamts().add(entity.getAddamts()));
                e.setDecramts(e.getDecramts().add(entity.getDecramts()));
                e.setPreamts(e.getPreamts().add(entity.getPreamts()));
                update(e);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void add(List<BudgetDetail> entities) throws RuntimeException {
        for (BudgetDetail e : entities) {
            add(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void subtract(BudgetDetail entity) throws RuntimeException {
        try {
            BudgetDetail e = findBudgetDetail(entity);
            if (e != null) {
                e.setAddamts(e.getAddamts().subtract(entity.getAddamts()));
                e.setDecramts(e.getDecramts().subtract(entity.getDecramts()));
                e.setPreamts(e.getPreamts().subtract(entity.getPreamts()));
                update(e);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.toString());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void subtract(List<BudgetDetail> entities) throws RuntimeException {
        for (BudgetDetail e : entities) {
            subtract(e);
        }
    }

}
