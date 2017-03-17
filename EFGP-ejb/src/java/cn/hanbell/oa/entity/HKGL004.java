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
@Table(name = "HK_GL004")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HKGL004.findAll", query = "SELECT h FROM HKGL004 h"),
    @NamedQuery(name = "HKGL004.findByOID", query = "SELECT h FROM HKGL004 h WHERE h.oid = :oid"),
    @NamedQuery(name = "HKGL004.findByPSN", query = "SELECT h FROM HKGL004 h WHERE h.processSerialNumber = :psn"),
    @NamedQuery(name = "HKGL004.findByFSN", query = "SELECT h FROM HKGL004 h WHERE h.formSerialNumber = :fsn")})
public class HKGL004 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "leauser")
    private String leauser;
    @Size(max = 255)
    @Column(name = "reason")
    private String reason;
    @Size(max = 255)
    @Column(name = "userTitle")
    private String userTitle;
    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
    @Column(name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;
    @Size(max = 255)
    @Column(name = "SerialNumber")
    private String serialNumber;
    @Size(max = 255)
    @Column(name = "hdn_emply")
    private String hdnEmply;
    @Size(max = 255)
    @Column(name = "time2")
    private String time2;
    @Size(max = 255)
    @Column(name = "time1")
    private String time1;
    @Size(max = 255)
    @Column(name = "emply")
    private String emply;
    @Column(name = "dayst")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayst;
    @Size(max = 255)
    @Column(name = "leano")
    private String leano;
    @Size(max = 255)
    @Column(name = "dept")
    private String dept;
    @Size(max = 255)
    @Column(name = "processSerialNumber")
    private String processSerialNumber;
    @Size(max = 255)
    @Column(name = "hdn_leatp")
    private String hdnLeatp;
    @Size(max = 255)
    @Column(name = "leatp")
    private String leatp;
    @Size(max = 255)
    @Column(name = "hdn_leano")
    private String hdnLeano;
    @Size(max = 255)
    @Column(name = "hdn_dept")
    private String hdnDept;
    @Size(max = 255)
    @Column(name = "leana")
    private String leana;
    @Column(name = "dayed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dayed;
    @Size(max = 255)
    @Column(name = "hdn_leauser")
    private String hdnLeauser;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "leaday2")
    private Double leaday2;
    @Column(name = "leaday3")
    private Double leaday3;
    @Size(max = 255)
    @Column(name = "hdn_leana")
    private String hdnLeana;
    @Column(name = "leaday1")
    private Double leaday1;
    @Size(max = 255)
    @Column(name = "day")
    private String day;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;
    @Size(max = 255)
    @Column(name = "hdn_facno")
    private String hdnFacno;

    public HKGL004() {
    }

    public HKGL004(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getLeauser() {
        return leauser;
    }

    public void setLeauser(String leauser) {
        this.leauser = leauser;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getFacno() {
        return facno;
    }

    public void setFacno(String facno) {
        this.facno = facno;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHdnEmply() {
        return hdnEmply;
    }

    public void setHdnEmply(String hdnEmply) {
        this.hdnEmply = hdnEmply;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getEmply() {
        return emply;
    }

    public void setEmply(String emply) {
        this.emply = emply;
    }

    public Date getDayst() {
        return dayst;
    }

    public void setDayst(Date dayst) {
        this.dayst = dayst;
    }

    public String getLeano() {
        return leano;
    }

    public void setLeano(String leano) {
        this.leano = leano;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getProcessSerialNumber() {
        return processSerialNumber;
    }

    public void setProcessSerialNumber(String processSerialNumber) {
        this.processSerialNumber = processSerialNumber;
    }

    public String getHdnLeatp() {
        return hdnLeatp;
    }

    public void setHdnLeatp(String hdnLeatp) {
        this.hdnLeatp = hdnLeatp;
    }

    public String getLeatp() {
        return leatp;
    }

    public void setLeatp(String leatp) {
        this.leatp = leatp;
    }

    public String getHdnLeano() {
        return hdnLeano;
    }

    public void setHdnLeano(String hdnLeano) {
        this.hdnLeano = hdnLeano;
    }

    public String getHdnDept() {
        return hdnDept;
    }

    public void setHdnDept(String hdnDept) {
        this.hdnDept = hdnDept;
    }

    public String getLeana() {
        return leana;
    }

    public void setLeana(String leana) {
        this.leana = leana;
    }

    public Date getDayed() {
        return dayed;
    }

    public void setDayed(Date dayed) {
        this.dayed = dayed;
    }

    public String getHdnLeauser() {
        return hdnLeauser;
    }

    public void setHdnLeauser(String hdnLeauser) {
        this.hdnLeauser = hdnLeauser;
    }

    public Double getLeaday2() {
        return leaday2;
    }

    public void setLeaday2(Double leaday2) {
        this.leaday2 = leaday2;
    }

    public Double getLeaday3() {
        return leaday3;
    }

    public void setLeaday3(Double leaday3) {
        this.leaday3 = leaday3;
    }

    public String getHdnLeana() {
        return hdnLeana;
    }

    public void setHdnLeana(String hdnLeana) {
        this.hdnLeana = hdnLeana;
    }

    public Double getLeaday1() {
        return leaday1;
    }

    public void setLeaday1(Double leaday1) {
        this.leaday1 = leaday1;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFormSerialNumber() {
        return formSerialNumber;
    }

    public void setFormSerialNumber(String formSerialNumber) {
        this.formSerialNumber = formSerialNumber;
    }

    public String getHdnFacno() {
        return hdnFacno;
    }

    public void setHdnFacno(String hdnFacno) {
        this.hdnFacno = hdnFacno;
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
        if (!(object instanceof HKGL004)) {
            return false;
        }
        HKGL004 other = (HKGL004) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.HKGL004[ oid=" + oid + " ]";
    }
    
}