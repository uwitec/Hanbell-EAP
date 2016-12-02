/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.rpt.control;

import cn.hanbell.efgp.rpt.HZCW027Report;
import cn.hanbell.util.BaseLib;
import cn.hanbell.util.SuperReportManagedBean;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;
import org.eclipse.birt.report.engine.api.EngineConstants;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "reportManagedBean")
@ViewScoped
public class ReportManagedBean extends SuperReportManagedBean {

    private String msg;
    private Map<String, String[]> paramMap;

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
            if (paramMap == null || paramMap.isEmpty()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("404");
            }
            if (!paramMap.containsKey("system") || !paramMap.containsKey("api") || !paramMap.containsKey("formid")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("404");
            }
            setReportClass(Class.forName("cn.hanbell.efgp.rpt.HZCW027Report").getClassLoader());
            print();
        } catch (Exception ex) {
            Logger.getLogger(ReportManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void print() throws Exception {
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("JNDIName", "java:global/Hanbell-EAP/EFGP-ejb/HZCW027Bean!cn.hanbell.oa.ejb.HZCW027Bean");
        reportParams.put("formid", paramMap.get("formid")[0]);
        String fileName = "accountreceipt" + BaseLib.formatDate("yyyyMMddHHmmss", BaseLib.getDate()) + ".pdf";
        String reportName = reportPath + "accountreceipt.rptdesign";
        String outputName = reportOutputPath + fileName;
        reportViewPath = reportViewContext + fileName;
        try {
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, reportParams, outputName, "pdf", null);
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
