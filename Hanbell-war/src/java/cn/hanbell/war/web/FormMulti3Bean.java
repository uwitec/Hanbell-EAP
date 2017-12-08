/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.war.web;

import cn.hanbell.war.control.UserManagedBean;
import cn.hanbell.eap.ejb.SystemProgramBean;
import cn.hanbell.eap.entity.SystemGrantPrg;
import com.lightshell.comm.FormDetailEntity;
import com.lightshell.comm.FormEntity;
import com.lightshell.comm.FormMulti3ManagedBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author KevinDong
 * @param <T>
 * @param <D1>
 * @param <D2>
 * @param <D3>
 */
public abstract class FormMulti3Bean<T extends FormEntity, D1 extends FormDetailEntity, D2 extends FormDetailEntity, D3 extends FormDetailEntity> extends FormMulti3ManagedBean<T, D1, D2, D3> {

    @EJB
    protected SystemProgramBean sysprgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected String persistenceUnitName;
    protected String appDataPath;
    protected String appResPath;
    protected SystemGrantPrg currentPrgGrant;

    protected Map<String, Object> openOptions;
    protected Map<String, List<String>> openParams;

    /**
     * @param entityClass
     * @param detailClass
     * @param detailClass2
     * @param detailClass3
     */
    public FormMulti3Bean(Class<T> entityClass, Class<D1> detailClass, Class<D2> detailClass2, Class<D3> detailClass3) {
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
        fc = FacesContext.getCurrentInstance();
        ec = fc.getExternalContext();
        appDataPath = ec.getRealPath("/") + ec.getInitParameter("cn.hanbell.web.appdatapath");
        appResPath = ec.getRealPath("/") + ec.getInitParameter("cn.hanbell.web.apprespath");
        reportPath = ec.getRealPath("/") + ec.getInitParameter("cn.hanbell.web.reportpath");
        reportOutputFormat = ec.getInitParameter("cn.hanbell.web.reportoutputformat");
        reportOutputPath = ec.getRealPath("/") + ec.getInitParameter("cn.hanbell.web.reportoutputpath");
        reportViewContext = ec.getInitParameter("cn.hanbell.web.reportviewcontext");
        int beginIndex = fc.getViewRoot().getViewId().lastIndexOf("/") + 1;
        int endIndex = fc.getViewRoot().getViewId().lastIndexOf(".");
        if (userManagedBean.getSystemGrantPrgList() != null && !userManagedBean.getSystemGrantPrgList().isEmpty()) {
            userManagedBean.getSystemGrantPrgList().stream().filter((p) -> (p.getSysprg().getApi().equals(fc.getViewRoot().getViewId().substring(beginIndex, endIndex)))).forEachOrdered((p) -> {
                currentPrgGrant = p;
            });
        }
        if (getCurrentPrgGrant() != null) {
            this.doAdd = getCurrentPrgGrant().getDoadd();
            this.doPriv = getCurrentPrgGrant().getDopriv();
            this.doPrt = getCurrentPrgGrant().getDoprt();
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
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getFormdate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen());
                this.newEntity.setFormid(formid);
            }
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (D1 detail : this.addedDetailList) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (D1 detail : this.editedDetailList) {
                    detail.setPid(newEntity.getFormid());
                }

            }
            if (this.addedDetailList2 != null && !this.addedDetailList2.isEmpty()) {
                for (D2 detail : this.addedDetailList2) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList2 != null && !this.editedDetailList2.isEmpty()) {
                for (D2 detail : this.editedDetailList2) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.addedDetailList3 != null && !this.addedDetailList3.isEmpty()) {
                for (D3 detail : this.addedDetailList3) {
                    detail.setPid(newEntity.getFormid());
                }
            }
            if (this.editedDetailList3 != null && !this.editedDetailList3.isEmpty()) {
                for (D3 detail : this.editedDetailList3) {
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
        return this.appResPath;
    }

    public String getAppResPath() {
        return this.appResPath;
    }

    @Override
    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
    }

    @Override
    public void print() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可打印数据");
            return;
        }
        //设置报表参数
        HashMap<String, Object> reportParams = new HashMap<>();
        reportParams.put("company", userManagedBean.getCurrentCompany().getName());
        reportParams.put("companyFullName", userManagedBean.getCurrentCompany().getFullname());
        reportParams.put("tel", userManagedBean.getCurrentCompany().getTel());
        reportParams.put("fax", userManagedBean.getCurrentCompany().getFax());
        reportParams.put("id", currentEntity.getId());
        reportParams.put("formid", currentEntity.getFormid());
        reportParams.put("JNDIName", this.currentPrgGrant.getSysprg().getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentPrgGrant.getSysprg().getRptformat() != null) {
            reportFormat = this.currentPrgGrant.getSysprg().getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentPrgGrant.getSysprg().getRptdesign();
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
            if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getRptclazz() != null) {
                reportClassLoader = Class.forName(this.currentPrgGrant.getSysprg().getRptclazz()).getClassLoader();
            }
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, reportParams, outputName, reportFormat, null);
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
        if (currentEntity != null && getCurrentPrgGrant() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "V":
                    this.doEdit = getCurrentPrgGrant().getDoedit() && false;
                    this.doDel = getCurrentPrgGrant().getDodel() && false;
                    this.doCfm = false;
                    this.doUnCfm = getCurrentPrgGrant().getDouncfm() && true;
                    break;
                default:
                    this.doEdit = getCurrentPrgGrant().getDoedit() && true;
                    this.doDel = getCurrentPrgGrant().getDodel() && true;
                    this.doCfm = getCurrentPrgGrant().getDocfm() && true;
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
                    showInfoMsg("Info", "更新成功");
                } else {
                    showErrorMsg("Error", "还原前检查失败");
                }
            } catch (Exception ex) {
                showErrorMsg("Error", ex.getMessage());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据");
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
                    showInfoMsg("Info", "更新成功");
                } else {
                    showErrorMsg("Error", "审核前检查失败");
                }
            } catch (Exception ex) {
                showErrorMsg("Error", ex.getMessage());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据");
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
     * @return the currentPrgGrant
     */
    public SystemGrantPrg getCurrentPrgGrant() {
        return currentPrgGrant;
    }

}
