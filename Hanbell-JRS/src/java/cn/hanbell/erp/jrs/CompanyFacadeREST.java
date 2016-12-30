/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.jrs;

import cn.hanbell.erp.entity.Company;
import cn.hanbell.jrs.SuperRESTForERP;
import cn.hanbell.util.SuperEJB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.company")
public class CompanyFacadeREST extends SuperRESTForERP<Company> {

    public CompanyFacadeREST() {
        super(Company.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Company findById(@PathParam("id") PathSegment id) {
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
    protected SuperEJB getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
