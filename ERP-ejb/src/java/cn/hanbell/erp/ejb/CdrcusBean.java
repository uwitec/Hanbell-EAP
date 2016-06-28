/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.crm.ejb.CRMGGBean;
import cn.hanbell.crm.entity.CRMGG;
import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Cdrcus;
import cn.hanbell.erp.entity.Cdrcusman;
import cn.hanbell.erp.entity.Cdrivo;
import cn.hanbell.erp.entity.Cdrscus;
import cn.hanbell.erp.entity.Invwh;
import cn.hanbell.erp.entity.Invwhclk;
import cn.hanbell.erp.entity.Miscode;
import cn.hanbell.erp.entity.Transwah;
import cn.hanbell.oa.ejb.HKYX006Bean;
import cn.hanbell.oa.ejb.SyncEFGPBean;
import cn.hanbell.oa.entity.HKYX006;
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
 * @author C0160
 */
@Stateless
@LocalBean
public class CdrcusBean extends SuperEJBForERP<Cdrcus> {

    @EJB
    private HKYX006Bean beanHKYX006;
    @EJB
    private CRMGGBean beanCRMGG;

    @EJB
    private SyncEFGPBean syncEFGPBean;

    @EJB
    private SyncGZBean syncGZBean;
    @EJB
    private SyncJNBean syncJNBean;
    @EJB
    private SyncNJBean syncNJBean;

    @EJB
    private CdrivoBean cdrivoBean;
    @EJB
    private CdrscusBean cdrscusBean;
    @EJB
    private CdrcusmanBean cdrcusmanBean;
    @EJB
    private InvwhBean invwhBean;
    @EJB
    private InvwhclkBean invwhclkBean;
    @EJB
    private TranswahBean transwahBean;

    private Cdrcus cdrcus;
    private final HashMap<SuperEJBForERP, List<?>> details = new HashMap<>();

    private final List<Cdrivo> cdrivoList = new ArrayList<>();
    private final List<Cdrscus> cdrscusList = new ArrayList<>();
    private final List<Cdrcusman> cdrcusmanList = new ArrayList<>();
    private final List<Transwah> transwahList = new ArrayList<>();
    private final List<Invwh> invwhList = new ArrayList<>();
    private final List<Invwhclk> invwhclkList = new ArrayList<>();
    private final List<Miscode> miscodeList = new ArrayList<>();

    public CdrcusBean() {
        super(Cdrcus.class);
    }

