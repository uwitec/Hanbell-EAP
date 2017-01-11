/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.SystemModuleBean;
import cn.hanbell.eap.entity.SystemModule;
import cn.hanbell.eap.lazy.SystemModuleModel;
import cn.hanbell.eap.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name="systemModuleManagedBean")
@SessionScoped
public class SystemModuleManagedBean extends SuperSingleBean<SystemModule> {

    @EJB
    private SystemModuleBean sysmoduleBean;

    /**
     * Creates a new instance of SystemModuleManagedBean
     */
    public SystemModuleManagedBean() {
        super(SystemModule.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setSortid(1);
    }

    @Override
    public void init() {
        this.superEJB = sysmoduleBean;
        setModel(new SystemModuleModel(sysmoduleBean));
        if (currentEntity == null) {
            setCurrentEntity(getNewEntity());
        }
        super.init();
    }

}
