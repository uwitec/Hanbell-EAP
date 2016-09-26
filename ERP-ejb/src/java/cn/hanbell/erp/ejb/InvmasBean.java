/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invmas;
import cn.hanbell.oa.ejb.HZJS034Bean;
import cn.hanbell.oa.ejb.HZJS034DetailBean;
import cn.hanbell.oa.ejb.SHBINV140Bean;
import cn.hanbell.oa.ejb.SHBINV140DetailBean;
import cn.hanbell.oa.entity.HZJS034;
import cn.hanbell.oa.entity.HZJS034Detail;
import cn.hanbell.oa.entity.SHBERPINV140Detail;
import cn.hanbell.oa.entity.SHBERPINV140;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.Query;

@Stateless
@LocalBean
public class InvmasBean extends SuperEJBForERP<Invmas> {

    @EJB
    private HZJS034Bean hzjs034Bean;
    @EJB
    private HZJS034DetailBean hzjs034DetailBean;

    @EJB
    private SHBINV140Bean shbinv140Bean;
    @EJB
    private SHBINV140DetailBean shbinv140DetailBean;

    public InvmasBean() {
        super(Invmas.class);
    }

    public Invmas findByItnbr(String itnbr) {
        Query query = getEntityManager().createNamedQuery("Invmas.findByItnbr");
        query.setParameter("itnbr", itnbr);
        try {
            return (Invmas) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public Boolean initByOAJHSQD(String psn) {

        HZJS034 h = hzjs034Bean.findByPSN(psn);
        List<HZJS034Detail> details = hzjs034DetailBean.findByFSN(h.getFormSerialNumber());
        this.setCompany(h.getFacno());
        try {

//  表身循环
            for (int i = 0; i < details.size(); i++) {
                HZJS034Detail detail = details.get(i);
                Invmas m = new Invmas();
                m.setItnbr(detail.getItnbr());       // 设置件号
                m.setItcls(detail.getItcls());       // 设置品号大类  
                m.setItclscode('3');                //
                m.setItdsc(detail.getItdsc());      //设置中文品名
                m.setSpdsc(detail.getSpdsc());      //设置中文规格
                m.setEitdsc(detail.getEitdsc());
                m.setEspdsc(detail.getEitdsc());    //设置英文品名
                m.setEspdsc(detail.getEspdsc());    //设置英文规格
                m.setUnmsr1(detail.getUnmsr1());    //设置单位一
                m.setUnmsr2(detail.getUnmsr2());    //设置单位二
                m.setMorpcode(detail.getMorpcode());
                m.setFvco('F');
                m.setIndate(BaseLib.getDate());

                persist(m);

            }

            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //件号转移申请单
    public Boolean initByOASHBINV140(String psn) {

        SHBERPINV140 h = shbinv140Bean.findByPSN(psn);
        List<SHBERPINV140Detail> details = shbinv140DetailBean.findByFSN(h.getFormSerialNumber());

        try {

            //  表身循环
            for (int i = 0; i < details.size(); i++) {

                SHBERPINV140Detail detail = details.get(i);

                this.setCompany(h.getFacno1());
                Invmas m = findByItnbr(detail.getItnbr());
                m.setItcls(detail.getItcls());                                  //设置品号大类               
                m.setItdsc(detail.getItdsc());                                  //设置中文品名
                m.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                m.setEitdsc(detail.getEitdsc());                                //设置英文品名
                m.setEspdsc(detail.getEspdsc());                                //设置英文规格
                m.setSitdsc(detail.getSitdsc());                                //设置品号简名
                m.setUnmsr1(detail.getUnmsr1());                                //设置单位一
                m.setUnmsr2(detail.getUnmsr2());                                //设置单位二
                m.setMorpcode(detail.getMorpcode());                            //设置自制采购码
                m.setFvco(detail.getFvco().charAt(0));                          //设置换算率属性
                if (detail.getRate2().length() > 0) {
                    m.setRate2(BigDecimal.valueOf(Double.parseDouble(detail.getRate2())));      //设置换算率
                } else {
                    m.setRate2(null);
                }
                m.setItdsc2(detail.getItdsc2());
                m.setSpdsc2(detail.getSpdsc2());
                m.setIndate(BaseLib.getDate());
                m.setUserno(h.getApplyuser());
                this.getEntityManager().detach(m);
                setCompany(h.getFacno2());
                persist(m);
                this.getEntityManager().flush();
            }

            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
