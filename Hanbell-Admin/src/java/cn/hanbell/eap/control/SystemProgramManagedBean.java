/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.SystemModuleBean;
import cn.hanbell.eap.ejb.SystemProgramBean;
import cn.hanbell.eap.entity.SystemModule;
import cn.hanbell.eap.entity.SystemProgram;
import cn.hanbell.eap.lazy.SystemProgramModel;
import cn.hanbell.eap.web.SuperSingleBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class SystemProgramManagedBean extends SuperSingleBean<SystemProgram> {

    @EJB
    private SystemModuleBean sysmoduleBean;
    @EJB
    private SystemProgramBean sysprgBean;

    private List<SystemModule> sysmoduleList;

    public SystemProgramManagedBean() {
        super(SystemProgram.class);
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
    public void create() {
        super.create();
        newEntity.setSortid(1);
        newEntity.setNoauto(Boolean.FALSE);
        newEntity.setNochange(Boolean.FALSE);
        newEntity.setDoadd(true);
        newEntity.setDoedit(true);
        newEntity.setDodel(true);
        newEntity.setDoprt(Boolean.FALSE);
        newEntity.setDocfm(true);
        newEntity.setDouncfm(true);
    }

    @Override
    public void init() {
        this.superEJB = sysprgBean;
        setModel(new SystemProgramModel(sysprgBean));
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
