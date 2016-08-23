/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.CdrscheduleBean;
import cn.hanbell.erp.entity.Cdrschedule;
import cn.hanbell.erp.entity.CdrschedulePK;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.cdrschedule")
public class CdrscheduleFacadeREST extends AbstractFacade<Cdrschedule> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;
    
    @EJB
    private CdrscheduleBean cdrscheduleBean;

    private CdrschedulePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;facno=facnoValue;cdrno=cdrnoValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        CdrschedulePK key = new CdrschedulePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> facno = map.get("facno");
        if (facno != null && !facno.isEmpty()) {
            key.setFacno(facno.get(0));
        }
        java.util.List<String> cdrno = map.get("cdrno");
        if (cdrno != null && !cdrno.isEmpty()) {
            key.setCdrno(cdrno.get(0));
        }
        return key;
    }

    public CdrscheduleFacadeREST() {
        super(Cdrschedule.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Cdrschedule entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, Cdrschedule entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Cdrschedule find(@PathParam("id") PathSegment id) {
        CdrschedulePK key = getPrimaryKey(id);
        return super.find(key);
    }

    //QueryParam
    @GET
    @Path("{cusno}/{filter}")
    @Produces({"application/json"})
    public List<Cdrschedule> findByCustomer(@PathParam("cusno") String cusno, @PathParam("filter") PathSegment filter) {
        String key,value,state;
        state = filter.getPath();  
        MultivaluedMap<String, String> mm = filter.getMatrixParameters();
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, List<String>> entrySet : mm.entrySet()) {
            key = entrySet.getKey();
            if (entrySet.getValue() != null) {
                value = entrySet.getValue().get(0);
            } else {
                value = "";
            }
            filters.put(key, value);
        }
        return cdrscheduleBean.findByCustomerAndStatusAndFilters(cusno, state, filters);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
