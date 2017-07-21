/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.REPMIBean;
import cn.hanbell.crm.ejb.REPTDBean;
import cn.hanbell.crm.entity.REPMI;
import cn.hanbell.crm.entity.REPMIPK;
import cn.hanbell.crm.entity.REPTD;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKFW006;
import cn.hanbell.oa.entity.HKFW006Cdrn30;
import cn.hanbell.oa.entity.HKFW006Cdrn30Detail;
import cn.hanbell.oa.entity.HKFW006Detail;
import cn.hanbell.oa.entity.HKFW006Inv310;
import cn.hanbell.oa.entity.HKFW006Inv310Detail;
import cn.hanbell.util.BaseLib;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Administrator
 */
@Stateless
@LocalBean
public class HKFW006Bean extends SuperEJBForEFGP<HKFW006> {

    @EJB
    private HKFW006Bean hkfw006Bean;
    @EJB
    private HKFW006DetailBean hkfw006DetailBean;
    @EJB
    private REPTDBean reptdBean;
    @EJB
    private REPMIBean repmiBean;
    @EJB
    private HKFW006Cdrn30Bean hkfw006CDRN30Bean;
     @EJB
    private HKFW006Inv310Bean hKFW006Inv310Bean;

    private List<HKFW006Detail> detailList;

    public HKFW006Bean() {
        super(HKFW006.class);
    }

