/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKFW005;
import cn.hanbell.oa.entity.HKFW005Detail;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class HKFW005Bean  extends SuperEJBForEFGP<HKFW005>{

    @EJB
    private HKFW005DetailBean hkfw005DetailBean;
    
    private List<HKFW005Detail> detailList;
    
    
    
  public HKFW005Bean(){
     super(HKFW005.class);
  }

    @Override
    public void setDetail(Object value) {
        this.detailList = hkfw005DetailBean.findByFSN(value); //To change body of generated methods, choose Tools | Templates.
    }
  
    /**
     * @return the detailList
     */
    public List<HKFW005Detail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<HKFW005Detail> detailList) {
        this.detailList = detailList;
    }
  
  
  
}
