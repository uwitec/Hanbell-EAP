/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C1368
 */
@Entity
@Table(name = "am_tb_asset_apply_dta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AmTbAssetApplyDta.findAll", query = "SELECT a FROM AmTbAssetApplyDta a"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByTrsep", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.amTbAssetApplyDtaPK.trsep = :trsep"),
    @NamedQuery(name = "AmTbAssetApplyDta.findBySpdsc", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.spdsc = :spdsc"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByApplyNum", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.applyNum = :applyNum"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByApplyHadSn", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.amTbAssetApplyDtaPK.applyHadSn = :applyHadSn"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByPnCode", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.pnCode = :pnCode"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByDispatchNum", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.dispatchNum = :dispatchNum"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByRequestNum", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.requestNum = :requestNum"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByMemo", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.memo = :memo"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByBudgetacc", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.budgetacc = :budgetacc"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByDmark2", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.dmark2 = :dmark2"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByDmark1", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.dmark1 = :dmark1"),
    @NamedQuery(name = "AmTbAssetApplyDta.findByPreprice", query = "SELECT a FROM AmTbAssetApplyDta a WHERE a.preprice = :preprice")})
public class AmTbAssetApplyDta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmTbAssetApplyDtaPK amTbAssetApplyDtaPK;
    @Size(max = 20)
    @Column(name = "spdsc")
    private String spdsc;
    @Column(name = "apply_num")
    private Integer applyNum;
    @Size(max = 4)
    @Column(name = "type_dta_code")
    private String typeDtaCode;
    @Size(max = 4)
    @Column(name = "type_dta_dta_code")
    private String typeDtaDtaCode;
    @Size(max = 20)
    @Column(name = "pn_code")
    private String pnCode;
    @Column(name = "dispatch_num")
    private Integer dispatchNum;
    @Column(name = "request_num")
    private Integer requestNum;
    @Size(max = 4)
    @Column(name = "type_had_code")
    private String typeHadCode;
    @Size(max = 100)
    @Column(name = "memo")
    private String memo;
    @Size(max = 20)
    @Column(name = "budgetacc")
    private String budgetacc;
    @Size(max = 10)
    @Column(name = "dmark2")
    private String dmark2;
    @Size(max = 10)
    @Column(name = "dmark1")
    private String dmark1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preprice")
    private BigDecimal preprice;
    @JoinColumn(name = "apply_had_sn", referencedColumnName = "apply_had_sn", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AmTbAssetApplyHad amTbAssetApplyHad;

    public AmTbAssetApplyDta() {
    }

    public AmTbAssetApplyDta(AmTbAssetApplyDtaPK amTbAssetApplyDtaPK) {
        this.amTbAssetApplyDtaPK = amTbAssetApplyDtaPK;
    }

    public AmTbAssetApplyDta(int trsep, String applyHadSn) {
        this.amTbAssetApplyDtaPK = new AmTbAssetApplyDtaPK(trsep, applyHadSn);
    }

    public AmTbAssetApplyDtaPK getAmTbAssetApplyDtaPK() {
        return amTbAssetApplyDtaPK;
    }

    public void setAmTbAssetApplyDtaPK(AmTbAssetApplyDtaPK amTbAssetApplyDtaPK) {
        this.amTbAssetApplyDtaPK = amTbAssetApplyDtaPK;
    }

    public String getSpdsc() {
        return spdsc;
    }

    public void setSpdsc(String spdsc) {
        this.spdsc = spdsc;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getTypeDtaCode() {
        return typeDtaCode;
    }

    public void setTypeDtaCode(String typeDtaCode) {
        this.typeDtaCode = typeDtaCode;
    }

    public String getTypeDtaDtaCode() {
        return typeDtaDtaCode;
    }

    public void setTypeDtaDtaCode(String typeDtaDtaCode) {
        this.typeDtaDtaCode = typeDtaDtaCode;
    }
    
    public String getPnCode() {
        return pnCode;
    }

    public void setPnCode(String pnCode) {
        this.pnCode = pnCode;
    }

    public Integer getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(Integer dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public Integer getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(Integer requestNum) {
        this.requestNum = requestNum;
    }

    public String getTypeHadCode() {
        return typeHadCode;
    }

    public void setTypeHadCode(String typeHadCode) {
        this.typeHadCode = typeHadCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBudgetacc() {
        return budgetacc;
    }

    public void setBudgetacc(String budgetacc) {
        this.budgetacc = budgetacc;
    }

    public String getDmark2() {
        return dmark2;
    }

    public void setDmark2(String dmark2) {
        this.dmark2 = dmark2;
    }

    public String getDmark1() {
        return dmark1;
    }

    public void setDmark1(String dmark1) {
        this.dmark1 = dmark1;
    }

    public BigDecimal getPreprice() {
        return preprice;
    }

    public void setPreprice(BigDecimal preprice) {
        this.preprice = preprice;
    }

    public AmTbAssetApplyHad getAmTbAssetApplyHad() {
        return amTbAssetApplyHad;
    }

    public void setAmTbAssetApplyHad(AmTbAssetApplyHad amTbAssetApplyHad) {
        this.amTbAssetApplyHad = amTbAssetApplyHad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amTbAssetApplyDtaPK != null ? amTbAssetApplyDtaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmTbAssetApplyDta)) {
            return false;
        }
        AmTbAssetApplyDta other = (AmTbAssetApplyDta) object;
        if ((this.amTbAssetApplyDtaPK == null && other.amTbAssetApplyDtaPK != null) || (this.amTbAssetApplyDtaPK != null && !this.amTbAssetApplyDtaPK.equals(other.amTbAssetApplyDtaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.erp.entity.AmTbAssetApplyDta[ amTbAssetApplyDtaPK=" + amTbAssetApplyDtaPK + " ]";
    }
    
}
