/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.jrs.SuperRESTForEFGP;
import cn.hanbell.oa.app.LeaveKind;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("efgp/leavekind")
@javax.enterprise.context.RequestScoped
public class LeaveKindFacadeREST extends SuperRESTForEFGP<LeaveKind> {

    public LeaveKindFacadeREST() {
        super(LeaveKind.class);
    }

    @Override
    protected SuperEJBForEFGP getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LeaveKind> findAll(String appid, String token) {
        if (isAuthorized(appid, token)) {
            try {
                List<LeaveKind> data = new ArrayList<>();
                data.add(new LeaveKind("1", "年休假"));
                data.add(new LeaveKind("2", "事假"));
                data.add(new LeaveKind("3", "病假"));
                data.add(new LeaveKind("4", "婚假"));
                data.add(new LeaveKind("5", "产检假"));
                data.add(new LeaveKind("6", "产假"));
                data.add(new LeaveKind("7", "陪产假"));
                data.add(new LeaveKind("8", "哺乳假"));
                data.add(new LeaveKind("9", "无薪假"));
                data.add(new LeaveKind("10", "工伤假"));
                data.add(new LeaveKind("11", "公假"));
                data.add(new LeaveKind("12", "丧假"));
                data.add(new LeaveKind("13", "补休假"));
                return data;
            } catch (Exception ex) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } else {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

}
