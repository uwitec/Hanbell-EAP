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
import cn.hanbell.oa.ejb.HKCW002Bean;
import cn.hanbell.oa.ejb.HKCW002purDetailBean;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002purDetail;
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
    private HKCW002Bean hkcw002Bean;
    
    @EJB
    private HKCW002purDetailBean hkcw002purDetailBean;
    
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
    
    
    public AmTbAssetApplyHad findBySheetno(String sheetno){
        Query query = getEntityManager().createNamedQuery("AmTbAssetApplyHad.findBySheetno");
        query.setParameter("srcno", sheetno);
        try {
            return (AmTbAssetApplyHad) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    
    public Boolean initByOAZCSQD(String psn){
        Date date;
        String facno,newsn;
        
        try{
        HKCW002 oa =hkcw002Bean.findByPSN(psn);
        if (oa == null) {
            throw new NullPointerException();
        }
        
        //判断ERP中是否已经抛转
            this.setCompany(oa.getFacno());
            if (this.findBySheetno(oa.getProcessSerialNumber()) != null) {
                return false;
            }
            
            List<HKCW002purDetail> details = hkcw002purDetailBean.findByFSN(oa.getFormSerialNumber());
            for (HKCW002purDetail detail : details) {
                
            }
        AmTbAssetApplyHad p = new AmTbAssetApplyHad();
        
        //生成领用单AA单号
        newsn = getFormId(BaseLib.getDate(), "AA"+ BaseLib.getDate().toString(), null, 5, "amTbAssetApplyHad", "apply_had_sn");
        p.setApplyHadSn(newsn);
        p.setDepno(oa.getDepno());
        p.setApplyPeople(oa.getUserman());
        p.setCreateTime(oa.getDatetime1());
        p.setApplyState(0);
        p.setCreatePeople(oa.getApplier());
        p.setFormid("HKCW002");
        p.setSheetno(oa.getSerialNumber());
        persist(p);
        
        
        //表身明细
            for (int i = 0; i < details.size(); i++) {
                HKCW002purDetail detail = details.get(i);
                AmTbAssetApplyDta pd = new AmTbAssetApplyDta();
                AmTbAssetApplyDtaPK pdk = new AmTbAssetApplyDtaPK();
                pdk.setTrsep((short) (i + 1));
                pdk.setApplyHadSn(newsn);
                pd.setAmTbAssetApplyDtaPK(pdk);
                pd.setSpdsc(oa.getSpdse());
                pd.setApplyNum(oa.getApplynum());
                pd.setTypeDtaCode(oa.getMidclasscode());
                pd.setTypeDtaDtaCode(oa.getSmallclasscode());
                pd.setPnCode(oa.getPncode());
                pd.setApplyNum(oa.getApplynum());
                pd.setTypeHadCode(oa.getBigclasscode());
                pd.setBudgetacc(oa.getBudgetacc());
                pd.setDmark1(oa.getDmark1());
                pd.setDmark2(oa.getDmark2());
                if(oa.getPremoney()!=null && ! oa.getPremoney().equals("")){
                pd.setPreprice(BigDecimal.valueOf(oa.getPremoney()));
                }
                amTbAssetApplyDtaBean.persist(pd);
                
            }
             return true;
        } catch (Exception ex) {
            Logger.getLogger(AmTbAssetApplyHadBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
