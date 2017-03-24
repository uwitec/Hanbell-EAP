/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package cn.hanbell.jrs;

import cn.hanbell.eap.ejb.BookingBean;
import cn.hanbell.eap.entity.Booking;
import cn.hanbell.oa.ejb.WorkFlowBean;
import com.lightshell.comm.BaseLib;
import java.util.HashMap;
import java.util.List;
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
public class TimerBean {

    @Inject
    private WorkFlowBean workFlowBean;

    @Inject
    private BookingBean bookingBean;

    @Resource
    TimerService timerService;

    private static final Logger logger = Logger.getLogger("cn.hanbell.jrs.TimerBean");

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

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void automaticTimer() {

        //预约系统提前10分钟发送短信通知
        List<Booking> bookingList = bookingBean.findNeedMsgNotify(0, 0, 10);
        if (bookingList != null && !bookingList.isEmpty()) {
            String[] contents = new String[3];
            for (Booking b : bookingList) {
                contents[0] = b.getName();
                contents[1] = BaseLib.formatDate("yyyy-MM-dd", b.getStartDate()) + " " + BaseLib.formatDate("HH:mm", b.getStartTime());
                BaseLib.sendShortMessage("8aaf07085adadc12015aeae7d82003a4", b.getMobile(), "162371", contents);
                b.setMsgNotified(b.getMsgNotified() - 1);
                bookingBean.update(b);
            }
        }

    }

}
