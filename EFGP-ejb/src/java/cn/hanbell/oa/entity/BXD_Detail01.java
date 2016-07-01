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
    @NamedQuery(name = "BXD_Detail01.findAll", query = "SELECT b FROM BXD_Detail01 b"),
    @NamedQuery(name = "BXD_Detail01.findByOID", query = "SELECT b FROM BXD_Detail01 b WHERE b.oid = :oid"),
    @NamedQuery(name = "BXD_Detail01.findByFSN", query = "SELECT b FROM BXD_Detail01 b WHERE b.formSerialNumber = :fsn")})
public class BXD_Detail01 implements Serializable {

    @Size(max = 255)
    @Column(name = "budgetDept_txt")
    private String budgetDepttxt;
    @Size(max = 255)
    @Column(name = "bmpb04c")
    private String bmpb04c;
    @Size(max = 255)
    @Column(name = "bmpb06c")
    private String bmpb06c;
    @Size(max = 255)
    @Column(name = "bmpb16f")
    private String bmpb16f;
    @Size(max = 255)
    @Column(name = "budgetAcc")
    private String budgetAcc;
    @Size(max = 255)
    @Column(name = "bmpb15f")
    private String bmpb15f;
    @Size(max = 255)
    @Column(name = "bmpb17c")
    private String bmpb17c;
    @Size(max = 255)
    @Column(name = "bmpb09c")
    private String bmpb09c;
    @Size(max = 255)
    @Column(name = "bmpb11c")
    private String bmpb11c;
    @Size(max = 255)
    @Column(name = "budgetDept_lbl")
    private String budgetDeptlbl;
    @Size(max = 255)
    @Column(name = "bmpb07c")
    private String bmpb07c;
    @Size(max = 255)
    @Column(name = "bmpb12c")
    private String bmpb12c;
    @Size(max = 255)
    @Column(name = "bmpb10c_txt")
    private String bmpb10cTxt;

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
    @Column(name = "bmpb08f")
    private String bmpb08f;
    @Size(max = 255)
    @Column(name = "bmpb01c")
    private String bmpb01c;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;

    public BXD_Detail01() {
    }

    public BXD_Detail01(String oid) {
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BXD_Detail01)) {
            return false;
        }
        BXD_Detail01 other = (BXD_Detail01) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.BXD_Detail01[ oid=" + oid + " ]";
    }

    public String getBudgetDepttxt() {
        return budgetDepttxt;
    }

    public void setBudgetDepttxt(String budgetDepttxt) {
        this.budgetDepttxt = budgetDepttxt;
    }

    public String getBmpb04c() {
        return bmpb04c;
    }

    public void setBmpb04c(String bmpb04c) {
        this.bmpb04c = bmpb04c;
    }

    public String getBmpb06c() {
        return bmpb06c;
    }

    public void setBmpb06c(String bmpb06c) {
        this.bmpb06c = bmpb06c;
    }

    public String getBmpb16f() {
        return bmpb16f;
    }

    public void setBmpb16f(String bmpb16f) {
        this.bmpb16f = bmpb16f;
    }

    public String getBudgetAcc() {
        return budgetAcc;
    }

    public void setBudgetAcc(String budgetAcc) {
        this.budgetAcc = budgetAcc;
    }

    public String getBmpb15f() {
        return bmpb15f;
    }

    public void setBmpb15f(String bmpb15f) {
        this.bmpb15f = bmpb15f;
    }

    public String getBmpb17c() {
        return bmpb17c;
    }

    public void setBmpb17c(String bmpb17c) {
        this.bmpb17c = bmpb17c;
    }

    public String getBmpb09c() {
        return bmpb09c;
    }

    public void setBmpb09c(String bmpb09c) {
        this.bmpb09c = bmpb09c;
    }

    public String getBmpb11c() {
        return bmpb11c;
    }

    public void setBmpb11c(String bmpb11c) {
        this.bmpb11c = bmpb11c;
    }

    public String getBudgetDeptlbl() {
        return budgetDeptlbl;
    }

    public void setBudgetDeptlbl(String budgetDeptlbl) {
        this.budgetDeptlbl = budgetDeptlbl;
    }

    public String getBmpb07c() {
        return bmpb07c;
    }

    public void setBmpb07c(String bmpb07c) {
        this.bmpb07c = bmpb07c;
    }

    public String getBmpb12c() {
        return bmpb12c;
    }

    public void setBmpb12c(String bmpb12c) {
        this.bmpb12c = bmpb12c;
    }

    public String getBmpb10cTxt() {
        return bmpb10cTxt;
    }

    public void setBmpb10cTxt(String bmpb10cTxt) {
        this.bmpb10cTxt = bmpb10cTxt;
    }
    
}
