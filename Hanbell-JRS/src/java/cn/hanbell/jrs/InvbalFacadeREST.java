/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.ejb.InvbalBean_GZ;
import cn.hanbell.erp.ejb.InvbalBean_JN;
import cn.hanbell.erp.ejb.InvbalBean_NJ;
import cn.hanbell.erp.ejb.InvbalBean_SHB;
import cn.hanbell.erp.entity.Invbal;
import cn.hanbell.erp.entity.InvbalPK;
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
@Path("shberp.invbal")
public class InvbalFacadeREST extends AbstractFacade<Invbal> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    @EJB
    private InvbalBean_SHB invbalBean_SHB;
    @EJB
    private InvbalBean_GZ invbalBean_GZ;
    @EJB
    private InvbalBean_JN invbalBean_JN;
    @EJB
    private InvbalBean_NJ invbalBean_NJ;

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

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, Invbal entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{itnbr}")
    @Produces({"application/json"})
    public List<Invbal> findByItnbrAndFilter(@PathParam("itnbr") PathSegment query) {
        String key,value;
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
        switch (facno) {
            case "C":
                entityList = invbalBean_SHB.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                break;
            case "G":
                entityList = invbalBean_GZ.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                break;
            case "J":
                entityList = invbalBean_JN.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                break;
            case "N":
                entityList = invbalBean_NJ.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                break;
            default:
                entityList = invbalBean_SHB.findByItnbrAndFilters(query.getPath() + "%", filters, true);
        }
        return entityList;

    }

    @GET
    @Path("{itnbr}/{fuzzy}")
    @Produces({"application/json"})
    public List<Invbal> findByItnbrAndFilterFuzzy(@PathParam("itnbr") PathSegment query, @PathParam("fuzzy") Boolean fuzzy) {

        List<Invbal> entityList;
        String facno = "";
        MultivaluedMap<String, String> mv = query.getMatrixParameters();
        Map<String, Object> filters = new HashMap<>();
        for (Map.Entry<String, List<String>> entrySet : mv.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue().get(0);
            filters.put(key, value);
            if (key.equals("facno")) {
                facno = value;
            }
        }
        if (fuzzy) {
            switch (facno) {
                case "C":
                    entityList = invbalBean_SHB.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                    break;
                case "G":
                    entityList = invbalBean_GZ.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                    break;
                case "J":
                    entityList = invbalBean_JN.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                    break;
                case "N":
                    entityList = invbalBean_NJ.findByItnbrAndFilters(query.getPath() + "%", filters, true);
                    break;
                default:
                    entityList = invbalBean_SHB.findByItnbrAndFilters(query.getPath() + "%", filters, true);
            }
        } else {
            switch (facno) {
                case "C":
                    entityList = invbalBean_SHB.findByItnbrAndFilters(query.getPath(), filters, false);
                    break;
                case "G":
                    entityList = invbalBean_GZ.findByItnbrAndFilters(query.getPath(), filters, false);
                    break;
                case "J":
                    entityList = invbalBean_JN.findByItnbrAndFilters(query.getPath(), filters, false);
                    break;
                case "N":
                    entityList = invbalBean_NJ.findByItnbrAndFilters(query.getPath(), filters, false);
                    break;
                default:
                    entityList = invbalBean_SHB.findByItnbrAndFilters(query.getPath(), filters, false);
            }
        }
        return entityList;

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
