/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs.erp;

import cn.hanbell.jrs.comm.AbstractFacade;
import cn.hanbell.erp.entity.Cdrdmas;
import cn.hanbell.erp.entity.CdrdmasPK;
import cn.hanbell.util.SuperEJB;
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
import javax.ws.rs.core.PathSegment;

/**
 * I
 *
 * @author C0160
 */
@Stateless
@Path("shberp.cdrdmas")
public class CdrdmasFacadeREST extends AbstractFacade<Cdrdmas> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    private CdrdmasPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;facno=facnoValue;cdrno=cdrnoValue;trseq=trseqValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        CdrdmasPK key = new CdrdmasPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> facno = map.get("facno");
        if (facno != null && !facno.isEmpty()) {
            key.setFacno(facno.get(0));
        }
        java.util.List<String> cdrno = map.get("cdrno");
        if (cdrno != null && !cdrno.isEmpty()) {
            key.setCdrno(cdrno.get(0));
        }
        java.util.List<String> trseq = map.get("trseq");
        if (trseq != null && !trseq.isEmpty()) {
            key.setTrseq(new java.lang.Short(trseq.get(0)));
        }
        return key;
    }

    public CdrdmasFacadeREST() {
        super(Cdrdmas.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Cdrdmas entity) {
        //super.create(entity);
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    public void edit(@PathParam("id") PathSegment id, Cdrdmas entity) {
//        //super.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        //shberp.entity.CdrdmasPK key = getPrimaryKey(id);
        //super.remove(super.findById(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @Override
    public Cdrdmas findById(@PathParam("id") PathSegment id) {
        CdrdmasPK key = getPrimaryKey(id);
        return null;
    }

    @GET
    @Path("{cusno}/{itnbrcus}/{count}")
    @Produces({"application/xml", "application/json"})
    public List<Cdrdmas> findByCustomerAndItnbrcus(@PathParam("cusno") String cusno, @PathParam("itnbrcus") String itnbrcus, @PathParam("count") int count) {
        Query query = em.createNamedQuery("Cdrdmas.findByCusnoAndItnbrcus").setFirstResult(0).setMaxResults(count);
        query.setParameter("cusno", cusno);
        query.setParameter("itnbrcus", "%" + itnbrcus + "%");
        return query.getResultList();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
