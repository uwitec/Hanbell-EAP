/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.entity.ItemMaster;
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
@Path("shberp.servitemmaster")
public class ItemMasterFacadeREST extends AbstractFacade<ItemMaster> {
    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public ItemMasterFacadeREST() {
        super(ItemMaster.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ItemMaster entity) {
         throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, ItemMaster entity) {
         throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
         throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{category}")
    @Produces({"application/json"})
    public List<ItemMaster> findByCategory(@PathParam("category") String category) {
        Query query = em.createNativeQuery("SELECT mc_smalltype_itnbr,itdsc FROM fw_mc_smalltype,invmas WHERE mc_smalltype_itnbr=itnbr AND mc_bigtype_itnbr = '" + category + "'");
        List result = query.getResultList();
        List<ItemMaster> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemMaster newEntity;
         for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ItemMaster(row[0].toString(), row[1].toString());
                dataList.add(newEntity);
            }
        }
        return dataList;
    }

    @GET
    @Path("{model}/{category}")
    @Produces({"application/json"})
    public List<ItemMaster> findByModelAndCategory(@PathParam("model") String model,@PathParam("category") String category) {
        Query query = em.createNativeQuery("SELECT mc_smalltype_itnbr,itdsc FROM fw_mc_smalltype,invmas WHERE mc_smalltype_itnbr=itnbr AND mc_type_code = '" + model + "' AND mc_bigtype_itnbr = '" + category + "'");
        List result = query.getResultList();
        List<ItemMaster> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemMaster newEntity;
         for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ItemMaster(row[0].toString(), row[1].toString());
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
