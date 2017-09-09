/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKJH001;
import cn.hanbell.oa.entity.HKJH001serial;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1587
 */
@Stateless
@LocalBean
public class HKJH001Bean extends SuperEJBForEFGP<HKJH001>{
    @EJB
    private HKJH001serialBean hkjh001serialBean;
    
    public HKJH001Bean(){
    super(HKJH001.class);
    }
    public boolean updatehkjh001(String psn){
        HKJH001 h = findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        String kind3 = h.getKind3();
        String fanco = h.getFacno();
        if ("AQ1".equals(kind3)||"AQ2".equals(kind3)||"AQ3".equals(kind3)){
            kind3 = "AQ";
        }
        HKJH001serial hs = hkjh001serialBean.findByKind(kind3);
        String kind = hs.getKind();
        String no = "";
        int serialno = hs.getSerialno();
        if (serialno < 10){
            no = "000"+serialno;
        }else if (serialno < 100) {
            no = "00"+serialno;
        }else if (serialno < 1000) {
            no = "0"+serialno;
        }else {
            no = ""+serialno;
        }
        no = fanco + kind3 + no ;
        h.setContractno(no);
        this.update(h);
        serialno++;
        hs.setSerialno(serialno);
        hkjh001serialBean.update(hs);
        return true;
    }
}
