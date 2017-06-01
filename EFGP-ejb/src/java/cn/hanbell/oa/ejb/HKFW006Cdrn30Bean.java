/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKFW006Cdrn30;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class HKFW006Cdrn30Bean extends SuperEJBForEFGP<HKFW006Cdrn30>{

    public HKFW006Cdrn30Bean() {
        super(HKFW006Cdrn30.class);
    }

    
    
   
}
