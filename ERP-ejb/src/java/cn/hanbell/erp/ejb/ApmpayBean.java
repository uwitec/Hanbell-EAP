/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Apmpad;
import cn.hanbell.erp.entity.ApmpadPK;
import cn.hanbell.erp.entity.Apmpay;
import cn.hanbell.erp.entity.ApmpayPK;
import cn.hanbell.oa.ejb.BXDDetail01Bean;
import cn.hanbell.oa.ejb.BxdBean;
import cn.hanbell.oa.entity.BXDDetail01;
import cn.hanbell.oa.entity.Bxd;
import cn.hanbell.util.BaseLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ApmpayBean extends SuperEJBForERP<Apmpay> {

    @EJB
    private ApmpadBean apmpadBean;

    @EJB
    private ApmsysBean apmsysBean;

    @EJB
    private BxdBean bxdBean;
    @EJB
    private BXDDetail01Bean bxdDetail01Bean;

    public ApmpayBean() {
        super(Apmpay.class);
    }

    @Override
    public Boolean initByOAPSN(String psn) {
        String facno;
        Bxd b = bxdBean.findByPSN(psn);

        if (b.getBmpa02c().equals("0")) {
            facno = "C";
        } else {
            facno = "G";
        }

        try {

            apmsysBean.setCompany(facno);
            ApmpayPK pk = new ApmpayPK();
            pk.setFacno("C");
            pk.setPaycode('1');
            pk.setPayno(apmsysBean.getFormId(facno, "APM525", BaseLib.getDate(), Boolean.TRUE));

            setCompany(facno);
            Apmpay h = new Apmpay();
            h.setApmpayPK(pk);
            h.setPayda(BaseLib.getDate());
            h.setDepno("13120");
            h.setRkd("MR01");
            h.setSumry("EFGP");
            h.setUsrno("C0160");
            h.setPaystat('0');

            List<BXDDetail01> details = bxdDetail01Bean.findByFSN(b.getFormSerialNumber());
            List<Apmpad> apmpads = new ArrayList<>();
            for (int i = 0; i < details.size(); i++) {
                System.out.println(details.get(i));
                BXDDetail01 detail = details.get(i);

                Apmpad apmpad = new Apmpad();
                ApmpadPK apmpadPK = new ApmpadPK();
                apmpadPK.setFacno(facno);
                apmpadPK.setPaycode('1');
                apmpadPK.setPayno(pk.getPayno());
                apmpadPK.setTrse((short) (i + 1));
                apmpad.setApmpadPK(apmpadPK);

                apmpad.setAccno(detail.getBudgetacc());
                
                

                apmpads.add(apmpad);
            }

            for (BXDDetail01 detail : details) {

            }

            HashMap<SuperEJBForERP, List<?>> detailAdded = new HashMap<>();
            detailAdded.put(apmpadBean, apmpads);
            
            persist(h, detailAdded);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
