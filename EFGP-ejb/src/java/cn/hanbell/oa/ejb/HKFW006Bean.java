/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.REPTDBean;
import cn.hanbell.crm.entity.REPTD;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKFW006;
import cn.hanbell.oa.entity.HKFW006Detail;
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

}
