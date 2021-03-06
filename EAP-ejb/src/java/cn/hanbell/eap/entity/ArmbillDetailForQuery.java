/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.entity;

import com.lightshell.comm.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author C0160
 */
@Entity
@Table(name = "armbilldetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArmbillDetailForQuery.findAll", query = "SELECT a FROM ArmbillDetailForQuery a"),
    @NamedQuery(name = "ArmbillDetailForQuery.findById", query = "SELECT a FROM ArmbillDetailForQuery a WHERE a.id = :id"),
    @NamedQuery(name = "ArmbillDetailForQuery.findByPId", query = "SELECT a FROM ArmbillDetailForQuery a WHERE a.armbill.formid = :pid ORDER BY a.seq")})
public class ArmbillDetailForQuery extends BaseEntity {

    @JoinColumn(name = "pid", referencedColumnName = "formid")
    @ManyToOne
    private Armbill armbill;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "billno")
    private String billno;
    @Column(name = "billdate")
    @Temporal(TemporalType.DATE)
    private Date billdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "extaxes")
    private BigDecimal extaxes;
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public ArmbillDetailForQuery() {
        this.extaxes = BigDecimal.ZERO;
        this.taxes = BigDecimal.ZERO;
        this.amts = BigDecimal.ZERO;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    /**
     * @return the billdate
     */
    public Date getBilldate() {
        return billdate;
    }

    /**
     * @param billdate the billdate to set
     */
    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public BigDecimal getExtaxes() {
        return extaxes;
    }

    public void setExtaxes(BigDecimal extaxes) {
        this.extaxes = extaxes;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArmbillDetailForQuery)) {
            return false;
        }
        ArmbillDetailForQuery other = (ArmbillDetailForQuery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.eap.entity.ArmbillDetail[ id=" + id + " ]";
    }

    /**
     * @return the armbill
     */
    public Armbill getArmbill() {
        return armbill;
    }

    /**
     * @param armbill the armbill to set
     */
    public void setArmbill(Armbill armbill) {
        this.armbill = armbill;
    }

}
