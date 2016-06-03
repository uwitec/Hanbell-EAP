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
 * @author C0160
 */
@Entity
@Table(name = "BXD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bxd.findAll", query = "SELECT b FROM Bxd b"),
    @NamedQuery(name = "Bxd.findByPSN", query = "SELECT b FROM Bxd b WHERE b.processSerialNumber = :psn"),
    @NamedQuery(name = "Bxd.findByFSN", query = "SELECT b FROM Bxd b WHERE b.formSerialNumber = :fsn")})
public class Bxd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "bmpc02c")
    private String bmpc02c;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bmpb02f")
    private Double bmpb02f;
    @Size(max = 255)
    @Column(name = "bmpa01c")
    private String bmpa01c;
    @Size(max = 255)
    @Column(name = "bmpb06c")
    private String bmpb06c;
    @Column(name = "bmpb16f")
    private Double bmpb16f;
    @Size(max = 255)
    @Column(name = "bmpc22c")
    private String bmpc22c;
    @Column(name = "bmpc06f")
    private Double bmpc06f;
    @Size(max = 255)
    @Column(name = "budgetacc")
    private String budgetacc;
    @Size(max = 255)
    @Column(name = "bmpc15c")
    private String bmpc15c;
    @Size(max = 255)
    @Column(name = "bmpa11c")
    private String bmpa11c;
    @Size(max = 255)
    @Column(name = "bmpb13c")
    private String bmpb13c;
    @Size(max = 255)
    @Column(name = "bmpb07c")
    private String bmpb07c;
    @Column(name = "bmpa06f")
    private Double bmpa06f;
    @Column(name = "bmpa15f")
    private Double bmpa15f;
    @Column(name = "bmpc11c")
    private Double bmpc11c;
    @Size(max = 255)
    @Column(name = "bmpc01c")
    private String bmpc01c;
    @Column(name = "bmpb03f")
    private Double bmpb03f;
    @Size(max = 255)
    @Column(name = "bmpa02c")
    private String bmpa02c;
    @Size(max = 255)
    @Column(name = "bmpc21c")
    private String bmpc21c;
    @Size(max = 255)
    @Column(name = "bmpc17c")
    private String bmpc17c;
    @Column(name = "bmpc05f")
    private Double bmpc05f;
    @Size(max = 255)
    @Column(name = "bmpb17c")
    private String bmpb17c;
    @Size(max = 255)
    @Column(name = "bmpc16c")
    private String bmpc16c;
    @Column(name = "bmpa09f")
    private Double bmpa09f;
    @Size(max = 255)
    @Column(name = "bmpb12c")
    private String bmpb12c;
    @Column(name = "bmpa16f")
    private Double bmpa16f;
    @Size(max = 255)
    @Column(name = "bmpa05c")
    private String bmpa05c;
    @Column(name = "bmpc12c")
    private Double bmpc12c;
    @Size(max = 255)
    @Column(name = "bmpa12f")
    private String bmpa12f;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Column(name = "bmpa03c")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bmpa03c;
    @Column(name = "bmpc10c")
    private Double bmpc10c;
    @Size(max = 255)
    @Column(name = "accname")
    private String accname;
    @Column(name = "bmpc00c")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bmpc00c;
    @Size(max = 255)
    @Column(name = "bmpb04c")
    private String bmpb04c;
    @Size(max = 255)
    @Column(name = "bmpc04c")
    private String bmpc04c;
    @Size(max = 255)
    @Column(name = "bmpc20c")
    private String bmpc20c;
    @Size(max = 255)
    @Column(name = "bmpc18c")
    private String bmpc18c;
    @Column(name = "bmpb08f")
    private Double bmpb08f;
    @Column(name = "bmpc09f")
    private Double bmpc09f;
    @Column(name = "bmpc08f")
    private Double bmpc08f;
    @Size(max = 255)
    @Column(name = "bmpb09c")
    private String bmpb09c;
    @Size(max = 255)
    @Column(name = "bmpa13f")
    private String bmpa13f;
    @Size(max = 255)
    @Column(name = "bmpb11c")
    private String bmpb11c;
    @Column(name = "bmpa08f")
    private Double bmpa08f;
    @Size(max = 255)
    @Column(name = "processSerialNumber")
    private String processSerialNumber;
    @Size(max = 255)
    @Column(name = "bmpb01c")
    private String bmpb01c;
    @Size(max = 255)
    @Column(name = "bmpc13c")
    private String bmpc13c;
    @Size(max = 255)
    @Column(name = "Textbox53")
    private String textbox53;
    @Size(max = 255)
    @Column(name = "bmpa04c")
    private String bmpa04c;
    @Size(max = 255)
    @Column(name = "bmpb05c")
    private String bmpb05c;
    @Size(max = 255)
    @Column(name = "bmpa00c")
    private String bmpa00c;
    @Size(max = 255)
    @Column(name = "bmpc19c")
    private String bmpc19c;
    @Size(max = 255)
    @Column(name = "bmpc03c")
    private String bmpc03c;
    @Column(name = "bmpb15f")
    private Double bmpb15f;
    @Column(name = "bmpb10c")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bmpb10c;
    @Column(name = "bmpc07f")
    private Double bmpc07f;
    @Column(name = "bmpa07f")
    private Double bmpa07f;
    @Size(max = 255)
    @Column(name = "bmpa10c")
    private String bmpa10c;
    @Size(max = 255)
    @Column(name = "bmpc14c")
    private String bmpc14c;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;
    @Size(max = 255)
    @Column(name = "bmpa14c")
    private String bmpa14c;

    public Bxd() {
    }

    public Bxd(String oid) {
        this.oid = oid;
    }

    public String getBmpc02c() {
        return bmpc02c;
    }

    public void setBmpc02c(String bmpc02c) {
        this.bmpc02c = bmpc02c;
    }

    public Double getBmpb02f() {
        return bmpb02f;
    }

    public void setBmpb02f(Double bmpb02f) {
        this.bmpb02f = bmpb02f;
    }

    public String getBmpa01c() {
        return bmpa01c;
    }

    public void setBmpa01c(String bmpa01c) {
        this.bmpa01c = bmpa01c;
    }

    public String getBmpb06c() {
        return bmpb06c;
    }

    public void setBmpb06c(String bmpb06c) {
        this.bmpb06c = bmpb06c;
    }

    public Double getBmpb16f() {
        return bmpb16f;
    }

    public void setBmpb16f(Double bmpb16f) {
        this.bmpb16f = bmpb16f;
    }

    public String getBmpc22c() {
        return bmpc22c;
    }

    public void setBmpc22c(String bmpc22c) {
        this.bmpc22c = bmpc22c;
    }

    public Double getBmpc06f() {
        return bmpc06f;
    }

    public void setBmpc06f(Double bmpc06f) {
        this.bmpc06f = bmpc06f;
    }

    public String getBudgetacc() {
        return budgetacc;
    }

    public void setBudgetacc(String budgetacc) {
        this.budgetacc = budgetacc;
    }

    public String getBmpc15c() {
        return bmpc15c;
    }

    public void setBmpc15c(String bmpc15c) {
        this.bmpc15c = bmpc15c;
    }

    public String getBmpa11c() {
        return bmpa11c;
    }

    public void setBmpa11c(String bmpa11c) {
        this.bmpa11c = bmpa11c;
    }

    public String getBmpb13c() {
        return bmpb13c;
    }

    public void setBmpb13c(String bmpb13c) {
        this.bmpb13c = bmpb13c;
    }

    public String getBmpb07c() {
        return bmpb07c;
    }

    public void setBmpb07c(String bmpb07c) {
        this.bmpb07c = bmpb07c;
    }

    public Double getBmpa06f() {
        return bmpa06f;
    }

    public void setBmpa06f(Double bmpa06f) {
        this.bmpa06f = bmpa06f;
    }

    public Double getBmpa15f() {
        return bmpa15f;
    }

    public void setBmpa15f(Double bmpa15f) {
        this.bmpa15f = bmpa15f;
    }

    public Double getBmpc11c() {
        return bmpc11c;
    }

    public void setBmpc11c(Double bmpc11c) {
        this.bmpc11c = bmpc11c;
    }

    public String getBmpc01c() {
        return bmpc01c;
    }

    public void setBmpc01c(String bmpc01c) {
        this.bmpc01c = bmpc01c;
    }

    public Double getBmpb03f() {
        return bmpb03f;
    }

    public void setBmpb03f(Double bmpb03f) {
        this.bmpb03f = bmpb03f;
    }

    public String getBmpa02c() {
        return bmpa02c;
    }

    public void setBmpa02c(String bmpa02c) {
        this.bmpa02c = bmpa02c;
    }

    public String getBmpc21c() {
        return bmpc21c;
    }

    public void setBmpc21c(String bmpc21c) {
        this.bmpc21c = bmpc21c;
    }

    public String getBmpc17c() {
        return bmpc17c;
    }

    public void setBmpc17c(String bmpc17c) {
        this.bmpc17c = bmpc17c;
    }

    public Double getBmpc05f() {
        return bmpc05f;
    }

    public void setBmpc05f(Double bmpc05f) {
        this.bmpc05f = bmpc05f;
    }

    public String getBmpb17c() {
        return bmpb17c;
    }

    public void setBmpb17c(String bmpb17c) {
        this.bmpb17c = bmpb17c;
    }

    public String getBmpc16c() {
        return bmpc16c;
    }

    public void setBmpc16c(String bmpc16c) {
        this.bmpc16c = bmpc16c;
    }

    public Double getBmpa09f() {
        return bmpa09f;
    }

    public void setBmpa09f(Double bmpa09f) {
        this.bmpa09f = bmpa09f;
    }

    public String getBmpb12c() {
        return bmpb12c;
    }

    public void setBmpb12c(String bmpb12c) {
        this.bmpb12c = bmpb12c;
    }

    public Double getBmpa16f() {
        return bmpa16f;
    }

    public void setBmpa16f(Double bmpa16f) {
        this.bmpa16f = bmpa16f;
    }

    public String getBmpa05c() {
        return bmpa05c;
    }

    public void setBmpa05c(String bmpa05c) {
        this.bmpa05c = bmpa05c;
    }

    public Double getBmpc12c() {
        return bmpc12c;
    }

    public void setBmpc12c(Double bmpc12c) {
        this.bmpc12c = bmpc12c;
    }

    public String getBmpa12f() {
        return bmpa12f;
    }

    public void setBmpa12f(String bmpa12f) {
        this.bmpa12f = bmpa12f;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getBmpa03c() {
        return bmpa03c;
    }

    public void setBmpa03c(Date bmpa03c) {
        this.bmpa03c = bmpa03c;
    }

    public Double getBmpc10c() {
        return bmpc10c;
    }

    public void setBmpc10c(Double bmpc10c) {
        this.bmpc10c = bmpc10c;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public Date getBmpc00c() {
        return bmpc00c;
    }

    public void setBmpc00c(Date bmpc00c) {
        this.bmpc00c = bmpc00c;
    }

    public String getBmpb04c() {
        return bmpb04c;
    }

    public void setBmpb04c(String bmpb04c) {
        this.bmpb04c = bmpb04c;
    }

    public String getBmpc04c() {
        return bmpc04c;
    }

    public void setBmpc04c(String bmpc04c) {
        this.bmpc04c = bmpc04c;
    }

    public String getBmpc20c() {
        return bmpc20c;
    }

    public void setBmpc20c(String bmpc20c) {
        this.bmpc20c = bmpc20c;
    }

    public String getBmpc18c() {
        return bmpc18c;
    }

    public void setBmpc18c(String bmpc18c) {
        this.bmpc18c = bmpc18c;
    }

    public Double getBmpb08f() {
        return bmpb08f;
    }

    public void setBmpb08f(Double bmpb08f) {
        this.bmpb08f = bmpb08f;
    }

    public Double getBmpc09f() {
        return bmpc09f;
    }

    public void setBmpc09f(Double bmpc09f) {
        this.bmpc09f = bmpc09f;
    }

    public Double getBmpc08f() {
        return bmpc08f;
    }

    public void setBmpc08f(Double bmpc08f) {
        this.bmpc08f = bmpc08f;
    }

    public String getBmpb09c() {
        return bmpb09c;
    }

    public void setBmpb09c(String bmpb09c) {
        this.bmpb09c = bmpb09c;
    }

    public String getBmpa13f() {
        return bmpa13f;
    }

    public void setBmpa13f(String bmpa13f) {
        this.bmpa13f = bmpa13f;
    }

    public String getBmpb11c() {
        return bmpb11c;
    }

    public void setBmpb11c(String bmpb11c) {
        this.bmpb11c = bmpb11c;
    }

    public Double getBmpa08f() {
        return bmpa08f;
    }

    public void setBmpa08f(Double bmpa08f) {
        this.bmpa08f = bmpa08f;
    }

    public String getProcessSerialNumber() {
        return processSerialNumber;
    }

    public void setProcessSerialNumber(String processSerialNumber) {
        this.processSerialNumber = processSerialNumber;
    }

    public String getBmpb01c() {
        return bmpb01c;
    }

    public void setBmpb01c(String bmpb01c) {
        this.bmpb01c = bmpb01c;
    }

    public String getBmpc13c() {
        return bmpc13c;
    }

    public void setBmpc13c(String bmpc13c) {
        this.bmpc13c = bmpc13c;
    }

    public String getTextbox53() {
        return textbox53;
    }

    public void setTextbox53(String textbox53) {
        this.textbox53 = textbox53;
    }

    public String getBmpa04c() {
        return bmpa04c;
    }

    public void setBmpa04c(String bmpa04c) {
        this.bmpa04c = bmpa04c;
    }

    public String getBmpb05c() {
        return bmpb05c;
    }

    public void setBmpb05c(String bmpb05c) {
        this.bmpb05c = bmpb05c;
    }

    public String getBmpa00c() {
        return bmpa00c;
    }

    public void setBmpa00c(String bmpa00c) {
        this.bmpa00c = bmpa00c;
    }

    public String getBmpc19c() {
        return bmpc19c;
    }

    public void setBmpc19c(String bmpc19c) {
        this.bmpc19c = bmpc19c;
    }

    public String getBmpc03c() {
        return bmpc03c;
    }

    public void setBmpc03c(String bmpc03c) {
        this.bmpc03c = bmpc03c;
    }

    public Double getBmpb15f() {
        return bmpb15f;
    }

    public void setBmpb15f(Double bmpb15f) {
        this.bmpb15f = bmpb15f;
    }

    public Date getBmpb10c() {
        return bmpb10c;
    }

    public void setBmpb10c(Date bmpb10c) {
        this.bmpb10c = bmpb10c;
    }

    public Double getBmpc07f() {
        return bmpc07f;
    }

    public void setBmpc07f(Double bmpc07f) {
        this.bmpc07f = bmpc07f;
    }

    public Double getBmpa07f() {
        return bmpa07f;
    }

    public void setBmpa07f(Double bmpa07f) {
        this.bmpa07f = bmpa07f;
    }

    public String getBmpa10c() {
        return bmpa10c;
    }

    public void setBmpa10c(String bmpa10c) {
        this.bmpa10c = bmpa10c;
    }

    public String getBmpc14c() {
        return bmpc14c;
    }

    public void setBmpc14c(String bmpc14c) {
        this.bmpc14c = bmpc14c;
    }

    public String getFormSerialNumber() {
        return formSerialNumber;
    }

    public void setFormSerialNumber(String formSerialNumber) {
        this.formSerialNumber = formSerialNumber;
    }

    public String getBmpa14c() {
        return bmpa14c;
    }

    public void setBmpa14c(String bmpa14c) {
        this.bmpa14c = bmpa14c;
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
        if (!(object instanceof Bxd)) {
            return false;
        }
        Bxd other = (Bxd) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.Bxd[ oid=" + oid + " ]";
    }
    
}
