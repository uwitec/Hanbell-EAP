/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Invbal;
import cn.hanbell.erp.entity.Invwhsafe;
import cn.hanbell.erp.entity.InvwhsafePK;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1587
 */
@Stateless
@LocalBean
public class InvwhsafeBean extends SuperEJBForERP<Invwhsafe>{
    
    @EJB
    private InvbalBean invbalBean;
    
    public InvwhsafeBean(){
        super(Invwhsafe.class);
    }
    public Boolean initINVWHSAFE(String a,String b ) {
        
        
        return true;
    }
    public int findByAll( String a,String b){
        int count = 0;
//循环查询数据库
//        List list = new ArrayList<Invwhsafe>();
//        list  = this.findAll();
        List<Invwhsafe> list  = this.findAll();
//        for(int i =0;(i+1)<list.size();i++){   
//        }
        int c[] = null;
        int i=0;
        for (Invwhsafe invwhsafe : list) {
            InvwhsafePK PK = invwhsafe.getInvwhsafePK();
            double low;
            double high;
            double take;
            double onhand1;
            BigDecimal Lowest = invwhsafe.getLowest();
            BigDecimal Highest = invwhsafe.getHighest();
            BigDecimal Mintake = invwhsafe.getMintake();
            low = Lowest.doubleValue();
            high = Highest.doubleValue();
            take = Mintake.doubleValue();
            String itnbr = PK.getItnbr();
            String wareh = PK.getWareh();
            Invbal IB = invbalBean.findByItnbrAndWareh(itnbr, wareh);
            BigDecimal Onhand1 = IB.getOnhand1();
            onhand1 = Onhand1.doubleValue();
            if(onhand1<low){
            c[i]=(int)((high-onhand1)/take); 
            }
        }  
    
        
        return count;
    }

   
    
    
    
    
    
}
