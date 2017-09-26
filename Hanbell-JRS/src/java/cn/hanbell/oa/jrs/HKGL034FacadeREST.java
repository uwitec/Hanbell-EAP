/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.jrs.ResponseMessage;
import cn.hanbell.jrs.SuperRESTForEFGP;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.ejb.HKGL034Bean;
import cn.hanbell.oa.entity.HKGL034;
import cn.hanbell.oa.model.HKGL034DetailModel;
import cn.hanbell.oa.model.HKGL034Model;
import cn.hanbell.util.BaseLib;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author C0160
 */
@Path("efgp/hkgl034")
@javax.enterprise.context.RequestScoped
public class HKGL034FacadeREST extends SuperRESTForEFGP<HKGL034> {

    @EJB
    private HKGL034Bean hkgl034Bean;

    @Override
    protected SuperEJBForEFGP getSuperEJB() {
        return hkgl034Bean;
    }

    public HKGL034FacadeREST() {
        super(HKGL034.class);
    }

    @POST
    @Path("create/wechat")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage create(OvertimeApplication entity, @QueryParam("appid") String appid, @QueryParam("token") String token) {
        if (isAuthorized(appid, token)) {
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

                m = new HKGL034Model();
                m.setApplyDate(BaseLib.getDate());
                m.setApplyUser(workFlowBean.getCurrentUser().getId());
                m.setHdnApplyUser(workFlowBean.getCurrentUser().getUserName());
                m.setApplyDept(workFlowBean.getUserFunction().getOrganizationUnit().getId());
                m.setHdnApplyDept(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
                m.setType(entity.getFormType());
                m.setHdnType(entity.getFormTypeDesc());
                //根据部门设置公司
                m.setFacno(workFlowBean.getCompanyByDeptId(m.getApplyDept()));
                m.setHdnFacno(m.getFacno());

                for (OvertimeApplicationDetail oad : entity.getDetailList()) {
                    d = new HKGL034DetailModel();
                    d.setSeq(oad.getSeq());
                    d.setDeptTxt(workFlowBean.getUserFunction().getOrganizationUnit().getId());
                    d.setDeptLbl(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
                    d.setEmployee(workFlowBean.getCurrentUser().getId());
                    d.setEmployeeName(workFlowBean.getCurrentUser().getUserName());
                    d.setContent(oad.getContent());
                    d.setDate1Txt(oad.getDate1());
                    d.setTime1Txt(oad.getTime1());
                    d.setTime2Txt(oad.getTime2());
                    d.setHour(oad.getHour());
                    if (oad.getLunch()) {
                        d.setHdnLunch("用餐");
                    } else {
                        d.setHdnLunch("不用餐");
                    }
                    if (oad.getDinner()) {
                        d.setHdnDinner("用餐");
                    } else {
                        d.setHdnDinner("不用餐");
                    }
                    detailList.add(d);
                }

                String formInstance = workFlowBean.buildXmlForEFGP("HK_GL034", m, details);
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
        } else {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public ResponseMessage create(MCHKGL034 entity, @QueryParam("appid") String appid, @QueryParam("token") String token) {
        if (isAuthorized(appid, token)) {
            if (entity == null || entity.getBody() == null || entity.getBody().isEmpty()) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            }
            HKGL034Model m;
            HKGL034DetailModel d;
            List<HKGL034DetailModel> detailList = new ArrayList<>();
            LinkedHashMap<String, List<?>> details = new LinkedHashMap<>();
            details.put("Detail", detailList);
            try {
                //初始化发起人
                workFlowBean.initUserInfo(entity.getHead().getId());

                m = new HKGL034Model();
                m.setApplyDate(BaseLib.getDate());
                m.setApplyUser(workFlowBean.getCurrentUser().getId());
                m.setHdnApplyUser(workFlowBean.getCurrentUser().getUserName());
                m.setApplyDept(workFlowBean.getUserFunction().getOrganizationUnit().getId());
                m.setHdnApplyDept(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
                m.setType(entity.getHead().getFormType());
                m.setHdnType(entity.getHead().getFormTypeDesc());
                //根据部门设置公司
                m.setFacno(entity.getHead().getCompany());
                m.setHdnFacno(m.getFacno());
                int seq = 0;
                    seq++;
                    d = new HKGL034DetailModel();
                    d.setSeq(String.valueOf(seq));
                    d.setDeptTxt(workFlowBean.getUserFunction().getOrganizationUnit().getId());
                    d.setDeptLbl(workFlowBean.getUserFunction().getOrganizationUnit().getOrganizationUnitName());
                    d.setEmployee(workFlowBean.getCurrentUser().getId());
                    d.setEmployeeName(workFlowBean.getCurrentUser().getUserName());
                    d.setContent(oad.getNote());
                    d.setDate1Txt(oad.getDate());
                    d.setTime1Txt(oad.getStarttime());
                    d.setTime2Txt(oad.getEndtime());
                    d.setHour(oad.getWorktime());
                    if (oad.getLunch().equals("Y")) {
                        d.setHdnLunch("用餐");
                    } else {
                        d.setHdnLunch("不用餐");
                    }
                    if (oad.getDinner().equals("Y")) {
                        d.setHdnDinner("用餐");
                    } else {
                        d.setHdnDinner("不用餐");
                    }
                    detailList.add(d);
                }

                String formInstance = workFlowBean.buildXmlForEFGP("HK_GL034", m, details);
                String subject = workFlowBean.getCurrentUser().getUserName() + "加班申请";
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
        } else {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
    }

}