    @Override
    public Boolean initByOAPSN(String psn) {

        String newcusno, facno;

        HKYX006 oa = beanHKYX006.findByPSN(psn);
        if (oa == null) {
            throw new NullPointerException();
        }

        CRMGG crmgg = beanCRMGG.findById(oa.getGg001());
        if (crmgg == null) {
            throw new NullPointerException();
        }

        switch (oa.getFacno()) {
            //SHB和分公司统一到SHB下
            case "C":
            case "G":
            case "J":
            case "N":
                facno = "C";
                break;
            default:
                facno = "C";
        }

        details.put(transwahBean, transwahList);
        details.put(invwhclkBean, invwhclkList);
        details.put(invwhBean, invwhList);
        details.put(cdrcusmanBean, cdrcusmanList);
        details.put(cdrscusBean, cdrscusList);
        details.put(cdrivoBean, cdrivoList);
        details.put(miscodeBean, miscodeList);

        setCompany(facno);//遇到Hanson\QTC等时需要变更

        cdrcus = new Cdrcus();
        cdrcus.setPrnyn('N');
        cdrcus.setDecode(oa.getDecode().charAt(0));
        cdrcus.setCusna(oa.getGg003());
        cdrcus.setCusds(oa.getGg004());
        cdrcus.setCussta(oa.getCussta().charAt(0));
        cdrcus.setDecode(oa.getDecode().charAt(0));
        cdrcus.setCuskind(oa.getGg011());
        cdrcus.setDmcode(oa.getDmcode().charAt(0));
        if (oa.getGg036().length() <= 40) {
            cdrcus.setAddress1(oa.getGg036());
        } else if (oa.getGg036().length() > 40) {
            cdrcus.setAddress1(oa.getGg036().substring(0, 40));
            cdrcus.setAddress2(oa.getGg036().substring(40));
        }
        cdrcus.setUniform(oa.getGg030());
        cdrcus.setBilnum(oa.getGg109());
        cdrcus.setCoin(oa.getGg084());
        cdrcus.setTax(oa.getGg098().charAt(0));
        cdrcus.setSndcode(oa.getSndcode());
        cdrcus.setPaycode(oa.getGg113().charAt(0));
        cdrcus.setTermcode(oa.getGg112());

        cdrcus.setHandays1(oa.getHandays1().shortValue());
        cdrcus.setSacode(oa.getGg009());
        cdrcus.setAreacode(oa.getGg008());
        cdrcus.setCuycode(oa.getGg010());

        cdrcus.setBoss(oa.getGg018());
        cdrcus.setContactman(oa.getContactman());
        cdrcus.setTel1(oa.getGg024());
        cdrcus.setFax(oa.getGg027());
        cdrcus.setBegdate(oa.getGg017());
        if (oa.getGg031() != null) {
            cdrcus.setCapamt(oa.getGg031().longValue());
        }
        cdrcus.setCusbakna(oa.getCusbakna());
        cdrcus.setCusacctno(oa.getCusacctno());

        cdrcus.setIndate(BaseLib.getDate());
        cdrcus.setUserno(oa.getGg078());
        cdrcus.setShr("mis");
        cdrcus.setShzt("Y");

        newcusno = getFormId(cdrcus.getIndate(), "S" + cdrcus.getCuycode(), null, 5, "cdrcus", "cusno");
        cdrcus.setCusno(newcusno);

        //生成发票客户资料
        Cdrivo i = new Cdrivo(newcusno, (short) 1, newcusno);
        i.setCusna(cdrcus.getCusna());
        i.setAddress1(cdrcus.getAddress1());
        i.setAddress2(cdrcus.getAddress2());
        i.setAddress3(cdrcus.getAddress3());
        i.setAddress4(cdrcus.getAddress4());
        i.setAddress5(cdrcus.getAddress5());
        i.setUniform(cdrcus.getUniform());
        i.setDifcode('1');
        i.setIvoyn('Y');
        i.setIndate(cdrcus.getIndate());
        i.setUserno(cdrcus.getUserno());

        //生成指配客户资料
        Cdrscus s = new Cdrscus(newcusno, (short) 1);
        s.setShpcusno(newcusno);
        s.setCusna(cdrcus.getCusna());
        s.setAddress1(cdrcus.getAddress1());
        s.setAddress2(cdrcus.getAddress2());
        s.setAddress3(cdrcus.getAddress3());
        s.setAddress4(cdrcus.getAddress4());
        s.setAddress5(cdrcus.getAddress5());
        s.setAreacode(cdrcus.getAreacode());
        s.setCuycode(cdrcus.getCuycode());
        s.setZonecode(cdrcus.getZonenum());
        s.setTel(cdrcus.getTel1());
        s.setFax(cdrcus.getFax());
        s.setShpyn('Y');
        s.setContactman(cdrcus.getContactman());
        s.setIndate(cdrcus.getIndate());
        s.setUserno(cdrcus.getUserno());

        //生成负责业务
        Cdrcusman m = new Cdrcusman(facno, newcusno);
        m.setMan(oa.getSalesman());
        m.setLatdate(cdrcus.getIndate());

        //生成借客户仓
        Invwh w = new Invwh();
        w.setFacno(facno);
        w.setProno("1");
        w.setWareh("JC" + newcusno);
        if (cdrcus.getCusna().length() < 6) {
            w.setWhdsc("借-" + cdrcus.getCusna());
        } else {
            w.setWhdsc("借-" + cdrcus.getCusna().substring(0, 5));
        }
        w.setMrpco('N');
        w.setCostyn('Y');
        w.setWclerk(m.getMan());
        w.setIndate(cdrcus.getIndate());
        w.setUserno("mis");

        //生成仓管员
        Invwhclk u = new Invwhclk(w.getFacno(), w.getProno(), w.getWareh(), w.getWclerk());
        u.setMainyn('Y');
        u.setIndate(cdrcus.getIndate());
        u.setUserno("mis");

        //生成客户周转仓资料
        Transwah t = new Transwah(w.getFacno(), w.getProno(), '2', newcusno, (short) 1);
        t.setWareh(w.getWareh());

        //生成miscode资料
        Miscode c = new Miscode("CA", newcusno);
        c.setCdesc(cdrcus.getCusna());
        c.setStatus('Y');
        c.setMascreyn('Y');
        c.setCusds(cdrcus.getCusds());

        cdrivoList.add(i);
        cdrscusList.add(s);
        cdrcusmanList.add(m);
        invwhList.add(w);
        invwhclkList.add(u);
        transwahList.add(t);
        miscodeList.add(c);

        crmgg.setGg004(cdrcus.getCusds());
        crmgg.setGg008(cdrcus.getAreacode());
        crmgg.setGg009(cdrcus.getSacode());
        crmgg.setGg010(cdrcus.getCuycode());
        crmgg.setGg011(cdrcus.getCuskind());

        crmgg.setGg017(BaseLib.formatDate("yyyy-MM-dd", cdrcus.getBegdate()));
        crmgg.setGg018(cdrcus.getBoss());
        crmgg.setGg024(cdrcus.getTel1());
        crmgg.setGg027(cdrcus.getFax());
        crmgg.setGg030(cdrcus.getUniform());
        if (cdrcus.getCapamt() != null) {
            crmgg.setGg031(BigDecimal.valueOf(cdrcus.getCapamt()));
        }
        crmgg.setGg036(oa.getGg036());
        crmgg.setGg043(newcusno);

        crmgg.setGg084(cdrcus.getCoin());
        crmgg.setGg098(cdrcus.getTax());
        crmgg.setGg105(cdrcus.getCusdse());
        crmgg.setGg109(cdrcus.getBilnum());
        //交易类别字段形态不符
        crmgg.setGg113(cdrcus.getPaycode());
        //回写更多内容

        try {
            persist(cdrcus, details);
            getEntityManager().flush();

            beanCRMGG.update(crmgg);

            beanHKYX006.getEntityManager().detach(oa);
            oa.setPz(newcusno);
            syncEFGPBean.syncUpdate(oa, null);
            syncEFGPBean.getEntityManager().flush();
            syncEFGPBean.getEntityManager().clear();

            //同步广州ERP
            resetFacno("G");
            syncGZBean.persist(cdrcus, details);
            syncGZBean.getEntityManager().flush();
            //同步济南ERP
            resetFacno("J");
            syncJNBean.persist(cdrcus, details);
            syncJNBean.getEntityManager().flush();
            //同步南京ERP
            resetFacno("N");
            syncNJBean.persist(cdrcus, details);
            syncNJBean.getEntityManager().flush();

            return true;

        } catch (Exception ex) {
            return false;
        } finally {
            doAfterPersist();
        }
    }

