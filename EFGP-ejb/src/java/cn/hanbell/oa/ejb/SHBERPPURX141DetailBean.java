/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.SHBERPPURX141Detail;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class SHBERPPURX141DetailBean extends SuperEJBForEFGP<SHBERPPURX141Detail>{

    public SHBERPPURX141DetailBean() {
        super(SHBERPPURX141Detail.class);
    }

}
