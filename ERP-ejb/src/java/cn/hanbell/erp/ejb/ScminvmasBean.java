/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Scminvmas;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */

@Stateless
@LocalBean
public class ScminvmasBean extends SuperEJBForERP<Scminvmas> {

    public ScminvmasBean() {
        super(Scminvmas.class);
    }
    
    
}