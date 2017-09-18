/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.crm.ejb.WARMBBean;
import cn.hanbell.crm.entity.WARMB;
import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invcls;
import cn.hanbell.erp.entity.Invmas;
import cn.hanbell.erp.entity.Scminvmas;
import cn.hanbell.oa.ejb.HKJS001Bean;
import cn.hanbell.oa.ejb.HKJS001DetailBean;
import cn.hanbell.oa.ejb.HZJS034Bean;
import cn.hanbell.oa.ejb.HZJS034DetailBean;
import cn.hanbell.oa.ejb.SHBERPPURX141Bean;
import cn.hanbell.oa.ejb.SHBINV140Bean;
import cn.hanbell.oa.ejb.SHBINV140DetailBean;
import cn.hanbell.oa.ejb.SHBINV146Bean;
import cn.hanbell.oa.ejb.SHBINV146DetailBean;
import cn.hanbell.oa.entity.HKJS001;
import cn.hanbell.oa.entity.HKJS001Detail;
import cn.hanbell.oa.entity.HZJS034;
import cn.hanbell.oa.entity.HZJS034Detail;
import cn.hanbell.oa.entity.SHBERPINV140Detail;
import cn.hanbell.oa.entity.SHBERPINV140;
import cn.hanbell.oa.entity.SHBERPINV146;
import cn.hanbell.oa.entity.SHBERPINV146Detail;
import cn.hanbell.oa.entity.SHBERPPURX141;
import cn.hanbell.oa.entity.SHBERPPURX141Detail;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.Query;

@Stateless
@LocalBean
public class InvmasBean extends SuperEJBForERP<Invmas> {

    @EJB
    private HKJS001Bean hkjs001Bean;
    @EJB
    private HKJS001DetailBean hkjs001DetailBean;
    @EJB
    private HZJS034Bean hzjs034Bean;
    @EJB
    private HZJS034DetailBean hzjs034DetailBean;
    @EJB
    private SHBERPPURX141Bean shberppurx141Bean;
    @EJB
    private SHBINV140Bean shbinv140Bean;
    @EJB
    private SHBINV140DetailBean shbinv140DetailBean;
    @EJB
    private SHBINV146Bean shbinv146Bean;
    @EJB
    private SHBINV146DetailBean shbinv146DetailBean;
    @EJB
    private InvclsBean invclsBean;
    @EJB
    private ScminvmasBean scminvmasBean;
    @EJB
    private SyncGZBean syncGZBean;
    @EJB
    private SyncJNBean syncJNBean;
    @EJB
    private SyncNJBean syncNJBean;
    @EJB
    private SyncCQBean syncCQBean;
    @EJB
    private WARMBBean warmbBean;

    public InvmasBean() {
        super(Invmas.class);
    }

