/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.efnet.ejb.HZFWDD05Bean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author C0160
 */
@WebService(serviceName = "WebService")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EFNETWebService {

    @EJB
    private HZFWDD05Bean hzfwdd05Bean;

    @WebMethod(operationName = "createHZFWDD05ByOAFSN")
    public String createHZFWDD05ByOAFSN(@WebParam(name = "fsn") String fsn) {
        Boolean ret = false;
        try {
            ret = hzfwdd05Bean.initByEFGPFormSerialNumber(fsn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "deleteHZFWDD05ByOAFSN")
    public String deleteHZFWDD05ByOAFSN(@WebParam(name = "fsn") String fsn) {
        Boolean ret = false;
        try {
            ret = hzfwdd05Bean.deleteByEFGPFormSerialNumber(fsn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
