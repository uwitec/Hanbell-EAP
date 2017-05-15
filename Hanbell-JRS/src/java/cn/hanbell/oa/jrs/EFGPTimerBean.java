/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.model.HZJS034DetailModel;
import cn.hanbell.oa.model.HZJS034Model;
import cn.hanbell.plm.ejb.PLMItnbrMasterTempBean;
import cn.hanbell.plm.entity.PLMItnbrDetailTemp;
import cn.hanbell.plm.entity.PLMItnbrMasterTemp;
import com.lightshell.comm.BaseLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

/**
 * TimerBean is a singleton session bean that creates a timer and prints out a
 * message when a programmaticTimeout occurs.
 */
@Stateless
@Startup
@DependsOn({"cn.hanbell.oa.ejb.workFlowBean"})
public class EFGPTimerBean {

    @Inject
    private WorkFlowBean workFlowBean;

    @Inject
    private PLMItnbrMasterTempBean plmItnbrMasterTempBean;

    @Resource
    TimerService timerService;

    private static final Logger logger = Logger.getLogger("cn.hanbell.jrs.PLMTimerBean");

    protected HashMap<String, Long> timeleft;
    protected HashMap<String, Boolean> timeout;

    public EFGPTimerBean() {
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

    @Schedule(minute = "*/5", hour = "*", persistent = false)
    public void automaticTimer() {
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
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }

}
