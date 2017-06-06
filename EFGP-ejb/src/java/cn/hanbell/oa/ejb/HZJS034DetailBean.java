/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.WARMBBean;
import cn.hanbell.crm.entity.WARMB;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HZJS034;
import cn.hanbell.oa.entity.HZJS034Detail;
import cn.hanbell.util.BaseLib;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class HZJS034DetailBean extends SuperEJBForEFGP<HZJS034Detail> {

    @EJB
    private HZJS034Bean hzjs034Bean;
    @EJB
    private HZJS034DetailBean hzjs034DetailBean;
    @EJB
    private WARMBBean warmbBean;
  //  @EJB
   // private InvclswahBean invclswahBean ;
    public HZJS034DetailBean() {
        super(HZJS034Detail.class);
    }



}
