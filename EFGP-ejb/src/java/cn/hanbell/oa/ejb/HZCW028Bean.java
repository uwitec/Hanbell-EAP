/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.PormyBean;
import cn.hanbell.crm.entity.Pormy;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HZCW028;
import cn.hanbell.oa.entity.HZCW028reDetail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class HZCW028Bean extends SuperEJBForEFGP<HZCW028> {

    @EJB
    private HZCW028reDetailBean hzcw028reDetailBean;
    @EJB
    private PormyBean pormyBean;

    public HZCW028Bean() {
        super(HZCW028.class);
    }

    public Boolean updateCRMPORMY(String psn) {
        try {
            HZCW028 h = this.findByPSN(psn);
            List<HZCW028reDetail> details = hzcw028reDetailBean.findByFSN(h.getFormSerialNumber());

            //表身循环
            for (int i = 0; i < details.size(); i++) {
                HZCW028reDetail detail = details.get(i);
                if ("6617".equals(detail.getBudgetAcc())) {
                    String[] split = detail.getRemark().split(",");
                    for (String s : split) {
                        Pormy p = pormyBean.findByMY002(s);
                        if (p != null && "".equals(p.getMy026())) {
                            p.setMy026(psn);
                            p.setMy025(detail.getFormSerialNumber());
                            pormyBean.update(p);
                        }
                    }
                } else {
                    Pormy p = pormyBean.findByMY002(detail.getRemark());
                    if (p != null && "".equals(p.getMy026())) {
                        p.setMy026(psn);
                        p.setMy025(detail.getFormSerialNumber());
                        pormyBean.update(p);
                    }

                }

            }
            pormyBean.getEntityManager().flush();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(HZCW028Bean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Boolean rollbackCRMPORMY(String psn) {
        try {
            HZCW028 h = this.findByPSN(psn);
            List<HZCW028reDetail> details = hzcw028reDetailBean.findByFSN(h.getFormSerialNumber());

            //表身循环
            for (int i = 0; i < details.size(); i++) {
                HZCW028reDetail detail = details.get(i);
                if ("6617".equals(detail.getBudgetAcc())) {
                    String[] split = detail.getRemark().split(",");
                    for (String s : split) {
                        Pormy p = pormyBean.findByMY002(s);
                        if (p != null && !p.getMy026().isEmpty()) {
                            p.setMy026("");
                            p.setMy025("");
                            pormyBean.update(p);
                        }
                    }
                } else {
                    Pormy p = pormyBean.findByMY002(detail.getRemark());
                    if (p != null && !p.getMy026().isEmpty()) {
                        p.setMy026("");
                        p.setMy025("");
                        pormyBean.update(p);
                    }

                }

            }
            pormyBean.getEntityManager().flush();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(HZCW028Bean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
