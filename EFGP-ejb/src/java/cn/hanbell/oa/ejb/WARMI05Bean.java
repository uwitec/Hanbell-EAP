/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.WARMI05;
import cn.hanbell.oa.entity.WARMI05Detail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class WARMI05Bean extends SuperEJBForEFGP<WARMI05> {

    @EJB
    private WARMI05DetailBean warmi05DetailBean;

    private List<WARMI05Detail> detailList;

    public WARMI05Bean() {
        super(WARMI05.class);
    }

    @Override
    public void setDetail(Object value) {
        detailList = warmi05DetailBean.findByFSN(value);
        if (detailList == null) {
            detailList = new ArrayList<>();
        }
    }

    /**
     * @return the detailList
     */
    public List<WARMI05Detail> getDetailList() {
        return detailList;
    }

}
