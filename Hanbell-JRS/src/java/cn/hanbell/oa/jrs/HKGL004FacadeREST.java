/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.eap.model.LeaveApplication;
import cn.hanbell.jrs.ResponseMessage;
import cn.hanbell.jrs.SuperREST;
import cn.hanbell.oa.ejb.HKGL004Bean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.HKGL004;
import cn.hanbell.oa.model.HKGL004Model;
import cn.hanbell.util.BaseLib;
import cn.hanbell.util.SuperEJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("efgp/hkgl004")
@javax.enterprise.context.RequestScoped
public class HKGL004FacadeREST extends SuperREST<HKGL004> {

    @Inject
    private WorkFlowBean workFlowBean;
    @Inject
    private HKGL004Bean hkgl004Bean;

    @Override
    protected SuperEJB getSuperEJB() {
        return hkgl004Bean;
    }

    public HKGL004FacadeREST() {
        super(HKGL004.class);
    }

    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage create(LeaveApplication entity) {
        if (entity == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        try {
            workFlowBean.initUserInfo(entity.getEmployee());
            HKGL004Model la = new HKGL004Model();
            //需要处理分公司人员公司别设定逻辑
            la.setFacno(entity.getEmployee().substring(0, 1));
            la.setEmply(workFlowBean.getCurrentUser().getId());
            la.setHdnEmply(workFlowBean.getCurrentUser().getUserName());
            la.setDept(workFlowBean.getUserFunction().getOrganizationUnit().getId());
            la.setHdnDept(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
            la.setCreatetime(BaseLib.getDate());
            la.setLeana(entity.getFormType());
            la.setHdnLeana(entity.getFormTypeDesc());
            la.setLeano(entity.getFormKind());
            la.setHdnLeano(entity.getFormKindDesc());
            la.setLeauser(workFlowBean.getCurrentUser().getId());
            la.setHdnLeauser(workFlowBean.getCurrentUser().getUserName());
            la.setLeatp(entity.getWorkType());
            la.setHdnLeatp(entity.getWorkTypeDesc());
            la.setDayst(BaseLib.getDate("yyyy-MM-dd", entity.getDate1()));
            la.setTime1(entity.getTime1());
            la.setDayed(BaseLib.getDate("yyyy-MM-dd", entity.getDate2()));
            la.setTime2(entity.getTime2());
            la.setLeaday1(entity.getLeaveDay());
            la.setLeaday2(entity.getLeaveHour());
            la.setLeaday3(entity.getLeaveMinute());
            la.setUserTitle(workFlowBean.getUserTitle().getTitleDefinition().getTitleDefinitionName());
            la.setReason(entity.getReason());
            String formInstance = workFlowBean.buildXmlForEFGP("HK_GL004", la, null);
            String subject = la.getHdnLeauser() + entity.getDate1() + "开始请假" + entity.getLeaveDay() + "天" + entity.getLeaveHour()+ "时" + entity.getLeaveMinute() + "分";
            String msg = workFlowBean.invokeProcess(workFlowBean.hostAdd, workFlowBean.hostPort, "PKG_HK_GL004", formInstance, subject);
            String[] rm = msg.split("\\$");
            if (rm.length == 2) {
                return new ResponseMessage(rm[0], rm[1]);
            } else {
                return new ResponseMessage("200", "Code=200");
            }
        } catch (Exception ex) {
            return new ResponseMessage("500", "系统错误更新失败");
        }
    }

}