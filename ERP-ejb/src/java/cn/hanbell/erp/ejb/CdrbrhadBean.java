/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Cdrbrdta;
import cn.hanbell.erp.entity.CdrbrdtaPK;
import cn.hanbell.erp.entity.Cdrbrhad;
import cn.hanbell.erp.entity.CdrbrhadPK;
import cn.hanbell.erp.entity.Cdrlnhad;
import cn.hanbell.erp.entity.Cdrobdou;
import cn.hanbell.oa.ejb.HKFW006Bean;
import cn.hanbell.oa.ejb.HKFW006Cdrn30Bean;
import cn.hanbell.oa.ejb.HKFW006Cdrn30DetailBean;
import cn.hanbell.oa.entity.HKFW006;
import cn.hanbell.oa.entity.HKFW006Cdrn30;
import cn.hanbell.oa.entity.HKFW006Cdrn30Detail;
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
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class CdrbrhadBean extends SuperEJBForERP<Cdrbrhad> {

    @EJB
    private CdrobdouBean cdrobdouBean;
    @EJB
    private CdrlnhadBean cdrlnhadBean;
    @EJB
    private CdrbrhadBean cdrbrhadBean;
    @EJB
    private CdrbrdtaBean cdrbrdtaBean;
    @EJB
    private HKFW006Cdrn30Bean hkfw006Cdrn30Bean;
    @EJB
    private HKFW006Cdrn30DetailBean hkfw006Cdrn30DetailBean;
    @EJB
    private HKFW006Bean hkfw006Bean;

    public CdrbrhadBean() {
        super(Cdrbrhad.class);
    }

    public Cdrbrhad findByPK(String facno, String brtrno) {
        Query q = this.getEntityManager().createNamedQuery("Cdrbrhad.findByPK");
        q.setParameter("facno", facno);
        q.setParameter("brtrno", brtrno);
        try {
            Object o = q.getSingleResult();
            return (Cdrbrhad) o;
        } catch (Exception ex) {
            return null;
        }
    }

    public Boolean initByOAHKFW006(String psn) {
        try {
            HKFW006Cdrn30 h = hkfw006Cdrn30Bean.findByPSN(psn);
            if (h == null) {
                throw new NullPointerException();
            }
            Date date;
            String facno = h.getFacno();
            String cdrobty = h.getCdrobty();
            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", BaseLib.getDate()));
            String brtrno = getCDRN30Brno(cdrobty, facno, date, "1", Boolean.TRUE);
            Cdrbrhad cdrbrhad = new Cdrbrhad();
            CdrbrhadPK pk = new CdrbrhadPK();
            pk.setFacno(facno);
            pk.setBrtrno(brtrno);
            cdrbrhad.setCdrbrhadPK(pk);
            cdrbrhad.setCdrobtype(cdrobty);
            cdrbrhad.setCusno(h.getCusno());
            cdrbrhad.setBrdate(BaseLib.getDate("yyyy/MM/dd", h.getBrdate()));
            cdrbrhad.setDepno(h.getDepno());
            cdrbrhad.setHmark01(h.getHmark01());
            cdrbrhad.setUserno(h.getUserno());
            cdrbrhad.setStatus('N');
            cdrbrhad.setLnwareh("JCZC");
            cdrbrhad.setPrtcnt((short) 1);
            cdrbrhad.setObjtype(h.getObjtype());
            String srcno = h.getProcessSerialNumber().substring(4);
            cdrbrhad.setHmark03(srcno);
            cdrbrhad.setProno(h.getProno());
            cdrbrhad.setIndate(BaseLib.getDate("yyyy/MM/dd", h.getIndate()));
            cdrbrhad.setHmark02(h.getHmark02());
            this.setCompany(facno);
            persist(cdrbrhad);

            List<HKFW006Cdrn30Detail> details = hkfw006Cdrn30DetailBean.findByFSN(h.getFormSerialNumber());
            if (details.size() <= 0) {
                throw new NullPointerException();
            }
            //  表身循环
            for (int i = 0; i < details.size(); i++) {
                HKFW006Cdrn30Detail detail = details.get(i);
                Cdrbrdta cdrbrdta = new Cdrbrdta();
                CdrbrdtaPK cdrbrdtaPK = new CdrbrdtaPK();
                cdrbrdtaPK.setBrtrno(brtrno);
                cdrbrdtaPK.setFacno(facno);
                cdrbrdtaPK.setTrseq((short) (i + 1));
                cdrbrdta.setCdrbrdtaPK(cdrbrdtaPK);
                cdrbrdta.setLntrno(detail.getBrowno());
                cdrbrdta.setLntrseq(Short.parseShort(detail.getTrseq()));
                cdrbrdta.setItnbr(detail.getItnbr());
                cdrbrdta.setWareh(detail.getWareh());
                cdrbrdta.setVarnr(detail.getVarnr());
                cdrbrdta.setBrpqy1(BigDecimal.valueOf(Double.parseDouble(detail.getBrpqy1())));
                cdrbrdta.setBrdate(cdrbrhad.getBrdate());
                cdrbrdta.setPyhbrdate(BaseLib.getDate("yyyy/MM/dd", detail.getPrebkdate()));
                cdrbrdta.setProno(h.getProno());
                cdrbrdta.setAsrsSta(0);
                cdrbrdta.setDmark2(detail.getDmark2());
                cdrbrdtaBean.setCompany(facno);
                cdrbrdtaBean.persist(cdrbrdta);
            }
            h.setPzno(brtrno);
            this.getEntityManager().flush();
            hkfw006Cdrn30Bean.update(h);
            HKFW006 fw006 = hkfw006Bean.findByPSN(psn);
            fw006.setPzno(brtrno);
            hkfw006Bean.update(fw006);

            return true;
        } catch (NullPointerException | ParseException ex) {
            Logger.getLogger(CdrbrhadBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private String getCDRN30Brno(String a_cdrobtype, String a_facno, Date a_trdate, String a_iocode, Boolean a_isupdate) {
        int li_ordno;
        int li_max = 0;
        String ls_serial = "";
        String ls_trno;
        String ls_maxno;
        String ls_nofmt;
        Character ls_autoyn;
        String ls_autochar;
        cdrobdouBean.setCompany(a_facno);
        Cdrobdou cdrobdou = cdrobdouBean.findByCdrobtype(a_cdrobtype);
        if (cdrobdou != null) {
            ls_autoyn = cdrobdou.getZautoyn();
            ls_autochar = cdrobdou.getZautochar().toString();
            ls_nofmt = cdrobdou.getZnofmt();
            li_ordno = Integer.parseInt(ls_nofmt.substring(5, 6));
            ls_trno = getCDRN30Staticno(a_facno, a_cdrobtype, a_trdate, ls_nofmt, ls_autochar);
            li_max = getCDRN30Maxno(a_facno, ls_trno, a_iocode, li_ordno);
            if (li_max == 0) {
                String a = "00000000001";
                ls_serial = ls_trno + a.substring(a.length() - li_ordno);
            } else {
                li_max += 1;
                String a = "000000000" + li_max;
                ls_serial = ls_trno + a.substring(a.length() - li_ordno);
            }

        }
        return ls_serial;

    }

    private String getCDRN30Staticno(String a_facno, String a_codrobtype, Date a_trdate, String a_nofmt, String a_autochar) {
        int li_ordno = 0;
        int li_month = 0;
        String ls_no;
        String ls_ch = "";
        String ls_curno = "";
        if (a_autochar == null || "0".equals(a_autochar)) {
            a_autochar = "";
        }
        ls_no = a_autochar;
        for (int i = 0; i < 5; i++) {
            ls_curno = a_nofmt.substring(i, i + 1);
            switch (ls_curno) {
                case "1":
                    ls_no += a_facno;
                    break;
                case "2":
                    ls_no += a_codrobtype;
                    break;
                case "3":
                    ls_no += BaseLib.formatDate("yyyy", a_trdate);
                    break;
                case "4":
                    ls_no += BaseLib.formatDate("yy", a_trdate);
                    break;
                case "5":
                    ls_no += BaseLib.formatDate("MM", a_trdate);
                    break;
                case "6":
                    li_month = Integer.parseInt(BaseLib.formatDate("MM", a_trdate), 10);
                    if (li_month < 10) {
                        ls_ch = String.valueOf(li_month);
                    }
                    if (li_month == 10) {
                        ls_ch = "A";
                    }
                    if (li_month == 11) {
                        ls_ch = "B";
                    }
                    if (li_month == 12) {
                        ls_ch = "C";
                    }
                    ls_no += ls_ch;
                    break;
                case "7":
                    ls_no += BaseLib.formatDate("dd", a_trdate);
                    break;
                default:
                    break;
            }
        }
        return ls_no;
    }

    private int getCDRN30Maxno(String a_facno, String a_format, String a_iocode, int a_serlen) {
        String ls_trno = "";
        String ls_maxno = "";
        String ls_format = "";
        String ls_lntrno = "";
        String ls_brtrno = "";
        int li_maxno = 0;
        int li_fmtlen = 0;
        int li_maxlen = 0;
        li_fmtlen = a_format.length();
        a_format += "%";
        cdrlnhadBean.setCompany(a_facno);
        Cdrlnhad cdrlnhad = cdrlnhadBean.findByPK(a_facno, a_format);
        if (cdrlnhad != null) {
            ls_lntrno = cdrlnhad.getCdrlnhadPK().getTrno();
        }
        cdrbrhadBean.setCompany(a_facno);
        Cdrbrhad cdrbrhad = cdrbrhadBean.findByPK(a_facno, a_format);
        if (cdrbrhad != null) {
            ls_brtrno = cdrbrhad.getCdrbrhadPK().getBrtrno();
        }
        if (false == ls_lntrno.isEmpty()) {
            ls_trno = ls_lntrno;
        }

        if (false == ls_brtrno.isEmpty()) {
            ls_trno = ls_brtrno;
        }
        li_maxlen = ls_trno.length();
        if (li_maxlen == 0) {
            return li_maxno;
        }
        li_maxno = Integer.parseInt(ls_trno.substring(ls_trno.length() - (li_maxlen - li_fmtlen)));
        return li_maxno;
    }

}
