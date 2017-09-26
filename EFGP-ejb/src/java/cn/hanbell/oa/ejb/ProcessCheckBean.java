/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.ProcessCheck;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class ProcessCheckBean extends SuperEJBForEFGP<ProcessCheck> {

    public ProcessCheckBean() {
        super(ProcessCheck.class);
    }

    public List<ProcessCheck> findByPSN(String serialNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Select ProcessInstance.serialNumber, WorkItem.currentState, Users.id ,Users.userName,WorkItem.workItemName, \n"
                + "       dbo.WorkItem.createdTime, WorkItem.completedTime from ProcessInstance \n"
                + "inner join WorkItem on WorkItem.contextOID=ProcessInstance.contextOID\n"
                + "inner join Users on Users.OID =  WorkItem.performerOID \n"
                + "inner join ProcessContext on ProcessContext.OID= ProcessInstance.contextOID\n"
                + "inner join ProcessPackage_ProcessDef on ProcessPackage_ProcessDef.ProcessPackageOID =ProcessContext.processPackageOID \n"
                + "inner join ActivityDefinition on ProcessPackage_ProcessDef.ProcessDefinitionOID = ActivityDefinition.containerOID\n"
                + "inner join ParticipantActivityInstance on ParticipantActivityInstance.contextOID=ProcessInstance.contextOID and WorkItem.containerOID=ParticipantActivityInstance.OID and ParticipantActivityInstance.definitionId=ActivityDefinition.id\n"
                + "left join ReassignWorkItemAuditData on ReassignWorkItemAuditData.sourceOID = WorkItem.OID\n"
                + "Where ProcessInstance.serialNumber = '" + serialNumber + "'");
        sb.append(" UNION Select ProcessInstance.serialNumber, WorkItem.currentState, Users.id , Users.userName,WorkItem.workItemName,\n"
                + "        dbo.WorkItem.createdTime, WorkItem.completedTime from ProcessInstance \n"
                + "inner join WorkItem on WorkItem.contextOID=ProcessInstance.contextOID\n"
                + "inner join Users on Users.OID =  WorkItem.performerOID \n"
                + "inner join ProcessContext on ProcessContext.OID= ProcessInstance.contextOID\n"
                + "inner join ProcessPackage_ProcessDef on ProcessPackage_ProcessDef.ProcessPackageOID =ProcessContext.processPackageOID \n"
                + "inner join ActivitySetDefinition on ProcessPackage_ProcessDef.ProcessDefinitionOID = ActivitySetDefinition.containerOID\n"
                + "inner join ActivityDefinition on ActivitySetDefinition.OID = ActivityDefinition.containerOID\n"
                + "inner join ParticipantActivityInstance on ParticipantActivityInstance.contextOID=ProcessInstance.contextOID and WorkItem.containerOID=ParticipantActivityInstance.OID and ParticipantActivityInstance.definitionId = ActivityDefinition.id\n"
                + "left join ReassignWorkItemAuditData on ReassignWorkItemAuditData.sourceOID = WorkItem.OID\n"
                + "Where ProcessInstance.serialNumber = '" + serialNumber + "' order by completedTime");
        Query query = getEntityManager().createNativeQuery(sb.toString());
        List result = query.getResultList();
        List<ProcessCheck> dataList = new ArrayList<>();
        if (!result.isEmpty() && null != result) {
            ProcessCheck newEntity;
            for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ProcessCheck(row[0].toString(), row[2].toString(), row[3].toString(), row[4].toString(), row[5].toString(), row[6].toString(), Integer.parseInt(row[1].toString()));
                dataList.add(newEntity);
            }
        }
        return dataList;

    }
}
