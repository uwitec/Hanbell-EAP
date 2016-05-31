/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.erp.ejb.ApmsysBean;
import cn.hanbell.erp.ejb.CdrcusBean;
import cn.hanbell.util.BaseLib;
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
    private ApmsysBean apmsysBean;

    @EJB
    private CdrcusBean cdrcusBean;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "createCustomerByXmlString")
    public String createCustomerByXmlString(@WebParam(name = "object") String xmlString) {
        return "";
    }

    @WebMethod(operationName = "createCustomerByOAFormId")
    public String createCustomerByOAFormId(@WebParam(name = "formid") String formid) {
        return "";
    }

    @WebMethod(operationName = "getAPM525FormId")
    public String getAPM525FormId(@WebParam(name = "facno") String facno) {
        String id = "";
        try {
            apmsysBean.setEntityManagerByCompany(facno);
            id = apmsysBean.getFormId(facno, "APM525", BaseLib.getDate(), Boolean.TRUE);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
