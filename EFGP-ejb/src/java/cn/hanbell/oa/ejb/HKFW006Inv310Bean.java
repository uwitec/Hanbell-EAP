/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKFW006Inv310;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class HKFW006Inv310Bean extends  SuperEJBForEFGP<HKFW006Inv310>{

    public HKFW006Inv310Bean() {
        super(HKFW006Inv310.class);
    }

}
