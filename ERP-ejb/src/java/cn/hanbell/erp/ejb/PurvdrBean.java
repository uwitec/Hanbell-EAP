/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Purvdr;
import cn.hanbell.erp.entity.PurvdrBuyer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1368
 */
@Stateless
@LocalBean
public class PurvdrBean  extends SuperEJBForERP<Purvdr>{

    @EJB
    private PurvdrBuyerBean purvdrBuyerBean;
    
    private final HashMap<SuperEJBForERP, List<?>> details = new HashMap<>();
    
    private final List<PurvdrBuyer> purvdrBuyerList=new ArrayList<>();



    public PurvdrBean() {
        super(Purvdr.class);
    }

    @Override
    public Boolean initByOAPSN(String psn) {
      details.put(purvdrBuyerBean, purvdrBuyerList);
     return false;
    }

}
