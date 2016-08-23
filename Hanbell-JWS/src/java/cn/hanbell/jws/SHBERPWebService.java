/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.erp.ejb.ApmpayBean;
import cn.hanbell.erp.ejb.BudgetDetailBean;
import cn.hanbell.erp.ejb.CdrcusBean;
import cn.hanbell.erp.ejb.PurvdrBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author C0160
 */
@WebService(serviceName = "WebService")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SHBERPWebService {

    @EJB
    private ApmpayBean apmpayBean;

    @EJB
    private CdrcusBean cdrcusBean;
    
    @EJB
    private PurvdrBean purvdrBean;

    @EJB
    private BudgetDetailBean budgetDetailBean;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "createCustomerByOAPSN")
    public String createCustomerByOAPSN(@WebParam(name = "psn") String psn) {

        Boolean ret = false;
        try {
            ret = cdrcusBean.initByOAPSN(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createAPM525ByOABXD")
    public String createAPM525ByOABXD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = apmpayBean.initByOABXD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createAPM525ByOAJZD")
    public String createAPM525ByOAJZD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = apmpayBean.initByOAJZD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }
    
    
     @WebMethod(operationName = "createPUR120ByOAPSN")
    public String createPUR120ByOAPSN(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = purvdrBean.initByOAPSN(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }
    
    
     @WebMethod(operationName = "updatePUR120ByOAPSN")
    public String updatePUR120ByOAPSN(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = purvdrBean.updateByOAPSN(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createAPM525ByOAJZGHD")
    public String createAPM525ByOAJZGHD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = apmpayBean.initByOAJZGHD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    
        @WebMethod(operationName = "AddBudgetPlanByOABXD")
        public String AddBudgetPlanByOABXD(@WebParam(name = "psn") String psn){
        Boolean ret = false;
            try {
                ret = budgetDetailBean.AddByOABXD(psn);
            } catch (Exception ex) {
                Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ret) {
                return "200";
            } else {
                return "404";
            }
        }
        
        
        @WebMethod(operationName = "AddBudgetPlanByOAJZD")
        public String AddBudgetPlanByOAJZD(@WebParam(name = "psn") String psn){
        Boolean ret = false;
            try {
                ret = budgetDetailBean.AddByOAJZD(psn);
            } catch (Exception ex) {
                Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ret) {
                return "200";
            } else {
                return "404";
            }
        }

        @WebMethod(operationName = "AddBudgetPlanByOAJZGHD")
        public String AddBudgetPlanByOAJZGHD(@WebParam(name = "psn") String psn){
        Boolean ret = false;
            try {
                ret = budgetDetailBean.AddByOAJZGHD(psn);
            } catch (Exception ex) {
                Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ret) {
                return "200";
            } else {
                return "404";
            }
        }
        
    }
