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
@Table(name = "SHB_ERP_INV140_Detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SHBERPINV140Detail.findAll", query = "SELECT s FROM SHBERPINV140Detail s"),
    @NamedQuery(name = "SHBERPINV140Detail.findBySitdsc", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.sitdsc = :sitdsc"),
    @NamedQuery(name = "SHBERPINV140Detail.findByOid", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.oid = :oid"),
    @NamedQuery(name = "SHBERPINV140Detail.findByRate2", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.rate2 = :rate2"),
    @NamedQuery(name = "SHBERPINV140Detail.findByNo", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.no = :no"),
    @NamedQuery(name = "SHBERPINV140Detail.findByEspdsc", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.espdsc = :espdsc"),
    @NamedQuery(name = "SHBERPINV140Detail.findByMorpcode", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.morpcode = :morpcode"),
    @NamedQuery(name = "SHBERPINV140Detail.findByUnmsr1", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.unmsr1 = :unmsr1"),
    @NamedQuery(name = "SHBERPINV140Detail.findByItdsc", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.itdsc = :itdsc"),
    @NamedQuery(name = "SHBERPINV140Detail.findByUnmsr2", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.unmsr2 = :unmsr2"),
    @NamedQuery(name = "SHBERPINV140Detail.findByEitdsc", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.eitdsc = :eitdsc"),
    @NamedQuery(name = "SHBERPINV140Detail.findBySpdsc", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.spdsc = :spdsc"),
    @NamedQuery(name = "SHBERPINV140Detail.findByFvco", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.fvco = :fvco"),
    @NamedQuery(name = "SHBERPINV140Detail.findBySpdsc2", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.spdsc2 = :spdsc2"),
    @NamedQuery(name = "SHBERPINV140Detail.findByItdsc2", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.itdsc2 = :itdsc2"),
    @NamedQuery(name = "SHBERPINV140Detail.findByItcls", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.itcls = :itcls"),
    @NamedQuery(name = "SHBERPINV140Detail.findByItnbr", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.itnbr = :itnbr"),
    @NamedQuery(name = "SHBERPINV140Detail.findByFSN", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.formSerialNumber = :fsn"),
    @NamedQuery(name = "SHBERPINV140Detail.findByClsdsc", query = "SELECT s FROM SHBERPINV140Detail s WHERE s.clsdsc = :clsdsc")})
public class SHBERPINV140Detail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "sitdsc")
    private String sitdsc;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "rate2")
    private String rate2;
    @Size(max = 255)
    @Column(name = "no")
    private String no;
    @Size(max = 255)
    @Column(name = "espdsc")
    private String espdsc;
    @Size(max = 255)
    @Column(name = "morpcode")
    private String morpcode;
    @Size(max = 255)
    @Column(name = "unmsr1")
    private String unmsr1;
    @Size(max = 255)
    @Column(name = "itdsc")
    private String itdsc;
    @Size(max = 255)
    @Column(name = "unmsr2")
    private String unmsr2;
    @Size(max = 255)
    @Column(name = "eitdsc")
    private String eitdsc;
    @Size(max = 255)
    @Column(name = "spdsc")
    private String spdsc;
    @Size(max = 255)
    @Column(name = "fvco")
    private String fvco;
    @Size(max = 255)
    @Column(name = "spdsc2")
    private String spdsc2;
    @Size(max = 255)
    @Column(name = "itdsc2")
    private String itdsc2;
    @Size(max = 255)
    @Column(name = "itcls")
    private String itcls;
    @Size(max = 255)
    @Column(name = "itnbr")
    private String itnbr;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;
    @Size(max = 255)
    @Column(name = "clsdsc")
    private String clsdsc;

    public SHBERPINV140Detail() {
    }

    public SHBERPINV140Detail(String oid) {
        this.oid = oid;
    }

    public String getSitdsc() {
        return sitdsc;
    }

    public void setSitdsc(String sitdsc) {
        this.sitdsc = sitdsc;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEspdsc() {
        return espdsc;
    }

    public void setEspdsc(String espdsc) {
        this.espdsc = espdsc;
    }

    public String getMorpcode() {
        return morpcode;
    }

    public void setMorpcode(String morpcode) {
        this.morpcode = morpcode;
    }

    public String getUnmsr1() {
        return unmsr1;
    }

    public void setUnmsr1(String unmsr1) {
        this.unmsr1 = unmsr1;
    }

    public String getItdsc() {
        return itdsc;
    }

    public void setItdsc(String itdsc) {
        this.itdsc = itdsc;
    }

    public String getUnmsr2() {
        return unmsr2;
    }

    public void setUnmsr2(String unmsr2) {
        this.unmsr2 = unmsr2;
    }

    public String getEitdsc() {
        return eitdsc;
    }

    public void setEitdsc(String eitdsc) {
        this.eitdsc = eitdsc;
    }

    public String getSpdsc() {
        return spdsc;
    }

    public void setSpdsc(String spdsc) {
        this.spdsc = spdsc;
    }

    public String getFvco() {
        return fvco;
    }

    public void setFvco(String fvco) {
        this.fvco = fvco;
    }

    public String getSpdsc2() {
        return spdsc2;
    }

    public void setSpdsc2(String spdsc2) {
        this.spdsc2 = spdsc2;
    }

    public String getItdsc2() {
        return itdsc2;
    }

    public void setItdsc2(String itdsc2) {
        this.itdsc2 = itdsc2;
    }

    public String getItcls() {
        return itcls;
    }

    public void setItcls(String itcls) {
        this.itcls = itcls;
    }

    public String getItnbr() {
        return itnbr;
    }

    public void setItnbr(String itnbr) {
        this.itnbr = itnbr;
    }

    public String getFormSerialNumber() {
        return formSerialNumber;
    }

    public void setFormSerialNumber(String formSerialNumber) {
        this.formSerialNumber = formSerialNumber;
    }

    public String getClsdsc() {
        return clsdsc;
    }

    public void setClsdsc(String clsdsc) {
        this.clsdsc = clsdsc;
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
        if (!(object instanceof SHBERPINV140Detail)) {
            return false;
        }
        SHBERPINV140Detail other = (SHBERPINV140Detail) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.SHBERPINV140Detail[ oid=" + oid + " ]";
    }
    
}