    public Invmas findByItnbr(String itnbr) {
        Query query = getEntityManager().createNamedQuery("Invmas.findByItnbr");
        query.setParameter("itnbr", itnbr);
        try {
            return (Invmas) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public Boolean initByOAJHSQD(String psn) {

        HZJS034 h = hzjs034Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        List<HZJS034Detail> details = hzjs034DetailBean.findByFSN(h.getFormSerialNumber());
        this.setCompany(h.getFacno());
        try {

            //表身循环
            for (int i = 0; i < details.size(); i++) {
                HZJS034Detail detail = details.get(i);
                if (null == this.findByItnbr(detail.getItnbr())) {
                    Invmas m = new Invmas();
                    m.setItnbr(detail.getItnbr());                                  //设置件号
                    m.setItcls(detail.getItcls());                                  //设置品号大类
                    m.setItclscode(detail.getItclscode().charAt(0));                //设置品号归类
                    m.setItdsc(detail.getItdsc());                                  //设置中文品名
                    m.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                    m.setEitdsc(detail.getEitdsc());                                //设置英文品名
                    m.setEspdsc(detail.getEspdsc());                                //设置英文规格
                    m.setUnmsr1(detail.getUnmsr1());                                //设置单位一
                    m.setUnmsr2(detail.getUnmsr2());                                //设置单位二
                    m.setUnmsr1e(detail.getUnmsr1e());                              //设置数量单位一（英文）
                    m.setFvco('F');                                                 //设置固定变动区分码
                    if ("".equals(detail.getUnmsr2()) || "N".equals(detail.getIsDUnit())) {
                        m.setJudco("11111");                                        //设置数量单位控制码
                    } else {
                        m.setJudco("41111");
                        m.setRate2(BigDecimal.valueOf(Double.parseDouble(detail.getRate2())));       //设置换算率
                    }
                    m.setPocode(' ');
                    m.setMorpcode(detail.getMorpcode());                            //设置自制采购码
                    m.setGroup1(detail.getGroup1());                                //设置分类码（一）
                    m.setGroup2(detail.getGroup2());                                //设置分类码（二）
                    m.setGroup3(detail.getGroup3());
                    m.setGroup4(detail.getGroup4());
                    m.setGroup5(detail.getGroup5());
                    m.setGroup6(detail.getGroup6());
                    m.setGroup7(detail.getGroup7());
                    m.setGroup8(detail.getGroup8());
                    m.setGroup9(detail.getGroup9());
                    m.setGroup10(detail.getGroup10());
                    m.setGroup11(detail.getGroup11());
                    m.setGroup12(detail.getGroup12());
                    m.setDirrvyn('N');                                              //设置是否为直接验收
                    m.setIoyn('N');                                                 //是否验收即出库
                    m.setPurtrtype(detail.getPurtrtype());                          //设置验收类别
                    m.setLowlevel((short) 0);
                    m.setExclcode('0');
                    m.setUserno(h.getEmpl());                                       //设置申请人
                    m.setIndate(BaseLib.getDate());
                    m.setLowlevelcst((short) 0);                                    //设置低阶码
                    m.setJityn(detail.getJityn().charAt(0));                        //即时到货管制
                    m.setGenre1(detail.getHdgenre1());                              //设置产品别
                    m.setDwf(BigDecimal.ZERO);
                    m.setDwg(BigDecimal.ZERO);
                    m.setDwl(BigDecimal.ZERO);
                    m.setDwt(BigDecimal.ZERO);

                    persist(m);
                    this.getEntityManager().flush();
                    if (h.getFacno().equals("C")) {
                        Scminvmas scm = new Scminvmas();
                        scm.setItnbr(detail.getItnbr());
                        scm.setItdsc(detail.getItdsc());
                        scm.setProducttype(detail.getProducttype());
                        scm.setLevel1(detail.getLevel1());
                        scm.setLevel2(detail.getLevel2());
                        scm.setTracetype(detail.getTracetype());
                        scm.setLotid(detail.getLotid());
                        scm.setCompid(detail.getCompid());
                        scm.setLno(detail.getLno());
                        scm.setWno(detail.getWno());
                        scm.setGetseq(detail.getGetseq());
                        scm.setPrinttype(detail.getPrinttype());
                        scm.setQcdata(detail.getQcdata());
                        scm.setQcdatanum(detail.getQcdatanum());
                        scm.setAsrstype(detail.getAsrstype());
                        scm.setSelfprint(detail.getSelfprint());
                        scm.setTransflag("N");
                        scm.setTranstime(BaseLib.getDate().toString());
                        scm.setDeydetyn("N");
                        scminvmasBean.setCompany(h.getFacno());
                        scminvmasBean.persist(scm);
                    }
                    if (h.getFacno().equals("C")) {
                        invclsBean.setCompany(h.getFacno());
                        Invcls c = invclsBean.findByItcls(detail.getItcls());
                        if (c.getNrcode().equals('0')) {
                            m.setDirrvyn('Y');
                        }
                        syncNJBean.persist(m, null);
                        syncNJBean.getEntityManager().flush();

                        syncGZBean.persist(m, null);
                        syncGZBean.getEntityManager().flush();

                        syncJNBean.persist(m, null);
                        syncJNBean.getEntityManager().flush();

                        syncCQBean.persist(m, null);
                        syncCQBean.getEntityManager().flush();
                    }
                }

            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    //件号转移申请单
    public Boolean initByOASHBINV140(String psn) {

        SHBERPINV140 h = shbinv140Bean.findByPSN(psn);
        List<SHBERPINV140Detail> details = shbinv140DetailBean.findByFSN(h.getFormSerialNumber());

        try {

            //表身循环
            for (int i = 0; i < details.size(); i++) {

                SHBERPINV140Detail detail = details.get(i);
                Invmas m;
                setCompany(h.getFacno1());
                //处理THB转SHB时中间加了一码逻辑
                if (h.getFacno1().equals("A")) {
                    m = findByItnbr(detail.getBitnbr());
                    m.setItnbr(detail.getItnbr());
                    m.setEitdsc("");
                    m.setEspdsc("");
                    m.setPocode(' ');
                    m.setJityn('N');
                    m.setNEcnnewitnbr("");
                    m.setNEcnno("");
                } else {
                    m = findByItnbr(detail.getItnbr());
                }
                if (m == null) {
                    throw new RuntimeException("ERP中找不到" + detail.getBitnbr());
                }
                m.setItcls(detail.getItcls());                                  //设置品号大类
                m.setItdsc(detail.getItdsc());                                  //设置中文品名
                m.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                m.setEitdsc(detail.getEitdsc());                                //设置英文品名
                m.setEspdsc(detail.getEspdsc());                                //设置英文规格
                m.setSitdsc(detail.getSitdsc());                                //设置品号简名
                m.setUnmsr1(detail.getUnmsr1());                                //设置单位一
                m.setUnmsr2(detail.getUnmsr2());                                //设置单位二
                m.setMorpcode(detail.getMorpcode());                            //设置自制采购码
                m.setFvco(detail.getFvco().charAt(0));                          //设置换算率属性
                if (detail.getRate2().length() > 0) {
                    m.setRate2(BigDecimal.valueOf(Double.parseDouble(detail.getRate2())));      //设置换算率
                } else {
                    m.setRate2(null);
                }
                m.setItdsc2(detail.getItdsc2());
                m.setSpdsc2(detail.getSpdsc2());
                m.setIndate(BaseLib.getDate());
                m.setUserno(h.getApplyuser());
                this.getEntityManager().detach(m);
                invclsBean.setCompany(h.getFacno2());
                Invcls c = invclsBean.findByItcls(detail.getItcls());
                m.setInvcls(c);
                m.setItclscode(c.getItclscode());

                setCompany(h.getFacno2());
                persist(m);
                this.getEntityManager().flush();

                //同步MES资料
                if (h.getFacno2().equals("C") || h.getFacno2().equals("K")) {
                    Scminvmas scm = new Scminvmas();
                    scm.setItnbr(detail.getItnbr());
                    scm.setItdsc(detail.getItdsc());
                    scm.setProducttype(detail.getProducttype());
                    scm.setLevel1(detail.getLevel1());
                    scm.setLevel2(detail.getLevel2());
                    scm.setTracetype(detail.getTracetype());
                    scm.setLotid(detail.getLotid());
                    scm.setCompid(detail.getCompid());
                    scm.setLno(detail.getLno());
                    scm.setWno(detail.getWno());
                    scm.setGetseq(detail.getGetseq());
                    scm.setPrinttype(detail.getPrinttype());
                    scm.setQcdata(detail.getQcdata());
                    scm.setQcdatanum(detail.getQcdatanum());
                    scm.setAsrstype(detail.getAsrstype());
                    scm.setSelfprint(detail.getSelfprint());
                    scm.setTransflag("N");
                    scm.setTranstime(BaseLib.getDate().toString());
                    scm.setDeydetyn("N");
                    scminvmasBean.setCompany(h.getFacno2());
                    scminvmasBean.persist(scm);
                }

                //同步分公司
                if (h.getFacno2().equals("C")) {
                    if (c.getNrcode().equals('0')) {
                        m.setDirrvyn('Y');
                    }
                    syncNJBean.persist(m, null);
                    syncNJBean.getEntityManager().flush();

                    syncGZBean.persist(m, null);
                    syncGZBean.getEntityManager().flush();

                    syncJNBean.persist(m, null);
                    syncJNBean.getEntityManager().flush();

                    syncCQBean.persist(m, null);
                    syncCQBean.getEntityManager().flush();
                }
            }

            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //件号名称规格修改单
    public Boolean updateByOASHBINV146(String psn) {

        SHBERPINV146 h = shbinv146Bean.findByPSN(psn);
        List<SHBERPINV146Detail> details = shbinv146DetailBean.findByFSN(h.getFormSerialNumber());

        try {

            //表身循环
            for (int i = 0; i < details.size(); i++) {

                SHBERPINV146Detail detail = details.get(i);

                this.setCompany(h.getFacno());
                Invmas m = findByItnbr(detail.getItnbr());
                //m.setItcls(detail.getItcls());                                //设置品号大类
                m.setItdsc(detail.getItdsc());                                  //设置中文品名
                m.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                m.setEitdsc(detail.getEitdsc());                                //设置英文品名
                m.setEspdsc(detail.getEspdsc());                                //设置英文规格
                m.setSitdsc(detail.getSitdsc());                                //设置品号简名
                m.setItdsc2(detail.getItdsc2());
                m.setSpdsc2(detail.getSpdsc2());
                m.setIndate(BaseLib.getDate());
                m.setUserno(h.getApplyuser());
                update(m);
                this.getEntityManager().flush();

                if (h.getFacno().equals("C")) {
                    this.setCompany("G");
                    if (this.findByItnbr(detail.getItnbr()) != null) {
                        Invmas gm = this.findByItnbr(detail.getItnbr());
                        gm.setItdsc(detail.getItdsc());                                  //设置中文品名
                        gm.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                        gm.setEitdsc(detail.getEitdsc());                                //设置英文品名
                        gm.setEspdsc(detail.getEspdsc());                                //设置英文规格
                        gm.setSitdsc(detail.getSitdsc());                                //设置品号简名
                        gm.setItdsc2(detail.getItdsc2());
                        gm.setSpdsc2(detail.getSpdsc2());
                        gm.setIndate(BaseLib.getDate());
                        gm.setUserno(h.getApplyuser());
                        update(gm);
                        this.getEntityManager().flush();
                    }

                    this.setCompany("J");
                    if (this.findByItnbr(detail.getItnbr()) != null) {
                        Invmas jm = this.findByItnbr(detail.getItnbr());
                        jm.setItdsc(detail.getItdsc());                                  //设置中文品名
                        jm.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                        jm.setEitdsc(detail.getEitdsc());                                //设置英文品名
                        jm.setEspdsc(detail.getEspdsc());                                //设置英文规格
                        jm.setSitdsc(detail.getSitdsc());                                //设置品号简名
                        jm.setItdsc2(detail.getItdsc2());
                        jm.setSpdsc2(detail.getSpdsc2());
                        jm.setIndate(BaseLib.getDate());
                        jm.setUserno(h.getApplyuser());
                        update(jm);
                        this.getEntityManager().flush();
                    }

                    this.setCompany("N");
                    if (this.findByItnbr(detail.getItnbr()) != null) {
                        Invmas nm = this.findByItnbr(detail.getItnbr());
                        nm.setItdsc(detail.getItdsc());                                  //设置中文品名
                        nm.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                        nm.setEitdsc(detail.getEitdsc());                                //设置英文品名
                        nm.setEspdsc(detail.getEspdsc());                                //设置英文规格
                        nm.setSitdsc(detail.getSitdsc());                                //设置品号简名
                        nm.setItdsc2(detail.getItdsc2());
                        nm.setSpdsc2(detail.getSpdsc2());
                        nm.setIndate(BaseLib.getDate());
                        nm.setUserno(h.getApplyuser());
                        update(nm);
                        this.getEntityManager().flush();
                    }

                    this.setCompany("C4");
                    if (this.findByItnbr(detail.getItnbr()) != null) {
                        Invmas e = this.findByItnbr(detail.getItnbr());
                        e.setItdsc(detail.getItdsc());                                  //设置中文品名
                        e.setSpdsc(detail.getSpdsc());                                  //设置中文规格
                        e.setEitdsc(detail.getEitdsc());                                //设置英文品名
                        e.setEspdsc(detail.getEspdsc());                                //设置英文规格
                        e.setSitdsc(detail.getSitdsc());                                //设置品号简名
                        e.setItdsc2(detail.getItdsc2());
                        e.setSpdsc2(detail.getSpdsc2());
                        e.setIndate(BaseLib.getDate());
                        e.setUserno(h.getApplyuser());
                        update(e);
                        this.getEntityManager().flush();
                    }

                    //更新CRM件号2017/7/11
                    WARMB warmb = warmbBean.findByMB001(detail.getItnbr());
                    if (warmb != null) {
                        warmb.setMb002(detail.getItdsc());
                        warmb.setMb003(detail.getSpdsc());
                        warmb.setMb029(detail.getEitdsc());
                        warmb.setMb030(detail.getEspdsc());
                        warmbBean.update(warmb);
                    }
                }
                //更新MES件号 2017/7/11
                if (h.getFacno().equals("C") || h.getFacno().equals("K")) {
                    scminvmasBean.setCompany(h.getFacno());
                    Scminvmas scminvmas = scminvmasBean.findByItnbr(detail.getItnbr());
                    if (scminvmas != null) {
                        scminvmas.setItdsc(detail.getItdsc());
                        scminvmas.setTransflag("N");
                        scminvmasBean.update(scminvmas);
                    }

                }
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //工程变更通知单
    public Boolean updateByOAHKJS001(String psn) {

        HKJS001 h = hkjs001Bean.findByPSN(psn);
        if (h == null) {
            return false;
        }
        //HKJS001上没有公司别，需要增加，此处先固定
        String facno = "C";
        Invmas item;
        List<Invmas> invmasList = new ArrayList<>();
        List<HKJS001Detail> details = hkjs001DetailBean.findByFSN(h.getFormSerialNumber());
        if (details == null || details.isEmpty()) {
            return true;
        }
        try {
            this.setCompany(facno);
            for (int i = 0; i < details.size(); i++) {
                HKJS001Detail d = details.get(i);
                item = findByItnbr(d.getBjh());
                if (item != null) {
                    item.setItdsc("#" + item.getItdsc());
                    item.setStopyn("AAAAAAAAAAAAA");
                    item.setNStopyn("N");
                    item.setNEcnnewitnbr(d.getAjh());
                    item.setNEcnno(h.getBgbh());
                    invmasList.add(item);
                }
            }
            if (!invmasList.isEmpty()) {
                update(invmasList);
                this.getEntityManager().flush();
                if (facno.equals("C")) {
                    this.setCompany("G");
                    update(invmasList);
                    this.getEntityManager().flush();
                    this.setCompany("J");
                    update(invmasList);
                    this.getEntityManager().flush();
                    this.setCompany("N");
                    update(invmasList);
                    this.getEntityManager().flush();
                    this.setCompany("C4");
                    update(invmasList);
                    this.getEntityManager().flush();
                }
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    //标准成本价格金额
    public Boolean updateByOASHBERPPURX141(String psn) {
        SHBERPPURX141 h = shberppurx141Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }

        try {
            List<SHBERPPURX141Detail> details = shberppurx141Bean.getDetailList(h.getFormSerialNumber());
            //表身循环
            for (int i = 0; i < details.size(); i++) {

                SHBERPPURX141Detail detail = details.get(i);

                this.setCompany(h.getFacno());
                Invmas m = findByItnbr(detail.getItnbr());
                if (m == null) {
                    throw new NullPointerException();
                }
                if ("".equals(detail.getCost())) {
                    m.setCost(null);
                } else {
                    m.setCost(BigDecimal.valueOf(Double.parseDouble(detail.getCost())));
                }
                if ("".equals(detail.getAsscost())) {
                    m.setAsscost(null);
                } else {
                    m.setAsscost(BigDecimal.valueOf(Double.parseDouble(detail.getAsscost())));
                }
                update(m);
            }
            return true;
        } catch (NullPointerException | NumberFormatException ex) {
            Logger.getLogger(InvmasBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
