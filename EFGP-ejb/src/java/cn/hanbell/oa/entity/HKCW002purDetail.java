/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C1368
 */
@Entity
@Table(name = "HK_CW002_purDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HKCW002purDetail.findAll", query = "SELECT h FROM HKCW002purDetail h"),
    @NamedQuery(name = "HKCW002purDetail.findBySubyearbal", query = "SELECT h FROM HKCW002purDetail h WHERE h.subyearbal = :subyearbal"),
    @NamedQuery(name = "HKCW002purDetail.findByOid", query = "SELECT h FROM HKCW002purDetail h WHERE h.oid = :oid"),
    @NamedQuery(name = "HKCW002purDetail.findByBudgetaccname", query = "SELECT h FROM HKCW002purDetail h WHERE h.budgetaccname = :budgetaccname"),
    @NamedQuery(name = "HKCW002purDetail.findByBudgetacc", query = "SELECT h FROM HKCW002purDetail h WHERE h.budgetacc = :budgetacc"),
    @NamedQuery(name = "HKCW002purDetail.findBySubperbal", query = "SELECT h FROM HKCW002purDetail h WHERE h.subperbal = :subperbal"),
    @NamedQuery(name = "HKCW002purDetail.findByDmark1", query = "SELECT h FROM HKCW002purDetail h WHERE h.dmark1 = :dmark1"),
    @NamedQuery(name = "HKCW002purDetail.findByDmark2", query = "SELECT h FROM HKCW002purDetail h WHERE h.dmark2 = :dmark2"),
    @NamedQuery(name = "HKCW002purDetail.findByDmark1name", query = "SELECT h FROM HKCW002purDetail h WHERE h.dmark1name = :dmark1name"),
    @NamedQuery(name = "HKCW002purDetail.findByMidclasscode", query = "SELECT h FROM HKCW002purDetail h WHERE h.midclasscode = :midclasscode"),
    @NamedQuery(name = "HKCW002purDetail.findByQgnum", query = "SELECT h FROM HKCW002purDetail h WHERE h.qgnum = :qgnum"),
    @NamedQuery(name = "HKCW002purDetail.findBySpdse", query = "SELECT h FROM HKCW002purDetail h WHERE h.spdse = :spdse"),
    @NamedQuery(name = "HKCW002purDetail.findByApamts", query = "SELECT h FROM HKCW002purDetail h WHERE h.apamts = :apamts"),
    @NamedQuery(name = "HKCW002purDetail.findByApplyunit", query = "SELECT h FROM HKCW002purDetail h WHERE h.applyunit = :applyunit"),
    @NamedQuery(name = "HKCW002purDetail.findByAdscode", query = "SELECT h FROM HKCW002purDetail h WHERE h.adscode = :adscode"),
    @NamedQuery(name = "HKCW002purDetail.findBySmallclasscode", query = "SELECT h FROM HKCW002purDetail h WHERE h.smallclasscode = :smallclasscode"),
    @NamedQuery(name = "HKCW002purDetail.findByPremoney", query = "SELECT h FROM HKCW002purDetail h WHERE h.premoney = :premoney"),
    @NamedQuery(name = "HKCW002purDetail.findBySfjj", query = "SELECT h FROM HKCW002purDetail h WHERE h.sfjj = :sfjj"),
    @NamedQuery(name = "HKCW002purDetail.findByDmark2name", query = "SELECT h FROM HKCW002purDetail h WHERE h.dmark2name = :dmark2name"),
    @NamedQuery(name = "HKCW002purDetail.findBySeq", query = "SELECT h FROM HKCW002purDetail h WHERE h.seq = :seq"),
    @NamedQuery(name = "HKCW002purDetail.findByLynum", query = "SELECT h FROM HKCW002purDetail h WHERE h.lynum = :lynum"),
    @NamedQuery(name = "HKCW002purDetail.findByPncode", query = "SELECT h FROM HKCW002purDetail h WHERE h.pncode = :pncode"),
    @NamedQuery(name = "HKCW002purDetail.findByBigclasscode", query = "SELECT h FROM HKCW002purDetail h WHERE h.bigclasscode = :bigclasscode"),
    @NamedQuery(name = "HKCW002purDetail.findByApplynum", query = "SELECT h FROM HKCW002purDetail h WHERE h.applynum = :applynum"),
    @NamedQuery(name = "HKCW002purDetail.findByDatetime1Txt", query = "SELECT h FROM HKCW002purDetail h WHERE h.datetime1Txt = :datetime1Txt"),
    @NamedQuery(name = "HKCW002purDetail.findByFSN", query = "SELECT h FROM HKCW002purDetail h WHERE h.formSerialNumber = :fsn")})
