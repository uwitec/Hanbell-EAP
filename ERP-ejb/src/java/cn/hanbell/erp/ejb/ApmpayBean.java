/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;

import cn.hanbell.erp.entity.Apmpad;
import cn.hanbell.erp.entity.ApmpadPK;
import cn.hanbell.erp.entity.Apmpay;
import cn.hanbell.erp.entity.ApmpayPK;
import cn.hanbell.erp.entity.BudgetDetail;
import cn.hanbell.oa.ejb.BXD_Detail01Bean;
import cn.hanbell.oa.ejb.BXDBean;
import cn.hanbell.oa.entity.BXD_Detail01;
import cn.hanbell.oa.entity.BXD;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class ApmpayBean extends SuperEJBForERP<Apmpay> {

    @EJB
    private AccrnoBean accrnoBean;

    @EJB
    private BudgetDetailBean budgetDetailBean;

    @EJB
    private AccacrBean accacrBean;

    @EJB
    private ApmpadBean apmpadBean;

    @EJB
    private ApmsysBean apmsysBean;

    @EJB
    private BXDBean BXDBean;
    @EJB
    private BXD_Detail01Bean bxdDetail01Bean;

    public ApmpayBean() {
        super(Apmpay.class);
    }

    @Override
    public Boolean initByOAPSN(String psn) {
        String facno, period;
        Date date;
        BXD b = BXDBean.findByPSN(psn);

        List<BudgetDetail> budgetDetails = new ArrayList<>();
        
//        switch (b.getBmpa02c()) {
//            case "0":
//                facno = "C";
//                break;
//            case "1":
//                facno = "N";
//                break;
//            case "2":
//                facno = "G";
//                break;
//            case "3":
//                facno = "J";
//                break;
//            case "4":
//                facno = "H";
//                break;
//            case "5":
//                facno = "K";
//                break;
//            case "6":
//                facno = "W";
//                break;
//            case "7":
//                facno = "Q";
//                break;
//
//        }

        if (b.getBmpa02c().equals("0")) {
            facno = "C";
        } else {
            facno = "G";
        }

        try {

            date = BaseLib.getDate("yyyy/MM/dd", BaseLib.formatDate("yyyy/MM/dd", BaseLib.getDate())); //付款日期
            period = BaseLib.formatDate("yyyyMM", date);

            apmsysBean.setCompany(facno);
            ApmpayPK pk = new ApmpayPK();
            pk.setFacno(facno);
            pk.setPaycode('2');
            pk.setPayno(apmsysBean.getFormId(facno, "APM525", date, Boolean.TRUE));

            setCompany(facno);
            Apmpay h = new Apmpay();
            h.setApmpayPK(pk);
            h.setPayda(date);
            accrnoBean.setCompany(facno);
            h.setVouno(accrnoBean.getFormId(facno, date, Boolean.TRUE));    //设置初稿传票编号

            h.setDepno(b.getBudgetDept());   //取得OA预算部门
            h.setRkd("MR01");
            h.setSumry(b.getBmpb01c());   //取得OA报销摘要
            h.setUsrno(b.getBmpa14c());            //OA表单输入人员
            h.setAppuserno(b.getBmpa04c());         //OA请款人
            h.setTaxym(BaseLib.formatDate("yyyyMM", b.getBmpa03c()));   //设置申报年月 （取得OA请款日期）
            h.setBilltype("BXD");           //取得OA表单名称
            h.setSrcno(b.getProcessSerialNumber());     //取得OA表单序列号
            h.setPaystat('0');              //设置付款状态
            h.setSrckind("3");          //设置来源区分码         

            List<BXD_Detail01> details01 = bxdDetail01Bean.findByFSN(b.getFormSerialNumber());
            List<Apmpad> apmpads = new ArrayList<>();

            accacrBean.setCompany(facno);
//            for (BXD_Detail01 detail01 : details01) {
//              
//            }
//  表身贷方循环
            for (int i = 0; i < details01.size(); i++) {
                Apmpad apmpad = new Apmpad();
                ApmpadPK apmpadPK = new ApmpadPK();
                apmpadPK.setFacno(facno);
                apmpadPK.setPaycode('2');
                apmpadPK.setPayno(pk.getPayno());
                apmpadPK.setTrse((short) (i + 1));
                apmpad.setApmpadPK(apmpadPK);

                BXD_Detail01 detail0l = details01.get(i);

                apmpad.setAccno(detail0l.getAccno());  //设置会计科目
                apmpad.setCoin(b.getCoin());  //设置币别
                apmpad.setRatio(BigDecimal.valueOf(b.getRatio())); //设置汇率

                if ("53".equals(apmpad.getAccno().substring(0, 2))) {
                    apmpad.setCuskind("9J");
                    apmpad.setVdrna(detail0l.getBmpb07c());
                } else {
                    apmpad.setCuskind("GE");   //设置对象代号类别（cuskind）
                    apmpad.setVdrno(detail0l.getBudgetDepttxt());  //设置费用部门（预算部门）
                    apmpad.setVdrna(detail0l.getBudgetDeptlbl());
                }
                apmpad.setTnfamt(BigDecimal.ZERO);  //本次冲账金额（人民币/本币）
                apmpad.setTnfamtfs(BigDecimal.ZERO);    //本次冲账金额（原币）
//                apmpad.setRefno("");  //对应相关单号，（目前为空白）
                apmpad.setRefamtfs(BigDecimal.ZERO);  //对应相关金额（原币）
                apmpad.setRefamt(BigDecimal.ZERO);    //对应相关金额（人民币/本币）

                if (b.getBmpa08f() > 0) {
                    double d1 = Double.parseDouble(detail0l.getBmpb02f());
                    apmpad.setDramtfs(BigDecimal.valueOf(d1));
                    //设置dramtfs（借方）原币
                    double d2 = (d1 * (b.getRatio()));
                    apmpad.setDramt(BigDecimal.valueOf(d2));       //设置dramt（借方）人民币
                } else {
                    double d1 = detail0l.getBmpb08f();
                    apmpad.setDramtfs(BigDecimal.valueOf(d1));
                    //设置dramtfs（借方）原币
                    double d2 = (d1 * (b.getRatio()));
                    apmpad.setDramt(BigDecimal.valueOf(d2));       //设置dramt（借方）人民币
                }
                apmpad.setCramtfs(BigDecimal.ZERO);     //设置 cramtfs(0)
                apmpad.setCramt(BigDecimal.ZERO);    //设置 cramt(0)
                apmpad.setVouseq((short) (3));      //对应相关序号
                apmpad.setTrntype("");  // 设置交易种类

                apmpad.setConfig(accacrBean.getConfig(facno, "APM", "3", h.getRkd(), apmpad.getAccno(), 'D')); //设置config参数
                //            apmpad.setConfig(accacrBean.getConfig(facno, "APM","3" , h.getRkd(), apmpad.getAccno(),'C' )); //设置config参数
                apmpads.add(apmpad);

                //预算金额更新逻辑
                BudgetDetail u;
                u = new BudgetDetail(facno, "", period,detail0l.getCenterid(), detail0l.getAccno(), "R", apmpadPK.getTrse(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(detail0l.getBmpb08f()), BigDecimal.ZERO);
                budgetDetails.add(u);

            }

            if (b.getBmpa08f() > 0) {
                Apmpad apmpad3 = new Apmpad();
                ApmpadPK apmpadPK3 = new ApmpadPK();
                apmpadPK3.setFacno(facno);
                apmpadPK3.setPaycode('2');
                apmpadPK3.setPayno(pk.getPayno());
                apmpadPK3.setTrse((short) (details01.size() + 1));
                apmpad3.setApmpadPK(apmpadPK3);

                apmpad3.setDramt(BigDecimal.ZERO);
                apmpad3.setDramtfs(BigDecimal.ZERO);
                apmpad3.setCoin(b.getCoin());   //设置币别
                apmpad3.setRatio(BigDecimal.valueOf(b.getRatio()));    //设置汇率
                apmpad3.setDramtfs(BigDecimal.valueOf(b.getBmpa08f()));                    //设置dramtfs（借方）原币税金
                Double sjdramt = b.getBmpa08f() * (b.getRatio());
                apmpad3.setDramt(BigDecimal.valueOf(sjdramt));       //设置dramt（借方）人民币

                apmpad3.setCramtfs(BigDecimal.ZERO);     //设置 cramtfs(0)
                apmpad3.setCramt(BigDecimal.ZERO);    //设置 cramt(0)
                apmpad3.setTnfamt(BigDecimal.ZERO);  //本次冲账金额（人民币/本币）
                apmpad3.setTnfamtfs(BigDecimal.ZERO);    //本次冲账金额（原币）
                apmpad3.setRefamtfs(BigDecimal.ZERO);  //对应相关金额（原币）
                apmpad3.setRefamt(BigDecimal.ZERO);    //对应相关金额（人民币/本币）     
                apmpad3.setAccno("2222");
                apmpad3.setConfig(accacrBean.getConfig(facno, "APM", "3", h.getRkd(), apmpad3.getAccno(), 'D'));  //设置config参数
                //apmpad3.setConfig(accacrBean.getConfig(facno, "APM","3" , h.getRkd(), apmpad2.getAccno(), 'D'));  //设置config参数

                apmpads.add(apmpad3);

            }

            Apmpad apmpad2 = new Apmpad();
            ApmpadPK apmpadPK2 = new ApmpadPK();
            apmpadPK2.setFacno(facno);
            apmpadPK2.setPaycode('2');
            apmpadPK2.setPayno(pk.getPayno());
            if (b.getBmpa08f() > 0) {
                apmpadPK2.setTrse((short) (details01.size() + 2));
            } else {
                apmpadPK2.setTrse((short) (details01.size() + 1));
            }
            apmpad2.setApmpadPK(apmpadPK2);

            apmpad2.setDramt(BigDecimal.ZERO);
            apmpad2.setDramtfs(BigDecimal.ZERO);
            apmpad2.setCoin(b.getCoin());   //设置币别
            apmpad2.setRatio(BigDecimal.valueOf(b.getRatio()));    //设置汇率
            double c1 = b.getBmpa09f();
            apmpad2.setCramt(BigDecimal.valueOf(c1));    //设置 cramt(人民币报销 总额)
            double c2 = b.getBmpa06f();
            apmpad2.setCramtfs(BigDecimal.valueOf(c2));     //设置 cramtfs(报销总额原币)
            apmpad2.setTnfamt(BigDecimal.ZERO);  //本次冲账金额（人民币/本币）
            apmpad2.setTnfamtfs(BigDecimal.ZERO);    //本次冲账金额（原币）
            apmpad2.setRefamtfs(BigDecimal.ZERO);  //对应相关金额（原币）
            apmpad2.setRefamt(BigDecimal.ZERO);    //对应相关金额（人民币/本币）     
            apmpad2.setAccno("2242");
            apmpad2.setConfig(accacrBean.getConfig(facno, "APM", "3", h.getRkd(), apmpad2.getAccno(), 'C'));  //设置config参数
            //          apmpad2.setConfig(accacrBean.getConfig(facno, "APM","3" , h.getRkd(), apmpad2.getAccno(), 'D'));  //设置config参数
            apmpad2.setCuskind("9E");
            apmpad2.setVdrno(b.getBmpa04c());

            apmpads.add(apmpad2);

            HashMap<SuperEJBForERP, List<?>> detailAdded = new HashMap<>();
            detailAdded.put(apmpadBean, apmpads);

            persist(h);
            getEntityManager().flush();

            apmpadBean.setCompany(facno);
            for (Apmpad apmpad : apmpads) {
                apmpadBean.persist(apmpad);
            }

            budgetDetailBean.setCompany(facno);
            budgetDetailBean.add(budgetDetails);
            budgetDetailBean.getEntityManager().flush();

            return true;
        } catch (Exception ex) {
            Logger.getLogger(ApmpayBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
