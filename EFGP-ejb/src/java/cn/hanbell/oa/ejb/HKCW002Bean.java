/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKCW002;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1368
 */
@Stateless
@LocalBean
public class HKCW002Bean  extends  SuperEJBForEFGP<HKCW002>{
    
    
   public HKCW002Bean() {
        super(HKCW002.class);
    }

}
