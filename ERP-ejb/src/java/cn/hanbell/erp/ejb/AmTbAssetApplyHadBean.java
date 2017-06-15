/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.AmTbAssetApplyDta;
import cn.hanbell.erp.entity.AmTbAssetApplyDtaPK;
import cn.hanbell.erp.entity.AmTbAssetApplyHad;
import cn.hanbell.oa.ejb.HKCW002PBean;
import cn.hanbell.oa.ejb.HKCW002PpurDetailBean;
import cn.hanbell.oa.entity.HKCW002P;
import cn.hanbell.oa.entity.HKCW002PpurDetail;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C1368
 */
@Stateless
@LocalBean
public class AmTbAssetApplyHadBean extends SuperEJBForERP<AmTbAssetApplyHad> {

    @EJB
    private HKCW002PBean hkcw002Bean;

    @EJB
    private HKCW002PpurDetailBean hkcw002purDetailBean;

    @EJB
    private AmTbAssetApplyDtaBean amTbAssetApplyDtaBean;

    @EJB
    private SyncGZBean syncGZBean;
    @EJB
    private SyncJNBean syncJNBean;
    @EJB
    private SyncNJBean syncNJBean;
    @EJB
    private SyncCQBean syncCQBean;
    @EJB
    private SyncSHBBean syncSHBBean;

    public AmTbAssetApplyHadBean() {
        super(AmTbAssetApplyHad.class);
    }

    public AmTbAssetApplyHad findBySheetno(String sheetno) {
        Query query = getEntityManager().createNamedQuery("AmTbAssetApplyHad.findBySheetno");
        query.setParameter("sheetno", sheetno);
        try {
            return (AmTbAssetApplyHad) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public Boolean initByOAZCSQD(String psn) {
        Date date;
        String facno, newsn;

        try {
            HKCW002P oa = hkcw002Bean.findByPSN(psn);
            if (oa == null) {
                throw new NullPointerException();
            }

            //判断ERP中是否已经抛转
            facno = oa.getFacno();
            this.setCompany(facno);
            if (this.findBySheetno(oa.getSerialNumber()) != null) {
                return false;
            }

            List<HKCW002PpurDetail> details = hkcw002purDetailBean.findByFSN(oa.getFormSerialNumber());
            for (HKCW002PpurDetail detail : details) {

            }
            AmTbAssetApplyHad p = new AmTbAssetApplyHad();

            //生成领用单AA单号
            newsn = getFormId(BaseLib.getDate(), "AA" + BaseLib.formatDate("yyyyMMdd", BaseLib.getDate()), null, 5, "am_tb_asset_apply_had", "apply_had_sn");

            p.setApplyHadSn(newsn);
            p.setDepno(oa.getUsingDept());
            p.setApplyPeople(oa.getUserman());
            p.setCreateTime(BaseLib.getDate());
            p.setApplyState(0);
            p.setCreatePeople(oa.getApplyUser());
            p.setFormid("HKCW002P");
            p.setSheetno(oa.getSerialNumber());
            p.setCocode(facno);

            persist(p);
            getEntityManager().flush();

            //表身明细
            for (int i = 0; i < details.size(); i++) {
                HKCW002PpurDetail detail = details.get(i);
                AmTbAssetApplyDta pd = new AmTbAssetApplyDta();
                AmTbAssetApplyDtaPK pdk = new AmTbAssetApplyDtaPK();
                pdk.setTrsep((short) (i + 1));
                pdk.setApplyHadSn(newsn);
                pd.setAmTbAssetApplyDtaPK(pdk);

                pd.setSpdsc(detail.getSpdse());
                if (detail.getApplynum() != null && !detail.getApplynum().equals("")) {
                    pd.setApplyNum(Integer.valueOf(detail.getApplynum()));
                }
                pd.setTypeDtaCode(detail.getMidclasscode());
                pd.setTypeDtaDtaCode(detail.getSmallclasscode());
                pd.setPnCode(detail.getPncode());
                pd.setTypeHadCode(detail.getBigclasscode());
                pd.setBudgetacc(detail.getBudgetacc());
                pd.setDmark1(detail.getDmark1());
                pd.setDmark2(detail.getDmark2());
                pd.setSpdsc(detail.getSpdse());
                if (!"".equals(detail.getApplynum())) {
                    pd.setApplyNum(Integer.parseInt(detail.getApplynum()));
                } else {
                    pd.setApplyNum(0);
                }

                pd.setTypeDtaCode(detail.getMidclasscode());

                if (detail.getPremoney() != null && !detail.getPremoney().equals("")) {
                    pd.setPreprice(BigDecimal.valueOf(detail.getPremoney().charAt(0)));
                }
                amTbAssetApplyDtaBean.setCompany(facno);
                amTbAssetApplyDtaBean.persist(pd);

            }
            oa.setIfturn("已抛转领用");
            return true;
        } catch (NullPointerException | NumberFormatException ex) {
            Logger.getLogger(AmTbAssetApplyHadBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
