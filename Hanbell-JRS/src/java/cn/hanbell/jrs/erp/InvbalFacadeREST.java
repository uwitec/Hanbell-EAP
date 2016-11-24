/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs.erp;

import cn.hanbell.jrs.comm.AbstractFacade;
import cn.hanbell.erp.ejb.InvbalBean;
import cn.hanbell.erp.entity.Invbal;
import cn.hanbell.erp.entity.InvbalPK;
import cn.hanbell.util.SuperEJB;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
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
@Path("shberp.invbal")
@javax.enterprise.context.RequestScoped
public class InvbalFacadeREST extends AbstractFacade<Invbal> {

    @Inject
    private InvbalBean invbalBean;

    private InvbalPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;itnbr=itnbrValue;facno=facnoValue;prono=pronoValue;wareh=warehValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        cn.hanbell.erp.entity.InvbalPK key = new cn.hanbell.erp.entity.InvbalPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> itnbr = map.get("itnbr");
        if (itnbr != null && !itnbr.isEmpty()) {
            key.setItnbr(itnbr.get(0));
        }
        java.util.List<String> facno = map.get("facno");
        if (facno != null && !facno.isEmpty()) {
            key.setFacno(facno.get(0));
        }
        java.util.List<String> prono = map.get("prono");
        if (prono != null && !prono.isEmpty()) {
            key.setProno(prono.get(0));
        }
        java.util.List<String> wareh = map.get("wareh");
        if (wareh != null && !wareh.isEmpty()) {
            key.setWareh(wareh.get(0));
        }
        return key;
    }

    public InvbalFacadeREST() {
        super(Invbal.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Invbal entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    public void edit(@PathParam("id") PathSegment id, Invbal entity) {
//        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{itnbr}/list")
    @Produces({"application/json"})
    public List<Invbal> findByItnbrAndFilter(@PathParam("itnbr") PathSegment query) {
        String key, value;
        List<Invbal> entityList;
        String facno = "";
        MultivaluedMap<String, String> mv = query.getMatrixParameters();
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, List<String>> entrySet : mv.entrySet()) {
            key = entrySet.getKey();
            value = entrySet.getValue().get(0);
            filters.put(key, value);
            if (key.equals("facno")) {
                facno = value;
            }
        }
        invbalBean.setCompany(facno);
        return invbalBean.findByItnbrAndFilters(query.getPath() + "%", filters, true);
    }

    @GET
    @Path("{itnbr}/{fuzzy}")
    @Produces({"application/json"})
    public List<Invbal> findByItnbrAndFilterFuzzy(@PathParam("itnbr") PathSegment query, @PathParam("fuzzy") Boolean fuzzy) {
        String key, value;
        List<Invbal> entityList;
        String facno = "";
        MultivaluedMap<String, String> mv = query.getMatrixParameters();
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, List<String>> entrySet : mv.entrySet()) {
            key = entrySet.getKey();
            value = entrySet.getValue().get(0);
            filters.put(key, value);
            if (key.equals("facno")) {
                facno = value;
            }
        }
        invbalBean.setCompany(facno);
        if (fuzzy) {
            entityList = invbalBean.findByItnbrAndFilters(query.getPath() + "%", filters, true);
        } else {
            entityList = invbalBean.findByItnbrAndFilters(query.getPath(), filters, false);
        }
        return entityList;

    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
