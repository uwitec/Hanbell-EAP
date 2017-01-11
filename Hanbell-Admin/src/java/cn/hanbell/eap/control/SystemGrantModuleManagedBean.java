/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.SystemGrantModuleBean;
import cn.hanbell.eap.ejb.SystemModuleBean;
import cn.hanbell.eap.ejb.SystemUserBean;
import cn.hanbell.eap.entity.SystemGrantModule;
import cn.hanbell.eap.entity.SystemModule;
import cn.hanbell.eap.entity.SystemUser;
import cn.hanbell.eap.lazy.SystemGrantModuleModel;
import cn.hanbell.eap.web.SuperSingleBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "systemGrantModuleManagedBean")
@SessionScoped
public class SystemGrantModuleManagedBean extends SuperSingleBean<SystemGrantModule> {

    @EJB
    private SystemGrantModuleBean sysgrantModuleBean;
    @EJB
    private SystemUserBean systemUserBean;
    @EJB
    private SystemModuleBean sysmoduleBean;

    private List<SystemUser> systemuserList;
    private List<SystemModule> sysmoduleList;

    /**
     * Creates a new instance of SystemGrantModuleManagedBean
     */
    public SystemGrantModuleManagedBean() {
        super(SystemGrantModule.class);
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
    public void init() {
        setSuperEJB(sysgrantModuleBean);
        setModel(new SystemGrantModuleModel(sysgrantModuleBean));
        setSystemuserList(systemUserBean.findAll());
        setSystemModuleList(sysmoduleBean.findAll());
        if (currentEntity == null) {
            currentEntity = newEntity;
        }
        super.init();
    }

    @Override
    public void pull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setToolBar() {

    }

    /**
     * @return the systemuserList
     */
    public List<SystemUser> getSystemuserList() {
        return systemuserList;
    }

    /**
     * @param systemuserList the systemuserList to set
     */
    public void setSystemuserList(List<SystemUser> systemuserList) {
        this.systemuserList = systemuserList;
    }

    /**
     * @return the sysmoduleList
     */
    public List<SystemModule> getSystemModuleList() {
        return sysmoduleList;
    }

    /**
     * @param sysmoduleList the sysmoduleList to set
     */
    public void setSystemModuleList(List<SystemModule> sysmoduleList) {
        this.sysmoduleList = sysmoduleList;
    }

}
