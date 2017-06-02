/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKCG007;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002Detail;
import cn.hanbell.oa.entity.HKCW002P;
import cn.hanbell.oa.entity.HKCW002PpurDetail;
import cn.hanbell.oa.model.HKCG007DetailModel;
import cn.hanbell.oa.model.HKCG007Model;
import cn.hanbell.util.BaseLib;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
public class HKCG007Bean extends SuperEJBForEFGP<HKCG007> {

    @EJB
    private WorkFlowBean workFlowBean;
    @EJB
    private HKCW002PBean hkcw002pBean;
    @EJB
    private HKCW002PpurDetailBean hkcw002ppurdetalBean;
    @EJB
    private HKCW002Bean hkcw002Bean;

    public HKCG007Bean() {
        super(HKCG007.class);
    }

    public Boolean initByHKCW002P(String psn) {
        try {
            HKCG007Model m;
            HKCG007DetailModel d;
            List<HKCG007DetailModel> detailList = new ArrayList<>();
            LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();
            details.put("purDetail", detailList);

            List<HKCW002PpurDetail> purDetailList;
            HKCW002P pm = hkcw002pBean.findByPSN(psn);

            //判断是否已抛转
            if (!"".equals(pm.getIfturn()) && pm.getIfturn() != null) {
                return false;
            }
            purDetailList = hkcw002ppurdetalBean.findByFSN(pm.getFormSerialNumber());
            if (purDetailList != null && !purDetailList.isEmpty()) {
                detailList.clear();//清除前面的资料
                int i = 0;
                for (HKCW002PpurDetail pd : purDetailList) {
                    i++;
                    if (pd.getQgnum() != null && !pd.getQgnum().isEmpty()) {
                        d = new HKCG007DetailModel();
                        d.setSeq(String.valueOf(i));
                        d.setItnbr(pd.getPncode());
                        d.setItdsc(pd.getPnname());
                        d.setSpdsc(pd.getSpdse());
                        d.setRqtdateTxt(pd.getDatetime1Txt());
                        d.setEmgyn(pd.getSfjj());
                        d.setPrqy1(pd.getApplynum());
                        d.setAppunit(pd.getApplyunit());
                        d.setApamts(pd.getApamts());
                        d.setBudgetacc(pd.getBudgetacc());
                        d.setBudgetaccname(pd.getBudgetaccname());
                        d.setDmark1(pd.getDmark1());
                        d.setDmark1name(pd.getDmark1name());
                        d.setDmark2(pd.getDmark2());
                        d.setDmark2name(pd.getDmark2name());
                        d.setPurdaskdescs("");
                        d.setAccPeriod(pd.getSubperbal());
                        d.setAccYear(pd.getSubyearbal());
                        d.setVdrno("");
                        d.setBuyer("");
                        d.setUnpris("");
                        d.setAskdateTxt("");
                        d.setTax("4");
                        d.setTaxrate("0.17");
                        d.setPosrccode("");
                        d.setAddcode(pd.getAddcode());
                        d.setVdrna("");
                        d.setBuyername("");
                        d.setCtotamts("0.00");
                        d.setCtramts("0.00");
                        d.setCtaxamts("0.00");
                        d.setQtotamts("0.00");
                        detailList.add(d);
                    }

                }
                if (detailList.size() > 0) {
                    //先用需求人员初始化，获得部门主管cfmuserno
                    workFlowBean.initUserInfo(pm.getUserman());
                    m = new HKCG007Model();
                    m.setFacno(pm.getFacno());
                    m.setProno("1");
                    m.setCreator(pm.getApplyUser());
                    m.setAppuser(pm.getUserman());
                    m.setDepno(pm.getUsingDept());
                    m.setCenterid(pm.getBudgetdept());
                    m.setAppDate(BaseLib.getDate());
                    m.setPrkind(pm.getPrkind());
                    m.setHmark1(pm.getHmark1());
                    m.setCurrency(pm.getCurrency());
                    m.setRate(pm.getRate());
                    m.setQtotaly(pm.getQtotaly());
                    m.setQtotalyRMB(pm.getQtotalrmb());
                    m.setFormid(psn);

                    m.setMastbuyer("");
                    m.setMastbuyername("");
                    m.setCtotaly(0.00);
                    m.setCtotalyRMB(0.00);
                    m.setIstechnique("N");
                    m.setDeptPeriod(pm.getDeptperbal());
                    m.setDeptYear(pm.getDeptyearbal());

                    m.setIsNine("0");
                    m.setYfPrice("N");
                    m.setBudgetcode(workFlowBean.getOrganizationUnit().getId());
                    m.setCfmuserno(workFlowBean.getOrganizationUnit().getManager().getId());

                    //项目建设效益
                    m.setZddw(pm.getZddw());
                    m.setSqr(pm.getSqr());
                    m.setDate65(pm.getDate65());
                    m.setCproname(pm.getCproname());
                    m.setTextbox56(pm.getTextbox56());
                    m.setGuige(pm.getGuige());
                    m.setTextbox64(pm.getTextbox64());
                    m.setTextbox63(pm.getTextbox63());
                    m.setTextbox55(pm.getTextbox55());
                    m.setProperty(pm.getProperty());
                    m.setProattr(pm.getProattr());
                    m.setTextbox62(pm.getTextbox62());
                    m.setSource(pm.getSource());
                    m.setTextArea80(pm.getTextArea80());
                    m.setTextbox81(pm.getTextbox81());
                    m.setTextbox82(pm.getTextbox82());
                    m.setTextbox84(pm.getTextbox84());
                    m.setTextbox85(pm.getTextbox85());
                    m.setTextbox91(pm.getTextbox91());
                    m.setTextbox92(pm.getTextbox92());
                    m.setTextbox93(pm.getTextbox93());
                    m.setTextbox94(pm.getTextbox94());
                    m.setTextbox95(pm.getTextbox95());
                    m.setTextbox101(pm.getTextbox101());
                    m.setTextbox102(pm.getTextbox102());
                    m.setTextbox103(pm.getTextbox103());
                    m.setTextbox104(pm.getTextbox104());
                    m.setTextbox106(pm.getTextbox106());
                    m.setTextbox111(pm.getTextbox111());
                    m.setTextbox114(pm.getTextbox114());
                    m.setTextbox116(pm.getTextbox116());
                    m.setTextbox108(pm.getTextbox108());
                    m.setTextbox112(pm.getTextbox112());
                    m.setTextbox3(pm.getTextbox3());
                    m.setTextbox0(pm.getTextbox0());
                    m.setTextbox7(pm.getTextbox7());
                    m.setTextbox10(pm.getTextbox10());
                    m.setTextbox4(pm.getTextbox4());
                    m.setTextbox15(pm.getTextbox15());

                    //重新初始化流程发起人员
                    workFlowBean.initUserInfo(pm.getApplyUser());
                    String formInstance = workFlowBean.buildXmlForEFGP("HK_CG007", m, details);
                    String subject = "资产申请单_" + pm.getProcessSerialNumber();
                    String msg = workFlowBean.invokeProcess(workFlowBean.hostAdd, workFlowBean.hostPort, "PKG_HK_CG007", formInstance, subject);
                    String[] rm = msg.split("\\$");
                    if (rm.length == 2 && rm[0].equals("200")) {
                        //更新资产申请单状态
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, rm[0]);
                        pm.setIfturn("已抛转请购单");
                        pm.setQgd(rm[1]);
                        hkcw002pBean.update(pm);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex.getMessage());
            return false;
        }
    }

    public Boolean initByHKCW002(String psn) {
        try {
            HKCG007Model m;
            HKCG007DetailModel d;
            List<HKCG007DetailModel> detailList = new ArrayList<>();
            LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();
            details.put("purDetail", detailList);

            List<HKCW002Detail> purDetailList;
            HKCW002 aa = hkcw002Bean.findByPSN(psn);
            if (aa == null) {
                return false;
            }
            //判断是否已抛转
            if ("0".equals(aa.getPurchase()) || !"".equals(aa.getHkcg007())) {
                return true;
            }
            hkcw002Bean.setDetail(aa.getFormSerialNumber());
            purDetailList = hkcw002Bean.getDetailList();
            if (purDetailList != null && !purDetailList.isEmpty()) {
                int i = 0;
                for (HKCW002Detail aad : purDetailList) {
                    i++;
                    if (aad.getPurqty() != null && !"".equals(aad.getPurqty()) && !"0".equals(aad.getPurqty())) {
                        d = new HKCG007DetailModel();
                        d.setSeq(String.valueOf(i));
                        d.setItnbr(aad.getItemno());
                        d.setItdsc(aad.getItemdesc());
                        d.setSpdsc(aad.getItemspec());
                        d.setRqtdateTxt(aad.getRqtdateTxt());
                        d.setEmgyn(aad.getEmgyn());
                        d.setPrqy1(aad.getPurqty());
                        d.setAppunit(aad.getUnit());
                        d.setApamts(aad.getPrice());
                        d.setBudgetacc(aad.getBudgetacc());
                        d.setBudgetaccname(aad.getBudgetaccname());
                        d.setAccPeriod(aad.getAccperbal());
                        d.setAccYear(aad.getAccyearbal());
                        d.setDmark1(aad.getDmark1());
                        d.setDmark1name(aad.getDmark1desc());
                        d.setDmark2(aad.getDmark2());
                        d.setDmark2name(aad.getDmark2desc());
                        d.setQtotamts(aad.getAmts());

                        d.setPurdaskdescs("");
                        d.setVdrno("");
                        d.setBuyer("");
                        d.setUnpris("");
                        d.setAskdateTxt("");
                        d.setTax("4");
                        d.setTaxrate("0.17");
                        d.setPosrccode("3");
                        d.setAddcode(aa.getAddcode());
                        d.setVdrna("");
                        d.setBuyername("");
                        d.setCtotamts("0.00");
                        d.setCtramts("0.00");
                        d.setCtaxamts("0.00");
                        d.setQtotamts("0.00");
                        detailList.add(d);
                    }
                }
                if (detailList.size() > 0) {
                    //先用需求人员初始化，获得部门主管cfmuserno
                    workFlowBean.initUserInfo(aa.getRequireUserno());
                    m = new HKCG007Model();
                    m.setFacno(aa.getFacno());
                    m.setProno("1");
                    m.setAppDate(BaseLib.getDate());
                    m.setAppuser(aa.getRequireUserno());
                    m.setDepno(aa.getRequireDeptno());
                    m.setCenterid(aa.getCenterid());
                    m.setCreator(aa.getApplyUserno());
                    m.setPrkind(aa.getPrkind());
                    m.setHmark1(aa.getHmark1());
                    m.setCurrency(aa.getCurrency());
                    m.setRate(aa.getRate());
                    m.setQtotaly(aa.getTotalamts());
                    m.setQtotalyRMB(aa.getTotalamt());
                    m.setFormid(psn);
                    m.setDeptPeriod(aa.getDeptperbal());
                    m.setDeptYear(aa.getDeptyearbal());

                    m.setMastbuyer("");
                    m.setMastbuyername("");
                    m.setCtotaly(0.00);
                    m.setCtotalyRMB(0.00);
                    m.setIstechnique("N");

                    m.setIsNine("0");
                    m.setYfPrice("N");
                    m.setBudgetcode(workFlowBean.getOrganizationUnit().getId());
                    m.setCfmuserno(workFlowBean.getOrganizationUnit().getManager().getId());

                    //项目建设效益
                    m.setZddw(aa.getFormulateDept());
                    m.setSqr(aa.getFormulateUser());
                    m.setDate65(aa.getFormulateDate());
                    m.setCproname(aa.getEquipmentName());
                    m.setTextbox56(aa.getEquipmentName2());
                    m.setGuige(aa.getEquipmentSpec());
                    m.setTextbox64(aa.getAdviceBrand());
                    m.setTextbox63(aa.getPlanqty());
                    m.setTextbox55(aa.getUsed());
                    m.setProperty(aa.getProperty());
                    m.setProattr(aa.getAttribute());
                    m.setTextbox62(aa.getVendor());
                    m.setSource(aa.getSource());
                    m.setTextArea80(aa.getRemark());
                    m.setTextbox81(aa.getFactor01());
                    m.setTextbox82(aa.getFactor02());
                    m.setTextbox84(aa.getFactor03());
                    m.setTextbox85(aa.getFactor04());
                    m.setTextbox91(aa.getFactor05());
                    m.setTextbox92(aa.getFactor06());
                    m.setTextbox93(aa.getFactor07());
                    m.setTextbox94(aa.getFactor08());
                    m.setTextbox95(aa.getFactor09());
                    m.setTextbox101(aa.getFactor10());
                    m.setTextbox102(aa.getFactor11());
                    m.setTextbox103(aa.getFactor12());
                    m.setTextbox104(aa.getConclusion());
                    m.setTextbox106(aa.getOrigproject1());
                    m.setTextbox111(aa.getOrigqty1());
                    m.setTextbox114(aa.getOrigrate1());
                    m.setTextbox116(aa.getNewrate1());
                    m.setTextbox108(aa.getOrigproject2());
                    m.setTextbox112(aa.getOrigqty2());
                    m.setTextbox3(aa.getOrigrate2());
                    m.setTextbox0(aa.getNewrate2());
                    m.setTextbox7(aa.getNewproject1());
                    m.setTextbox10(aa.getNewqty1());
                    m.setTextbox4(aa.getNewproject2());
                    m.setTextbox15(aa.getNewqty2());

                    //重新初始化流程发起人员
                    workFlowBean.initUserInfo(aa.getApplyUserno());
                    String formInstance = workFlowBean.buildXmlForEFGP("HK_CG007", m, details);
                    String subject = "资产申请单_" + aa.getProcessSerialNumber();
                    String msg = workFlowBean.invokeProcess(workFlowBean.hostAdd, workFlowBean.hostPort, "PKG_HK_CG007", formInstance, subject);
                    String[] rm = msg.split("\\$");
                    if (rm.length == 2 && rm[0].equals("200")) {
                        //更新资产申请单状态
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, rm[0]);
                        aa.setHkcg007(rm[1]);
                        hkcw002Bean.update(aa);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex.getMessage());
            return false;
        }
    }

}
