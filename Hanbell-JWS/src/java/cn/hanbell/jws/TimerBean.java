/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package cn.hanbell.jws;

import cn.hanbell.eam.ejb.AssetAcceptanceBean;
import cn.hanbell.eam.ejb.AssetDistributeBean;
import cn.hanbell.eam.ejb.AssetItemBean;
import cn.hanbell.eam.entity.AssetAcceptance;
import cn.hanbell.eam.entity.AssetAcceptanceDetail;
import cn.hanbell.eam.entity.AssetDistribute;
import cn.hanbell.eam.entity.AssetDistributeDetail;
import cn.hanbell.eam.entity.AssetItem;
import cn.hanbell.erp.ejb.InvbalBean;
import cn.hanbell.erp.ejb.InvwhsafeBean;
import cn.hanbell.erp.ejb.InvhadBean;
import cn.hanbell.erp.ejb.InvmasBean;
import cn.hanbell.erp.ejb.PurachBean;
import cn.hanbell.erp.ejb.PurhaskBean;
import cn.hanbell.erp.entity.Invbal;
import cn.hanbell.erp.entity.Invdta;
import cn.hanbell.erp.entity.Invhad;
import cn.hanbell.erp.entity.Invmas;
import cn.hanbell.erp.entity.Invwhsafe;
import cn.hanbell.erp.entity.InvwhsafePK;
import cn.hanbell.erp.entity.Puracd;
import cn.hanbell.erp.entity.Purach;
import cn.hanbell.erp.entity.Purhask;
import cn.hanbell.oa.ejb.HKCW002Bean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002Detail;
import cn.hanbell.oa.model.HZJS034DetailModel;
import cn.hanbell.oa.model.HZJS034Model;
import cn.hanbell.oa.model.SHBERPINV325DetailModel;
import cn.hanbell.oa.model.SHBERPINV325Model;
import cn.hanbell.plm.ejb.PLMItnbrMasterTempBean;
import cn.hanbell.plm.entity.PLMItnbrDetailTemp;
import cn.hanbell.plm.entity.PLMItnbrMasterTemp;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.SuperEJB;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;

/**
 * TimerBean is a singleton session bean that creates a timer and prints out a
 * message when a programmaticTimeout occurs.
 */
@Singleton
@Startup
public class TimerBean {

    //EJBForEAM
    @EJB
    private AssetAcceptanceBean assetAcceptanceBean;
    @EJB
    private AssetItemBean assetItemBean;
    @EJB
    private AssetDistributeBean assetDistributeBean;

    //EJBForEFGP
    @EJB
    private HKCW002Bean hkcw002Bean;
    @EJB
    private WorkFlowBean workFlowBean;

    //EJBForERP
    @EJB
    private InvwhsafeBean invwhsafeBean;
    @EJB
    private InvbalBean invbalBean;
    @EJB
    private InvhadBean invhadBean;
    @EJB
    private InvmasBean invmasBean;
    @EJB
    private PurachBean purachBean;
    @EJB
    private PurhaskBean purhaskBean;

    //EJBForPLM
    @EJB
    private PLMItnbrMasterTempBean plmItnbrMasterTempBean;

    @Resource
    TimerService timerService;

    private static final Logger logger = Logger.getLogger("cn.hanbell.jws.TimerBean");

    public TimerBean() {

    }

