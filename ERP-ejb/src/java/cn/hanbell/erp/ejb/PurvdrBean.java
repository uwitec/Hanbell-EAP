/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Miscode;
import cn.hanbell.erp.entity.Purvdr;
import cn.hanbell.erp.entity.PurvdrBuyer;
import cn.hanbell.oa.ejb.HKCG016Bean;
import cn.hanbell.oa.ejb.HKCG017Bean;
import cn.hanbell.oa.entity.HKCG016;
import cn.hanbell.oa.entity.HKCG017;
import cn.hanbell.util.BaseLib;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author C1368
 */
@Stateless
@LocalBean
public class PurvdrBean extends SuperEJBForERP<Purvdr> {

    @EJB
    private PurvdrBuyerBean purvdrBuyerBean;

    private final HashMap<SuperEJBForERP, List<?>> details = new HashMap<>();

    private final List<PurvdrBuyer> purvdrBuyerList = new ArrayList<>();

    @EJB
    private HKCG016Bean beanHKCG016;

    @EJB
    private HKCG017Bean beanHKCG017;

    @EJB
    private SyncGZBean syncGZBean;
    @EJB
    private SyncJNBean syncJNBean;
    @EJB
    private SyncNJBean syncNJBean;
    @EJB
    private SyncSHBBean syncSHBBean;

    @EJB
    private MiscodeBean miscodeBean;

    public PurvdrBean() {
        super(Purvdr.class);
    }

    @Override
    public Boolean initByOAPSN(String psn) {
        details.put(purvdrBuyerBean, purvdrBuyerList);
        HKCG016 oa = beanHKCG016.findByPSN(psn);
        if (oa == null) {
            throw new NullPointerException();
        }

        Purvdr erp = new Purvdr();

        String facno, code, newvdrno;
        switch (oa.getFacno()) {
            //SHB和分公司统一到SHB下
            case "C":
            case "G":
            case "J":
            case "N":
                facno = "C";
                code = "S";
                break;
            default:
                facno = oa.getFacno();
                code = facno;
        }
        Miscode m;
        this.miscodeBean.setCompany(facno);
        m = miscodeBean.findByCdesc(oa.getTtbankna());
        if (m == null) {
            m = new Miscode("NB", miscodeBean.getFormId("NB", "B", 4));
            m.setCdesc(oa.getTtbankna());
            m.setStatus('Y');
            m.setMascreyn('N');
            m.setCusds(oa.getTtbankna());
            miscodeBean.persist(m);
        }

        setCompany(facno);

        erp.setVdrna(oa.getVdrna());
        if (oa.getVdrsta() != null && !oa.getVdrsta().equals("")) {
            erp.setVdrsta(oa.getVdrsta().charAt(0));
        }
        erp.setVdrds(oa.getVdrds());
        erp.setVdrdse(oa.getVdrdse());
        erp.setAddress(oa.getAddress());
        if (oa.getDecode() != null && !oa.getDecode().equals("")) {
            erp.setDecode(oa.getDecode().charAt(0));
        }
        erp.setZonenum(oa.getZonenum());
        erp.setCoin(oa.getCoin());
        erp.setTax(oa.getTax());
        if (oa.getTaxrate() != null && !oa.getTaxrate().equals("")) {
            erp.setTaxrate(BigDecimal.valueOf(Double.parseDouble(oa.getTaxrate())));
        }
        erp.setUniform(oa.getUniform());
        erp.setPurkind(oa.getPurkind());
        erp.setTermcode(oa.getTermcode());
        erp.setAreacode(oa.getAreacode());
        if (oa.getPaycom() != null && !oa.getPaycom().equals("")) {
            erp.setPaycom(oa.getPaycom().charAt(0));
        }
        erp.setCuycode(oa.getCuycode());
        if (oa.getPaycode() != null && !oa.getPaycode().equals("")) {
            erp.setPaycode(oa.getPaycode().charAt(0));
        }
        if (oa.getTickdays() != null && !oa.getTickdays().equals("")) {
            erp.setTickdays(Short.valueOf(oa.getTickdays()));
        }
        erp.setBoss(oa.getBoss());
        erp.setMark1(oa.getMark1());
        erp.setSndcode(oa.getSndcode());
        erp.setContactman(oa.getContactman());
        erp.setTtbankno(m.getMiscodePK().getCode());
        erp.setTtbankna(oa.getTtbankna());
        erp.setTtbanknum(oa.getTtbanknum());
        erp.setTtname(oa.getTtname());
        if (oa.getYeramt() != null && !oa.getYeramt().equals("")) {
            erp.setYeramt(BigDecimal.valueOf(Double.parseDouble(oa.getYeramt())));
        }
        if (oa.getCapamt() != null && !oa.getCapamt().equals("")) {
            erp.setCapamt(BigDecimal.valueOf(Double.parseDouble(oa.getCapamt())));
        }
        erp.setTel1(oa.getTel1());
        erp.setTel2(oa.getTel2());
        erp.setWebsite(oa.getWebsite());
        if (oa.getAbccode() != null && !oa.getAbccode().equals("")) {
            erp.setAbccode(oa.getAbccode().charAt(0));
        }
        erp.setFax1(oa.getFax1());
        erp.setFax2(oa.getFax2());
        erp.setBegdate(oa.getBegdate());
        erp.setLatdate(oa.getLatdate());
        erp.setTitle(oa.getTitle());
        erp.setFkfs(oa.getFktype());
        erp.setPaysepcode('1');
        erp.setHandays1((short) 30);

        //生成厂商编号
        newvdrno = getFormId(BaseLib.getDate(), code + erp.getCuycode(), null, 5, "purvdr", "vdrno");
        erp.setVdrno(newvdrno);

        //生成采购员
        PurvdrBuyer b = new PurvdrBuyer(facno, "1", newvdrno);
        b.setBuyer(oa.getUserno());

        purvdrBuyerList.add(b);

        try {
            persist(erp, details);
            getEntityManager().flush();

            oa.setVdrno(newvdrno);
            beanHKCG016.update(oa);

            switch (oa.getFacno()) {
                case "G":
                    //同步广州ERP
                    //resetFacno("G");
                    syncGZBean.persist(erp, null);
                    syncGZBean.getEntityManager().flush();
                    break;
                case "J":
                    //同步济南ERP
                    //resetFacno("J");
                    syncJNBean.persist(erp, null);
                    syncJNBean.getEntityManager().flush();
                    break;
                case "N":
                    //同步南京ERP
                    //resetFacno("N");
                    syncNJBean.persist(erp, null);
                    syncNJBean.getEntityManager().flush();
                    break;
                default:
            }
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            resetObjects();
        }

    }

