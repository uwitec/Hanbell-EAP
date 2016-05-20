/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.erp.ejb.CdrcusBean;
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
@WebService(serviceName = "SHBERPWebService")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SHBERPWebService {

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

}
