/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.REPMIBean;
import cn.hanbell.crm.ejb.REPTDBean;
import cn.hanbell.crm.entity.REPMI;
import cn.hanbell.crm.entity.REPMIPK;
import cn.hanbell.crm.entity.REPTD;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKFW006;
import cn.hanbell.oa.entity.HKFW006Cdrn30;
import cn.hanbell.oa.entity.HKFW006Cdrn30Detail;
import cn.hanbell.oa.entity.HKFW006Detail;
import cn.hanbell.util.BaseLib;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class HKFW006Bean extends SuperEJBForEFGP<HKFW006> {

    @EJB
    private HKFW006Bean hkfw006Bean;
    @EJB
    private HKFW006DetailBean hkfw006DetailBean;
    @EJB
    private REPTDBean reptdBean;
    @EJB
    private REPMIBean repmiBean;
    @EJB
    private HKFW006Cdrn30Bean hkfw006CDRN30Bean;

    private List<HKFW006Detail> detailList;

    public HKFW006Bean() {
        super(HKFW006.class);
    }

    public Boolean updateReptdByOAHKFW006(String psn) {
        try {
            HKFW006 h = hkfw006Bean.findByPSN(psn);
            if (h == null) {
                throw new NullPointerException();
            }
            if ("2".equals(h.getRettype()) && !"".equals(h.getLitnbr().trim())) {
                String td001 = h.getHdTC001();
                String td002 = h.getFwno().split(td001)[1];
                String td003 = h.getHdTD003();
                REPTD td = reptdBean.findByPK(td001, td002, td003);
                td.setTd047("Y");
                reptdBean.update(td);
                return  true ;
            }

            List<HKFW006Detail> details = hkfw006DetailBean.findByFSN(h.getFormSerialNumber());
            if (details.size() > 0) {
                for (int i = 0; i < details.size(); i++) {
                    HKFW006Detail detail = details.get(i);
                    String td001 = h.getHdTC001();
                    String td002 = h.getFwno().split(td001)[1];
                    String td003 = detail.getHdTD003();
                    REPTD td = reptdBean.findByPK(td001, td002, td003);
                    td.setTd047("Y");
                    reptdBean.update(td);
                }
            }

            return true;

        } catch (Exception ex) {

            Logger.getLogger(HKFW006Bean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //更新CRM REPMI
    public Boolean updateREPMIByOAHKFW006(String psn) {
        try {
            HKFW006Cdrn30 h = hkfw006CDRN30Bean.findByPSN(psn);
            if (h == null) {
                throw new NullPointerException();
            }
            List<HKFW006Cdrn30Detail> details = hkfw006CDRN30Bean.getDetailList(h.getFormSerialNumber());
            if (details.size() > 0) {
                for (int i = 0; i < details.size(); i++) {
                    HKFW006Cdrn30Detail detail = details.get(i);
                    if(!detail.getVarnr().equals(detail.getDmark2())){
                        String mi002 = detail.getVarnr();
                        REPMI repmi = repmiBean.findByMI002(mi002);
                        if(repmi == null){
                        } else {
                           REPMIPK repmipk = new REPMIPK();
                           repmipk.setMi001(detail.getItnbr());
                           repmipk.setMi002(mi002);
                           repmi.setREPMIPK(repmipk);
                           String mi003 = "OA反写"+BaseLib.getDate();
                           repmi.setMi003(mi003);
                           repmi.setMi014(h.getCusno());
                           repmi.setMi015(h.getCusname());
                           repmiBean.update(repmi);
                        }
                    }

                }
            }
            return true;

        } catch (Exception ex) {

            Logger.getLogger(HKFW006Bean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void setDetail(Object value) {
        this.detailList = hkfw006DetailBean.findByFSN(value); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the detailList
     */
    public List<HKFW006Detail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<HKFW006Detail> detailList) {
        this.detailList = detailList;
    }
}
