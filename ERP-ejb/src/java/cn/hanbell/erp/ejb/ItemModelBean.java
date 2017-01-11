/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.ItemModel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
@Named
public class ItemModelBean extends SuperEJBForERP<ItemModel> {

    public ItemModelBean() {
        super(ItemModel.class);
    }

    @Override
    public List<ItemModel> findAll() {
        Query query = getEntityManager().createNativeQuery("SELECT cmcmodel,itnbr FROM cdrdmmodel ORDER BY cmcmodel ");
        List result = query.getResultList();
        List<ItemModel> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemModel newEntity;
            for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ItemModel(row[0].toString(), row[1].toString());
                dataList.add(newEntity);
            }
        }
        return dataList;
    }

    public List<ItemModel> findByKind(String kind) {
        Query query = getEntityManager().createNativeQuery("SELECT cmcmodel,itnbr FROM cdrdmmodel WHERE kind = '" + kind + "' ORDER BY cmcmodel ");
        List result = query.getResultList();
        List<ItemModel> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemModel newEntity;
            for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ItemModel(row[0].toString(), row[1].toString());
                dataList.add(newEntity);
            }
        }
        return dataList;
    }

    public List<ItemModel> findByKind(String kind, int from,int pageSize) {
        Query query = getEntityManager().createNativeQuery("SELECT cmcmodel,itnbr FROM cdrdmmodel WHERE kind = '" + kind + "' ORDER BY cmcmodel ").setFirstResult(from).setMaxResults(pageSize);
        List result = query.getResultList();
        List<ItemModel> dataList = new ArrayList<>();
        if (result != null && !result.isEmpty()) {
            ItemModel newEntity;
            for (int i = 0; i < result.size(); i++) {
                Object[] row = (Object[]) result.get(i);
                newEntity = new ItemModel(row[0].toString(), row[1].toString());
                dataList.add(newEntity);
            }
        }
        return dataList;
    }

}