public class HKCW002purDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "subyearbal")
    private String subyearbal;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "budgetaccname")
    private String budgetaccname;
    @Size(max = 255)
    @Column(name = "budgetacc")
    private String budgetacc;
    @Size(max = 255)
    @Column(name = "subperbal")
    private String subperbal;
    @Size(max = 255)
    @Column(name = "dmark1")
    private String dmark1;
    @Size(max = 255)
    @Column(name = "dmark2")
    private String dmark2;
    @Size(max = 255)
    @Column(name = "dmark1name")
    private String dmark1name;
    @Size(max = 255)
    @Column(name = "midclasscode")
    private String midclasscode;
    @Size(max = 255)
    @Column(name = "qgnum")
    private String qgnum;
    @Size(max = 255)
    @Column(name = "spdse")
    private String spdse;
    @Size(max = 255)
    @Column(name = "apamts")
    private String apamts;
    @Size(max = 255)
    @Column(name = "applyunit")
    private String applyunit;
    @Size(max = 255)
    @Column(name = "adscode")
    private String adscode;
    @Size(max = 255)
    @Column(name = "smallclasscode")
    private String smallclasscode;
    @Size(max = 255)
    @Column(name = "premoney")
    private String premoney;
    @Size(max = 255)
    @Column(name = "sfjj")
    private String sfjj;
    @Size(max = 255)
    @Column(name = "dmark2name")
    private String dmark2name;
    @Size(max = 255)
    @Column(name = "seq")
    private String seq;
    @Size(max = 255)
    @Column(name = "lynum")
    private String lynum;
    @Size(max = 255)
    @Column(name = "pncode")
    private String pncode;
    @Size(max = 255)
    @Column(name = "bigclasscode")
    private String bigclasscode;
    @Size(max = 255)
    @Column(name = "applynum")
    private String applynum;
    @Size(max = 255)
    @Column(name = "datetime1_txt")
    private String datetime1Txt;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;

    public HKCW002purDetail() {
    }

    public HKCW002purDetail(String oid) {
        this.oid = oid;
    }

    public String getSubyearbal() {
        return subyearbal;
    }

    public void setSubyearbal(String subyearbal) {
        this.subyearbal = subyearbal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBudgetaccname() {
        return budgetaccname;
    }

    public void setBudgetaccname(String budgetaccname) {
        this.budgetaccname = budgetaccname;
    }

    public String getBudgetacc() {
        return budgetacc;
    }

    public void setBudgetacc(String budgetacc) {
        this.budgetacc = budgetacc;
    }

    public String getSubperbal() {
        return subperbal;
    }

    public void setSubperbal(String subperbal) {
        this.subperbal = subperbal;
    }

    public String getDmark1() {
        return dmark1;
    }

    public void setDmark1(String dmark1) {
        this.dmark1 = dmark1;
    }

    public String getDmark2() {
        return dmark2;
    }

    public void setDmark2(String dmark2) {
        this.dmark2 = dmark2;
    }

    public String getDmark1name() {
        return dmark1name;
    }

    public void setDmark1name(String dmark1name) {
        this.dmark1name = dmark1name;
    }

    public String getMidclasscode() {
        return midclasscode;
    }

    public void setMidclasscode(String midclasscode) {
        this.midclasscode = midclasscode;
    }

    public String getQgnum() {
        return qgnum;
    }

    public void setQgnum(String qgnum) {
        this.qgnum = qgnum;
    }

    public String getSpdse() {
        return spdse;
    }

    public void setSpdse(String spdse) {
        this.spdse = spdse;
    }

    public String getApamts() {
        return apamts;
    }

    public void setApamts(String apamts) {
        this.apamts = apamts;
    }

    public String getApplyunit() {
        return applyunit;
    }

    public void setApplyunit(String applyunit) {
        this.applyunit = applyunit;
    }

    public String getAdscode() {
        return adscode;
    }

    public void setAdscode(String adscode) {
        this.adscode = adscode;
    }

    public String getSmallclasscode() {
        return smallclasscode;
    }

    public void setSmallclasscode(String smallclasscode) {
        this.smallclasscode = smallclasscode;
    }

    public String getPremoney() {
        return premoney;
    }

    public void setPremoney(String premoney) {
        this.premoney = premoney;
    }

    public String getSfjj() {
        return sfjj;
    }

    public void setSfjj(String sfjj) {
        this.sfjj = sfjj;
    }

    public String getDmark2name() {
        return dmark2name;
    }

    public void setDmark2name(String dmark2name) {
        this.dmark2name = dmark2name;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getLynum() {
        return lynum;
    }

    public void setLynum(String lynum) {
        this.lynum = lynum;
    }

    public String getPncode() {
        return pncode;
    }

    public void setPncode(String pncode) {
        this.pncode = pncode;
    }

    public String getBigclasscode() {
        return bigclasscode;
    }

    public void setBigclasscode(String bigclasscode) {
        this.bigclasscode = bigclasscode;
    }

    public String getApplynum() {
        return applynum;
    }

    public void setApplynum(String applynum) {
        this.applynum = applynum;
    }

    public String getDatetime1Txt() {
        return datetime1Txt;
    }

    public void setDatetime1Txt(String datetime1Txt) {
        this.datetime1Txt = datetime1Txt;
    }

    public String getFormSerialNumber() {
        return formSerialNumber;
    }

    public void setFormSerialNumber(String formSerialNumber) {
        this.formSerialNumber = formSerialNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HKCW002purDetail)) {
            return false;
        }
        HKCW002purDetail other = (HKCW002purDetail) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.HKCW002purDetail[ oid=" + oid + " ]";
    }
    
}
