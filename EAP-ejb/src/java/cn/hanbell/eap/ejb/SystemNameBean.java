/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.ejb;

import cn.hanbell.eap.comm.SuperEJBForEAP;
import cn.hanbell.eap.entity.SystemName;
import javax.ejb.Stateless;

/**
 *
 * @author C0160
 */
@Stateless
public class SystemNameBean extends SuperEJBForEAP<SystemName> {

    public SystemNameBean() {
        super(SystemName.class);
    }
    
}
