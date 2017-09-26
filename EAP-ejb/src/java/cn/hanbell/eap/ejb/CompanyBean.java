/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.ejb;

import cn.hanbell.eap.comm.SuperEJBForEAP;
import cn.hanbell.eap.entity.Company;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Named
@Stateless
@LocalBean
public class CompanyBean extends SuperEJBForEAP<Company> {

    public CompanyBean() {
        super(Company.class);
    }

    public Company findByCompany(String value) {
        Query query = getEntityManager().createNamedQuery("Company.findByCompany");
        query.setParameter("company", value);
        try {
            Object o = query.getSingleResult();
            return (Company) o;
        } catch (Exception ex) {
            return null;
        }
    }

}
