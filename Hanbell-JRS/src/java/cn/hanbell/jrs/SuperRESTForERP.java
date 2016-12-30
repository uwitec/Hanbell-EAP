/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperRESTForERP<T> extends SuperREST<T> {

    public SuperRESTForERP(Class<T> entityClass) {
        super(entityClass);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage create(T entity) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

    @DELETE
    @Produces({"application/json"})
    @Override
    public ResponseMessage remove(@PathParam("id") PathSegment id) {
        throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
    }

}
