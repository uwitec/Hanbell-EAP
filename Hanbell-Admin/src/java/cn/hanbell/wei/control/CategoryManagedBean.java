/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.wei.control;

import cn.hanbell.eap.web.SuperSingleBean;
import cn.hanbell.wei.ejb.CategoryBean;
import cn.hanbell.wei.entity.Category;
import cn.hanbell.wei.lazy.CategoryModel;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class CategoryManagedBean extends SuperSingleBean<Category> {

    @EJB
    private CategoryBean categoryBean;

    public CategoryManagedBean() {
        super(Category.class);
    }

    @Override
    public String getAppImgPath() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getInitParameter("cn.hanbell.wx.web.appimgpath");
    }

    @Override
    public String getAppDataPath() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getInitParameter("cn.hanbell.wx.web.appdatapath");
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        setEntityList(categoryBean.findAll());
        if (this.entityList != null && !this.entityList.isEmpty()) {
            jab = categoryBean.createJsonArrayBuilder(this.entityList);
            this.buildJsonFile(jab.build(), getAppDataPath(), "category.json");
        }
    }

    @Override
    public void init() {
        this.superEJB = categoryBean;
        setModel(new CategoryModel(categoryBean));
        super.init();
    }

}
