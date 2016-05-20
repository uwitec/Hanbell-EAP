/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.entity.ItemFault;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.itemfault")
public class ItemFaultFacadeREST extends AbstractFacade<ItemFault> {
    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public ItemFaultFacadeREST() {
        super(ItemFault.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemFault entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, ItemFault entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public ItemFault find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<ItemFault> findAll() {
        Query query = em.createNativeQuery("SELECT distinct mc_bigtype_itnbr,mc_bigtype_name FROM fw_mc_bigtype WHERE offer_code='RCSWX' ");
        List result = query.getResultList();
        List<ItemFault> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemFault newEntity;
            for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ItemFault(row[0].toString(), row[1].toString());
                dataList.add(newEntity);
            }
        }
        return dataList;
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
