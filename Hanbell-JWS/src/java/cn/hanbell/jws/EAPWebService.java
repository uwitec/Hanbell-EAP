/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.crm.ejb.WARMBBean;
import cn.hanbell.crm.entity.WARMB;
import cn.hanbell.eam.ejb.AssetApplyBean;
import cn.hanbell.eam.entity.AssetApply;
import cn.hanbell.eam.entity.AssetApplyDetail;
import cn.hanbell.eap.ejb.DepartmentBean;
import cn.hanbell.eap.ejb.SystemUserBean;
import cn.hanbell.eap.entity.Department;
import cn.hanbell.eap.entity.SystemUser;
import cn.hanbell.erp.ejb.InvclswahBean;
import cn.hanbell.erp.entity.Invclswah;
import cn.hanbell.oa.ejb.HKCG007Bean;
import cn.hanbell.oa.ejb.HKCW002Bean;
import cn.hanbell.oa.ejb.HKFW006Bean;
import cn.hanbell.oa.ejb.HKGL038Bean;
import cn.hanbell.oa.ejb.HZCW028Bean;
import cn.hanbell.oa.ejb.HZCW033Bean;
import cn.hanbell.oa.ejb.HZJS034Bean;
import cn.hanbell.oa.ejb.SERI12Bean;
import cn.hanbell.oa.ejb.WARMI05Bean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002Detail;
import cn.hanbell.oa.entity.HZJS034;
import cn.hanbell.oa.entity.HZJS034Detail;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@WebService(serviceName = "EAP")
@Stateless()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EAPWebService {

    //EJBForCRM
    @EJB
    private WARMBBean warmbBean;

    //EJBForEAP
    @EJB
    private DepartmentBean departmentBean;
    @EJB
    private SystemUserBean systemUserBean;

    //EJBForEAM
    @EJB
    private AssetApplyBean assetApplyBean;

    //EJBForEFGP
    @EJB
    private WorkFlowBean workFlowBean;
    @EJB
    private HKCG007Bean hkcg007Bean;
    @EJB
    private HKCW002Bean hkcw002Bean;
    @EJB
    private HZCW028Bean hzcw028Bean;
    @EJB
    private HZCW033Bean hzcw033Bean;
    @EJB
    private HKFW006Bean hkfw006Bean;
    @EJB
    private HKGL038Bean hkgl038Bean;
    @EJB
    private HZJS034Bean hzjs034Bean;
    @EJB
    private SERI12Bean seri12Bean;
    @EJB
    private WARMI05Bean warmi05Bean;

    //EJBForERP
    @EJB
    private InvclswahBean invclswahBean;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "createCRMWARMBByOAJHSQD")
    public String createCRMWARMBByOAJHSQD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        HZJS034 h = hzjs034Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        hzjs034Bean.setDetail(h.getFormSerialNumber());
        List<HZJS034Detail> details = hzjs034Bean.getDetailList();
        try {

            //表身循环
            for (int i = 0; i < details.size(); i++) {
                HZJS034Detail detail = details.get(i);
                WARMB m = new WARMB();
                m.setCompany(h.getFacno());
                m.setCreator(h.getEmpl());
                m.setMb001(detail.getItnbr());                                  //设置件号
                m.setMb008(detail.getItcls());                                  //设置品号大类
                m.setMb002(detail.getItdsc());                                  //设置中文品名
                m.setMb003(detail.getSpdsc());                                  //设置中文规格
                m.setMb004(detail.getUnmsr1());                                 //设置单位一
                m.setMb029(detail.getEitdsc());
                m.setMb030(detail.getEspdsc());
                if (null != detail.getMorpcode()) {
                    switch (detail.getMorpcode()) {
                        case "W":
                            m.setMb010("M");
                            break;
                        case "H":
                            m.setMb010("Y");
                            break;
                        case "A":
                            m.setMb010("S");
                            break;
                        default:
                            m.setMb010(detail.getMorpcode());                   //设置自制采购码
                            break;
                    }
                }
                m.setMb028("Y");                                                //设置产品序号管理
                m.setMb050("Y");                                                //设置需核销
                invclswahBean.setCompany(h.getFacno());
                Invclswah invclswah = invclswahBean.findByInvclswahPK(h.getFacno(), "1", detail.getItcls());
                if (invclswah != null) {
                    m.setMb011(invclswah.getDefwah());
                }
                m.setMb033(detail.getItnbr());
                m.setMb057(BaseLib.formatDate("yyyyMMdd", BaseLib.getDate()));  //设置生效日期日期
                warmbBean.persist(m);
            }
            ret = true;
        } catch (Exception ex) {
            Logger.getLogger(HZJS034Detail.class.getName()).log(Level.SEVERE, null, ex);
            ret = false;
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createEAMAssetApplyByOAHKCW002")
    public String createEAMAssetApplyByOAHKCW002(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            HKCW002 e;
            List<HKCW002Detail> detailList;
            e = hkcw002Bean.findByPSN(psn);
            if (e != null) {
                Department dept;
                SystemUser user;
                AssetApply aa;
                AssetApplyDetail aad;
                List<AssetApplyDetail> aadList = new ArrayList<>();
                hkcw002Bean.setDetail(e.getFormSerialNumber());
                detailList = hkcw002Bean.getDetailList();

                if (detailList != null && !detailList.isEmpty()) {
                    //EAM资产申请主档
                    aa = new AssetApply();
                    aa.setCompany(e.getFacno());
                    aa.setFormdate(e.getFormdate());
                    aa.setApplyDeptno(e.getApplyDeptno());
                    dept = departmentBean.findByDeptno(e.getApplyDeptno());
                    if (dept != null) {
                        aa.setApplyDeptname(dept.getDept());
                    }
                    aa.setApplyUserno(e.getApplyUserno());
                    user = systemUserBean.findByUserId(e.getApplyUserno());
                    if (user != null) {
                        aa.setApplyUsername(user.getUsername());
                    }
                    aa.setRequireDeptno(e.getRequireDeptno());
                    dept = departmentBean.findByDeptno(e.getRequireDeptno());
                    if (dept != null) {
                        aa.setRequireDeptname(dept.getDept());
                    }
                    aa.setRequireUserno(e.getRequireUserno());
                    user = systemUserBean.findByUserId(e.getRequireUserno());
                    if (user != null) {
                        aa.setRequireUsername(user.getUsername());
                    }
                    aa.setRemark(e.getProcessSerialNumber());

                    aa.setStatusToNew();
                    aa.setCreator(e.getProcessSerialNumber());
                    aa.setCredateToNow();
                    //EAM资产申请明细
                    int i = 0;
                    for (HKCW002Detail d : detailList) {
                        i++;
                        aad = new AssetApplyDetail();
                        aad.setSeq(i);
                        aad.setRequireDeptno(aa.getRequireDeptno());
                        aad.setRequireDeptname(aa.getRequireDeptname());
                        aad.setRequireUserno(aa.getRequireUserno());
                        aad.setRequireUsername(aa.getRequireUsername());
                        aad.setAssetItem(assetApplyBean.findByItemno(d.getItemno()));
                        aad.setItemdesc(d.getItemdesc());
                        aad.setItemspec(d.getItemspec());
                        aad.setQty(BigDecimal.valueOf(Double.valueOf(d.getQty())));
                        aad.setDisqty(BigDecimal.valueOf(Double.valueOf(d.getDisqty())));
                        if (!"".equals(d.getPurqty())) {
                            aad.setPurqty(BigDecimal.valueOf(Double.valueOf(d.getPurqty())));
                        } else {
                            aad.setPurqty(BigDecimal.ZERO);
                        }
                        aad.setAccqty(BigDecimal.ZERO);
                        aad.setUnit(d.getUnit());
                        aad.setCurrency(e.getCurrency());
                        aad.setExchange(BigDecimal.valueOf(e.getRate()));
                        aad.setPrice(BigDecimal.valueOf(Double.valueOf(d.getPrice())));
                        aad.setAmts(BigDecimal.valueOf(Double.valueOf(d.getAmts())));
                        aad.setBudgetacc(d.getBudgetacc());
                        aad.setBudgetaccname(d.getBudgetaccname());
                        aad.setDmark1(d.getDmark1());
                        aad.setDmark1desc(d.getDmark1desc());
                        aad.setDmark2(d.getDmark2());
                        aad.setDmark2desc(d.getDmark2desc());
                        aad.setSrcapi("HK_CW002");
                        aad.setSrcformid(d.getFormSerialNumber());
                        aad.setSrcseq(Integer.valueOf(d.getSeq()));
                        aad.setDistributed(false);
                        aad.setPurchased(Boolean.valueOf(e.getPurchase()));
                        aad.setStatus("10");
                        //添加到明细列表
                        aadList.add(aad);
                    }
                    //新增到EAM
                    String formid = assetApplyBean.initAssetApply(aa, aadList);
                    if (formid != null && !"".equals(formid)) {
                        e.setSrcformid(formid);
                        hkcw002Bean.update(e);//记录EAM单号
                    }
                }
                ret = true;
            } else {
                ret = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createOAHKCG007ByOAHKCW002")
    public String createOAHKCG007ByOAHKCW002(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkcg007Bean.initByHKCW002(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createOAHKCG007ByOAHKCW002P")
    public String createOAHKCG007ByOAHKCW002P(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkcg007Bean.initByHKCW002P(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateCRMPORMYByOABXD")
    public String updateCRMPORMYByOABXD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hzcw028Bean.updateCRMPORMY(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateCRMREPMIByOAHKFW006")
    public String updateCRMREPMIByOAHKFW006(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkfw006Bean.updateREPMIByOAHKFW006(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateCRMSERBQByOASERI12")
    public String updateCRMSERBQByOASERI12(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = seri12Bean.updateSerbq(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateCRMWARTBByOAWARMI05")
    public String updateCRMWARTBByOAWARMI05(@WebParam(name = "psn") String psn, @WebParam(name = "step") String step) {
        Boolean ret = false;
        try {
            ret = warmi05Bean.updateWARTB(psn, step);
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateOAHKGL037ByOAHKGL038")
    public String updateOAHKGL037ByOAHKGL038(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkgl038Bean.updateHKGL037(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }
}
