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
 * @author C0160
 */
@Entity
@Table(name = "BXD_Detail01")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BXDDetail01.findAll", query = "SELECT b FROM BXDDetail01 b"),
    @NamedQuery(name = "BXDDetail01.findByOID", query = "SELECT b FROM BXDDetail01 b WHERE b.oid = :oid"),
    @NamedQuery(name = "BXDDetail01.findByFSN", query = "SELECT b FROM BXDDetail01 b WHERE b.formSerialNumber = :fsn")})
public class BXDDetail01 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "bmpb03f")
    private String bmpb03f;
    @Size(max = 255)
    @Column(name = "no")
    private String no;
    @Size(max = 255)
    @Column(name = "bmpb02f")
    private String bmpb02f;
    @Size(max = 255)
    @Column(name = "accname")
    private String accname;
    @Size(max = 255)
    @Column(name = "bmpb08f")
    private String bmpb08f;
    @Size(max = 255)
    @Column(name = "bmpb01c")
    private String bmpb01c;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;
    @Size(max = 255)
    @Column(name = "budgetacc")
    private String budgetacc;

    public BXDDetail01() {
    }

    public BXDDetail01(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBmpb03f() {
        return bmpb03f;
    }

    public void setBmpb03f(String bmpb03f) {
        this.bmpb03f = bmpb03f;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBmpb02f() {
        return bmpb02f;
    }

    public void setBmpb02f(String bmpb02f) {
        this.bmpb02f = bmpb02f;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getBmpb08f() {
        return bmpb08f;
    }

    public void setBmpb08f(String bmpb08f) {
        this.bmpb08f = bmpb08f;
    }

    public String getBmpb01c() {
        return bmpb01c;
    }

    public void setBmpb01c(String bmpb01c) {
        this.bmpb01c = bmpb01c;
    }

    public String getFormSerialNumber() {
        return formSerialNumber;
    }

    public void setFormSerialNumber(String formSerialNumber) {
        this.formSerialNumber = formSerialNumber;
    }

    public String getBudgetacc() {
        return budgetacc;
    }

    public void setBudgetacc(String budgetacc) {
        this.budgetacc = budgetacc;
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
        if (!(object instanceof BXDDetail01)) {
            return false;
        }
        BXDDetail01 other = (BXDDetail01) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.BXDDetail01[ oid=" + oid + " ]";
    }
    
}
