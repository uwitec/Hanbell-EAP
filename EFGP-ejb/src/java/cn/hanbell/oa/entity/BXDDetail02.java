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
@Table(name = "BXD_Detail02")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BXDDetail02.findAll", query = "SELECT b FROM BXDDetail02 b"),
    @NamedQuery(name = "BXDDetail02.findByOid", query = "SELECT b FROM BXDDetail02 b WHERE b.oid = :oid"),
    @NamedQuery(name = "BXDDetail02.findByBmpc02c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc02c = :bmpc02c"),
    @NamedQuery(name = "BXDDetail02.findByNo", query = "SELECT b FROM BXDDetail02 b WHERE b.no = :no"),
    @NamedQuery(name = "BXDDetail02.findByBmpc10c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc10c = :bmpc10c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc00c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc00c = :bmpc00c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc04c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc04c = :bmpc04c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc20c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc20c = :bmpc20c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc18c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc18c = :bmpc18c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc22c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc22c = :bmpc22c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc06f", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc06f = :bmpc06f"),
    @NamedQuery(name = "BXDDetail02.findByBmpc09f", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc09f = :bmpc09f"),
    @NamedQuery(name = "BXDDetail02.findByBmpc08f", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc08f = :bmpc08f"),
    @NamedQuery(name = "BXDDetail02.findByBmpc15c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc15c = :bmpc15c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc11c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc11c = :bmpc11c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc13c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc13c = :bmpc13c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc01c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc01c = :bmpc01c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc19c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc19c = :bmpc19c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc03c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc03c = :bmpc03c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc21c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc21c = :bmpc21c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc17c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc17c = :bmpc17c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc05f", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc05f = :bmpc05f"),
    @NamedQuery(name = "BXDDetail02.findByBmpc16c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc16c = :bmpc16c"),
    @NamedQuery(name = "BXDDetail02.findByBmpc07f", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc07f = :bmpc07f"),
    @NamedQuery(name = "BXDDetail02.findByBmpc14c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc14c = :bmpc14c"),
    @NamedQuery(name = "BXDDetail02.findByFormSerialNumber", query = "SELECT b FROM BXDDetail02 b WHERE b.formSerialNumber = :formSerialNumber"),
    @NamedQuery(name = "BXDDetail02.findByBmpc12c", query = "SELECT b FROM BXDDetail02 b WHERE b.bmpc12c = :bmpc12c")})
public class BXDDetail02 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "bmpc02c")
    private String bmpc02c;
    @Size(max = 255)
    @Column(name = "no")
    private String no;
    @Size(max = 255)
    @Column(name = "bmpc10c")
    private String bmpc10c;
    @Size(max = 255)
    @Column(name = "bmpc00c")
    private String bmpc00c;
    @Size(max = 255)
    @Column(name = "bmpc04c")
    private String bmpc04c;
    @Size(max = 255)
    @Column(name = "bmpc20c")
    private String bmpc20c;
    @Size(max = 255)
    @Column(name = "bmpc18c")
    private String bmpc18c;
    @Size(max = 255)
    @Column(name = "bmpc22c")
    private String bmpc22c;
    @Size(max = 255)
    @Column(name = "bmpc06f")
    private String bmpc06f;
    @Size(max = 255)
    @Column(name = "bmpc09f")
    private String bmpc09f;
    @Size(max = 255)
    @Column(name = "bmpc08f")
    private String bmpc08f;
    @Size(max = 255)
    @Column(name = "bmpc15c")
    private String bmpc15c;
    @Size(max = 255)
    @Column(name = "bmpc11c")
    private String bmpc11c;
    @Size(max = 255)
    @Column(name = "bmpc13c")
    private String bmpc13c;
    @Size(max = 255)
    @Column(name = "bmpc01c")
    private String bmpc01c;
    @Size(max = 255)
    @Column(name = "bmpc19c")
    private String bmpc19c;
    @Size(max = 255)
    @Column(name = "bmpc03c")
    private String bmpc03c;
    @Size(max = 255)
    @Column(name = "bmpc21c")
    private String bmpc21c;
    @Size(max = 255)
    @Column(name = "bmpc17c")
    private String bmpc17c;
    @Size(max = 255)
    @Column(name = "bmpc05f")
    private String bmpc05f;
    @Size(max = 255)
    @Column(name = "bmpc16c")
    private String bmpc16c;
    @Size(max = 255)
    @Column(name = "bmpc07f")
    private String bmpc07f;
    @Size(max = 255)
    @Column(name = "bmpc14c")
    private String bmpc14c;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;
    @Size(max = 255)
    @Column(name = "bmpc12c")
    private String bmpc12c;

    public BXDDetail02() {
    }

    public BXDDetail02(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBmpc02c() {
        return bmpc02c;
    }

    public void setBmpc02c(String bmpc02c) {
        this.bmpc02c = bmpc02c;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getBmpc10c() {
        return bmpc10c;
    }

    public void setBmpc10c(String bmpc10c) {
        this.bmpc10c = bmpc10c;
    }

    public String getBmpc00c() {
        return bmpc00c;
    }

    public void setBmpc00c(String bmpc00c) {
        this.bmpc00c = bmpc00c;
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

    public String getBmpc22c() {
        return bmpc22c;
    }

    public void setBmpc22c(String bmpc22c) {
        this.bmpc22c = bmpc22c;
    }

    public String getBmpc06f() {
        return bmpc06f;
    }

    public void setBmpc06f(String bmpc06f) {
        this.bmpc06f = bmpc06f;
    }

    public String getBmpc09f() {
        return bmpc09f;
    }

    public void setBmpc09f(String bmpc09f) {
        this.bmpc09f = bmpc09f;
    }

    public String getBmpc08f() {
        return bmpc08f;
    }

    public void setBmpc08f(String bmpc08f) {
        this.bmpc08f = bmpc08f;
    }

    public String getBmpc15c() {
        return bmpc15c;
    }

    public void setBmpc15c(String bmpc15c) {
        this.bmpc15c = bmpc15c;
    }

    public String getBmpc11c() {
        return bmpc11c;
    }

    public void setBmpc11c(String bmpc11c) {
        this.bmpc11c = bmpc11c;
    }

    public String getBmpc13c() {
        return bmpc13c;
    }

    public void setBmpc13c(String bmpc13c) {
        this.bmpc13c = bmpc13c;
    }

    public String getBmpc01c() {
        return bmpc01c;
    }

    public void setBmpc01c(String bmpc01c) {
        this.bmpc01c = bmpc01c;
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

    public String getBmpc05f() {
        return bmpc05f;
    }

    public void setBmpc05f(String bmpc05f) {
        this.bmpc05f = bmpc05f;
    }

    public String getBmpc16c() {
        return bmpc16c;
    }

    public void setBmpc16c(String bmpc16c) {
        this.bmpc16c = bmpc16c;
    }

    public String getBmpc07f() {
        return bmpc07f;
    }

    public void setBmpc07f(String bmpc07f) {
        this.bmpc07f = bmpc07f;
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

    public String getBmpc12c() {
        return bmpc12c;
    }

    public void setBmpc12c(String bmpc12c) {
        this.bmpc12c = bmpc12c;
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
        if (!(object instanceof BXDDetail02)) {
            return false;
        }
        BXDDetail02 other = (BXDDetail02) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.BXDDetail02[ oid=" + oid + " ]";
    }
    
}
