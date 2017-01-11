/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.SystemNameBean;
import cn.hanbell.eap.entity.SystemName;
import cn.hanbell.eap.lazy.SystemNameModel;
import cn.hanbell.eap.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name="systemNameManagedBean")
@SessionScoped
public class SystemNameManagedBean extends SuperSingleBean<SystemName> {

    @EJB
    private SystemNameBean systemNameBean;

    /**
     * Creates a new instance of SystemNameManagedBean
     */
    public SystemNameManagedBean() {
        super(SystemName.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setSortid(1);
    }

    @Override
    public void init() {
        this.superEJB = systemNameBean;
        setModel(new SystemNameModel(systemNameBean));
        super.init();
    }

}
