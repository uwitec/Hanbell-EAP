/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.crm.ejb.SERBQBean;
import cn.hanbell.crm.ejb.SERBRBean;
import cn.hanbell.crm.entity.SERBQ;
import cn.hanbell.crm.entity.SERBR;
import cn.hanbell.crm.entity.SERBRPK;
import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.SERI12;
import cn.hanbell.oa.entity.SERI12grid1SERI12;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C1587
 */
@Stateless
@LocalBean
public class SERI12Bean extends SuperEJBForEFGP<SERI12> {

    @EJB
    private SERBQBean serbqBean;

    @EJB
    private SERBRBean serbrBean;
    @EJB
    private SERI12grid1SERI12Bean seri12grid1seri12Bean;

    public SERI12Bean() {
        super(SERI12.class);
    }



    public boolean updateSerbq(String psn) {
        SERI12 h = findByPSN(psn);
        if (h == null) {
            throw new NullPointerException();
        }
        String BQ001;
        String BQ129;
        String BQ130;
        String BQ131;
        String BQ132;
        String BQ133;
        String BQ134;
        String BQ035;
        //List<Seri12> seri12 = findByFSN(h.getFormSerialNumber());
        BQ001 = h.getBq001();
        BQ035 = h.getBq035();
        BQ129 = h.getBq129();

        if (null != BQ129) {
            switch (BQ129) {
                case "客户投诉":
                    BQ129 = "1";
                    break;
                case "赠送":
                    BQ129 = "2";
                    break;
                case "技术支持":
                    BQ129 = "3";
                    break;
                case "统包服务":
                    BQ129 = "4";
                    break;
                case "例行巡检":
                    BQ129 = "5";
                    break;
                case "客户财产维修":
                    BQ129 = "6";
                    break;
                case "收费服务":
                    BQ129 = "7";
                    break;
                case "新机调试":
                    BQ129 = "8";
                    break;
                default:
                    return false;
            }
        }
        BQ130 = h.getBq130();
        BQ131 = h.getBq131();
        if (null != BQ131) {
            switch (BQ131) {
                case "汉钟原因免费":
                    BQ131 = "HZMF";
                    break;
                case "汉钟原因收费":
                    BQ131 = "HZSF";
                    break;
                case "客户原因免费":
                    BQ131 = "KHMF";
                    break;
                case "客户原因收费":
                    BQ131 = "KHSF";
                    break;
                case "柯茂客户原因免费":
                    BQ131 = "KMKHMF";
                    break;
                case "柯茂客户原因收费":
                    BQ131 = "KMKHSF";
                    break;
                case "柯茂原因免费":
                    BQ131 = "KMMF";
                    break;
                case "柯茂原因收费":
                    BQ131 = "KMSF";
                    break;
                case "其它":
                    BQ131 = "ZRQT";
                    break;
                default:
                    break;
            }
        }
        BQ132 = h.getBq132();
        BQ133 = h.getBq133();
        BQ134 = h.getBq134();
        if (null != BQ134) {
            switch (BQ134) {
                case "有效客诉":
                    BQ134 = "YX";
                    break;
                case "无效客诉":
                    BQ134 = "WX";
                    break;
                case "其他":
                    BQ134 = "QT";
                    break;
                default:
                    break;
            }
        }
        SERBQ BQ;
        BQ = serbqBean.findByBq001(BQ001);
        if (BQ == null) {
            throw new NullPointerException();
        }
        BQ.setBq001(BQ001);
        BQ.setBq035(BQ035);
        BQ.setBq129(BQ129);
        BQ.setBq130(BQ130);
        BQ.setBq131(BQ131);
        BQ.setBq132(BQ132);
        BQ.setBq133(BQ133);
        BQ.setBq134(BQ134);

        serbqBean.update(BQ);

        List<SERI12grid1SERI12> detail1 = seri12grid1seri12Bean.findByFSN(h.getFormSerialNumber());
        for (int i = 0; i < detail1.size(); i++) {
            SERBR BR;
            String a;
            a = i + "";
            BR = serbrBean.findByPK(BQ001, a);
            BR.setBr007(BQ035);
            serbrBean.update(BR);
        }
        return true;
    }
}
