/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.BXD_Detail01;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class BXD_Detail01Bean extends SuperEJBForEFGP<BXD_Detail01> {

    public BXD_Detail01Bean() {
        super(BXD_Detail01.class);
    }
    
}