    protected void resetFacno(String facno) {
        for (Cdrcusman m : cdrcusmanList) {
            m.getCdrcusmanPK().setFacno(facno);
        }
        for (Invwh w : invwhList) {
            w.setFacno(facno);
        }
        for (Invwhclk u : invwhclkList) {
            u.getInvwhclkPK().setFacno(facno);
        }
        for (Transwah t : transwahList) {
            t.getTranswahPK().setFacno(facno);
        }
    }

    protected Boolean doAfterPersist() {
        if (cdrivoList != null && !cdrivoList.isEmpty()) {
            cdrivoList.clear();
        }
        if (cdrscusList != null && !cdrscusList.isEmpty()) {
            cdrscusList.clear();
        }
        if (cdrcusmanList != null && !cdrcusmanList.isEmpty()) {
            cdrcusmanList.clear();
        }
        if (invwhList != null && !invwhList.isEmpty()) {
            invwhList.clear();
        }
        if (invwhclkList != null && !invwhclkList.isEmpty()) {
            invwhclkList.clear();
        }
        if (transwahList != null && !transwahList.isEmpty()) {
            transwahList.clear();
        }
        if (miscodeList != null && !miscodeList.isEmpty()) {
            miscodeList.clear();
        }
        if (details != null) {
            details.clear();
        }
        return true;
    }

}
