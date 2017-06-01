/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKCG007;
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
                if (detailList.size()>0) {
                    workFlowBean.initUserInfo(pm.getApplyUser());
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
                    m.setApamts(0.00);
                    m.setVdrno("");
                    m.setVdrna("");
                    m.setBuyer("");
                    m.setBuyername("");
                    m.setUnpris(0.00);
                    m.setAskdate(BaseLib.getDate());
                    m.setIsNine("0");
                    m.setYfPrice("N");

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

}
