/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import vn.hanbell.erp.ejb.InvmasBean;

/**
 *
 * @author C0160
 */
@WebService(serviceName = "VHBERP")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class VHBERPWebService {

    @EJB
    private InvmasBean invmasBean;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "createINV140ByOAVHTK001")
    public String createINV140ByOAVHTK001(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invmasBean.initByOAVHTK001(psn);
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
