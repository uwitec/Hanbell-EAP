/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "HZ_CW017")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JZD.findAll", query = "SELECT j FROM JZD j"),
    @NamedQuery(name = "JZD.findByOid", query = "SELECT j FROM JZD j WHERE j.oid = :oid"),
    @NamedQuery(name = "JZD.findByCenterid", query = "SELECT j FROM JZD j WHERE j.centerid = :centerid"),
    @NamedQuery(name = "JZD.findByOther", query = "SELECT j FROM JZD j WHERE j.other = :other"),
    @NamedQuery(name = "JZD.findByAllowance", query = "SELECT j FROM JZD j WHERE j.allowance = :allowance"),
    @NamedQuery(name = "JZD.findByReason", query = "SELECT j FROM JZD j WHERE j.reason = :reason"),
    @NamedQuery(name = "JZD.findByPrePayDate", query = "SELECT j FROM JZD j WHERE j.prePayDate = :prePayDate"),
    @NamedQuery(name = "JZD.findByEntertain", query = "SELECT j FROM JZD j WHERE j.entertain = :entertain"),
    @NamedQuery(name = "JZD.findByPreCustomer", query = "SELECT j FROM JZD j WHERE j.preCustomer = :preCustomer"),
    @NamedQuery(name = "JZD.findByPreLocation", query = "SELECT j FROM JZD j WHERE j.preLocation = :preLocation"),
    @NamedQuery(name = "JZD.findByPeople", query = "SELECT j FROM JZD j WHERE j.people = :people"),
    @NamedQuery(name = "JZD.findByFacno", query = "SELECT j FROM JZD j WHERE j.facno = :facno"),
    @NamedQuery(name = "JZD.findByTrafficfee", query = "SELECT j FROM JZD j WHERE j.trafficfee = :trafficfee"),
    @NamedQuery(name = "JZD.findByPreAccno", query = "SELECT j FROM JZD j WHERE j.preAccno = :preAccno"),
    @NamedQuery(name = "JZD.findByCreator", query = "SELECT j FROM JZD j WHERE j.creator = :creator"),
    @NamedQuery(name = "JZD.findByAmount", query = "SELECT j FROM JZD j WHERE j.amount = :amount"),
    @NamedQuery(name = "JZD.findByPSN", query = "SELECT j FROM JZD j WHERE j.processSerialNumber = :psn"),
    @NamedQuery(name = "JZD.findByPreAccname", query = "SELECT j FROM JZD j WHERE j.preAccname = :preAccname"),
    @NamedQuery(name = "JZD.findByDeptPeriod", query = "SELECT j FROM JZD j WHERE j.deptPeriod = :deptPeriod"),
    @NamedQuery(name = "JZD.findByDeptYear", query = "SELECT j FROM JZD j WHERE j.deptYear = :deptYear"),
    @NamedQuery(name = "JZD.findByLoanTotal", query = "SELECT j FROM JZD j WHERE j.loanTotal = :loanTotal"),
    @NamedQuery(name = "JZD.findByCoin", query = "SELECT j FROM JZD j WHERE j.coin = :coin"),
    @NamedQuery(name = "JZD.findByLoanUser", query = "SELECT j FROM JZD j WHERE j.loanUser = :loanUser"),
    @NamedQuery(name = "JZD.findByLoanProperty", query = "SELECT j FROM JZD j WHERE j.loanProperty = :loanProperty"),
    @NamedQuery(name = "JZD.findByProduct", query = "SELECT j FROM JZD j WHERE j.product = :product"),
    @NamedQuery(name = "JZD.findByLoanDept", query = "SELECT j FROM JZD j WHERE j.loanDept = :loanDept"),
    @NamedQuery(name = "JZD.findByArrears", query = "SELECT j FROM JZD j WHERE j.arrears = :arrears"),
    @NamedQuery(name = "JZD.findByTraffic", query = "SELECT j FROM JZD j WHERE j.traffic = :traffic"),
    @NamedQuery(name = "JZD.findByTotalRMB", query = "SELECT j FROM JZD j WHERE j.totalRMB = :totalRMB"),
    @NamedQuery(name = "JZD.findByDays", query = "SELECT j FROM JZD j WHERE j.days = :days"),
    @NamedQuery(name = "JZD.findByRatio", query = "SELECT j FROM JZD j WHERE j.ratio = :ratio"),
    @NamedQuery(name = "JZD.findByAccommodation", query = "SELECT j FROM JZD j WHERE j.accommodation = :accommodation"),
    @NamedQuery(name = "JZD.findByLoanDate", query = "SELECT j FROM JZD j WHERE j.loanDate = :loanDate"),
    @NamedQuery(name = "JZD.findByFSN", query = "SELECT j FROM JZD j WHERE j.formSerialNumber = :fsn"),
})
public class JZD implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "centerid")
    private String centerid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "other")
    private Double other;
    @Column(name = "allowance")
    private Double allowance;
    @Size(max = 255)
    @Column(name = "reason")
    private String reason;
    @Column(name = "prePayDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prePayDate;
    @Column(name = "entertain")
    private Double entertain;
    @Size(max = 255)
    @Column(name = "preCustomer")
    private String preCustomer;
    @Size(max = 255)
    @Column(name = "preLocation")
    private String preLocation;
    @Column(name = "people")
    private Integer people;
    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
    @Column(name = "trafficfee")
    private Double trafficfee;
    @Size(max = 255)
    @Column(name = "preAccno")
    private String preAccno;
    @Size(max = 255)
    @Column(name = "creator")
    private String creator;
    @Size(max = 255)
    @Column(name = "amount")
    private String amount;
    @Size(max = 255)
    @Column(name = "processSerialNumber")
    private String processSerialNumber;
    @Size(max = 255)
    @Column(name = "preAccname")
    private String preAccname;
    @Column(name = "deptPeriod")
    private Double deptPeriod;
    @Column(name = "deptYear")
    private Double deptYear;
    @Column(name = "loanTotal")
    private Double loanTotal;
    @Size(max = 255)
    @Column(name = "coin")
    private String coin;
    @Size(max = 255)
    @Column(name = "loanUser")
    private String loanUser;
    @Size(max = 255)
    @Column(name = "loanProperty")
    private String loanProperty;
    @Size(max = 255)
    @Column(name = "product")
    private String product;
    @Size(max = 255)
    @Column(name = "loanDept")
    private String loanDept;
    @Column(name = "arrears")
    private Double arrears;
    @Size(max = 255)
    @Column(name = "traffic")
    private String traffic;
    @Column(name = "totalRMB")
    private Double totalRMB;
    @Column(name = "days")
    private Integer days;
    @Column(name = "ratio")
    private Double ratio;
    @Column(name = "accommodation")
    private Double accommodation;
    @Column(name = "loanDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;

    public JZD() {
    }

    public JZD(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCenterid() {
        return centerid;
    }

    public void setCenterid(String centerid) {
        this.centerid = centerid;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public Double getAllowance() {
        return allowance;
    }

    public void setAllowance(Double allowance) {
        this.allowance = allowance;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getPrePayDate() {
        return prePayDate;
    }

    public void setPrePayDate(Date prePayDate) {
        this.prePayDate = prePayDate;
    }

    public Double getEntertain() {
        return entertain;
    }

    public void setEntertain(Double entertain) {
        this.entertain = entertain;
    }

    public String getPreCustomer() {
        return preCustomer;
    }

    public void setPreCustomer(String preCustomer) {
        this.preCustomer = preCustomer;
    }

    public String getPreLocation() {
        return preLocation;
    }

    public void setPreLocation(String preLocation) {
        this.preLocation = preLocation;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getFacno() {
        return facno;
    }

    public void setFacno(String facno) {
        this.facno = facno;
    }

    public Double getTrafficfee() {
        return trafficfee;
    }

    public void setTrafficfee(Double trafficfee) {
        this.trafficfee = trafficfee;
    }

    public String getPreAccno() {
        return preAccno;
    }

    public void setPreAccno(String preAccno) {
        this.preAccno = preAccno;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProcessSerialNumber() {
        return processSerialNumber;
    }

    public void setProcessSerialNumber(String processSerialNumber) {
        this.processSerialNumber = processSerialNumber;
    }

    public String getPreAccname() {
        return preAccname;
    }

    public void setPreAccname(String preAccname) {
        this.preAccname = preAccname;
    }

    public Double getDeptPeriod() {
        return deptPeriod;
    }

    public void setDeptPeriod(Double deptPeriod) {
        this.deptPeriod = deptPeriod;
    }

    public Double getDeptYear() {
        return deptYear;
    }

    public void setDeptYear(Double deptYear) {
        this.deptYear = deptYear;
    }

    public Double getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(Double loanTotal) {
        this.loanTotal = loanTotal;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getLoanUser() {
        return loanUser;
    }

    public void setLoanUser(String loanUser) {
        this.loanUser = loanUser;
    }

    public String getLoanProperty() {
        return loanProperty;
    }

    public void setLoanProperty(String loanProperty) {
        this.loanProperty = loanProperty;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLoanDept() {
        return loanDept;
    }

    public void setLoanDept(String loanDept) {
        this.loanDept = loanDept;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public Double getTotalRMB() {
        return totalRMB;
    }

    public void setTotalRMB(Double totalRMB) {
        this.totalRMB = totalRMB;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Double accommodation) {
        this.accommodation = accommodation;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
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
        if (!(object instanceof JZD)) {
            return false;
        }
        JZD other = (JZD) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.JZD[ oid=" + oid + " ]";
    }
    
}
