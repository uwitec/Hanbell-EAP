/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.ejb;

import cn.hanbell.oa.comm.SuperEJBForEFGP;
import cn.hanbell.oa.entity.HKCW002;
import cn.hanbell.oa.entity.HKCW002Detail;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author C0160
 */
@Stateless
@LocalBean
public class HKCW002Bean extends SuperEJBForEFGP<HKCW002> {

    @EJB
    private HKCW002DetailBean hkcw002DetailBean;

    private List<HKCW002Detail> detailList;

    public HKCW002Bean() {
        super(HKCW002.class);
    }

    public List<HKCW002> findNotAcceptance() {
        Query query = getEntityManager().createNamedQuery("HKCW002.findNotAcceptance");
        try {
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public void setDetail(String fsn) {
        detailList = hkcw002DetailBean.findByFSN(fsn);
        if (detailList == null) {
            detailList = new ArrayList<>();
        }
    }

    /**
     * @return the detailList
     */
    public List<HKCW002Detail> getDetailList() {
        return detailList;
    }

}
