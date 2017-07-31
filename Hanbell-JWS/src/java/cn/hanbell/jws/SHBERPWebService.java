/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.erp.ejb.AmTbAssetApplyHadBean;
import cn.hanbell.erp.ejb.ApmpayBean;
import cn.hanbell.erp.ejb.BudgetDetailBean;
import cn.hanbell.erp.ejb.CdrbrhadBean;
import cn.hanbell.erp.ejb.CdrcusBean;
import cn.hanbell.erp.ejb.CdrlnhadBean;
import cn.hanbell.erp.ejb.InvbalBean;
import cn.hanbell.erp.ejb.InvbatBean;
import cn.hanbell.erp.ejb.InvhadBean;
import cn.hanbell.erp.ejb.InvmasBean;
import cn.hanbell.erp.ejb.PurhaskBean;
import cn.hanbell.erp.ejb.PurvdrBean;
import cn.hanbell.erp.ejb.SecgprgBean;
import cn.hanbell.oa.ejb.HKCG007Bean;
import cn.hanbell.oa.ejb.HZCW028Bean;
import cn.hanbell.oa.ejb.HZCW033Bean;
import java.math.BigDecimal;
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
    private BudgetDetailBean budgetDetailBean;
    @EJB
    private CdrcusBean cdrcusBean;
    @EJB
    private InvbalBean invbalBean;
    @EJB
    private InvbatBean invbatBean;
    @EJB
    private InvmasBean invmasBean;
    @EJB
    private PurhaskBean purhaskBean;
    @EJB
    private PurvdrBean purvdrBean;

    @EJB
    private HZCW028Bean hzcw028Bean;
    @EJB
    private HZCW033Bean hzcw033Bean;
    @EJB
    private SecgprgBean secgprgBean;
    @EJB
    private InvhadBean invhadBean;
    @EJB
    private CdrlnhadBean cdrlnhadBean;
    @EJB
    private CdrbrhadBean cdrbrhadBean;

    @EJB
    private AmTbAssetApplyHadBean amTbAssetApplyHadBean;

    @EJB
    private HKCG007Bean hkcg007Bean;

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "addBudgetPlanByOABXD")
    public String addBudgetPlanByOABXD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = budgetDetailBean.addByOABXD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "addBudgetPlanByOAJZD")
    public String addBudgetPlanByOAJZD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = budgetDetailBean.addByOAJZD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "addBudgetPlanByOAJZGHD")
    public String addBudgetPlanByOAJZGHD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = budgetDetailBean.addByOAJZGHD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createAMLYByOAZCSQD")
    public String createAMLYByOAZCSQD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = amTbAssetApplyHadBean.initByOAZCSQD(psn);
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

    @WebMethod(operationName = "createCDRN20ByOAWARMI05")
    public String createCDRN20ByOAWARMI05(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = cdrlnhadBean.initByOAWARI05(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createCDRN30ByOAHKFW006CDRN30")
    public String createCDRN30ByOAHKFW006CDRN30(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = cdrbrhadBean.initByOAHKFW006(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
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

    @WebMethod(operationName = "createINV140ByOAJHSQD")
    public String createINV140ByOAJHSQD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invmasBean.initByOAJHSQD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createINV140ByOAJHZYD")
    public String createINV140ByOAJHZYD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invmasBean.initByOASHBINV140(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createINV310ByOAHKFW006INV310")
    public String createINV310ByOAHKFW006INV310(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invhadBean.initByOAHKFW006(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createINV310ByOAWARMI05")
    public String createINV310ByOAWARMI05(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invhadBean.initByOAWARMI05(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createINV325ByOASHBERPINV325")
    public String createINV325ByOASHBERPINV325(@WebParam(name = "psn") String psn) {
        String ret = null;
        try {
            ret = invhadBean.initByOASHBERPINV325(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret != null && !"".equals(ret)) {
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

    @WebMethod(operationName = "createPUR210ByOAQGD")
    public String createPUR210ByOAQGD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = purhaskBean.initByOAQGD(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createSECGPRGByOAERPQXSQ")
    public String createSECGPRGByOAERPQXSQ(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = secgprgBean.initSECGPRG(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "isLessThenInvbal")
    public String isLessThenInvbal(@WebParam(name = "facno") String facno, @WebParam(name = "prono") String prono, @WebParam(name = "itnbr") String itnbr, @WebParam(name = "wareh") String wareh, @WebParam(name = "qty") String qty) {
        Boolean ret;
        try {
            invbalBean.setCompany(facno);
            ret = invbalBean.isGreatThenInvbal(facno, prono, itnbr, wareh, BigDecimal.valueOf(Double.valueOf(qty)));
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "500";
        }
        if (ret) {
            return "404";
        } else {
            return "200";
        }
    }

    @WebMethod(operationName = "isLessThenInvbat")
    public String isLessThenInvbat(@WebParam(name = "facno") String facno, @WebParam(name = "prono") String prono, @WebParam(name = "itnbr") String itnbr, @WebParam(name = "wareh") String wareh, @WebParam(name = "fixnr") String fixnr, @WebParam(name = "varnr") String varnr, @WebParam(name = "qty") String qty) {
        Boolean ret;
        try {
            invbatBean.setCompany(facno);
            ret = invbatBean.isGreatThenInvbat(facno, prono, itnbr, wareh, fixnr, varnr, BigDecimal.valueOf(Double.valueOf(qty)));
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "500";
        }
        if (ret) {
            return "404";
        } else {
            return "200";
        }
    }

    @WebMethod(operationName = "subtractBudgetPlanByOABXD")
    public String subtractBudgetPlanByOABXD(@WebParam(name = "psn") String psn, @WebParam(name = "isReject") String isReject) {
        Boolean ret = false;
        try {
            if ("1".equals(isReject)) {
                ret = apmpayBean.subtractBudgetPlanByOABXD(psn);
            } else {
                return "200";
            }
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "subtractBudgetPlanByOAJZD")
    public String subtractBudgetPlanByOAJZD(@WebParam(name = "psn") String psn, @WebParam(name = "isReject") String isReject) {
        Boolean ret = false;
        try {
            if ("1".equals(isReject)) {
                ret = apmpayBean.subtractBudgetPlanByOAJZD(psn);
            } else {
                return "200";
            }
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "subtractBudgetPlanByOAJZGHD")
    public String subtractBudgetPlanByOAJZGHD(@WebParam(name = "psn") String psn, @WebParam(name = "isReject") String isReject) {
        Boolean ret = false;
        try {
            if ("1".equals(isReject)) {
                ret = apmpayBean.subtractBudgetPlanByOAJZGHD(psn);
            } else {
                return "200";
            }
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateCustomerByOAPSN")
    public String updateCustomerByOAPSN(@WebParam(name = "psn") String psn) {

        Boolean ret = false;
        try {
            ret = cdrcusBean.updateByOAPSN(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateINV140ByOAHKJS001")
    public String updateINV140ByOAHKJS001(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invmasBean.updateByOAHKJS001(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateINV140ByOAJHXGD")
    public String updateINV140ByOAJHXGD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invmasBean.updateByOASHBINV146(psn);
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

    //<-------------------------------以下内容保持在最后,以后要删除------------------------------->
    @WebMethod(operationName = "updateCRMPORMYByOABXD")
    public String updateCRMPORMYByOABXD(@WebParam(name = "psn") String psn, @WebParam(name = "status") String status) {
        Boolean ret = false;
        try {
            ret = hzcw028Bean.updateCRMPORMY(psn ,status);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "rollbackCRMPORMYByOABXD")
    public String rollbackCRMPORMYByOABXD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzcw028Bean.rollbackCRMPORMY(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateCRMPORMYByOAJZGHD")
    public String updateCRMPORMYByOAJZGHD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzcw033Bean.updateCRMPORMY(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "rollbackCRMPORMYByOAJZGHD")
    public String rollbackCRMPORMYByOAJZGHD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzcw033Bean.rollbackCRMPORMY(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "initSECGPRGByOAERPQXSQ")
    public String initSECGPRGByOAERPQXSQ(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = secgprgBean.initSECGPRG(psn);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createHKCG007ByHKCW002P")
    public String createHKCG007ByHKCW002P(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkcg007Bean.initByHKCW002P(psn);
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
