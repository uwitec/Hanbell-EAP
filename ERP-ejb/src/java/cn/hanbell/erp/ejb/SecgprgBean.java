/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Secgprg;
import cn.hanbell.erp.entity.SecgprgPK;
import cn.hanbell.erp.entity.Secgsys;
import cn.hanbell.erp.entity.Secusys;
import cn.hanbell.oa.ejb.SHBERPMIS226DetailBean;
import cn.hanbell.oa.ejb.SHBERPMIS226Bean;
import cn.hanbell.oa.entity.SHBERPMIS226Detail;
import cn.hanbell.oa.entity.SHBERPMIS226;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C1587
 */
@Stateless
@LocalBean
public class SecgprgBean extends SuperEJBForERP<Secgprg> {

    @EJB
    private SHBERPMIS226Bean sherpmis226Bean;

    @EJB
    private SHBERPMIS226DetailBean sherpmis226detailBean;
    @EJB
    private SecuprgBean secuprgBean;
    @EJB
    private SecgsysBean secgsysBean;
    @EJB
    private SecusysBean secusysBean;

    public SecgprgBean() {
        super(Secgprg.class);
    }

    public Boolean initSECGPRG(String psn) {

        SHBERPMIS226 h = sherpmis226Bean.findByPSN(psn);
        Secgprg g = new Secgprg();
        Secgprg gc = new Secgprg();
        String hdn_jb1;
        String hdn_jb2;
        String hdn_jb3;
        String hdn_jb4;
        String hdn_jb5;
        String hdn_jb6;
        String hdn_jb7;
        String hdn_jb8;
        String hdn_jb9;
        String hdn_jb10;
        String hdn_jb11;
        String hdn_jb12;
        String hdn_jb13;
        String hdn_other1;
        String hdn_other2;
        String hdn_other3;
        String hdn_other4;
        String hdn_other5;
        String hdn_other6;
        String hdn_other7;
        String hdn_other8;
        String hdn_other9;
        if (h == null) {
            throw new NullPointerException();
        }
        List<SHBERPMIS226Detail> details = sherpmis226detailBean.findByFSN(h.getFormSerialNumber());
        this.setCompany(h.getFacno());
        try {
            //表身循环
            for (int i = 0; i < details.size(); i++) {
                SHBERPMIS226Detail detail = details.get(i);
                String prgno, sysno, groupno, gtype,gctype;
                SecgprgPK gpk = new SecgprgPK();
                SecgprgPK gcpk = new SecgprgPK();
                prgno = detail.getJtcx();
                sysno = detail.getMk();
                groupno = h.getApplyuser();
                gtype = "U";
                gctype = "Z";
                gpk.setPrgno(prgno);
                gcpk.setPrgno(prgno);
                gpk.setSysno(sysno);
                gpk.setGroupno(groupno);
                gpk.setGtype(gtype);
                gcpk.setSysno(sysno);
                gcpk.setGroupno(groupno);
                gcpk.setGtype(gctype);
                g.setSecgprgPK(gpk);
                gc.setSecgprgPK(gcpk);
                hdn_jb1 = detail.getHdnJb1();
                hdn_jb2 = detail.getHdnJb2();
                hdn_jb3 = detail.getHdnJb3();
                hdn_jb4 = detail.getHdnJb4();
                hdn_jb5 = detail.getHdnJb5();
                hdn_jb6 = detail.getHdnJb6();
                hdn_jb7 = detail.getHdnJb7();
                hdn_jb8 = detail.getHdnJb8();
                hdn_jb9 = detail.getHdnJb9();
                hdn_jb10 = detail.getHdnJb10();
                hdn_jb11 = detail.getHdnJb11();
                hdn_jb12 = detail.getHdnJb12();
                hdn_jb13 = detail.getHdnJb13();
                hdn_other1 = detail.getHdnOther1();
                hdn_other2 = detail.getHdnOther2();
                hdn_other3 = detail.getHdnOther3();
                hdn_other4 = detail.getHdnOther4();
                hdn_other5 = detail.getHdnOther5();
                hdn_other6 = detail.getHdnOther6();
                hdn_other7 = detail.getHdnOther7();
                hdn_other8 = detail.getHdnOther8();
                hdn_other9 = detail.getHdnOther9();
                if ("".equals(hdn_jb1)) {
                    g.setPadd('N');
                    gc.setPadd('N');
                } else {
                    g.setPadd('Y');
                    gc.setPadd('Y');
                }
                if ("".equals(hdn_jb2)) {
                    g.setPdelete('N');
                    gc.setPdelete('N');
                } else {
                    g.setPdelete('Y');
                    gc.setPdelete('Y');
                }
                if ("".equals(hdn_jb3)) {
                    g.setPmodify('N');
                    gc.setPmodify('N');
                } else {
                    g.setPmodify('Y');
                    gc.setPmodify('Y');
                }
                if ("".equals(hdn_jb4)) {
                    g.setPquery('N');
                    gc.setPquery('N');
                } else {
                    g.setPquery('Y');
                    gc.setPquery('Y');
                }
                if ("".equals(hdn_jb5)) {
                    g.setPcommit('N');
                    gc.setPcommit('N');
                } else {
                    g.setPcommit('Y');
                    gc.setPcommit('Y');
                }
                if ("".equals(hdn_jb6)) {
                    g.setPcancel('N');
                    gc.setPcancel('N');
                } else {
                    g.setPcancel('Y');
                    gc.setPcancel('Y');
                }
                if ("".equals(hdn_jb7)) {
                    g.setPprint('N');
                    gc.setPprint('N');
                } else {
                    g.setPprint('Y');
                    gc.setPprint('Y');
                }
                if ("".equals(hdn_jb8)) {
                    g.setRunsig('N');
                    gc.setRunsig('N');
                } else {
                    g.setRunsig('Y');
                    gc.setRunsig('Y');
                }
                if ("".equals(hdn_jb9)) {
                    g.setPrelation1('N');
                    gc.setPrelation1('N');
                } else {
                    g.setPrelation1('Y');
                    gc.setPrelation1('Y');
                }
                if ("".equals(hdn_jb10)) {
                    g.setPrelation2('N');
                    gc.setPrelation2('N');
                } else {
                    g.setPrelation2('Y');
                    gc.setPrelation2('Y');
                }
                if ("".equals(hdn_jb11)) {
                    g.setPrelation3('N');
                    gc.setPrelation3('N');
                } else {
                    g.setPrelation3('Y');
                    gc.setPrelation3('Y');
                }
                if ("".equals(hdn_jb12)) {
                    g.setPrelation4('N');
                    gc.setPrelation4('N');
                } else {
                    g.setPrelation4('Y');
                    gc.setPrelation4('Y');
                }
                if ("".equals(hdn_jb13)) {
                    g.setStepbystep('N');
                    gc.setStepbystep('N');
                } else {
                    g.setStepbystep('Y');
                    gc.setStepbystep('Y');
                }
                if ("".equals(hdn_other1)) {
                    g.setPother1('N');
                    gc.setPother1('N');
                } else {
                    g.setPother1('Y');
                    gc.setPother1('Y');
                }
                if ("".equals(hdn_other2)) {
                    g.setPother2('N');
                    gc.setPother2('N');
                } else {
                    g.setPother2('Y');
                    gc.setPother2('Y');
                }
                if ("".equals(hdn_other3)) {
                    g.setPother3('N');
                    gc.setPother3('N');
                } else {
                    g.setPother3('Y');
                    gc.setPother3('Y');
                }
                if ("".equals(hdn_other4)) {
                    g.setPother4('N');
                    gc.setPother4('N');
                } else {
                    g.setPother4('Y');
                    gc.setPother4('Y');
                }
                if ("".equals(hdn_other5)) {
                    g.setPother5('N');
                    gc.setPother5('N');
                } else {
                    g.setPother5('Y');
                    gc.setPother5('Y');
                }
                if ("".equals(hdn_other6)) {
                    g.setPother6('N');
                    gc.setPother6('N');
                } else {
                    g.setPother6('Y');
                    gc.setPother6('Y');
                }
                if ("".equals(hdn_other7)) {
                    g.setPother7('N');
                    gc.setPother7('N');
                } else {
                    g.setPother7('Y');
                    gc.setPother7('Y');
                }
                if ("".equals(hdn_other8)) {
                    g.setPother8('N');
                    gc.setPother8('N');
                } else {
                    g.setPother8('Y');
                    gc.setPother8('Y');
                }
                if ("".equals(hdn_other9)) {
                    g.setPother9('N');
                    gc.setPother9('N');
                } else {
                    g.setPother9('Y');
                    gc.setPother9('Y');
                }
                Secgprg s = this.findByPK(prgno, sysno, groupno, gtype);
                secgsysBean.setCompany(h.getFacno());
                Secgsys k = secgsysBean.findByPK(sysno, groupno, gtype);
                if (k == null) {
                    Secgsys secgsys = new Secgsys(sysno, groupno, gtype);
                    secgsysBean.persist(secgsys);
                }
                secusysBean.setCompany(h.getFacno());
                Secusys uk = secusysBean.findByPK(sysno, groupno);
                if (uk == null) {
                    Secusys secusys;
                    secusys = new Secusys(sysno, groupno);
                    secusysBean.persist(secusys);
                }
                if (s == null) {
                    persist(g);
                    persist(gc);
                    this.getEntityManager().flush();
                } else {
                    update(g);
                    update(gc);
                }

            }
            Boolean initSECUPRG;
            secuprgBean.setCompany(h.getFacno());
            initSECUPRG = secuprgBean.initSECUPRG(psn);

            //initsecgsys = secgsysBean.initSecgsys(psn);
            if (initSECUPRG == true) {
                return true;

            } else {
                return false;
            }
            //return initsecgsys==true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Secgprg findByPK(String prgno, String sysno, String groupno, String gtype) {
        Query query = getEntityManager().createNamedQuery("Secgprg.findByPK");
        query.setParameter("prgno", prgno);
        query.setParameter("sysno", sysno);
        query.setParameter("groupno", groupno);
        query.setParameter("gtype", gtype);
        try {
            Object o = query.getSingleResult();
            return (Secgprg) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
