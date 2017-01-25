/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.rpt.control;

import cn.hanbell.eap.ejb.SystemProgramBean;
import cn.hanbell.eap.entity.SystemProgram;
import cn.hanbell.oa.ejb.ProcessInstanceBean;
import cn.hanbell.oa.entity.ProcessInstance;
import cn.hanbell.util.BaseLib;
import cn.hanbell.util.SuperReportManagedBean;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "reportManagedBean")
@ViewScoped
public class ReportManagedBean extends SuperReportManagedBean {

    @EJB
    private SystemProgramBean systemProgramBean;
    @EJB
    private ProcessInstanceBean processInstanceBean ;
    
    private String msg;
    private Map<String, String[]> paramMap;

    protected SystemProgram systemProgram;

    public ReportManagedBean() {
    }

    @Override
    public void construct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        this.reportPath = fc.getExternalContext().getRealPath("/") + fc.getExternalContext().getInitParameter("cn.hanbell.rpt.reportpath");
        this.reportOutputPath = fc.getExternalContext().getRealPath("/") + fc.getExternalContext().getInitParameter("cn.hanbell.rpt.reportoutputpath");
        this.reportViewContext = fc.getExternalContext().getInitParameter("cn.hanbell.rpt.reportviewcontext");
        msg = reportPath;
        super.construct();
    }

    @Override
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        paramMap = fc.getExternalContext().getRequestParameterValuesMap();
        try {
            if (paramMap == null || paramMap.isEmpty() || !paramMap.containsKey("system") || !paramMap.containsKey("api")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("params.xhtml");
            }
            print();
        } catch (Exception ex) {
            Logger.getLogger(ReportManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void print() throws Exception {
        systemProgram = systemProgramBean.findBySystemAndAPI(paramMap.get("system")[0], paramMap.get("api")[0]);
        if (systemProgram == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("deny.xhtml");
        }
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("JNDIName", systemProgram.getRptjndi());
        if (paramMap.containsKey("formid")) {
            if("EFGP".equals(paramMap.get("system")[0])){
                ProcessInstance pi =  processInstanceBean.findByOID(paramMap.get("formid")[0]);
                reportParams.put("formid", pi.getSerialNumber());
            }else{
                reportParams.put("formid", paramMap.get("formid")[0]);
            }
          
        }
        if (paramMap.containsKey("filterFields")) {
            reportParams.put("filterFields", paramMap.get("filterFields")[0]);
        }
        if (paramMap.containsKey("sortFields")) {
            reportParams.put("sortFields", paramMap.get("sortFields")[0]);
        }
        reportOutputFormat = systemProgram.getRptformat();
        String fileName = systemProgram.getApi() + BaseLib.formatDate("yyyyMMddHHmmss", BaseLib.getDate()) + "." + reportOutputFormat;
        String reportName = reportPath + systemProgram.getRptdesign();
        String outputName = reportOutputPath + fileName;
        reportViewPath = reportViewContext + fileName;
        try {
            this.setReportClass(Class.forName(systemProgram.getRptclazz()).getClassLoader());
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, reportParams, outputName, reportOutputFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
