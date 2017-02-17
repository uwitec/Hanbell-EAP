/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.SystemGrantModuleBean;
import cn.hanbell.eap.ejb.SystemGrantPrgBean;
import cn.hanbell.eap.ejb.SystemRoleDetailBean;
import cn.hanbell.eap.entity.SystemGrantModule;
import cn.hanbell.eap.entity.SystemGrantPrg;
import cn.hanbell.eap.entity.SystemProgram;
import cn.hanbell.eap.entity.SystemRoleDetail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "menuManagedBean")
@SessionScoped
public class MenuManagedBean implements Serializable {

    @EJB
    private SystemRoleDetailBean systemRoleDetailBean;
    @EJB
    private SystemGrantModuleBean systemGrantModuleBean;
    @EJB
    private SystemGrantPrgBean systemGrantPrgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    private UserManagedBean userManagedBean;

    private List<SystemGrantModule> userModuleGrantList;
    private List<SystemGrantModule> roleModuleGrantList;
    private List<SystemGrantModule> moduleGrantList;
    private List<SystemGrantPrg> userPrgGrantList;
    private List<SystemGrantPrg> rolePrgGrantList;
    private List<SystemGrantPrg> prgGrantList;
    private List<SystemRoleDetail> roleList;

    private MenuModel model;

    public MenuManagedBean() {
    }

    @PostConstruct
    public void init() {

        boolean flag;
        moduleGrantList = new ArrayList<>();
        prgGrantList = new ArrayList<>();

        model = new DefaultMenuModel();

        if (getUserManagedBean() != null) {

            DefaultSubMenu appmenu;
            DefaultSubMenu submenu;
            DefaultMenuItem menuitem;

            menuitem = new DefaultMenuItem("Home");
            menuitem.setId("menu_home");
            menuitem.setOutcome("home");
            menuitem.setIcon("dashboard");

            model.addElement(menuitem);

            appmenu = new DefaultSubMenu("应用");
            appmenu.setIcon("menu");
            //将用户权限和角色权限合并后产生菜单,用户权限优先角色权限
            moduleGrantList.clear();
            userModuleGrantList = systemGrantModuleBean.findByUserId(userManagedBean.getCurrentUser().getId());
            userModuleGrantList.forEach((m) -> {
                moduleGrantList.add(m);
            });
            prgGrantList.clear();
            userPrgGrantList = systemGrantPrgBean.findByUserId(userManagedBean.getCurrentUser().getId());
            userPrgGrantList.forEach((p) -> {
                prgGrantList.add(p);
            });
            roleList = systemRoleDetailBean.findByUserId(userManagedBean.getCurrentUser().getId());
            for (SystemRoleDetail r : roleList) {
                roleModuleGrantList = systemGrantModuleBean.findByRoleId(r.getPid());
                if (userModuleGrantList.isEmpty()) {
                    moduleGrantList.addAll(roleModuleGrantList);
                } else {
                    for (SystemGrantModule m : roleModuleGrantList) {
                        flag = true;
                        for (SystemGrantModule e : userModuleGrantList) {
                            if (e.getSystemModule().getId().compareTo(m.getSystemModule().getId()) == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            moduleGrantList.add(m);
                        }
                    }
                }
                rolePrgGrantList = systemGrantPrgBean.findByRoleId(r.getPid());
                if (userPrgGrantList.isEmpty()) {
                    prgGrantList.addAll(rolePrgGrantList);
                } else {
                    for (SystemGrantPrg p : rolePrgGrantList) {
                        flag = true;
                        for (SystemGrantPrg e : userPrgGrantList) {
                            if (e.getSysprg().getId().compareTo(p.getSysprg().getId()) == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            prgGrantList.add(p);
                        }
                    }
                }
            }
            moduleGrantList.sort((SystemGrantModule o1, SystemGrantModule o2) -> {
                if (o1.getSystemModule().getSortid() < o2.getSystemModule().getSortid()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            prgGrantList.sort((SystemGrantPrg o1, SystemGrantPrg o2) -> {
                if (o1.getSysprg().getSortid() < o2.getSysprg().getSortid()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            userManagedBean.setSystemGrantPrgList(prgGrantList);
            for (SystemGrantModule m : moduleGrantList) {
                submenu = new DefaultSubMenu(m.getSystemModule().getName());
                submenu.setIcon("menu");
                for (SystemGrantPrg p : prgGrantList) {
                    if (p.getPid() == m.getSystemModule().getId()) {
                        menuitem = new DefaultMenuItem(p.getSysprg().getName());
                        menuitem.setIcon("menu");
                        menuitem.setOutcome(p.getSysprg().getApi());
                        submenu.addElement(menuitem);
                    }
                }
                appmenu.addElement(submenu);
            }
            model.addElement(appmenu);

            submenu = new DefaultSubMenu("用户");
            submenu.setIcon("menu");
            menuitem = new DefaultMenuItem("更改密码");
            menuitem.setIcon("menu");
            menuitem.setOutcome("resetPwd");
            submenu.addElement(menuitem);

            model.addElement(submenu);
            //系统管理菜单
            submenu = new DefaultSubMenu("系统");
            submenu.setIcon("menu");
            submenu.setRendered(userManagedBean.getCurrentUser().getUserid().equals("Admin"));

            menuitem = new DefaultMenuItem("用户维护");
            menuitem.setIcon("menu");
            menuitem.setOutcome("systemuser");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("用户授权");
            menuitem.setIcon("menu");
            menuitem.setOutcome("usergrant");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("角色群组");
            menuitem.setIcon("menu");
            menuitem.setOutcome("systemrole");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("角色授权");
            menuitem.setIcon("menu");
            menuitem.setOutcome("rolegrant");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("系统维护");
            menuitem.setIcon("menu");
            menuitem.setOutcome("sysname");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("模块维护");
            menuitem.setIcon("menu");
            menuitem.setOutcome("sysmodule");
            submenu.addElement(menuitem);

            menuitem = new DefaultMenuItem("功能维护");
            menuitem.setIcon("menu");
            menuitem.setOutcome("sysprg");
            submenu.addElement(menuitem);

            model.addElement(submenu);
        }
    }

    @PreDestroy
    public void destory() {

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
     * @return the model
     */
    public MenuModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(MenuModel model) {
        this.model = model;
    }

}
