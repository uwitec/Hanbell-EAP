/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.PurvdrBuyer;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1368
 */
@Stateless
@LocalBean
public class PurvdrBuyerBean extends SuperEJBForERP<PurvdrBuyer> {

  

    public PurvdrBuyerBean() {
        super(PurvdrBuyer.class);
    }

}
