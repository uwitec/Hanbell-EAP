/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invbal;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

/**
 *
 * @author C0160
 */
public abstract class InvbalBean extends SuperEJBForERP<Invbal> {

    public InvbalBean() {
        super(Invbal.class);
    }

    public List<Invbal> findByItnbrAndFilters(String itnbr, Map<String, Object> filters, Boolean fuzzy) {

        StringBuilder sb = new StringBuilder();
        if (fuzzy) {
            sb.append("SELECT i FROM Invbal i WHERE i.invbalPK.itnbr like :itnbr  AND SUBSTRING(i.invbalPK.wareh,1,1)<>'J' AND ((i.onhand1 - i.preqy1) > 0) ");
        } else {
            sb.append("SELECT i FROM Invbal i WHERE i.invbalPK.itnbr = :itnbr  AND SUBSTRING(i.invbalPK.wareh,1,1)<>'J' AND ((i.onhand1 - i.preqy1) > 0) ");
        }
        boolean flag;
        if (filters != null) {
            for (Map.Entry<String, Object> e : filters.entrySet()) {
                flag = true;
                try {
                    Invbal.class.getDeclaredField(e.getKey());
                } catch (NoSuchFieldException | SecurityException ex) {
                    flag = false;
                }
                if (flag) {
                    sb.append(" AND i.").append(e.getKey()).append(" = :").append(e.getKey());
                } else {
                    sb.append(" AND i.invbalPK.").append(e.getKey()).append(" = :").append(e.getKey());
                }
            }
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString());
        //参数赋值
        query.setParameter("itnbr", itnbr);
        if (filters != null) {
            for (Map.Entry<String, Object> e : filters.entrySet()) {
                query.setParameter(e.getKey(), e.getValue());
            }
        }
        return query.getResultList();

    }

}
