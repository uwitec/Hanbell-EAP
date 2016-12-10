/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKCG007;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1491    
 */
@Stateless
@LocalBean
public class HKCG007Bean extends  SuperEJBForEFGP<HKCG007>{

    public HKCG007Bean() {
        super(HKCG007.class);
    }

}
