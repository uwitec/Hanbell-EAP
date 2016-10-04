/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.util.SuperEJB;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author C0160
 * @param <T>
 * @param <V>
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    protected abstract SuperEJB getSuperEJB();

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        getSuperEJB().persist(entity);
    }

//    public void edit(T entity) {
//        getSuperEJB().update(entity);
//    }
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, T entity) {
        getSuperEJB().persist(entity);
    }

    public void remove(T entity) {
        getSuperEJB().delete(entity);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public T findById(@PathParam("id") PathSegment id) {
        return (T) getSuperEJB().findById(id.getPath());
    }

    public List<T> findAll() {
        return getSuperEJB().findAll();
    }

    public int getRowCount() {
        return getSuperEJB().getRowCount();
    }

}
