/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author C0160
 */
@Stateless
public class InvbalBean_NJ extends InvbalBean {

    @PersistenceContext(unitName = "PU_njerp")
    protected EntityManager em;

    public InvbalBean_NJ() {

    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
