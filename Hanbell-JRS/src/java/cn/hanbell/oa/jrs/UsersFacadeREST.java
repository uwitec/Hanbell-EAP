/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.oa.model.LeaveApplication;
import cn.hanbell.jrs.ResponseMessage;
import cn.hanbell.jrs.SuperREST;
import cn.hanbell.oa.ejb.UsersBean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.Users;
import cn.hanbell.util.BaseLib;
import cn.hanbell.util.SuperEJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("efgp/users")
@javax.enterprise.context.RequestScoped
public class UsersFacadeREST extends SuperREST<Users> {

    @Inject
    private UsersBean usersBean;

    @Override
    protected SuperEJB getSuperEJB() {
        return usersBean;
    }

    public UsersFacadeREST() {
        super(Users.class);
    }

    @GET
    @Path("LDAP/single/{id}/{pwd}")
    @Produces({"application/json"})
    public Users findByLDAPAccount(@PathParam("id") String id, @PathParam("pwd") String pwd) {
        try {
            if (BaseLib.ADAuth("172.16.10.6:389", id + "@hanbell.com.cn", pwd)) {
                Users u = usersBean.findById(id);
                if (u != null) {
                    return u;
                } else {
                    throw new WebApplicationException(Response.Status.NOT_FOUND);
                }
            }
        } catch (Exception ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

}
