/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.oa.model.OvertimeApplication;
import cn.hanbell.jrs.ResponseMessage;
import cn.hanbell.jrs.SuperREST;
import cn.hanbell.oa.ejb.HKGL034Bean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.HKGL034;
import cn.hanbell.oa.model.HKGL034DetailModel;
import cn.hanbell.oa.model.HKGL034Model;
import cn.hanbell.oa.model.OvertimeApplicationDetail;
import cn.hanbell.util.BaseLib;
import cn.hanbell.util.SuperEJB;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
@Path("efgp/hkgl034")
@javax.enterprise.context.RequestScoped
public class HKGL034FacadeREST extends SuperREST<HKGL034> {

    @Inject
    private WorkFlowBean workFlowBean;
    @Inject
    private HKGL034Bean hkgl034Bean;

    @Override
    protected SuperEJB getSuperEJB() {
        return hkgl034Bean;
    }

    public HKGL034FacadeREST() {
        super(HKGL034.class);
    }

    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage create(OvertimeApplication entity) {
        if (entity == null || entity.getDetailList() == null || entity.getDetailList().isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        HKGL034Model m;
        HKGL034DetailModel d;
        List<HKGL034DetailModel> detailList = new ArrayList<>();
        LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();
        details.put("Detail", detailList);
        try {
            workFlowBean.initUserInfo(entity.getEmployee());
            HKGL034Model oa = new HKGL034Model();
            //需要处理分公司人员公司别设定逻辑
            oa.setFacno(entity.getEmployee().substring(0, 1));
            oa.setEmply(workFlowBean.getCurrentUser().getId());
            oa.setHdnEmply(workFlowBean.getCurrentUser().getUserName());
            oa.setDept(workFlowBean.getUserFunction().getOrganizationUnit().getId());
            oa.setHdnDept(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
            oa.setCreatedate(BaseLib.getDate());
            oa.setType(entity.getFormType());
            oa.setHdnType(entity.getFormTypeDesc());

            for (OvertimeApplicationDetail oad : entity.getDetailList()) {
                d = new HKGL034DetailModel();
                d.setSeq(oad.getSeq());
                d.setLunch("不用餐");
                d.setDinner("不用餐");
                d.setJbtimeTxt(oad.getDate1());
                d.setStarttimeTxt(oad.getTime1());
                d.setEndtimeTxt(oad.getTime2());
                d.setHour(oad.getHour());
                d.setJbdeptTxt(workFlowBean.getUserFunction().getOrganizationUnit().getId());
                d.setJbdeptLbl(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
                d.setEmpl(workFlowBean.getCurrentUser().getId());
                d.setEmplname(workFlowBean.getCurrentUser().getUserName());
                d.setContent(oad.getContent());
                detailList.add(d);
            }

            String formInstance = workFlowBean.buildXmlForEFGP("HK_GL034", oa, details);
            String subject = entity.getEmployee() + "加班申请";
            String msg = workFlowBean.invokeProcess(workFlowBean.hostAdd, workFlowBean.hostPort, "PKG_HK_GL034", formInstance, subject);
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
