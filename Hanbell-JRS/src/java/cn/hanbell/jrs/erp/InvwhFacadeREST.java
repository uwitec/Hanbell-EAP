/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs.erp;

import cn.hanbell.jrs.comm.AbstractFacade;
import cn.hanbell.erp.entity.Invwh;
import cn.hanbell.util.SuperEJB;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.invwh")
public class InvwhFacadeREST extends AbstractFacade<Invwh> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public InvwhFacadeREST() {
        super(Invwh.class);
    }

    @POST
    @Override
    @Consumes({"application/xml"})
    public void create(Invwh entity) {
        super.create(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        //super.remove(super.findById(id));
    }


    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Invwh> findAll() {
        return super.findAll();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
