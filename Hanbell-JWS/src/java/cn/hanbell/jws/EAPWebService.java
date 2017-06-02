/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.eam.ejb.AssetApplyBean;
import cn.hanbell.eam.entity.AssetApply;
import cn.hanbell.eam.entity.AssetApplyDetail;
import cn.hanbell.eap.ejb.DepartmentBean;
import cn.hanbell.eap.ejb.SystemUserBean;
import cn.hanbell.eap.entity.Department;
import cn.hanbell.eap.entity.SystemUser;
import cn.hanbell.oa.ejb.HKCG007Bean;
import cn.hanbell.oa.ejb.HKCW002Bean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002Detail;
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

    @EJB
    private DepartmentBean departmentBean;

    @EJB
    private SystemUserBean systemUserBean;

    @EJB
    private WorkFlowBean workFlowBean;

    @EJB
    private HKCW002Bean hkcw002Bean;

    @EJB
    private HKCG007Bean hkcg007Bean;

    @EJB
    private AssetApplyBean assetApplyBean;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
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
                    aa.setRemark(e.getRemark());
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
                    assetApplyBean.initAssetApply(aa, aadList);
                }
                ret = true;
            } else {
                ret = false;
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

    @WebMethod(operationName = "createOAHKCG007ByOAHKCW002")
    public String createOAHKCG007ByOAHKCW002(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkcg007Bean.initByHKCW002(psn);
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
