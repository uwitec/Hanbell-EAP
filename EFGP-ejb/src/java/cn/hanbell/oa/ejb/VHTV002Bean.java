/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.VHTV001;
import cn.hanbell.oa.entity.VHTV002;
import cn.hanbell.oa.entity.VHTV002reDetail;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class VHTV002Bean extends SuperEJBForEFGP<VHTV002> {

    @EJB
    private VHTV001Bean vhtv001Bean;

    @EJB
    private VHTV002reDetailBean vhtv002redetailBean;

    public VHTV002Bean() {
        super(VHTV002.class);
    }

    public boolean updatevhtv002(String psn) {
        VHTV002 h = findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        Double TotaltaxInclusiveRMB = h.getTotaltaxInclusiveRMB();
        String FSN = h.getFormSerialNumber();
        List<VHTV002reDetail> details = vhtv002redetailBean.findByFSN(FSN);
        VHTV002reDetail detail = details.get(0);
        String LoanNo = detail.getLoanNo();
        VHTV001 VH = vhtv001Bean.findByPSN(LoanNo);
        VH.setArrears(VH.getArrears() - TotaltaxInclusiveRMB);
        vhtv001Bean.update(VH);
        return true;
    }
}
