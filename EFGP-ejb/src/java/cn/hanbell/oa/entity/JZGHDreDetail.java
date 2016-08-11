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
 * @author Administrator
 */
@Entity
@Table(name = "HZ_CW033_reDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JZGHDreDetail.findAll", query = "SELECT j FROM JZGHDreDetail j"),
    @NamedQuery(name = "JZGHDreDetail.findBySummary", query = "SELECT j FROM JZGHDreDetail j WHERE j.summary = :summary"),
    @NamedQuery(name = "JZGHDreDetail.findByOid", query = "SELECT j FROM JZGHDreDetail j WHERE j.oid = :oid"),
    @NamedQuery(name = "JZGHDreDetail.findByLoanAmount", query = "SELECT j FROM JZGHDreDetail j WHERE j.loanAmount = :loanAmount"),
    @NamedQuery(name = "JZGHDreDetail.findByNotaxes", query = "SELECT j FROM JZGHDreDetail j WHERE j.notaxes = :notaxes"),
    @NamedQuery(name = "JZGHDreDetail.findByRemark", query = "SELECT j FROM JZGHDreDetail j WHERE j.remark = :remark"),
    @NamedQuery(name = "JZGHDreDetail.findByNo", query = "SELECT j FROM JZGHDreDetail j WHERE j.no = :no"),
    @NamedQuery(name = "JZGHDreDetail.findByTaxInclusive", query = "SELECT j FROM JZGHDreDetail j WHERE j.taxInclusive = :taxInclusive"),
    @NamedQuery(name = "JZGHDreDetail.findByAccno", query = "SELECT j FROM JZGHDreDetail j WHERE j.accno = :accno"),
    @NamedQuery(name = "JZGHDreDetail.findByEntertainReason", query = "SELECT j FROM JZGHDreDetail j WHERE j.entertainReason = :entertainReason"),
    @NamedQuery(name = "JZGHDreDetail.findByLoanNo", query = "SELECT j FROM JZGHDreDetail j WHERE j.loanNo = :loanNo"),
    @NamedQuery(name = "JZGHDreDetail.findByAccPeriod", query = "SELECT j FROM JZGHDreDetail j WHERE j.accPeriod = :accPeriod"),
    @NamedQuery(name = "JZGHDreDetail.findByAccYear", query = "SELECT j FROM JZGHDreDetail j WHERE j.accYear = :accYear"),
    @NamedQuery(name = "JZGHDreDetail.findByRefund", query = "SELECT j FROM JZGHDreDetail j WHERE j.refund = :refund"),
    @NamedQuery(name = "JZGHDreDetail.findByEntertainDate", query = "SELECT j FROM JZGHDreDetail j WHERE j.entertainDate = :entertainDate"),
    @NamedQuery(name = "JZGHDreDetail.findByLoanProp", query = "SELECT j FROM JZGHDreDetail j WHERE j.loanProp = :loanProp"),
    @NamedQuery(name = "JZGHDreDetail.findByBudgetDepttxt", query = "SELECT j FROM JZGHDreDetail j WHERE j.budgetDepttxt = :budgetDepttxt"),
    @NamedQuery(name = "JZGHDreDetail.findByBudgetAcc", query = "SELECT j FROM JZGHDreDetail j WHERE j.budgetAcc = :budgetAcc"),
    @NamedQuery(name = "JZGHDreDetail.findByResearch", query = "SELECT j FROM JZGHDreDetail j WHERE j.research = :research"),
    @NamedQuery(name = "JZGHDreDetail.findByEntertainPeople", query = "SELECT j FROM JZGHDreDetail j WHERE j.entertainPeople = :entertainPeople"),
    @NamedQuery(name = "JZGHDreDetail.findByProduct", query = "SELECT j FROM JZGHDreDetail j WHERE j.product = :product"),
    @NamedQuery(name = "JZGHDreDetail.findByEntertainObj", query = "SELECT j FROM JZGHDreDetail j WHERE j.entertainObj = :entertainObj"),
    @NamedQuery(name = "JZGHDreDetail.findByAccName", query = "SELECT j FROM JZGHDreDetail j WHERE j.accName = :accName"),
    @NamedQuery(name = "JZGHDreDetail.findByBudgetAccname", query = "SELECT j FROM JZGHDreDetail j WHERE j.budgetAccname = :budgetAccname"),
    @NamedQuery(name = "JZGHDreDetail.findByApplyAmount", query = "SELECT j FROM JZGHDreDetail j WHERE j.applyAmount = :applyAmount"),
    @NamedQuery(name = "JZGHDreDetail.findByTaxes", query = "SELECT j FROM JZGHDreDetail j WHERE j.taxes = :taxes"),
    @NamedQuery(name = "JZGHDreDetail.findByFSN", query = "SELECT j FROM JZGHDreDetail j WHERE j.formSerialNumber = :fsn")})