    protected void resetObjects() {
        if (purvdrBuyerList != null && !purvdrBuyerList.isEmpty()) {
            purvdrBuyerList.clear();
        }
    }

    @Override
    public Boolean updateByOAPSN(String psn) {
        details.put(purvdrBuyerBean, purvdrBuyerList);
        HKCG017 oa = beanHKCG017.findByPSN(psn);
        if (oa == null) {
            throw new NullPointerException();
        }

        String facno, code, newvdrno;
        switch (oa.getFacno()) {
            //SHB和分公司统一到SHB下
            case "C":
            case "G":
            case "J":
            case "N":
                facno = "C";
                code = "S";
                break;
            default:
                facno = oa.getFacno();
                code = facno;
        }
        setCompany(facno);

        Purvdr erp;
        erp = this.findById(oa.getVdrno());
        if (erp == null) {
            throw new NullPointerException();
        }

        if ("1".equals(oa.getCheckbox11())) {
            Miscode m;
            this.miscodeBean.setCompany(facno);
            m = miscodeBean.findByCdesc(oa.getTtbankna());
            if (m == null) {
                m = new Miscode("NB", miscodeBean.getFormId("NB", "B", 4));
                m.setCdesc(oa.getTtbankna());
                m.setStatus('Y');
                m.setMascreyn('N');
                m.setCusds(oa.getTtbankna());
                miscodeBean.persist(m);
                erp.setTtbankna(oa.getTtbankna());
                erp.setTtbankno(m.getMiscodePK().getCode());
            }
        }

        if (oa.getBvdrds().equals("1")) {
            erp.setVdrds(oa.getVdrds());
        }
        if (oa.getBfkfs().equals("1")) {
            erp.setFkfs(oa.getFktype());
        }
        if (oa.getCheckbox10().equals("1")) {
            erp.setAddress(oa.getAddress());
        }
        if (oa.getCheckbox12().equals("1")) {
            erp.setTtbanknum(oa.getTtbanknum());
        }
        if (oa.getCheckbox13().equals("1")) {
            erp.setUniform(oa.getUniform());
        }
        if (oa.getCheckbox14().equals("1")) {
            erp.setContactman(oa.getContactman());
        }
        if (oa.getCheckbox15().equals("1")) {
            erp.setTel1(oa.getTel1());
        }
        if (oa.getCheckbox16().equals("1")) {
            erp.setTel2(oa.getTel2());
        }
        if (oa.getCheckbox17().equals("1")) {
            erp.setFax1(oa.getFax1());
        }

        try {
            update(erp);
            getEntityManager().flush();

            switch (oa.getFacno()) {
                case "G":
                    //同步广州ERP
                    //resetFacno("G");
                    syncGZBean.update(erp, null);
                    syncGZBean.getEntityManager().flush();
                    break;
                case "J":
                    //同步济南ERP
                    //resetFacno("J");
                    syncJNBean.update(erp, null);
                    syncJNBean.getEntityManager().flush();
                    break;
                case "N":
                    //同步南京ERP
                    //resetFacno("N");
                    syncNJBean.update(erp, null);
                    syncNJBean.getEntityManager().flush();
                    break;
                default:
            }
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            resetObjects();
        }

    }

}
