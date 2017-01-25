/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.util.SuperEJB;
import java.util.List;
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
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperREST<T> {

    protected Class<T> entityClass;

    protected abstract SuperEJB getSuperEJB();

    public SuperREST(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage create(T entity) {
        try {
            getSuperEJB().persist(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", "系统错误更新失败");
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage edit(@PathParam("id") PathSegment id, T entity) {
        try {
            getSuperEJB().update(entity);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", "系统错误更新失败");
        }
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public ResponseMessage remove(@PathParam("id") PathSegment id) {
        try {
            T t = (T) getSuperEJB().findById(id.getPath());
            if (t == null) {
                return new ResponseMessage("404", "内容为空");
            }
            getSuperEJB().delete(t);
            return new ResponseMessage("200", "更新成功");
        } catch (Exception ex) {
            return new ResponseMessage("500", "系统错误更新失败");
        }
    }

    @GET
    @Produces({"application/json"})
    public List<T> findAll() {
        return getSuperEJB().findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public T findById(@PathParam("id") PathSegment id) {
        return (T) getSuperEJB().findById(id.getPath());
    }

    @GET
    @Path("{offset}/{pageSize}")
    @Produces({"application/json"})
    public List<T> findAll(@PathParam("offset") int offset, @PathParam("pageSize") int pageSize) {
        return getSuperEJB().findAll(offset, pageSize);
    }

    public int getRowCount() {
        return getSuperEJB().getRowCount();
    }

}