public class JZGHDreDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "summary")
    private String summary;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "loanAmount")
    private String loanAmount;
    @Size(max = 255)
    @Column(name = "notaxes")
    private String notaxes;
    @Size(max = 255)
    @Column(name = "remark")
    private String remark;
    @Size(max = 255)
    @Column(name = "no")
    private String no;
    @Size(max = 255)
    @Column(name = "taxInclusive")
    private Float taxInclusive;
    @Size(max = 255)
    @Column(name = "accno")
    private String accno;
    @Size(max = 255)
    @Column(name = "entertainReason")
    private String entertainReason;
    @Size(max = 255)
    @Column(name = "loanNo")
    private String loanNo;
    @Size(max = 255)
    @Column(name = "accPeriod")
    private String accPeriod;
    @Size(max = 255)
    @Column(name = "accYear")
    private String accYear;
    @Size(max = 255)
    @Column(name = "refund")
    private Float refund;
    @Size(max = 255)
    @Column(name = "entertainDate")
    private String entertainDate;
    @Size(max = 255)
    @Column(name = "loanProp")
    private String loanProp;
    @Size(max = 255)
    @Column(name = "budgetDept_txt")
    private String budgetDepttxt;
    @Size(max = 255)
    @Column(name = "budgetDept_lbl")
    private String budgetDeptlbl;
    @Size(max = 255)
    @Column(name = "budgetAcc")
    private String budgetAcc;
    @Size(max = 255)
    @Column(name = "research")
    private String research;
    @Size(max = 255)
    @Column(name = "entertainPeople")
    private String entertainPeople;
    @Size(max = 255)
    @Column(name = "product")
    private String product;
    @Size(max = 255)
    @Column(name = "entertainObj")
    private String entertainObj;
    @Size(max = 255)
    @Column(name = "accName")
    private String accName;
    @Size(max = 255)
    @Column(name = "budgetAccname")
    private String budgetAccname;
    @Size(max = 255)
    @Column(name = "applyAmount")
    private Float applyAmount;
    @Size(max = 255)
    @Column(name = "taxes")
    private String taxes;
    @Size(max = 255)
    @Column(name = "centerid")
    private String centerid;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;

    public JZGHDreDetail() {
    }

    public JZGHDreDetail(String oid) {
        this.oid = oid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getNotaxes() {
        return notaxes;
    }

    public void setNotaxes(String notaxes) {
        this.notaxes = notaxes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Float getTaxInclusive() {
        return taxInclusive;
    }

    public void setTaxInclusive(Float taxInclusive) {
        this.taxInclusive = taxInclusive;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getEntertainReason() {
        return entertainReason;
    }

    public void setEntertainReason(String entertainReason) {
        this.entertainReason = entertainReason;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public String getAccPeriod() {
        return accPeriod;
    }

    public void setAccPeriod(String accPeriod) {
        this.accPeriod = accPeriod;
    }

    public String getAccYear() {
        return accYear;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear;
    }

    public Float getRefund() {
        return refund;
    }

    public void setRefund(Float refund) {
        this.refund = refund;
    }

    public String getEntertainDate() {
        return entertainDate;
    }

    public void setEntertainDate(String entertainDate) {
        this.entertainDate = entertainDate;
    }

    public String getLoanProp() {
        return loanProp;
    }

    public void setLoanProp(String loanProp) {
        this.loanProp = loanProp;
    }

    public String getBudgetDepttxt() {
        return budgetDepttxt;
    }

    public void setBudgetDepttxt(String budgetDepttxt) {
        this.budgetDepttxt = budgetDepttxt;
    }

    public String getBudgetDeptlbl() {
        return budgetDeptlbl;
    }

    public void setBudgetDeptlbl(String budgetDeptlbl) {
        this.budgetDeptlbl = budgetDeptlbl;
    }
    public String getBudgetAcc() {
        return budgetAcc;
    }

    public void setBudgetAcc(String budgetAcc) {
        this.budgetAcc = budgetAcc;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getEntertainPeople() {
        return entertainPeople;
    }

    public void setEntertainPeople(String entertainPeople) {
        this.entertainPeople = entertainPeople;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEntertainObj() {
        return entertainObj;
    }

    public void setEntertainObj(String entertainObj) {
        this.entertainObj = entertainObj;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getBudgetAccname() {
        return budgetAccname;
    }

    public void setBudgetAccname(String budgetAccname) {
        this.budgetAccname = budgetAccname;
    }

    public Float getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(Float applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

      public String getCenterid() {
        return centerid;
    }

    public void setCenterid(String centerid) {
        this.centerid = centerid;
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
        if (!(object instanceof JZGHDreDetail)) {
            return false;
        }
        JZGHDreDetail other = (JZGHDreDetail) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.JZGHDreDetail[ oid=" + oid + " ]";
    }

  
    
}
