/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invdou;
import cn.hanbell.erp.entity.Invdta;
import cn.hanbell.erp.entity.InvdtaPK;
import cn.hanbell.erp.entity.Invhad;
import cn.hanbell.erp.entity.InvhadPK;
import cn.hanbell.erp.entity.Invmas;
import cn.hanbell.erp.entity.Invserno;
import cn.hanbell.erp.entity.InvsernoPK;
import cn.hanbell.oa.ejb.HKFW006Bean;
import cn.hanbell.oa.ejb.HKFW006Inv310Bean;
import cn.hanbell.oa.ejb.HKFW006Inv310DetailBean;
import cn.hanbell.oa.entity.HKFW006;
import cn.hanbell.oa.entity.HKFW006Inv310;
import cn.hanbell.oa.entity.HKFW006Inv310Detail;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1491
 */
@Stateless
@LocalBean
public class InvhadBean extends SuperEJBForERP<Invhad> {

    @EJB
    private HKFW006Inv310Bean hkfw006inv310Bean;
    @EJB
    private HKFW006Bean hkfw006Bean;
    @EJB
    private HKFW006Inv310DetailBean hkfw006inv310DetailBean;
    @EJB
    private InvdouBean invdouBean;
    @EJB
    private InvsernoBean invsernoBean;
    @EJB
    private InvmasBean invmasBean;
    @EJB
    private InvdtaBean invdtaBean;

    public InvhadBean() {
        super(Invhad.class);
    }

    public Boolean initByOAHKFW006(String psn) {
        Date date;
        HKFW006Inv310 h = hkfw006inv310Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }

