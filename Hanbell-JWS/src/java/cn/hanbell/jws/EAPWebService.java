/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jws;

import cn.hanbell.crm.ejb.WARMBBean;
import cn.hanbell.crm.entity.WARMB;
import cn.hanbell.eam.ejb.AssetApplyBean;
import cn.hanbell.eam.ejb.AssetCardBean;
import cn.hanbell.eam.ejb.AssetCategoryBean;
import cn.hanbell.eam.ejb.AssetDisposeBean;
import cn.hanbell.eam.ejb.AssetItemBean;
import cn.hanbell.eam.ejb.AssetScrapBean;
import cn.hanbell.eam.ejb.WarehouseBean;
import cn.hanbell.eam.entity.AssetApply;
import cn.hanbell.eam.entity.AssetApplyDetail;
import cn.hanbell.eam.entity.AssetCard;
import cn.hanbell.eam.entity.AssetCategory;
import cn.hanbell.eam.entity.AssetDispose;
import cn.hanbell.eam.entity.AssetDisposeDetail;
import cn.hanbell.eam.entity.AssetItem;
import cn.hanbell.eam.entity.AssetScrap;
import cn.hanbell.eam.entity.AssetScrapDetail;
import cn.hanbell.eap.ejb.DepartmentBean;
import cn.hanbell.eap.ejb.SystemUserBean;
import cn.hanbell.eap.entity.Department;
import cn.hanbell.eap.entity.SystemUser;
import cn.hanbell.erp.ejb.InvclswahBean;
import cn.hanbell.erp.ejb.InvmasBean;
import cn.hanbell.erp.entity.Invclswah;
import cn.hanbell.oa.ejb.HKCG007Bean;
import cn.hanbell.oa.ejb.HKCW002Bean;
import cn.hanbell.oa.ejb.HKFW006Bean;
import cn.hanbell.oa.ejb.HKGL038Bean;
import cn.hanbell.oa.ejb.HKJH001Bean;
import cn.hanbell.oa.ejb.HZCW028Bean;
import cn.hanbell.oa.ejb.HZCW033Bean;
import cn.hanbell.oa.ejb.HZCW034Bean;
import cn.hanbell.oa.ejb.HZJS034Bean;
import cn.hanbell.oa.ejb.SERI12Bean;
import cn.hanbell.oa.ejb.SHBERPPURX141Bean;
import cn.hanbell.oa.ejb.SHBINV140Bean;
import cn.hanbell.oa.ejb.WARMI05Bean;
import cn.hanbell.oa.ejb.WorkFlowBean;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002Detail;
import cn.hanbell.oa.entity.HZCW034;
import cn.hanbell.oa.entity.HZCW034Detail;
import cn.hanbell.oa.entity.HZJS034;
import cn.hanbell.oa.entity.HZJS034Detail;
import cn.hanbell.oa.entity.SHBERPINV140;
import cn.hanbell.oa.entity.SHBERPINV140Detail;
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
    @EJB
    private AssetCardBean assetCardBean;
    @EJB
    private AssetCategoryBean assetCategoryBean;
    @EJB
    private AssetDisposeBean assetDisposeBean;
    @EJB
    private AssetItemBean assetItemBean;
    @EJB
    private AssetScrapBean assetScrapBean;
    @EJB
    private WarehouseBean warehouseBean;

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
    private HZCW034Bean hzcw034Bean;
    @EJB
    private HKFW006Bean hkfw006Bean;
    @EJB
    private HKGL038Bean hkgl038Bean;
    @EJB
    private HZJS034Bean hzjs034Bean;
    @EJB
    private HKJH001Bean hkjh001Bean;
    @EJB
    private SERI12Bean seri12Bean;
    @EJB
    private SHBERPPURX141Bean shberppurx141Bean;
    @EJB
    private SHBINV140Bean shbinv140Bean;
    @EJB
    private WARMI05Bean warmi05Bean;

    //EJBForERP
    @EJB
    private InvclswahBean invclswahBean;
    @EJB
    private InvmasBean invmasBean;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "createCRMREPMFByOASHBERPPURX141")
    public String createCRMREPMFByOASHBERPPURX141(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = shberppurx141Bean.initByOASHBERPPURX141(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
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
                if (null == warmbBean.findByMB001(detail.getItnbr())) {
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

    @WebMethod(operationName = "createCRMWARMBByOAJHZYD")
    public String createCRMWARMBByOAJHZYD(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        SHBERPINV140 h = shbinv140Bean.findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        if ("C".equals(h.getFacno2())) {
            List<SHBERPINV140Detail> details = shbinv140Bean.getDetailList(h.getFormSerialNumber());
            try {
                //表身循环
                for (int i = 0; i < details.size(); i++) {
                    SHBERPINV140Detail detail = details.get(i);
                    if (null == warmbBean.findByMB001(detail.getItnbr())) {
                        WARMB m = new WARMB();
                        m.setCompany(h.getFacno2());
                        m.setCreator(h.getApplyuser());
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
                        invclswahBean.setCompany(h.getFacno2());
                        Invclswah invclswah = invclswahBean.findByInvclswahPK(h.getFacno2(), "1", detail.getItcls());
                        if (invclswah != null) {
                            m.setMb011(invclswah.getDefwah());
                        }
                        m.setMb033(detail.getItnbr());
                        m.setMb057(BaseLib.formatDate("yyyyMMdd", BaseLib.getDate()));  //设置生效日期日期
                        warmbBean.persist(m);
                    }
                }
                ret = true;
            } catch (Exception ex) {
                Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            ret = true;
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

    @WebMethod(operationName = "createEAMAssetDisposeByEAMAssetScrap")
    public String createEAMAssetDisposeByEAMAssetScrap(@WebParam(name = "formid") String formid) {
        Boolean ret = false;
        HZCW034 e;
        try {
            AssetScrap as;
            List<AssetScrapDetail> asdList;
            as = assetScrapBean.findByFormId(formid);
            if (as == null) {
                throw new NullPointerException(formid + "不存在");
            }
            assetScrapBean.setDetail(as.getFormid());
            asdList = assetScrapBean.getDetailList();
            if (asdList == null || asdList.isEmpty()) {
                return "200";
            }
            String assetno;
            AssetDispose ad;
            AssetDisposeDetail add;
            List<AssetDisposeDetail> addList = new ArrayList<>();
            int i = 0;
            for (AssetScrapDetail d : asdList) {
                i++;
                add = new AssetDisposeDetail();
                add.setSeq(i);
                if (d.getAssetItem().getCategory().getNoauto()) {
                    //资产编号
                    add.setAssetCard(d.getAssetCard());
                    add.setAssetno(d.getAssetno());
                } else {
                    //单据编号
                    assetno = d.getPid() + "-" + BaseLib.formatString("0000", String.valueOf(d.getSeq()));
                    AssetCard ac = assetCardBean.findByAssetno(assetno);
                    if (ac == null) {
                        throw new NullPointerException(assetno + "资产卡片不存在");
                    }
                    add.setAssetCard(ac);
                    add.setAssetno(ac.getFormid());
                }
                add.setAssetItem(d.getAssetItem());
                add.setQty(d.getQty());
                add.setUnit(d.getUnit());
                add.setBuyPrice(d.getPrice());
                add.setBuyAmts(d.getAmts());
                add.setBuyDate(d.getBuyDate());
                add.setSurplusValue(d.getSurplusValue());
                add.setAmts(BigDecimal.ZERO);
                add.setUserno(d.getUserno());
                add.setUsername(d.getUsername());
                add.setDeptno(d.getDeptno());
                add.setDeptname(d.getDeptname());
                add.setUsed(d.getUsed());
                add.setWarehouse(d.getWarehouse2());
                add.setSrcapi("assetscrap");
                add.setSrcformid(d.getPid());
                add.setSrcseq(d.getSeq());
                add.setRelapi(d.getSrcapi());
                add.setRelformid(d.getSrcformid());
                add.setRelseq(d.getSrcseq());
                addList.add(add);
            }
            ad = new AssetDispose();
            ad.setCompany(as.getCompany());
            ad.setFormdate(BaseLib.getDate());
            ad.setDeptno(as.getDeptno());
            ad.setDeptname(as.getDeptname());
            //处置总额
            ad.setTotalAmts(BigDecimal.ZERO);
            //残值总额
            ad.setSurplusValue(BigDecimal.ZERO);
            ad.setReason(as.getReason());
            ad.setRemark(as.getRemark());
            ad.setStatus("N");
            ad.setCreator(as.getCfmuser());
            ad.setCredateToNow();
            String id = assetDisposeBean.initAssetDispose(ad, addList);
            if (id != null && !"".equals(id)) {
                ad.setStatus("V");
                assetDisposeBean.verify(ad);//抛转后直接确认
                ret = true;
            } else {
                ret = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(formid).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }

    }

    @WebMethod(operationName = "createEAMAssetDisposeByOAHZCW034")
    public String createEAMAssetDisposeByOAHZCW034(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        HZCW034 e;
        try {
            e = hzcw034Bean.findByPSN(psn);
            if (e == null) {
                throw new NullPointerException(psn + "不存在");
            } else if (e.getSourceno() == null || "".equals(e.getSourceno())) {
                return "200";
            }
            AssetScrap as;
            List<AssetScrapDetail> asdList;
            as = assetScrapBean.findByFormId(e.getSourceno());
            if (as == null) {
                throw new NullPointerException(e.getSourceno() + "不存在");
            }
            assetScrapBean.setDetail(as.getFormid());
            asdList = assetScrapBean.getDetailList();
            if (asdList == null || asdList.isEmpty()) {
                return "200";
            }
            String assetno;
            AssetDispose ad;
            AssetDisposeDetail add;
            List<AssetDisposeDetail> addList = new ArrayList<>();
            int i = 0;
            for (AssetScrapDetail d : asdList) {
                i++;
                add = new AssetDisposeDetail();
                add.setSeq(i);
                if (d.getAssetItem().getCategory().getNoauto()) {
                    //资产编号
                    add.setAssetCard(d.getAssetCard());
                    add.setAssetno(d.getAssetno());
                } else {
                    //单据编号
                    assetno = d.getPid() + "-" + BaseLib.formatString("0000", String.valueOf(d.getSeq()));
                    AssetCard ac = assetCardBean.findByAssetno(assetno);
                    if (ac == null) {
                        throw new NullPointerException(assetno + "资产卡片不存在");
                    }
                    add.setAssetCard(ac);
                    add.setAssetno(ac.getFormid());
                }
                add.setAssetItem(d.getAssetItem());
                add.setQty(d.getQty());
                add.setUnit(d.getUnit());
                add.setBuyPrice(d.getPrice());
                add.setBuyAmts(d.getAmts());
                add.setBuyDate(d.getBuyDate());
                add.setSurplusValue(d.getSurplusValue());
                add.setAmts(BigDecimal.ZERO);
                add.setUserno(d.getUserno());
                add.setUsername(d.getUsername());
                add.setDeptno(d.getDeptno());
                add.setDeptname(d.getDeptname());
                add.setUsed(d.getUsed());
                add.setWarehouse(d.getWarehouse2());
                add.setSrcapi("assetscrap");
                add.setSrcformid(d.getPid());
                add.setSrcseq(d.getSeq());
                add.setRelapi(d.getSrcapi());
                add.setRelformid(d.getSrcformid());
                add.setRelseq(d.getSrcseq());
                addList.add(add);
            }
            ad = new AssetDispose();
            ad.setCompany(e.getFacno());
            ad.setFormdate(BaseLib.getDate());
            ad.setDeptno(as.getDeptno());
            ad.setDeptname(as.getDeptname());
            //处置总额
            ad.setTotalAmts(BigDecimal.ZERO);
            //残值总额
            ad.setSurplusValue(BigDecimal.ZERO);
            ad.setReason(as.getReason());
            ad.setRemark(as.getRemark());
            ad.setStatus("N");
            ad.setCreator(e.getCreator());
            ad.setCredateToNow();
            String formid = assetDisposeBean.initAssetDispose(ad, addList);
            if (formid != null && !"".equals(formid)) {
                e.setSourceno(formid);
                hzcw034Bean.update(e);//记录EAM单号
                ad.setStatus("V");
                assetDisposeBean.verify(ad);//抛转后直接确认
                ret = true;
            } else {
                ret = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(psn).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }

    }

    @WebMethod(operationName = "createEAMAssetItemByOAJS034")
    public String createEAMAssetItemByOAJS034(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            HZJS034 h = hzjs034Bean.findByPSN(psn);
            if (h == null) {
                throw new NullPointerException();
            }
            if (!h.getGenre1().equals("ZC")) {
                return "200";
            }
            hzjs034Bean.setDetail(h.getFormSerialNumber());
            List<HZJS034Detail> details = hzjs034Bean.getDetailList();
            AssetItem ai;
            AssetCategory ac;
            for (HZJS034Detail detail : details) {
                if (detail.getItnbr().substring(0, 2).equals("B1")) {
                    continue;
                }
                ai = assetItemBean.findByItemno(detail.getItnbr());
                if (ai != null) {
                    continue;
                }
                ac = assetCategoryBean.findByCategory(detail.getItcls());
                if (ac == null) {
                    continue;
                }
                ai = new AssetItem();
                ai.setCategory(ac);
                ai.setItemno(detail.getItnbr());
                ai.setItemdesc(detail.getItdsc());
                ai.setItemspec(detail.getSpdsc());
                ai.setUnit(detail.getUnmsr1());
                ai.setStatus("N");
                ai.setCreator(h.getEmpl());
                ai.setCredateToNow();
                assetItemBean.persist(ai);
            }
            ret = true;
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createEAMAssetItemByOAHZJS034")
    public String createEAMAssetItemByOAHZJS034(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            HZJS034 h = hzjs034Bean.findByPSN(psn);
            if (h == null) {
                throw new NullPointerException();
            }
            if (!h.getGenre1().equals("ZC")) {
                return "200";
            }
            hzjs034Bean.setDetail(h.getFormSerialNumber());
            List<HZJS034Detail> details = hzjs034Bean.getDetailList();
            AssetItem ai;
            AssetCategory ac;
            for (HZJS034Detail detail : details) {
                if (detail.getItnbr().substring(0, 2).equals("B1")) {
                    continue;
                }
                ai = assetItemBean.findByItemno(detail.getItnbr());
                if (ai != null) {
                    continue;
                }
                ac = assetCategoryBean.findByCategory(detail.getItcls());
                if (ac == null) {
                    continue;
                }
                ai = new AssetItem();
                ai.setCategory(ac);
                ai.setItemno(detail.getItnbr());
                ai.setItemdesc(detail.getItdsc());
                ai.setItemspec(detail.getSpdsc());
                ai.setUnit(detail.getUnmsr1());
                ai.setStatus("N");
                ai.setCreator(h.getEmpl());
                ai.setCredateToNow();
                assetItemBean.persist(ai);
            }
            ret = true;
        } catch (Exception ex) {
            Logger.getLogger(SHBERPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "createEAMAssetScrapByOAHZCW034")
    public String createEAMAssetScrapByOAHZCW034(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        HZCW034 e;
        List<HZCW034Detail> detailList;
        try {
            e = hzcw034Bean.findByPSN(psn);
            if (e == null) {
                throw new NullPointerException(psn + "不存在");
            } else if (e.getSourceno() != null && !"".equals(e.getSourceno())) {
                return "200";
            }
            hzcw034Bean.setDetail(e.getFormSerialNumber());
            detailList = hzcw034Bean.getDetailList();
            if (detailList == null || detailList.isEmpty()) {
                throw new NullPointerException(psn + "没有明细资料");
            }
            Department dept;
            AssetCard ac;
            AssetItem ai;
            AssetScrap as;
            AssetScrapDetail asd;
            List<AssetScrapDetail> asdList = new ArrayList<>();
            int i = 0;
            for (HZCW034Detail d : detailList) {
                ac = assetCardBean.findByAssetno(d.getAssetno());
                ai = assetItemBean.findByItemno(d.getItnbr());
                if (ac == null) {
                    throw new RuntimeException(d.getAssetno() + "EAM中不存在");
                } else if (ac.getScrap()) {
                    throw new RuntimeException(d.getAssetno() + "EAM中已报废");
                }
                if (ai == null) {
                    throw new RuntimeException(d.getItnbr() + "EAM中不存在");
                }
                i++;
                asd = new AssetScrapDetail();
                asd.setSeq(i);
                asd.setAssetCard(ac);
                asd.setAssetno(d.getAssetno());
                asd.setAssetItem(ai);
                asd.setQty(com.lightshell.comm.BaseLib.convertObject(BigDecimal.class, d.getScrapqty()));
                asd.setUnit(ac.getUnit());
                asd.setPrice(com.lightshell.comm.BaseLib.convertObject(BigDecimal.class, d.getPrice()));
                asd.setAmts(com.lightshell.comm.BaseLib.convertObject(BigDecimal.class, d.getAmts()));
                asd.setSurplusValue(com.lightshell.comm.BaseLib.convertObject(BigDecimal.class, d.getSurplusValue()));
                asd.setUserno(d.getUserno());
                asd.setUsername(d.getUsername());
                asd.setDeptno(d.getDeptno());
                asd.setDeptname(d.getDeptname());
                asd.setBuyDate(BaseLib.getDate("yyyy/MM/dd", d.getBuyDatetxt()));
                asd.setUsed(Integer.parseInt(d.getUsetime()));
                asd.setWarehouse(warehouseBean.findByWarehouseno(d.getWarehouseno()));
                asd.setWarehouse2(warehouseBean.findByWarehouseno(d.getWarehouseno2()));
                asd.setSrcapi("EFGP");
                asd.setSrcformid(d.getFormSerialNumber());
                asd.setSrcseq(Integer.parseInt(d.getSeq()));
                asdList.add(asd);
            }
            as = new AssetScrap();
            as.setCompany(e.getFacno());
            as.setFormdate(e.getApplydate());
            as.setDeptno(e.getApplydept());
            dept = departmentBean.findByDeptno(e.getApplydept());
            if (dept != null) {
                as.setDeptname(dept.getDept());
            }
            if (e.getCountsum() != null) {
                as.setTotalAmts(BigDecimal.valueOf(e.getCountsum()));
            } else {
                as.setTotalAmts(BigDecimal.ZERO);
            }
            if (e.getRsum() != null) {
                as.setSurplusValue(BigDecimal.valueOf(e.getRsum()));
            } else {
                as.setSurplusValue(BigDecimal.ZERO);
            }
            as.setReason(e.getMark());
            as.setRemark(psn);
            as.setStatus("N");
            as.setCreator(e.getCreator());
            as.setCredateToNow();
            String formid = assetScrapBean.initAssetScrap(as, asdList);
            if (formid != null && !"".equals(formid)) {
                e.setSourceno(formid);
                hzcw034Bean.update(e);//记录EAM单号
                as.setStatus("V");
                assetScrapBean.verify(as);//抛转后直接确认
                ret = true;
            } else {
                ret = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(psn).log(Level.SEVERE, null, ex);
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

    @WebMethod(operationName = "rollbackCRMWARTBByOAWARMI05")
    public String rollbackCRMWARTBByOAWARMI05(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = warmi05Bean.initWARTB(psn);
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
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "rollbackEAMAssetDisposeByOAHZCW034")
    public String rollbackEAMAssetDisposeByOAHZCW034(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        HZCW034 e;
        try {
            e = hzcw034Bean.findByPSN(psn);
            if (e == null) {
                throw new NullPointerException(psn + "不存在");
            } else if (e.getSourceno() == null || "".equals(e.getSourceno())) {
                return "200";
            }
            AssetDispose ad = assetDisposeBean.findByFormId(e.getSourceno());
            if (ad == null) {
                throw new NullPointerException(e.getSourceno() + "不存在");
            }
            assetDisposeBean.unverify(ad);
            ret = true;
        } catch (Exception ex) {
            Logger.getLogger(psn).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }

    }

    @WebMethod(operationName = "updateCRMPORMYByOABXD")
    public String updateCRMPORMYByOABXD(@WebParam(name = "psn") String psn, @WebParam(name = "status") String status) {
        Boolean ret = false;
        try {
            ret = hzcw028Bean.updateCRMPORMY(psn, status);
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

    @WebMethod(operationName = "updateERPINV140ByOASHBERPPURX141")
    public String updateERPINV140ByOASHBERPPURX141(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = invmasBean.updateByOASHBERPPURX141(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ret) {
            return "200";
        } else {
            return "404";
        }
    }

    @WebMethod(operationName = "updateOAHKJH001ByOAHKJH001")
    public String updateOAHKJH001ByOAHKJH001(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = hkjh001Bean.updatehkjh001(psn);
        } catch (Exception ex) {
            Logger.getLogger(EAPWebService.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @WebMethod(operationName = "updateOAProcessInstanceByOAWARMI05")
    public String updateOAProcessInstanceByOAWARMI05(@WebParam(name = "psn") String psn) {
        Boolean ret = false;
        try {
            ret = warmi05Bean.updatesubject(psn);
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