    public Boolean updateReptdByOAHKFW006(String psn) {
        try {
            HKFW006 h = hkfw006Bean.findByPSN(psn);
            if (h == null) {
                throw new NullPointerException();
            }
            if ("2".equals(h.getRettype()) && !"".equals(h.getLitnbr().trim())) {
                String td001 = h.getHdTC001();
                String td002 = h.getFwno().split(td001)[1];
                String td003 = h.getHdTD003();
                REPTD td = reptdBean.findByPK(td001, td002, td003);
                td.setTd047("Y");
                reptdBean.update(td);
                return true;
            }

            List<HKFW006Detail> details = hkfw006DetailBean.findByFSN(h.getFormSerialNumber());
            if (details.size() > 0) {
                for (int i = 0; i < details.size(); i++) {
                    HKFW006Detail detail = details.get(i);
                    String td001 = h.getHdTC001();
                    String td002 = h.getFwno().split(td001)[1];
                    String td003 = detail.getHdTD003();
                    REPTD td = reptdBean.findByPK(td001, td002, td003);
                    td.setTd047("Y");
                    reptdBean.update(td);
                }
            }

            return true;

        } catch (Exception ex) {

            Logger.getLogger(HKFW006Bean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //更新CRM REPMI
    public Boolean updateREPMIByOAHKFW006(String psn) {
        HKFW006 hkfw006 = this.findByPSN(psn);
        if(hkfw006 == null){
            throw  new NullPointerException();
        }
        try {
            HKFW006Cdrn30 h = hkfw006CDRN30Bean.findByPSN(psn);
            if (h != null) {
                if (h.getPzcdrn30().equals("1")) {
                    List<HKFW006Cdrn30Detail> details = hkfw006CDRN30Bean.getDetailList(h.getFormSerialNumber());
                    if (details != null && details.size() > 0) {
                        for (int i = 0; i < details.size(); i++) {
                            HKFW006Cdrn30Detail detail = details.get(i);
                            if (!detail.getVarnr().equals(detail.getDmark2())) {
                                String mi002 = detail.getDmark2();
                                REPMI repmi = repmiBean.findByMI002(mi002);
                                if (repmi == null) {
                                } else if (!repmi.getREPMIPK().getMi001().equals(detail.getItnbr())) {
                                    String company = repmi.getCompany();
                                    String creator = repmi.getCreator();
                                    String createDate = repmi.getCreateDate();
                                    String usrGroup = repmi.getUsrGroup();
                                    String modifier = repmi.getModifier();
                                    String modiDate = repmi.getModiDate();
                                    Short flag = repmi.getFlag();
                                    String mi004 = repmi.getMi004();
                                    String mi005 = repmi.getMi005();
                                    String mi006 = repmi.getMi006();
                                    String mi007 = repmi.getMi007();
                                    String mi008 = repmi.getMi008();
                                    String mi009 = repmi.getMi009();
                                    String mi010 = repmi.getMi010();
                                    String mi011 = repmi.getMi011();
                                    String mi012 = repmi.getMi012();
                                    String mi013 = repmi.getMi013();
                                    String customer = repmi.getCustomer();
                                    String product = repmi.getProduct();
                                    String region = repmi.getRegion();
                                    String dealer = repmi.getDealer();
                                    String repmi01 = repmi.getRepmi01();
                                    String repmi02 = repmi.getRepmi02();
                                    String repmi03 = repmi.getRepmi03();
                                    String repmi04 = repmi.getRepmi04();
                                    String repmi05 = repmi.getRepmi05();
                                    String repmi06 = repmi.getRepmi06();
                                    String repmi07 = repmi.getRepmi07();
                                    String repmi08 = repmi.getRepmi08();
                                    String repmi09 = repmi.getRepmi09();
                                    String repmi10 = repmi.getRepmi10();
                                    String repmi11 = repmi.getRepmi11();
                                    String repmi12 = repmi.getRepmi12();
                                    String repmi13 = repmi.getRepmi13();
                                    String repmi14 = repmi.getRepmi14();
                                    String repmi15 = repmi.getRepmi15();
                                    String repmi16 = repmi.getRepmi16();
                                    String repmi17 = repmi.getRepmi17();
                                    String repmi18 = repmi.getRepmi18();
                                    String repmi19 = repmi.getRepmi19();
                                    String repmi20 = repmi.getRepmi20();
                                    String mi016 = repmi.getMi016();
                                    repmiBean.delete(repmi);
                                    REPMI r = new REPMI();
                                    REPMIPK repmipk = new REPMIPK();
                                    repmipk.setMi001(detail.getItnbr());
                                    repmipk.setMi002(mi002);
                                    r.setREPMIPK(repmipk);
                                    String mi003 = "OA反写 " + BaseLib.formatDate("yyyy/MM/dd", BaseLib.getDate());
                                    r.setMi003(mi003);
                                    r.setMi004(mi004);
                                    r.setMi005(mi005);
                                    r.setMi006(mi006);
                                    r.setMi007(mi007);
                                    r.setMi008(mi008);
                                    r.setMi009(mi009);
                                    r.setMi010(mi010);
                                    r.setMi011(mi011);
                                    r.setMi012(mi012);
                                    r.setMi013(mi013);
                                    r.setMi014(h.getCusno());
                                    r.setMi015(h.getCusname());
                                    r.setMi016(mi016);
                                    r.setCompany(company);
                                    r.setCreateDate(createDate);
                                    r.setCreator(creator);
                                    r.setUsrGroup(usrGroup);
                                    r.setModiDate(modiDate);
                                    r.setModifier(modifier);
                                    r.setFlag(flag);
                                    r.setCustomer(customer);
                                    r.setProduct(product);
                                    r.setRegion(region);
                                    r.setDealer(dealer);
                                    r.setRepmi01(repmi01);
                                    r.setRepmi02(repmi02);
                                    r.setRepmi03(repmi03);
                                    r.setRepmi04(repmi04);
                                    r.setRepmi05(repmi05);
                                    r.setRepmi06(repmi06);
                                    r.setRepmi07(repmi07);
                                    r.setRepmi08(repmi08);
                                    r.setRepmi09(repmi09);
                                    r.setRepmi10(repmi10);
                                    r.setRepmi11(repmi11);
                                    r.setRepmi12(repmi12);
                                    r.setRepmi13(repmi13);
                                    r.setRepmi14(repmi14);
                                    r.setRepmi15(repmi15);
                                    r.setRepmi16(repmi16);
                                    r.setRepmi17(repmi17);
                                    r.setRepmi18(repmi18);
                                    r.setRepmi19(repmi19);
                                    r.setRepmi20(repmi20);
                                    repmiBean.persist(r);

                                }
                            }

                        }
                    }
                }
            }
            
            HKFW006Inv310 hinv310 = hKFW006Inv310Bean.findByPSN(psn);
              if (hinv310 != null) {
                if (hinv310.getPzinv310().equals("1") && hkfw006.getRettype().equals("2") ) {
                    List<HKFW006Inv310Detail> detailsinv310 = hKFW006Inv310Bean.getDetailList(hinv310.getFormSerialNumber());
                    if (detailsinv310 != null && detailsinv310.size() > 0) {
                        for (int i = 0; i < detailsinv310.size(); i++) {
                            HKFW006Inv310Detail detail = detailsinv310.get(i);
                            if (!detail.getVarnr().equals("%") && !detail.getVarnr().equals("")) {
                                String mi002 = detail.getVarnr();
                                REPMI repmi = repmiBean.findByMI002(mi002);
                                if (repmi == null) {
                                } else if (!repmi.getREPMIPK().getMi001().equals(detail.getItnbr())) {
                                    String company = repmi.getCompany();
                                    String creator = repmi.getCreator();
                                    String createDate = repmi.getCreateDate();
                                    String usrGroup = repmi.getUsrGroup();
                                    String modifier = repmi.getModifier();
                                    String modiDate = repmi.getModiDate();
                                    Short flag = repmi.getFlag();
                                    String mi004 = repmi.getMi004();
                                    String mi005 = repmi.getMi005();
                                    String mi006 = repmi.getMi006();
                                    String mi007 = repmi.getMi007();
                                    String mi008 = repmi.getMi008();
                                    String mi009 = repmi.getMi009();
                                    String mi010 = repmi.getMi010();
                                    String mi011 = repmi.getMi011();
                                    String mi012 = repmi.getMi012();
                                    String mi013 = repmi.getMi013();
                                    String customer = repmi.getCustomer();
                                    String product = repmi.getProduct();
                                    String region = repmi.getRegion();
                                    String dealer = repmi.getDealer();
                                    String repmi01 = repmi.getRepmi01();
                                    String repmi02 = repmi.getRepmi02();
                                    String repmi03 = repmi.getRepmi03();
                                    String repmi04 = repmi.getRepmi04();
                                    String repmi05 = repmi.getRepmi05();
                                    String repmi06 = repmi.getRepmi06();
                                    String repmi07 = repmi.getRepmi07();
                                    String repmi08 = repmi.getRepmi08();
                                    String repmi09 = repmi.getRepmi09();
                                    String repmi10 = repmi.getRepmi10();
                                    String repmi11 = repmi.getRepmi11();
                                    String repmi12 = repmi.getRepmi12();
                                    String repmi13 = repmi.getRepmi13();
                                    String repmi14 = repmi.getRepmi14();
                                    String repmi15 = repmi.getRepmi15();
                                    String repmi16 = repmi.getRepmi16();
                                    String repmi17 = repmi.getRepmi17();
                                    String repmi18 = repmi.getRepmi18();
                                    String repmi19 = repmi.getRepmi19();
                                    String repmi20 = repmi.getRepmi20();
                                    String mi016 = repmi.getMi016();
                                    repmiBean.delete(repmi);
                                    REPMI r = new REPMI();
                                    REPMIPK repmipk = new REPMIPK();
                                    repmipk.setMi001(detail.getItnbr());
                                    repmipk.setMi002(mi002);
                                    r.setREPMIPK(repmipk);
                                    String mi003 = "OA反写 " + BaseLib.formatDate("yyyy/MM/dd", BaseLib.getDate());
                                    r.setMi003(mi003);
                                    r.setMi004(mi004);
                                    r.setMi005(mi005);
                                    r.setMi006(mi006);
                                    r.setMi007(mi007);
                                    r.setMi008(mi008);
                                    r.setMi009(mi009);
                                    r.setMi010(mi010);
                                    r.setMi011(mi011);
                                    r.setMi012(mi012);
                                    r.setMi013(mi013);
                                    r.setMi014(hinv310.getDepno());
                                    r.setMi015(hinv310.getDepname());
                                    r.setMi016(mi016);
                                    r.setCompany(company);
                                    r.setCreateDate(createDate);
                                    r.setCreator(creator);
                                    r.setUsrGroup(usrGroup);
                                    r.setModiDate(modiDate);
                                    r.setModifier(modifier);
                                    r.setFlag(flag);
                                    r.setCustomer(customer);
                                    r.setProduct(product);
                                    r.setRegion(region);
                                    r.setDealer(dealer);
                                    r.setRepmi01(repmi01);
                                    r.setRepmi02(repmi02);
                                    r.setRepmi03(repmi03);
                                    r.setRepmi04(repmi04);
                                    r.setRepmi05(repmi05);
                                    r.setRepmi06(repmi06);
                                    r.setRepmi07(repmi07);
                                    r.setRepmi08(repmi08);
                                    r.setRepmi09(repmi09);
                                    r.setRepmi10(repmi10);
                                    r.setRepmi11(repmi11);
                                    r.setRepmi12(repmi12);
                                    r.setRepmi13(repmi13);
                                    r.setRepmi14(repmi14);
                                    r.setRepmi15(repmi15);
                                    r.setRepmi16(repmi16);
                                    r.setRepmi17(repmi17);
                                    r.setRepmi18(repmi18);
                                    r.setRepmi19(repmi19);
                                    r.setRepmi20(repmi20);
                                    repmiBean.persist(r);

                                }
                            }

                        }
                    }
                }
            }
            
            
            return true;

        } catch (Exception ex) {

            Logger.getLogger(HKFW006Bean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public void setDetail(Object value) {
        this.detailList = hkfw006DetailBean.findByFSN(value); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the detailList
     */
    public List<HKFW006Detail> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<HKFW006Detail> detailList) {
        this.detailList = detailList;
    }
}
