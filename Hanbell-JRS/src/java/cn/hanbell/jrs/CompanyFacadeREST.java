/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.entity.Company;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.company")
public class CompanyFacadeREST extends AbstractFacade<Company> {

    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public CompanyFacadeREST() {
        super(Company.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Company entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Company entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Company find(@PathParam("id") String id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Company> findAll() {
        List<Company> entityList = new ArrayList();
        entityList.add(new Company("C", "上海汉钟"));
        entityList.add(new Company("G", "汉钟广州"));
        entityList.add(new Company("J", "汉钟济南"));
        entityList.add(new Company("N", "汉钟南京"));
        return entityList;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
