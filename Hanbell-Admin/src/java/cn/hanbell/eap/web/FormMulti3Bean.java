/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.web;

import cn.hanbell.eap.control.UserManagedBean;
import cn.hanbell.eap.ejb.SystemProgramBean;
import cn.hanbell.eap.entity.SystemProgram;
import com.lightshell.comm.FormDetailEntity;
import com.lightshell.comm.FormEntity;
import com.lightshell.comm.FormMulti3ManagedBean;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author KevinDong
 * @param <T>
 * @param <V>
 * @param <X>
 * @param <Z>
 */
public abstract class FormMulti3Bean<T extends FormEntity, V extends FormDetailEntity, X extends FormDetailEntity, Z extends FormDetailEntity> extends FormMulti3ManagedBean<T, V, X, Z> {

    @EJB
    protected SystemProgramBean sysprgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected String persistenceUnitName;
    protected String appDataPath;
    protected String appImgPath;
    protected SystemProgram currentSystemProgram;

    /**
     * @param entityClass
     * @param detailClass
     * @param detailClass2
     * @param detailClass3
     */
    public FormMulti3Bean(Class<T> entityClass, Class<V> detailClass, Class<X> detailClass2, Class<Z> detailClass3) {
        this.entityClass = entityClass;
        this.detailClass = detailClass;
        this.detailClass2 = detailClass2;
        this.detailClass3 = detailClass3;
    }

    @Override
    protected void buildJsonObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildJsonArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void construct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        appDataPath = fc.getExternalContext().getInitParameter("cn.hanbell.eap.web.appdatapath");
        appImgPath = fc.getExternalContext().getInitParameter("cn.hanbell.eap.web.appimgpath");
        reportPath = fc.getExternalContext().getInitParameter("cn.hanbell.eap.web.reportpath");
        reportOutputFormat = fc.getExternalContext().getInitParameter("cn.hanbell.eap.web.reportoutputformat");
        reportOutputPath = fc.getExternalContext().getInitParameter("cn.hanbell.eap.web.reportoutputpath");
        reportViewContext = fc.getExternalContext().getInitParameter("cn.hanbell.eap.web.reportviewcontext");
        int beginIndex = fc.getViewRoot().getViewId().lastIndexOf("/") + 1;
        int endIndex = fc.getViewRoot().getViewId().lastIndexOf(".");
        currentSystemProgram = sysprgBean.findByAPI(fc.getViewRoot().getViewId().substring(beginIndex, endIndex));
        if (getCurrentSystemProgram() != null) {
            this.doAdd = getCurrentSystemProgram().getDoadd();
            this.doPrt = getCurrentSystemProgram().getDoprt();
        }
        super.construct();
    }

    @Override
    public void create() {
        super.create();
        if (newEntity != null) {
            newEntity.setStatus("N");
            newEntity.setCreator(getUserManagedBean().getCurrentUser().getUsername());
            newEntity.setCredateToNow();
        }
        setCurrentEntity(newEntity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentSystemProgram() != null) {
            if (this.getCurrentSystemProgram().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getFormdate(), this.getCurrentSystemProgram().getNolead(), this.getCurrentSystemProgram().getNoformat(), this.getCurrentSystemProgram().getNoseqlen());
                this.newEntity.setFormid(formid);
            }
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (V detail : this.addedDetailList) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (V detail : this.editedDetailList) {
                    detail.setPid(newEntity.getFormid());
                }

            }
            if (this.addedDetailList2 != null && !this.addedDetailList2.isEmpty()) {
                for (X detail : this.addedDetailList2) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList2 != null && !this.editedDetailList2.isEmpty()) {
                for (X detail : this.editedDetailList2) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.addedDetailList3 != null && !this.addedDetailList3.isEmpty()) {
                for (Z detail : this.addedDetailList3) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList3 != null && !this.editedDetailList3.isEmpty()) {
                for (Z detail : this.editedDetailList3) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String getAppDataPath() {
        return this.appDataPath;
    }

    @Override
    public String getAppImgPath() {
        return this.appImgPath;
    }

    @Override
    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
    }

    @Override
    public void print() throws Exception {
        if (currentEntity == null) {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可打印数据");
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("formid", currentEntity.getFormid());
        params.put("JNDIName", this.currentSystemProgram.getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentSystemProgram.getRptformat() != null) {
            reportFormat = this.currentSystemProgram.getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentSystemProgram.getRptdesign();
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
            if (this.currentSystemProgram != null && this.currentSystemProgram.getRptclazz() != null) {
                reportClassLoader = Class.forName(this.currentSystemProgram.getRptclazz()).getClassLoader();
            }
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, params, outputName, reportFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void preview() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.reportViewPath);
    }

    @Override
    public void pull() {

    }

    @Override
    public void push() {
        buildJsonArray();
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && getCurrentSystemProgram() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "V":
                    this.doEdit = getCurrentSystemProgram().getDoedit() && false;
                    this.doDel = getCurrentSystemProgram().getDodel() && false;
                    this.doCfm = false;
                    this.doUnCfm = getCurrentSystemProgram().getDouncfm() && true;
                    break;
                default:
                    this.doEdit = getCurrentSystemProgram().getDoedit() && true;
                    this.doDel = getCurrentSystemProgram().getDodel() && true;
                    this.doCfm = getCurrentSystemProgram().getDocfm() && true;
                    this.doUnCfm = false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setStatus("N");
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser(null);
                    currentEntity.setCfmdate(null);
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "还原前检查失败");
                }
            } catch (Exception ex) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", ex.toString());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setStatus("V");
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setCfmdateToNow();
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败");
                }
            } catch (Exception ex) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", ex.toString());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    /**
     * @return the currentSystemProgram
     */
    public SystemProgram getCurrentSystemProgram() {
        return currentSystemProgram;
    }

}
