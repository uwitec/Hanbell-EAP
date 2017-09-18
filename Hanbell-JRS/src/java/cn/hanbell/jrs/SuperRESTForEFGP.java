/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperRESTForEFGP<T> {

    protected Class<T> entityClass;

    protected abstract SuperEJBForEFGP getSuperEJB();

    @Inject
    protected cn.hanbell.eap.ejb.SystemNameBean systemNameBean;

    @Inject
    protected cn.hanbell.oa.ejb.WorkFlowBean workFlowBean;

    public SuperRESTForEFGP(Class<T> entityClass) {
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
    public T findById(@PathParam("id") PathSegment id, @QueryParam("appid") String appid, @QueryParam("token") String token) {
        if (this.systemNameBean.isAuthorized(appid, token)) {
            try {
                return (T) getSuperEJB().findById(id.getPath());
            } catch (Exception ex) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } else {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

    @GET
    @Path("{offset}/{pageSize}")
    @Produces({"application/json"})
    public List<T> findAll(@PathParam("offset") int offset, @PathParam("pageSize") int pageSize) {
        return getSuperEJB().findAll(offset, pageSize);
    }

    protected boolean isAuthorized(String appid, String token) {
        return systemNameBean.isAuthorized(appid, token);
    }

    public int getRowCount() {
        return getSuperEJB().getRowCount();
    }

}
