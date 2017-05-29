package cn.hanbell.jws;

import cn.hanbell.erp.ejb.InvmasBean;
import cn.hanbell.oa.ejb.HKFW004DetailBean;
import cn.hanbell.oa.ejb.HKFW006Bean;
import cn.hanbell.oa.ejb.HZJS034DetailBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
@WebService(serviceName = "CRM")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CRMWebService {

    @EJB
    private HKFW004DetailBean hzfw004DetailBean;
    @EJB
    private HZJS034DetailBean hzjs034DetailBean;
    @EJB
    private HKFW006Bean hkfw006Bean;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "updateCRMREPTDByOAHKFW004Detail")
    public String createHZFWDD05ByOAFSN(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzfw004DetailBean.updateReptdByOAHKFW004(psn);
        } catch (Exception ex) {
            Logger.getLogger(CRMWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "rollbackCRMREPTDByOAHKFW004")
    public String rollbackCRMREPTDByOAHKFW004(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzfw004DetailBean.rollbackReptdByOAHKFW004(psn);
        } catch (Exception ex) {
            Logger.getLogger(CRMWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateREPTDByOAHKFW006")
    public String updateREPTDByOAHKFW006(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkfw006Bean.updateReptdByOAHKFW006(psn);
        } catch (Exception ex) {
            Logger.getLogger(CRMWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createCRMWARMBByOAJHSQD")
    public String createCRMWARMBByOAJHSQD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzjs034DetailBean.createCRMWARMBByOAJHSQD(psn);
        } catch (Exception ex) {
            Logger.getLogger(CRMWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

}
