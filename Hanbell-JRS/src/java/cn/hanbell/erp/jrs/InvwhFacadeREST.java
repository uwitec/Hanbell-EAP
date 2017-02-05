/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.entity.Invwh;
import cn.hanbell.jrs.SuperRESTForERP;
import cn.hanbell.util.SuperEJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp/invwh")
public class InvwhFacadeREST extends SuperRESTForERP<Invwh> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public InvwhFacadeREST() {
        super(Invwh.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
