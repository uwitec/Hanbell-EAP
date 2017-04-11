package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Secuprg;
import cn.hanbell.erp.entity.SecuprgPK;
import cn.hanbell.oa.ejb.SHBERPMIS226Bean;
import cn.hanbell.oa.ejb.SHBERPMIS226DetailBean;
import cn.hanbell.oa.entity.SHBERPMIS226;
import cn.hanbell.oa.entity.SHBERPMIS226Detail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author
 */
@Stateless
@LocalBean
public class SecuprgBean extends SuperEJBForERP<Secuprg> {

    @EJB
    private SHBERPMIS226Bean sherpmis226Bean;

    @EJB
    private SHBERPMIS226DetailBean sherpmis226detailBean;

    public SecuprgBean() {
        super(Secuprg.class);
    }

    public Boolean initSECUPRG(String psn) {

        String prgno;
        String userno;
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
        SHBERPMIS226 h = sherpmis226Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        List<SHBERPMIS226Detail> details = sherpmis226detailBean.findByFSN(h.getFormSerialNumber());
        this.setCompany(h.getFacno());
        try {

            //表身循环
            for (int i = 0; i < details.size(); i++) {
                SHBERPMIS226Detail detail = details.get(i);
                Secuprg u = new Secuprg();
                prgno = detail.getJtcx();
                userno = h.getApplyuser();
                //Secuprg u = findByPK(prgno,userno);
                SecuprgPK upk = new SecuprgPK();
                upk.setPrgno(prgno);
                upk.setUserno(userno);
                u.setSecuprgPK(upk);
                u.setPuserno("mis");
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
                    u.setPadd('N');
                } else {
                    u.setPadd('Y');
                }
                if ("".equals(hdn_jb2)) {
                    u.setPdelete('N');
                } else {
                    u.setPdelete('Y');
                }
                if ("".equals(hdn_jb3)) {
                    u.setPmodify('N');
                } else {
                    u.setPmodify('Y');
                }
                if ("".equals(hdn_jb4)) {
                    u.setPquery('N');
                } else {
                    u.setPquery('Y');
                }
                if ("".equals(hdn_jb5)) {
                    u.setPcommit('N');
                } else {
                    u.setPcommit('Y');
                }
                if ("".equals(hdn_jb6)) {
                    u.setPcancel('N');
                } else {
                    u.setPcancel('Y');
                }
                if ("".equals(hdn_jb7)) {
                    u.setPprint('N');
                } else {
                    u.setPprint('Y');
                }
                if ("".equals(hdn_jb8)) {
                    u.setRunsig('N');
                } else {
                    u.setRunsig('Y');
                }
                if ("".equals(hdn_jb9)) {
                    u.setPrelation1('N');
                } else {
                    u.setPrelation1('Y');
                }
                if ("".equals(hdn_jb10)) {
                    u.setPrelation2('N');
                } else {
                    u.setPrelation2('Y');
                }
                if ("".equals(hdn_jb11)) {
                    u.setPrelation3('N');
                } else {
                    u.setPrelation3('Y');
                }
                if ("".equals(hdn_jb12)) {
                    u.setPrelation4('N');
                } else {
                    u.setPrelation4('Y');
                }
                if ("".equals(hdn_jb13)) {
                    u.setStepbystep('N');
                } else {
                    u.setStepbystep('Y');
                }
                if ("".equals(hdn_other1)) {
                    u.setPother1('N');
                } else {
                    u.setPother1('N');
                }
                if ("".equals(hdn_other2)) {
                    u.setPother2('N');
                } else {
                    u.setPother2('N');
                }
                if ("".equals(hdn_other3)) {
                    u.setPother3('N');
                } else {
                    u.setPother3('N');
                }
                if ("".equals(hdn_other4)) {
                    u.setPother4('N');
                } else {
                    u.setPother4('N');
                }
                if ("".equals(hdn_other5)) {
                    u.setPother5('N');
                } else {
                    u.setPother5('N');
                }
                if ("".equals(hdn_other6)) {
                    u.setPother6('N');
                } else {
                    u.setPother6('N');
                }
                if ("".equals(hdn_other7)) {
                    u.setPother7('N');
                } else {
                    u.setPother7('N');
                }
                if ("".equals(hdn_other8)) {
                    u.setPother8('N');
                } else {
                    u.setPother8('N');
                }
                if ("".equals(hdn_other9)) {
                    u.setPother9('N');
                } else {
                    u.setPother9('N');
                }
                //this.setCompany(facno);
                this.setCompany("C");
                Secuprg s = this.findByPK(prgno, userno);

                if (s == null) {
                    persist(u);
                    System.out.println("3");
                } else {
                    update(u);
                    System.out.println("4");
                }

            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Secuprg findByPK(String prgno, String userno) {
        Query query = getEntityManager().createNamedQuery("Secuprg.findByPK");
        query.setParameter("prgno", prgno);
        query.setParameter("userno", userno);
        try {
            Object o = query.getSingleResult();
            return (Secuprg) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