        try {
            Invhad invhad = new Invhad();
            InvhadPK pk = new InvhadPK();
            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", BaseLib.getDate()));
            String facno = h.getFacno();
            String prono = "1";
            String depno = h.getDepno();
            String trtype = h.getTrtype();
            String trno = GetINV310Trno(facno, depno, trtype, date, true);
            pk.setFacno(facno);
            pk.setProno(prono);
            pk.setTrno(trno);
            invhad.setInvhadPK(pk);
            invhad.setTrtype(trtype);
            invhad.setTrdate(date);
            invhad.setDepno(depno);
            invhad.setIocode('1');
            invhad.setHmark1(h.getHmark1());
            invhad.setHmark2(h.getHmark2());
            invhad.setResno(h.getResno());
            invhad.setFwno(h.getFwno());
            invhad.setKfno(h.getKfno());
            String srcno = h.getProcessSerialNumber().substring(4);
            invhad.setSourceno(srcno);

            HKFW006 hkfw006 = hkfw006Bean.findByPSN(psn);
            invhad.setUserno(hkfw006.getApplyuser());
            invhad.setIndate(date);
            // invhad.setCfmuserno(trno);
            //invhad.setCfmdate(date);
            invhad.setStatus("N".charAt(0));
            invhad.setPrtcnt((short) 0);
            invhad.setAsrsQty(BigDecimal.ZERO);
            invhad.setVdrno("");
            invhad.setKfspdsc("");
            this.setCompany(facno);
            persist(invhad);

            List<HKFW006Inv310Detail> details = hkfw006inv310DetailBean.findByFSN(h.getFormSerialNumber());
            if (details.size() <= 0) {
                throw new NullPointerException();
            }
            //  表身循环
            for (int i = 0; i < details.size(); i++) {
                HKFW006Inv310Detail detail = details.get(i);
                Invdta invdta = new Invdta();
                InvdtaPK invdtaPK = new InvdtaPK();
                invdtaPK.setFacno(facno);
                invdtaPK.setItnbr(detail.getItnbr());
                invdtaPK.setProno("1");
                invdtaPK.setTrno(trno);
                invdtaPK.setTrseq(i + 1);
                invdta.setInvdtaPK(invdtaPK);
                invdta.setTrtype(trtype);
                invmasBean.setCompany(facno);
                System.out.println(detail.getItnbr());
                Invmas m = invmasBean.findByItnbr(detail.getItnbr());
                invdta.setItcls(m.getItcls());
                invdta.setItclscode(m.getItclscode());
                invdta.setTrnqy1(BigDecimal.valueOf(Double.parseDouble(detail.getNum())));
                invdta.setTrnqy2(BigDecimal.ZERO);
                invdta.setTrnqy3(BigDecimal.ZERO);
                invdta.setUnmsr1(m.getUnmsr1());
                invdta.setWareh(detail.getWareh());
                invdta.setIocode('1');
                invdta.setVarnr(detail.getVarnr());
                invdtaBean.setCompany(facno);
                invdtaBean.persist(invdta);
                invdtaBean.getEntityManager().flush();

            }
            h.setPzno(trno);
            hkfw006inv310Bean.update(h);
            HKFW006 fw006 = hkfw006Bean.findByPSN(psn);
            fw006.setPzno(trno);
            hkfw006Bean.update(fw006);
            return true;
        } catch (ParseException | NullPointerException | NumberFormatException ex) {
            Logger.getLogger(InvhadBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private String GetINV310Trno(String facno, String depno, String trtype, Date trdate, boolean updateflag) {
        String ls_autochar;
        Character ls_autono;
        String ls_nofmt;
        String ls_trno;
        String ls_depcode = "";
        String ls_serial = "";
        int ll_max;
        int li_ordno;
        invdouBean.setCompany(facno);
        Invdou invdou = invdouBean.findByTrtype(trtype);
        if (invdou != null) {

            ls_autochar = invdou.getAutochar().toString();
            ls_autono = invdou.getAutoyn();
            ls_nofmt = invdou.getNofmt();
            li_ordno = Integer.parseInt(ls_nofmt.substring(5));//流水号
            ls_trno = this.GetINV310Staticno(facno, ls_depcode, trtype, trdate, ls_nofmt, ls_autochar);
            ll_max = this.GetINV310MaxTrno(facno, trtype, ls_trno);
            if (ll_max == 0) {
                String a = "00000000001";
                ls_serial = ls_trno + a.substring(a.length() - li_ordno);       //右边取li_ordno位
                if (updateflag) {
                    try {
                        Invserno invser = new Invserno();
                        InvsernoPK invserPK = new InvsernoPK();
                        invserPK.setFacno(facno);
                        invserPK.setTrtype(trtype);
                        invserPK.setKeyformat(ls_trno);
                        invser.setInvsernoPK(invserPK);
                        invser.setMaxserno((short) 1);
                        invsernoBean.setCompany(facno);
                        invsernoBean.persist(invser);
                    } catch (Exception ex) {
                        return "";
                    }
                }
            } else {
                ll_max += 1;
                String a = "0000000000" + ll_max;//
                ls_serial = ls_trno + a.substring(a.length() - li_ordno);//右边取li_ordno位
                if (updateflag) {
                    try {
                        invsernoBean.setCompany(facno);
                        Invserno invser = invsernoBean.findByPK(facno, trtype, ls_trno);
                        invser.setMaxserno((short) ll_max);
                        invsernoBean.update(invser);
                    } catch (Exception ex) {
                        return "";
                    }

                }
            }
        }
        return ls_serial;
    }

    private String GetINV310Staticno(String facno, String depcode, String trtype, Date trdate, String ls_nofmt, String autochar) {
        String ls_no;
        String ls_str = "";
        String ls_ch;
        int li_i = 0;
        int li_ordno = 0;
        int li_month = 0;
        if (autochar == null || "0".equals(autochar)) {
            autochar = "";
        }
        ls_no = autochar.trim();
        for (int i = 0; i < 5; i++) {
            ls_ch = ls_nofmt.substring(i, i + 1);
            switch (ls_ch) {
                case "1":
                    ls_no += facno;
                    break;
                case "2":
                    ls_no += trtype;
                    break;
                case "3":
                    ls_no += BaseLib.formatDate("yyyy", trdate);
                    break;
                case "4":
                    ls_no += BaseLib.formatDate("yy", trdate);
                    break;
                case "5":
                    ls_no += BaseLib.formatDate("MM", trdate);
                    break;
                case "6":
                    li_month = Integer.parseInt(BaseLib.formatDate("MM", trdate), 10);
                    if (li_month == 10) {
                        ls_str = "A";
                    }
                    if (li_month == 11) {
                        ls_str = "B";
                    }
                    if (li_month == 12) {
                        ls_str = "C";
                    }
                    if (li_month < 10) {
                        ls_str = BaseLib.formatDate("MM", trdate);
                    }
                    ls_no += ls_str;
                    break;
                case "7":
                    ls_no += BaseLib.formatDate("dd", trdate);
                    break;
                case "9":
                    ls_no += depcode.trim();
                    break;
                default:
                    break;
            }
        }
        return ls_no;
    }

    private int GetINV310MaxTrno(String facno, String trtype, String ls_trno) {
        int retvalue = 0;
        invsernoBean.setCompany(facno);
        Invserno invserno = invsernoBean.findByPK(facno, trtype, ls_trno);

        if (invserno != null) {
            retvalue = invserno.getMaxserno();

        }
        return retvalue;
    }

}