    @Schedule(minute = "*/30", hour = "*", persistent = false)
    public void createEAMAssetAcceptanceByERPPUR530() {
        //将ERP资产验收同步到EAM资产入库
        List<HKCW002Detail> hkcw002Details;
        List<HKCW002> hkcw002List = hkcw002Bean.findNotAcceptance();
        if (hkcw002List != null && !hkcw002List.isEmpty()) {
            String[] acceptno;
            boolean flag;
            //用于更新资产明细
            List<HKCW002Detail> editedHKCW002Detail = new ArrayList<>();
            HashMap<SuperEJB, List<?>> hkcw002DetailEdited = new HashMap<>();
            hkcw002DetailEdited.put(hkcw002Bean, editedHKCW002Detail);

            for (HKCW002 e : hkcw002List) {
                purhaskBean.setCompany(e.getFacno());
                purachBean.setCompany(e.getFacno());
                //HKCG007抛转PUR210时截取了流程序号,省略了PKG_
                Purhask prh = purhaskBean.findBySrcno(e.getHkcg007().substring(4));
                if (prh != null) {
                    //根据请购单号得到验收单号数组
                    acceptno = purachBean.findByPRN(prh.getPurhaskPK().getPrno());
                    if (acceptno != null) {

                        try {
                            flag = true;
                            int i;
                            BigDecimal qty;
                            //EAM相关对象
                            AssetItem ai;
                            List<AssetAcceptanceDetail> addedDetail = new ArrayList();
                            //ERP相关对象
                            Purach purach;
                            List<Puracd> puracdList;
                            //EFGP相关对象
                            hkcw002Bean.setDetail(e.getFormSerialNumber());
                            hkcw002Details = hkcw002Bean.getDetailList();

                            if (hkcw002Details != null && !hkcw002Details.isEmpty()) {

                                for (HKCW002Detail d : hkcw002Details) {

                                    if (d.getRelqty() != null && !"".equals(d.getRelqty())) {
                                        qty = BigDecimal.valueOf(Double.parseDouble(d.getRelqty()));
                                        if (qty.compareTo(BigDecimal.valueOf(Double.parseDouble(d.getPurqty()))) >= 0) {
                                            continue;
                                        }
                                    }

                                    qty = BigDecimal.ZERO;
                                    for (String n : acceptno) {
                                        purach = purachBean.findByAcceptno(n);
                                        puracdList = purachBean.findByAcceptnoAndItnbr(n, d.getItemno());
                                        if (purach != null && puracdList != null && !puracdList.isEmpty()) {
                                            //ERP每一笔验收产生一笔EAM资产入库
                                            i = 0;
                                            addedDetail.clear();//清空之前列表
                                            //计算累计验收数量
                                            for (Puracd acd : puracdList) {
                                                qty = qty.add(acd.getOkqy1());
                                            }
                                            for (Puracd acd : puracdList) {
                                                //判断验收记录，处理多次验收逻辑
                                                if (d.getRelno() == null) {
                                                    d.setRelno("");
                                                }
                                                if (d.getRelno().compareTo(n) < 0) {
                                                    //新的验收记录，产生EAM入库
                                                    i++;
                                                    AssetAcceptanceDetail aad = new AssetAcceptanceDetail();
                                                    //aad.setPid("");
                                                    aad.setSeq(i);
                                                    aad.setAcceptdate(acd.getAcceptdate());
                                                    aad.setAcceptDeptno(acd.getDepno());
                                                    aad.setAcceptUserno(acd.getUserno());
                                                    ai = assetItemBean.findByItemno(acd.getItnbr());
                                                    aad.setAssetItem(ai);
                                                    aad.setQty(acd.getAccqy1());
                                                    aad.setUnit(ai.getUnit());
                                                    aad.setQcpass(false);
                                                    aad.setQcqty(acd.getOkqy1());
                                                    aad.setAddqty(BigDecimal.ZERO);
                                                    aad.setWarehouse(ai.getCategory().getWarehouse());
                                                    aad.setCurrency(acd.getCoin());
                                                    aad.setExchange(acd.getRatio());
                                                    aad.setTaxrate(acd.getTaxrate());
                                                    aad.setPrice(acd.getUnpris());
                                                    aad.setAmts(acd.getTramts());
                                                    aad.setExtax(acd.getTotamts().subtract(acd.getTaxamts()));
                                                    aad.setTaxes(acd.getTaxamts());
                                                    aad.setSrcapi("ERP");
                                                    aad.setSrcformid(n);
                                                    aad.setSrcseq(Integer.valueOf(acd.getPuracdPK().getTrseq()));
                                                    aad.setStatus("40");

                                                    addedDetail.add(aad);

                                                    //更新资产申请明细关联单号
                                                    d.setRelno(n);
                                                    d.setRelseq(String.valueOf(acd.getPuracdPK().getTrseq()));
                                                    d.setRelqty(String.valueOf(qty));

                                                    editedHKCW002Detail.add(d);
                                                }
                                            }
                                            if (addedDetail.size() > 0) {

                                                AssetAcceptance aa = new AssetAcceptance();
                                                aa.setCompany(purach.getPurachPK().getFacno());
                                                aa.setFormid("");
                                                aa.setFormdate(purach.getAcceptdate());
                                                aa.setVendorno(purach.getVdrno());
                                                aa.setDeptno(purach.getDepno());
                                                aa.setRemark(e.getProcessSerialNumber() + "_" + purach.getPurachPK().getAcceptno());
                                                aa.setStatus("N");
                                                //产生EAM资产入库
                                                assetAcceptanceBean.initAssetAcceptance(aa, addedDetail);

                                            }
                                        }
                                    }
                                }
                                if (editedHKCW002Detail.size() > 0) {
                                    //判断整张资产申请是否全部验收
                                    for (HKCW002Detail d : hkcw002Details) {
                                        if (d.getRelqty() != null && !"".equals(d.getRelqty())) {
                                            qty = BigDecimal.valueOf(Double.parseDouble(d.getRelqty()));
                                            if (qty.compareTo(BigDecimal.valueOf(Double.parseDouble(d.getPurqty()))) < 0) {
                                                flag = false;
                                            }
                                        } else {
                                            flag = false;
                                        }
                                    }
                                    if (flag) {
                                        //全部验收完成
                                        e.setRelformid(Arrays.toString(acceptno));
                                    }
                                    hkcw002Bean.update(e, null, hkcw002DetailEdited, null);
                                }
                            }

                        } catch (Exception ex) {
                            Logger.getLogger("createEAMAssetAcceptanceByERPPUR530").log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        }
        logger.log(Level.INFO, "syncEAMAssetAcceptanceWithERPPUR530");
    }

    @Schedule(minute = "*/5", hour = "*", persistent = false)
    public void createERPINV310ByEAMAssetDistribute() {
        //将EAM资产领用同步到ERP INV310手工领料
        List<AssetDistribute> adList = assetDistributeBean.findByStatus("V");//已确认的领用

        if (adList != null && !adList.isEmpty()) {
            short trseq;
            String trtype = "IAB";
            String facno;
            String prono = "1";
            String trno;
            Date trdate;
            List<AssetDistributeDetail> addList;
            List<Invdta> addedDetail = new ArrayList();

            //用于更新资产明细
            for (AssetDistribute e : adList) {

                if (e.getRelformid() != null && !"".equals(e.getRelformid())) {
                    continue;
                }

                facno = e.getCompany();
                trdate = e.getFormdate();
                trno = "";
                trseq = 0;
                assetDistributeBean.setDetail(e.getFormid());
                addList = assetDistributeBean.getDetailList();
                if (addList != null && !addList.isEmpty()) {
                    addedDetail.clear();
                    try {
                        for (AssetDistributeDetail d : addList) {
                            trseq++;
                            Invdta invdta = new Invdta(d.getAssetItem().getItemno(), facno, prono, trno, trseq);
                            invdta.setTrtype(trtype);
                            //获取品号资料
                            Invmas m = invmasBean.findByItnbr(d.getAssetItem().getItemno());
                            if (m == null) {
                                throw new RuntimeException("EAM" + e.getFormid() + "中的品号" + d.getAssetItem().getItemno() + "ERP中不存在");
                            }
                            invdta.setItcls(m.getItcls());
                            invdta.setItclscode(m.getItclscode());
                            invdta.setTrnqy1(d.getQty());
                            invdta.setTrnqy2(BigDecimal.ZERO);
                            invdta.setTrnqy3(BigDecimal.ZERO);
                            invdta.setUnmsr1(m.getUnmsr1());
                            invdta.setWareh(d.getWarehouse2().getRemark());
                            invdta.setFixnr("");
                            invdta.setVarnr("");
                            invdta.setIocode('0');

                            //加入库存出入新增列表
                            addedDetail.add(invdta);
                        }

                        Invhad invhad = new Invhad(facno, prono, trno);
                        invhad.setTrtype(trtype);
                        invhad.setTrdate(trdate);
                        invhad.setDepno(e.getDeptno());
                        invhad.setIocode('0');
                        invhad.setResno("K15");
                        invhad.setHmark1(null);
                        invhad.setHmark2("0008");
                        invhad.setSourceno(e.getFormid());
                        invhad.setStatus('N');
                        invhad.setUserno("mis");
                        invhad.setIndate(trdate);

                        trno = invhadBean.initINV310(invhad, addedDetail);
                        if (trno != null && !"".equals(trno)) {
                            e.setRelformid(trno);
                            e.setStatus("T");
                            assetDistributeBean.update(e);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(InvhadBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        logger.log(Level.INFO, "createERPINV310ByEAMAssetDistribute");
    }

    @Schedule(minute = "*/5", hour = "*", persistent = false)
    public void createOAHZJS034ByPLM() {
        try {
            HZJS034Model m;
            HZJS034DetailModel d;
            List<HZJS034DetailModel> detailList = new ArrayList<>();
            LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();

            details.put("Detail", detailList);

            List<PLMItnbrDetailTemp> plmDetailList;
            List<PLMItnbrMasterTemp> plmMasterList = plmItnbrMasterTempBean.findNeedThrow();
            if (plmMasterList != null && !plmMasterList.isEmpty()) {
                for (PLMItnbrMasterTemp pm : plmMasterList) {
                    plmDetailList = plmItnbrMasterTempBean.findNeedThrowDetail(pm.getItemNumber());
                    if (plmDetailList != null && !plmDetailList.isEmpty()) {
                        detailList.clear();//清除前面的资料
                        int i = 0;
                        for (PLMItnbrDetailTemp pd : plmDetailList) {
                            i++;
                            d = new HZJS034DetailModel();
                            d.setSeq(String.valueOf(i));
                            d.setItcls("");
                            d.setClsdsc("");
                            d.setItnbr(pd.getCItnbr());
                            d.setItdsc(pd.getCItdsc());
                            d.setSpdsc(pd.getCSpdsc());
                            d.setEitdsc(pd.getCEitdsc());
                            d.setEspdsc(pd.getCEspdsc());
                            d.setKind("1");
                            d.setMorpcode("P");
                            d.setUnmsr1(pd.getUnmsr());
                            d.setUnmsr1e(pd.getUnmsre());
                            d.setUnmsr2("");
                            d.setIsDUnit("N");
                            d.setYt("");
                            d.setRemark("");
                            detailList.add(d);
                        }
                        workFlowBean.initUserInfo(pm.getApplicant());
                        m = new HZJS034Model();
                        m.setFacno(pm.getProno());
                        m.setEmpl(workFlowBean.getCurrentUser().getId());
                        m.setDept(workFlowBean.getUserFunction().getOrganizationUnit().getId());
                        m.setIndate(BaseLib.getDate());
                        m.setPLMNO(pm.getItemNumber());
                        String formInstance = workFlowBean.buildXmlForEFGP("HZ_JS034", m, details);
                        String subject = "PLM件号申请_" + pm.getItemNumber();
                        String msg = workFlowBean.invokeProcess(workFlowBean.hostAdd, workFlowBean.hostPort, "HZ_JS034", formInstance, subject);
                        String[] rm = msg.split("\\$");
                        if (rm.length == 2 && rm[0].equals("200")) {
                            //更新PLM状态
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, rm[0]);
                            pm.setCTriggerYn('Y');
                            plmItnbrMasterTempBean.update(pm);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(Level.INFO, "PLM件号抛转轮询");
    }

    @Schedule(minute = "∗/50", hour="7,8", persistent = false)
    public void automaticTimer2() {
        try {
            SHBERPINV325Model s;
            SHBERPINV325DetailModel dm;
            List<SHBERPINV325DetailModel> detailList = new ArrayList<>();
            LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();
            details.put("Detail", detailList);
            List<Invwhsafe> list = null;
            list = invwhsafeBean.findAll();
            int c;
            int i = 0;
            for (Invwhsafe iw : list) {
                InvwhsafePK PK = iw.getInvwhsafePK();
                double low;
                double high;
                double take;
                double onhand1;
                low = iw.getLowest().doubleValue();
                high = iw.getHighest().doubleValue();
                take = iw.getMintake().doubleValue();
                String itnbr = PK.getItnbr();
                String wareh = PK.getWareh();
                String itdsc = iw.getItdsc();
                Invbal IB = invbalBean.findByItnbrAndWareh(itnbr, wareh);
                BigDecimal Onhand1 = IB.getOnhand1();
                onhand1 = Onhand1.doubleValue();
                if (onhand1 < low) {
                    c = (int) ((high - onhand1) / take);
                    System.out.println(c);
                } else {
                    continue;
                }
                i++;
                dm = new SHBERPINV325DetailModel();
                dm.setSeq(String.valueOf(i));
                dm.setItnbr(itnbr);
                dm.setItdsc(itdsc);
                dm.setCountnum(c + "");
                System.out.println(dm.getCountnum());
                System.out.println(i);
                System.out.println(String.valueOf(i));
                dm.setSumnum((take * c) + "");
                System.out.println(dm.getSumnum());
                dm.setDfromwareh("ASRS01");
                dm.setDtowareh("ZP01");
                dm.setBz("");
                detailList.add(dm);
            }
            workFlowBean.initUserInfo("C0385");
            s = new SHBERPINV325Model();
            s.setFacno("C");
            s.setApplyuser("C1587");
            s.setDept("1F220");
            s.setProno("1");
            s.setResno("T01");
            s.setTrtype("IAC");
            s.setFromwareh("ASRA01");
            s.setTowareh("ZP01");
            s.setBz("");
            s.setDfromwareh("");
            s.setDtowareh("");
            s.setItdsc("");
            s.setItnbr("");
            s.setSumnum("");
            s.setCountnum("");
            if (detailList.size() > 0) {
                String formInstance = workFlowBean.buildXmlForEFGP("SHB_ERP_INV325", s, details);
                String subject = "生产制领料";
                String msg = workFlowBean.invokeProcess(workFlowBean.hostAdd, workFlowBean.hostPort, "PKG_SHB_ERP_INV325", formInstance, subject);
                System.out.println(msg);
            } else {

            }

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }
}
