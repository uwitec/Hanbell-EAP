/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.jrs.SuperRESTForEFGP;
import cn.hanbell.oa.app.WorkType;
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
@Path("efgp/worktype")
@javax.enterprise.context.RequestScoped
public class WorkTypeFacadeREST extends SuperRESTForEFGP<WorkType> {

    public WorkTypeFacadeREST() {
        super(WorkType.class);
    }

    @Override
    protected SuperEJBForEFGP getSuperEJB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WorkType> findAll(String appid, String token) {
        if (isAuthorized(appid, token)) {
            try {
                List<WorkType> data = new ArrayList<>();
                data.add(new WorkType("1", "常日班：08：00-17：10"));
                data.add(new WorkType("2", "加工早班：08：00-20：20"));
                data.add(new WorkType("3", "加工晚班：20：00-8：20"));
                data.add(new WorkType("4", "三次元早班：08:00-20:20"));
                data.add(new WorkType("5", "三次元晚班：20：00-08：20"));
                data.add(new WorkType("6", "试车早班：08：00-16：10"));
                data.add(new WorkType("7", "试车中班：16：00-00：10"));
                data.add(new WorkType("8", "试车晚班：00：00-08：10"));
                data.add(new WorkType("9", "装配早班：08：00-16：30"));
                data.add(new WorkType("10", "装配中班：16：10-00：40"));
                data.add(new WorkType("11", "物流早班：07：00-16：10"));
                data.add(new WorkType("12", "物流中班：13：00-21：40"));
                data.add(new WorkType("13", "特殊班别：09：00-16：10"));
                return data;
            } catch (Exception ex) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } else {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

}
