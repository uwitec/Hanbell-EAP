/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKCW002purDetail;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1368
 */
@Stateless
@LocalBean
public class HKCW002purDetailBean extends  SuperEJBForEFGP<HKCW002purDetail>{

   public HKCW002purDetailBean(){
       super(HKCW002purDetail.class);
   }
}
