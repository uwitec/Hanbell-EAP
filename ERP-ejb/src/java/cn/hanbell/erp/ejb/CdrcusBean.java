/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.ejb;

import cn.hanbell.erp.comm.SuperEJBForERP;
import cn.hanbell.erp.entity.Cdrcus;
import cn.hanbell.erp.entity.Cdrcusman;
import cn.hanbell.erp.entity.Cdrivo;
import cn.hanbell.erp.entity.Cdrscus;
import cn.hanbell.erp.entity.Invwh;
import cn.hanbell.erp.entity.Invwhclk;
import cn.hanbell.erp.entity.Miscode;
import cn.hanbell.erp.entity.Transwah;
import cn.hanbell.util.BaseLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
        facno = "C";//遇到Hanson\QTC等时需要变更

        details.put(transwahBean, transwahList);
        details.put(invwhclkBean, invwhclkList);
        details.put(invwhBean, invwhList);
        details.put(cdrcusmanBean, cdrcusmanList);
        details.put(cdrscusBean, cdrscusList);
        details.put(cdrivoBean, cdrivoList);
        details.put(miscodeBean, miscodeList);

        //<---------------------------------
        //获取OA资料转换成ERP对象        
        //--------------------------------->
        setCompany(facno);
        //测试随机生成一个客户简称
        Random r = new Random();
        cdrcus = new Cdrcus();
        cdrcus.setPrnyn('N');
        cdrcus.setDecode('1');
        cdrcus.setCusna("EFGP" + r.nextInt(1000));
        cdrcus.setCusds("EFGP" + r.nextInt(1000) + "完整名称");
        cdrcus.setCuskind("R");
        cdrcus.setSacode("01");
        cdrcus.setAreacode("HD");
        cdrcus.setCuycode("ZJ");
        cdrcus.setAddress1("XXXXXXXX");

        cdrcus.setUniform("310116" + r.nextInt(100000000));
        cdrcus.setCoin("RMB");
        cdrcus.setTax('4');
        cdrcus.setBilnum("A");
        cdrcus.setDmcode('1');
        cdrcus.setPaycode('3');
        cdrcus.setHandays1((short) 30);
        cdrcus.setSndcode("001");
        cdrcus.setTermcode("C&F");

        cdrcus.setBoss("李老板");
        cdrcus.setContactman("李老板");
        cdrcus.setTel1("57350280");
        cdrcus.setFax("57352004");
        cdrcus.setBegdate(BaseLib.getDate());
        cdrcus.setCapamt(1000000l);

        cdrcus.setIndate(BaseLib.getDate());
        cdrcus.setUserno("C0160");
        cdrcus.setShr("C0160");
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
        m.setMan("mis");
        m.setLatdate(cdrcus.getIndate());

        //生成借客户仓
        Invwh w = new Invwh();
        w.setFacno(facno);
        w.setProno("1");
        w.setWareh("JC" + newcusno);
        w.setWhdsc("借-" + cdrcus.getCusna());
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

        try {
            persist(cdrcus, details);
            getEntityManager().flush();
            //同步广州ERP
            resetFacno("G");
            syncGZBean.syncPersist(cdrcus, details);
            syncGZBean.getEntityManager().flush();
            //同步济南ERP
            resetFacno("J");
            syncJNBean.syncPersist(cdrcus, details);
            syncJNBean.getEntityManager().flush();
            //同步南京ERP
            resetFacno("N");
            syncNJBean.syncPersist(cdrcus, details);
            syncNJBean.getEntityManager().flush();

            return doAfterPersist();

        } catch (Exception ex) {
            return false;
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
