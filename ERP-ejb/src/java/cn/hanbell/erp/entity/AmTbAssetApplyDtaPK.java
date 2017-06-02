/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author C1368
 */
@Embeddable
public class AmTbAssetApplyDtaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "trsep")
    private int trsep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "apply_had_sn")
    private String applyHadSn;

    public AmTbAssetApplyDtaPK() {
    }

    public AmTbAssetApplyDtaPK(int trsep, String applyHadSn) {
        this.trsep = trsep;
        this.applyHadSn = applyHadSn;
    }

    public int getTrsep() {
        return trsep;
    }

    public void setTrsep(int trsep) {
        this.trsep = trsep;
    }

    public String getApplyHadSn() {
        return applyHadSn;
    }

    public void setApplyHadSn(String applyHadSn) {
        this.applyHadSn = applyHadSn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) trsep;
        hash += (applyHadSn != null ? applyHadSn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmTbAssetApplyDtaPK)) {
            return false;
        }
        AmTbAssetApplyDtaPK other = (AmTbAssetApplyDtaPK) object;
        if (this.trsep != other.trsep) {
            return false;
        }
        if ((this.applyHadSn == null && other.applyHadSn != null) || (this.applyHadSn != null && !this.applyHadSn.equals(other.applyHadSn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.erp.entity.AmTbAssetApplyDtaPK[ trsep=" + trsep + ", applyHadSn=" + applyHadSn + " ]";
    }
    
}
