/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.WARMBBean;
import cn.hanbell.crm.entity.WARMB;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HZJS034;
import cn.hanbell.oa.entity.HZJS034Detail;
import cn.hanbell.util.BaseLib;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class HZJS034DetailBean extends SuperEJBForEFGP<HZJS034Detail> {

    @EJB
    private HZJS034Bean hzjs034Bean;
    @EJB
    private HZJS034DetailBean hzjs034DetailBean;
    @EJB
    private WARMBBean warmbBean;

    public HZJS034DetailBean() {
        super(HZJS034Detail.class);
    }

    public Boolean createCRMWARMBByOAJHSQD(String psn) {
        HZJS034 h = hzjs034Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        List<HZJS034Detail> details = hzjs034DetailBean.findByFSN(h.getFormSerialNumber());
        try {

            //表身循环
            for (int i = 0; i < details.size(); i++) {
                HZJS034Detail detail = details.get(i);
                WARMB m = new WARMB();
                m.setCompany(h.getFacno());
                m.setCreator(h.getEmpl());
                m.setMb001(detail.getItnbr());                                  //设置件号
                m.setMb008(detail.getItcls());                                  //设置品号大类
                m.setMb002(detail.getItdsc());                                  //设置中文品名
                m.setMb003(detail.getSpdsc());                                  //设置中文规格
                m.setMb004(detail.getUnmsr1());                                  //设置单位一
                m.setMb029(detail.getEitdsc());
                m.setMb030(detail.getEspdsc());
                m.setMb010(detail.getMorpcode());                               //设置自制采购码
                m.setMb028("Y");                                                //设置产品序号管理
                m.setMb050("Y");                                                //设置需核销
                m.setMb057(BaseLib.formatDate("yyyyMMdd", BaseLib.getDate()));  //设置生效日期日期
                warmbBean.persist(m);
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(HZJS034Detail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
