/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.BudgetDetail;
import cn.hanbell.oa.ejb.HZCW017Bean;
import cn.hanbell.oa.ejb.HZCW028Bean;
import cn.hanbell.oa.ejb.HZCW028reDetailBean;
import cn.hanbell.oa.ejb.HZCW033Bean;
import cn.hanbell.oa.ejb.HZCW033reDetailBean;
import cn.hanbell.oa.entity.HZCW017;
import cn.hanbell.oa.entity.HZCW028;
import cn.hanbell.oa.entity.HZCW028reDetail;
import cn.hanbell.oa.entity.HZCW033;
import cn.hanbell.oa.entity.HZCW033reDetail;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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

    @EJB
    private HZCW028Bean hzcw028Bean;
    @EJB
    private HZCW028reDetailBean hzcw028reDetailBean;
    @EJB
    private HZCW033Bean hzcw033Bean;
    @EJB
    private HZCW033reDetailBean hzcw033reDetailBean;
    @EJB
    private HZCW017Bean hzcw017Bean;

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

    public Boolean addByOABXD(String psn) {
        String period;
        Date date;
        HZCW028 b = hzcw028Bean.findByPSN(psn);
        String facno = b.getFacno();
        List<BudgetDetail> budgetDetails = new ArrayList<>();
        List<HZCW028reDetail> details = null;
        try {

            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", b.getAppDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);
            System.out.println(period);

            this.setCompany(facno);

            details = hzcw028reDetailBean.findByFSN(b.getFormSerialNumber());

            for (int i = 0; i < details.size(); i++) {

                HZCW028reDetail detail = details.get(i);
                //预算金额更新逻辑
                BudgetDetail u;
                u = new BudgetDetail(facno, "", period, detail.getCenterid(), detail.getBudgetAcc(), "R", i + 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(detail.getTaxInclusive() * (b.getRatio())));
                budgetDetails.add(u);

            }

            this.add(budgetDetails);
            this.getEntityManager().flush();

            return true;
        } catch (ParseException | RuntimeException ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (details != null && !details.isEmpty()) {
                details.clear();
            }
            if (budgetDetails != null && !budgetDetails.isEmpty()) {
                budgetDetails.clear();
            }
        }
    }

    public Boolean subtractByOABXD(String psn) {
        String period;
        Date date;
        HZCW028 b = hzcw028Bean.findByPSN(psn);
        String facno = b.getFacno();
        List<BudgetDetail> budgetDetails = new ArrayList<>();
        List<HZCW028reDetail> details = null;
        try {
            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", b.getAppDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);
            System.out.println(period);

            this.setCompany(facno);

            details = hzcw028reDetailBean.findByFSN(b.getFormSerialNumber());

            for (int i = 0; i < details.size(); i++) {

                HZCW028reDetail detail = details.get(i);

                //预算金额更新逻辑
                BudgetDetail u;
                u = new BudgetDetail(facno, "", period, detail.getCenterid(), detail.getBudgetAcc(), "R", i + 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(detail.getTaxInclusive() * (b.getRatio())));
                budgetDetails.add(u);

            }

            this.subtract(budgetDetails);
            this.getEntityManager().flush();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (details != null && !details.isEmpty()) {
                details.clear();
            }
            if (budgetDetails != null && !budgetDetails.isEmpty()) {
                budgetDetails.clear();
            }
        }
    }

    public Boolean addByOAJZD(String psn) {
        String period;
        Date date;
        HZCW017 j = hzcw017Bean.findByPSN(psn);
        String facno = j.getFacno();
        List<BudgetDetail> budgetDetails = new ArrayList<>();

        try {

            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", j.getLoanDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);
            System.out.println(period);

            this.setCompany(facno);

            //预算金额更新逻辑
            BudgetDetail u;
            u = new BudgetDetail(facno, "", period, j.getCenterid(), j.getPreAccno(), "R", 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(j.getTotalRMB()));
            budgetDetails.add(u);

            this.add(budgetDetails);
            this.getEntityManager().flush();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean subtractByOAJZD(String psn) {
        String period;
        Date date;
        HZCW017 j = hzcw017Bean.findByPSN(psn);
        String facno = j.getFacno();
        List<BudgetDetail> budgetDetails = new ArrayList<>();

        try {

            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", j.getLoanDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);
            System.out.println(period);

            this.setCompany(facno);

            //预算金额更新逻辑
            BudgetDetail u;
            u = new BudgetDetail(facno, "", period, j.getCenterid(), j.getPreAccno(), "R", 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(j.getTotalRMB()));
            budgetDetails.add(u);

            this.subtract(budgetDetails);
            this.getEntityManager().flush();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean addByOAJZGHD(String psn) {
        String period;
        Date date;
        HZCW033 j = hzcw033Bean.findByPSN(psn);
        String facno = j.getFacno();
        List<BudgetDetail> budgetDetails = new ArrayList<>();

        try {

            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", j.getAppDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);
            System.out.println(period);

            this.setCompany(facno);

            List<HZCW033reDetail> details = hzcw033reDetailBean.findByFSN(j.getFormSerialNumber());

            for (int i = 0; i < details.size(); i++) {

                HZCW033reDetail detail = details.get(i);

                //预算金额更新逻辑
                BudgetDetail u;
                u = new BudgetDetail(facno, "", period, detail.getCenterid(), detail.getBudgetAcc(), "R", i + 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(detail.getTaxInclusive() * (j.getRatio())));
                budgetDetails.add(u);

            }
            //改变借支单科目预扣
            BudgetDetail u1;
            HZCW017 jz = hzcw017Bean.findByPSN(details.get(0).getLoanNo());
            Date date1 = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", jz.getLoanDate())); //借支单日期
            String period1 = BaseLib.formatDate("yyyyMM", date1);
            if (j.getTotaltaxInclusiveRMB() <= jz.getTotalRMB()) {
                u1 = new BudgetDetail(jz.getFacno(), "", period1, jz.getCenterid(), jz.getPreAccno(), "R", 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO.subtract(BigDecimal.valueOf(j.getTotaltaxInclusiveRMB())));
            } else {
                u1 = new BudgetDetail(jz.getFacno(), "", period1, jz.getCenterid(), jz.getPreAccno(), "R", 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO.subtract(BigDecimal.valueOf(jz.getTotalRMB())));
            }
            budgetDetails.add(u1);

            this.add(budgetDetails);
            this.getEntityManager().flush();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Boolean subtractByOAJZGHD(String psn) {
        String period;
        Date date;
        HZCW033 j = hzcw033Bean.findByPSN(psn);
        String facno = j.getFacno();
        List<BudgetDetail> budgetDetails = new ArrayList<>();

        try {

            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", j.getAppDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);
            System.out.println(period);

            this.setCompany(facno);

            List<HZCW033reDetail> details = hzcw033reDetailBean.findByFSN(j.getFormSerialNumber());

            for (int i = 0; i < details.size(); i++) {

                HZCW033reDetail detail = details.get(i);

                //预算金额更新逻辑
                BudgetDetail u;
                u = new BudgetDetail(facno, "", period, detail.getCenterid(), detail.getBudgetAcc(), "R", i + 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(detail.getTaxInclusive() * (j.getRatio())));
                budgetDetails.add(u);

            }
            //改变借支单科目预扣
            BudgetDetail u1;
            HZCW017 jz = hzcw017Bean.findByPSN(details.get(0).getLoanNo());
            Date date1 = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", jz.getLoanDate())); //借支单日期
            String period1 = BaseLib.formatDate("yyyyMM", date1);
            u1 = new BudgetDetail(jz.getFacno(), "", period1, jz.getCenterid(), jz.getPreAccno(), "R", 1, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO.subtract(BigDecimal.valueOf(j.getTotaltaxInclusiveRMB())));
            budgetDetails.add(u1);

            this.subtract(budgetDetails);
            this.getEntityManager().flush();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
