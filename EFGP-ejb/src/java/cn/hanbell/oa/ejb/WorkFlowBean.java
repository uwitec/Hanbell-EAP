/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.FormInstance;
import cn.hanbell.oa.entity.OrganizationUnit;
import cn.hanbell.oa.entity.Users;
import cn.hanbell.util.BaseLib;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.client.Call;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
@DependsOn({"UsersBean", "FunctionsBean"})
public class WorkFlowBean extends SuperEJBForEFGP<FormInstance> implements Serializable {

    public WorkFlowBean() {
        super(FormInstance.class);
    }

    public String buildXmlForEFGP(String formName, Object master, LinkedHashMap<String, List<?>> details) {

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<").append(formName).append(">");
        buildXmlForEFGPMaster(xmlBuilder, formName, master);
        if (details != null && !details.isEmpty()) {
            for (Map.Entry<String, List<?>> e : details.entrySet()) {
                xmlBuilder.append(buildXmlForEFGPDetails(e.getKey(), e.getValue()));
            }
        }
        xmlBuilder.append("</").append(formName).append(">");
        return xmlBuilder.toString();

    }

    protected void buildXmlForEFGPMaster(StringBuilder builder, String formName, Object master) throws RuntimeException {

        Field[] fields = master.getClass().getDeclaredFields();//不含继承自超类的属性
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                if (f.getName().equals("creator") || f.getName().endsWith("user") || f.getName().endsWith("User")) {
                    Users user = this.findUserByUserno(f.get(master).toString());
                    if (user == null) {
                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, f.get(master).toString() + "用户不存在");
                        throw new RuntimeException();
                    }
                    builder.append("<").append(f.getName()).append("  id=\"").append(f.getName()).append("\" label=\"").append(user.getUserName()).append("\"");
                    builder.append("  hidden=\"").append(user.getOid()).append("\" list_hidden=\"\"");
                    builder.append("  dataType=\"").append(f.getType().getName()).append("\">").append(f.get(master)).append("</").append(f.getName()).append(">");
                } else if (f.getName().equals("dept") || f.getName().equals("department") || f.getName().endsWith("dept") || f.getName().endsWith("Dept")) {
                    OrganizationUnit dept = this.findOrgUnitByDeptno(f.get(master).toString());
                    if (dept == null) {
                        Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, f.get(master).toString() + "部门不存在");
                        throw new RuntimeException();
                    }
                    builder.append("<").append(f.getName()).append("  id=\"").append(f.getName()).append("\" label=\"").append(dept.getOrganizationUnitName()).append("\"");
                    builder.append("  hidden=\"").append(dept.getOid()).append("\" list_hidden=\"\"");
                    builder.append("  dataType=\"").append(f.getType().getName()).append("\">").append(f.get(master)).append("</").append(f.getName()).append(">");
                } else {
                    builder.append("<").append(f.getName()).append("  id=\"").append(f.getName()).append("\"  dataType=\"").append(f.getType().getName()).append("\" perDataProId=\"\">");
                    builder.append(f.get(master)).append("</").append(f.getName()).append(">");
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(formName).log(Level.SEVERE, null, ex);
            }
        }

    }

    protected String buildXmlForEFGPDetails(String detailName, List<?> detailList) {

        StringBuilder detailBuilder = new StringBuilder();
        detailBuilder.append("<").append(detailName).append(" id=\"").append(detailName).append("\"><records>");
        if (detailList != null && !detailList.isEmpty()) {
            int i = 0;
            for (Object detail : detailList) {
                buildEFGPDetail(detailBuilder, detailName, detail, i);
                i++;
            }
        }
        detailBuilder.append("</records></").append(detailName).append(">");
        return detailBuilder.toString();

    }

    protected void buildEFGPDetail(StringBuilder builder, String detailName, Object detail, int index) {

        Field[] fields = detail.getClass().getDeclaredFields();
        builder.append("<record  id = \"");
        builder.append(detailName).append("_");
        builder.append(index).append("\">");
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                builder.append("<item id=\"").append(f.getName()).append("\"");
                builder.append(" dataType=\"").append(f.getType().getName()).append("\" perDataProId=\"\">").append(f.get(detail)).append("</item>");
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(detailName).log(Level.SEVERE, null, ex);
            }
        }
        builder.append("</record>");

    }

    public String invokeProcess(String host, String port, String processId, String formFieldValue, String subject) throws Exception {
        if ((getCurrentUser() == null) || (getUserFunction() == null)) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, "用户或部门不存在");
            return "401$用户或部门不存在";
        }
        return invokeProcess(host, port, processId, getCurrentUser().getId(), getUserFunction().getOrganizationUnit().getId(), formFieldValue, subject);
    }

    public String invokeProcess(String host, String port, String processId, String userId, String orgUnitId, String formFieldValue, String subject) throws Exception {

        currentUser = usersBean.findById(userId);
        if (getCurrentUser() == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, userId + "不存在");
            return "401$用户不存在";
        }
        organizationUnit = organizationUnitBean.findById(orgUnitId);
        if (getOrganizationUnit() == null) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, orgUnitId + "不存在");
            return "401$部门不存在";
        }

        Object[] params = null;
        Object object = null;

        String formOID = null;
        String serialNo = null;
        try {
            //建立一个WebServices调用连接
            Call call = BaseLib.getAXISCall(host, port, "/NaNaWeb/services/WorkflowService?wsdl");
            //查找表单FormOID，一个流程关联多个表单时，返回值用","分开
            call.setOperationName(new QName("WorkflowService", "findFormOIDsOfProcess"));
            //转入流程代号
            params = new Object[]{processId};
            //获取表单唯一代号
            object = call.invoke(params);
            formOID = object.toString();
            //发起一个流程
            call.setOperationName(new QName("WorkflowService", "invokeProcess"));
            params = new Object[]{processId, userId, orgUnitId, formOID, formFieldValue, subject};
            object = call.invoke(params);
            serialNo = object.toString();
            return "200$" + serialNo;
        } catch (ServiceException | RemoteException e) {
            throw new RuntimeException(e);
        } finally {
            currentUser = null;
            userFunction = null;
        }

    }

}
