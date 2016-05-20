/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.FormInstance;
import cn.hanbell.util.BaseLib;
import java.io.Serializable;
import java.rmi.RemoteException;
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
