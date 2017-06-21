/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invbal;
import cn.hanbell.erp.entity.Invbat;
import cn.hanbell.erp.entity.Invdou;
import cn.hanbell.erp.entity.Invdta;
import cn.hanbell.erp.entity.InvdtaPK;
import cn.hanbell.erp.entity.Invhad;
import cn.hanbell.erp.entity.InvhadPK;
import cn.hanbell.erp.entity.Invmas;
import cn.hanbell.erp.entity.Invserno;
import cn.hanbell.erp.entity.InvsernoPK;
import cn.hanbell.erp.entity.Invtrn;
import cn.hanbell.oa.ejb.HKFW006Bean;
import cn.hanbell.oa.ejb.HKFW006Inv310Bean;
import cn.hanbell.oa.ejb.HKFW006Inv310DetailBean;
import cn.hanbell.oa.ejb.WARMI05Bean;
import cn.hanbell.oa.entity.HKFW006;
import cn.hanbell.oa.entity.HKFW006Inv310;
import cn.hanbell.oa.entity.HKFW006Inv310Detail;
import cn.hanbell.oa.entity.WARMI05;
import cn.hanbell.oa.entity.WARMI05Detail;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private WARMI05Bean warmi05Bean;

    @EJB
    private InvdouBean invdouBean;
    @EJB
    private InvsernoBean invsernoBean;
    @EJB
    private InvmasBean invmasBean;
    @EJB
    private InvbalBean invbalBean;
    @EJB
    private InvbatBean invbatBean;
    @EJB
    private InvtrnBean invtrnBean;
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
            String trno = getINV310Trno(facno, depno, trtype, date, true);
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
            //表身循环
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
                //System.out.println(detail.getItnbr());
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

    public Boolean initByOAWARMI05(String psn) {

        WARMI05 e = warmi05Bean.findByPSN(psn);
        if (e == null) {
            throw new NullPointerException();
        }
        warmi05Bean.setDetail(e.getFormSerialNumber());
        if (warmi05Bean.getDetailList().isEmpty()) {
            throw new NullPointerException();
        }
        String trtype = null;
        if (e.getTa001().equals("FWLL")) {
            //服务领料
            trtype = "IAF";
        } else if (e.getTa001().equals("FWTL")) {
            //服务退料
            trtype = "IAG";
        }
        if (trtype == null) {
            throw new NullPointerException("单据类别错误，OA需要FWLL或FWTL");
        }
        //判断是否已经抛转
        if ((trtype.equals("IAF") && e.getTa025() != null && !"".equals(e.getTa025())) || (trtype.equals("IAG")) && e.getTa026() != null && !"".equals(e.getTa026())) {
            return true;//返回false会导致流程死循环
        }

        boolean flag = false;
        String facno = e.getTa014();
        String prono = "1";
        String trno = "";
        Date trdate = BaseLib.getDate();
        short trseq = 0;
        //获取ERP库存交易类别
        invdouBean.setCompany(facno);
        Invdou invdou = invdouBean.findByTrtype(trtype);
        if (invdou == null) {
            throw new NullPointerException("单据类别错误，ERP需要IAF或IAG");
        }
        Invbal invbal;
        Invbat invbat;
        Invtrn invtrn;
        List<Invbal> invbalList = new ArrayList();
        List<Invbat> invbatList = new ArrayList();
        List<Invtrn> invtrnList = new ArrayList();

        List<Invdta> addedDetail = new ArrayList();
        HashMap<SuperEJBForERP, List<?>> detailAdded = new HashMap<>();
        detailAdded.put(invdtaBean, addedDetail);
        this.setCompany(facno);
        invmasBean.setCompany(facno);
        invbalBean.setCompany(facno);
        invbatBean.setCompany(facno);
        invtrnBean.setCompany(facno);
        try {
            for (WARMI05Detail d : warmi05Bean.getDetailList()) {
                trseq++;
                Invdta invdta = new Invdta();
                InvdtaPK invdtaPK = new InvdtaPK();
                invdtaPK.setFacno(facno);
                invdtaPK.setItnbr(d.getTb004());
                invdtaPK.setProno(prono);
                //invdtaPK.setTrno();
                invdtaPK.setTrseq(trseq);
                invdta.setInvdtaPK(invdtaPK);
                invdta.setTrtype(trtype);
                //获取品号资料
                Invmas m = invmasBean.findByItnbr(d.getTb004());
                if (m == null) {
                    throw new RuntimeException(d.getTb004() + "ERP中不存在");
                }
                invdta.setItcls(m.getItcls());
                invdta.setItclscode(m.getItclscode());
                if (d.getTb009() == null || "".equals(d.getTb009())) {
                    invdta.setTrnqy1(BigDecimal.valueOf(Double.parseDouble(d.getTb007())));
                } else {
                    invdta.setTrnqy1(BigDecimal.valueOf(Double.parseDouble(d.getTb009())));
                }
                invdta.setTrnqy2(BigDecimal.ZERO);
                invdta.setTrnqy3(BigDecimal.ZERO);
                invdta.setUnmsr1(m.getUnmsr1());
                invdta.setWareh(d.getTb010());
                invdta.setFixnr(d.getTb011());
                invdta.setVarnr(d.getTb019());
                invdta.setIocode(invdou.getIocode());

                if (invdou.getIocode() == '2') {
                    //库存可利用量检查
                    flag = invbalBean.isGreatThenInvbal(facno, prono, invdta.getInvdtaPK().getItnbr(), invdta.getWareh(), invdta.getTrnqy1());
                    if (flag) {
                        throw new RuntimeException(d.getTb004() + "库存可利用量不足(invbal)");
                    } else {
                        invbal = new Invbal(facno, prono, invdta.getInvdtaPK().getItnbr(), invdta.getWareh());
                        invbal.setItcls(invdta.getItcls());
                        invbal.setItclscode(invdta.getItclscode());
                        invbal.setOnhand1(invdta.getTrnqy1());
                        //加入库存更新列表
                        invbalList.add(invbal);
                    }
                    //批号可利用量检查
                    if (m.getInvcls().getNrcode() != '0') {
                        invdta.setDefaultValue();
                        flag = invbatBean.isGreatThenInvbat(facno, prono, invdta.getInvdtaPK().getItnbr(), invdta.getWareh(), invdta.getFixnr(), invdta.getVarnr(), invdta.getTrnqy1());
                        if (flag) {
                            throw new RuntimeException(d.getTb004() + "库存可利用量不足(invbat)");
                        } else {
                            invbat = new Invbat(facno, prono, invdta.getInvdtaPK().getItnbr(), invdta.getWareh(), invdta.getFixnr(), invdta.getVarnr());
                            invbat.setItcls(invdta.getItcls());
                            invbat.setItclscode(invdta.getItclscode());
                            invbat.setOnhand1(invdta.getTrnqy1());
                            //加入库存更新列表
                            invbatList.add(invbat);
                        }
                    }
                }
                //交易单号预设空白，后续重新赋值
                invtrn = new Invtrn(facno, prono, trtype, "", trseq);
                invtrn.setItnbr(invdta.getInvdtaPK().getItnbr());
                invtrn.setTrdate(trdate);
                invtrn.setDepno(e.getTa032());
                invtrn.setHmark1(e.getTa031());
                invtrn.setHmark2(e.getTa030());
                invtrn.setWareh(invdta.getWareh());
                invtrn.setFixnr(invdta.getFixnr());
                invtrn.setVarnr(invdta.getVarnr());
                invtrn.setTrnqy1(invdta.getTrnqy1());
                invtrn.setTrnqy2(invdta.getTrnqy2());
                invtrn.setTrnqy3(invdta.getTrnqy3());
                invtrn.setUnmsr1(m.getUnmsr1());
                invtrn.setIocode(invdou.getIocode());
                invtrn.setUserno(e.getTa012());
                invtrn.setCfmuserno(e.getTa012());
                invtrn.setRescode(e.getTa042());
                invtrn.setSyscode(invdou.getSyscode());
                invtrn.setSourceno(e.getTa001() + e.getTa002());
                invtrn.setItcls(invdta.getItcls());
                invtrn.setItclscode(invdta.getItclscode());
                invtrn.setIndate(trdate);
                invtrn.setCfmdate(trdate);

                //加入库存出入新增列表
                addedDetail.add(invdta);
                //加入交易历史新增列表
                invtrnList.add(invtrn);
            }
            trno = getINV310Trno(facno, "", trtype, trdate, true);
            Invhad invhad = new Invhad(facno, "1", trno);
            invhad.setTrtype(trtype);
            invhad.setTrdate(trdate);
            invhad.setDepno(e.getTa032());//客户
            invhad.setIocode(invdou.getIocode());
            invhad.setHmark1(e.getTa031());//区域
            invhad.setHmark2(e.getTa030());//产品
            invhad.setResno(e.getTa042());
            invhad.setKfno(e.getTa039());
            invhad.setFwno(e.getTa040() + e.getTa041());
            invhad.setSourceno(e.getTa002());
            invhad.setStatus('Y');
            invhad.setUserno(e.getTa012());
            invhad.setIndate(trdate);
            invhad.setCfmuserno(e.getTa012());
            invhad.setCfmdate(trdate);
            //明细列表交易单号赋值
            for (Invdta d : addedDetail) {
                d.getInvdtaPK().setTrno(trno);
            }
            //更新ERP INV310
            this.persist(invhad, detailAdded);
            //更新ERP交易历史
            for (Invtrn t : invtrnList) {
                t.getInvtrnPK().setTrno(trno);
                invtrnBean.persist(t);
            }
            //更新ERP库存数量
            if (invdou.getIocode() == '1') {
                //入库增加库存
                invbalBean.add(invbalList);
                invbatBean.add(invbatList);
            } else if (invdou.getIocode() == '2') {
                //出库减少库存
                invbalBean.subtract(invbalList);
                invbatBean.subtract(invbatList);
            }
            //回写OA记录ERP单号
            if (trtype.equals("IAF")) {
                e.setTa025(trno);
            } else if (trtype.equals("IAG")) {
                e.setTa026(trno);
            }
            warmi05Bean.update(e);

            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvhadBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private String getINV310Trno(String facno, String depno, String trtype, Date trdate, boolean updateflag) {
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
            ls_trno = this.getINV310Staticno(facno, ls_depcode, trtype, trdate, ls_nofmt, ls_autochar);
            ll_max = this.getINV310MaxTrno(facno, trtype, ls_trno);
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

    private String getINV310Staticno(String facno, String depcode, String trtype, Date trdate, String ls_nofmt, String autochar) {
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

    private int getINV310MaxTrno(String facno, String trtype, String ls_trno) {
        int retvalue = 0;
        invsernoBean.setCompany(facno);
        Invserno invserno = invsernoBean.findByPK(facno, trtype, ls_trno);

        if (invserno != null) {
            retvalue = invserno.getMaxserno();

        }
        return retvalue;
    }

}
