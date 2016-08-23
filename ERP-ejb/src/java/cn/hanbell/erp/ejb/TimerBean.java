/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.demo.BudgetDetail;
import cn.hanbell.erp.demo.HelloNaNa;
import cn.hanbell.oa.ejb.WorkFlowBean;

import cn.hanbell.util.BaseLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 * TimerBean is a singleton session bean that creates a timer and prints out a
 * message when a programmaticTimeout occurs.
 */
@Stateless
@Startup
@DependsOn({"cn.hanbell.oa.ejb.workFlowBean"})
public class TimerBean {

    @EJB
    private WorkFlowBean workFlowBean;

    @Resource
    TimerService timerService;

    private static final Logger logger = Logger.getLogger("cn.hanbell.ejb.TimerBean");

    protected HashMap<String, Long> timeleft;
    protected HashMap<String, Boolean> timeout;

    public TimerBean() {
        this.timeleft = new HashMap<>();
        this.timeout = new HashMap<>();
    }

    public void removeSession(String sessionId) {
        if (timeleft.containsKey(sessionId)) {
            timeleft.remove(sessionId);
        }
    }

    public void setTimer(long minutes, String info) {
        timeleft.put(info, minutes);
        timeout.put(info, Boolean.FALSE);
        Timer timer = timerService.createTimer(minutes * 60000, info);
    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        if (timeleft.containsKey(timer.getInfo().toString())) {
            timeleft.replace(timer.getInfo().toString(), (long) 0);
        }
        if (timeout.containsKey(timer.getInfo().toString())) {
            timeout.replace(timer.getInfo().toString(), Boolean.TRUE);
        }
    }

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void automaticTimeout() {

        logger.info("Automatic timeout occurred");
        String formInstance, formXml, status;
        LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();

        HelloNaNa master = new HelloNaNa("C0160", "C0160", "13120", "这是一个对象转换测试", "13000");
        List<BudgetDetail> budgetDetail = new ArrayList<>();
        budgetDetail.add(new BudgetDetail("1", "summary", "1601", "固定资产", "1000", "100"));
        budgetDetail.add(new BudgetDetail("2", "summary", "6717", "差旅费", "2000", "200"));
        budgetDetail.add(new BudgetDetail("3", "summary", "6717", "差旅费", "3000", "3000"));

        details.put("budgetDetail", budgetDetail);
        //formXml = BaseLib.buildXmlForEFGP("HelloNaNa", master, details);

        //System.out.println("formXml:" + formXml);
        workFlowBean.initUserInfo("C0160");
       // formInstance = workFlowBean.replaceVariables(formXml);
        //formInstance =  formInstance.replace("</HelloNaNa>","<trafficDetail id=\"trafficDetail\"><records><record id=\"trafficDetail_0\"><item id=\"seq\" dataType=\"java.lang.String\" perDataProId=\"\">1</item>             <item id=\"content\" dataType=\"java.lang.String\" perDataProId=\"\">出差市区</item>             <item id=\"trafficamts\" dataType=\"java.lang.String\" perDataProId=\"\">12</item>             <item id=\"chargeamts\" dataType=\"java.lang.String\" perDataProId=\"\">25</item>             <item id=\"otheramts\" dataType=\"java.lang.String\" perDataProId=\"\">12</item>          </record>        </records>      </trafficDetail>    </HelloNaNa> ");
        try {
          //  status = workFlowBean.invokeProcess("http://192.168.1.73", "8086", "PKG14600928645341", formInstance, "Invoke Process FROM ERP");
         //   System.out.println("processResult:" + status);
        } catch (Exception ex) {
            Logger.getLogger(TimerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

       // System.out.println("XML:" + formInstance);

    }

}
