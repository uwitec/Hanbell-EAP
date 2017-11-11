/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.comm;

import cn.hanbell.oa.ejb.FunctionsBean;
import cn.hanbell.oa.ejb.OrganizationUnitBean;
import cn.hanbell.oa.ejb.TitleBean;
import cn.hanbell.oa.ejb.UsersBean;
import cn.hanbell.oa.entity.Functions;
import cn.hanbell.oa.entity.OrganizationUnit;
import cn.hanbell.oa.entity.Title;
import cn.hanbell.oa.entity.Users;
import cn.hanbell.util.SuperEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperEJBForEFGP<T> extends SuperEJB<T> {

    @PersistenceContext(unitName = "EFGP-ejbPU")
    private EntityManager em;

    @EJB
    protected UsersBean usersBean;
    @EJB
    protected FunctionsBean functionsBean;
    @EJB
    protected OrganizationUnitBean organizationUnitBean;
    @EJB
    protected TitleBean titleBean;

    protected Users currentUser;
    protected Functions userFunction;
    protected OrganizationUnit organizationUnit;
    protected Title userTitle;

    public SuperEJBForEFGP(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public T findByOID(Object value) {
        Query query = getEntityManager().createNamedQuery(getClassName() + ".findByOID");
        query.setParameter("oid", value);
        try {
            Object entity = query.getSingleResult();
            if (entity != null) {
                return (T) entity;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public T findByPSN(Object value) {
        Query query = getEntityManager().createNamedQuery(getClassName() + ".findByPSN");
        query.setParameter("psn", value);
        try {
            Object entity = query.getSingleResult();
            if (entity != null) {
                return (T) entity;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> findByFSN(Object value) {
        Query query = getEntityManager().createNamedQuery(getClassName() + ".findByFSN");
        query.setParameter("fsn", value);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Users findUserByUserno(String userno) {
        try {
            return usersBean.findById(userno);
        } catch (Exception ex) {
            return null;
        }
    }

    public OrganizationUnit findOrgUnitByDeptno(String deptno) {
        try {
            return organizationUnitBean.findById(deptno);
        } catch (Exception ex) {
            return null;
        }
    }

    public void initUserInfo(String userid) {
        this.currentUser = usersBean.findById(userid);
        this.userFunction = functionsBean.findByUserOID(currentUser.getOid());
        this.organizationUnit = userFunction.getOrganizationUnit();
        this.userTitle = titleBean.findByOUOIDAndUserOID(organizationUnit.getOid(), getCurrentUser().getOid());
        if (organizationUnit != null) {
            this.currentUser.setDeptno(organizationUnit.getId());
            this.currentUser.setDeptname(organizationUnit.getOrganizationUnitName());
        }
        if (userTitle != null) {
            this.currentUser.setTitle(userTitle);
        }
    }

    /**
     * @return the currentUser
     */
    public Users getCurrentUser() {
        return currentUser;
    }

    /**
     * @return the userFunction
     */
    public Functions getUserFunction() {
        return userFunction;
    }

    /**
     * @return the organizationUnit
     */
    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }

    /**
     * @return the userTitle
     */
    public Title getUserTitle() {
        return userTitle;
    }

}
