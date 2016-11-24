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
@Table(name = "HK_GL034")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HKGL034.findAll", query = "SELECT h FROM HKGL034 h"),
    @NamedQuery(name = "HKGL034.findByOID", query = "SELECT h FROM HKGL034 h WHERE h.oid = :oid"),
    @NamedQuery(name = "HKGL034.findByType", query = "SELECT h FROM HKGL034 h WHERE h.type = :type"),
    @NamedQuery(name = "HKGL034.findByFacno", query = "SELECT h FROM HKGL034 h WHERE h.facno = :facno"),
    @NamedQuery(name = "HKGL034.findByDept", query = "SELECT h FROM HKGL034 h WHERE h.dept = :dept"),
    @NamedQuery(name = "HKGL034.findByPSN", query = "SELECT h FROM HKGL034 h WHERE h.processSerialNumber = :psn"),
    @NamedQuery(name = "HKGL034.findByFSN", query = "SELECT h FROM HKGL034 h WHERE h.formSerialNumber = :fsn")})
public class HKGL034 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "endtime")
    private String endtime;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "jbnr")
    private String jbnr;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
    @Size(max = 255)
    @Column(name = "SerialNumber")
    private String serialNumber;
    @Size(max = 255)
    @Column(name = "hdn_dinner")
    private String hdnDinner;
    @Size(max = 255)
    @Column(name = "hdn_emply")
    private String hdnEmply;
    @Size(max = 255)
    @Column(name = "emply")
    private String emply;
    @Size(max = 255)
    @Column(name = "emplname")
    private String emplname;
    @Size(max = 255)
    @Column(name = "hdn_type")
    private String hdnType;
    @Size(max = 255)
    @Column(name = "dept")
    private String dept;
    @Size(max = 255)
    @Column(name = "lunch")
    private String lunch;
    @Size(max = 255)
    @Column(name = "processSerialNumber")
    private String processSerialNumber;
    @Size(max = 255)
    @Column(name = "empl")
    private String empl;
    @Size(max = 255)
    @Column(name = "starttime")
    private String starttime;
    @Column(name = "jbtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jbtime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hour")
    private Double hour;
    @Size(max = 255)
    @Column(name = "hdn_dept")
    private String hdnDept;
    @Size(max = 255)
    @Column(name = "dinner")
    private String dinner;
    @Size(max = 255)
    @Column(name = "hdn_lunch")
    private String hdnLunch;
    @Size(max = 255)
    @Column(name = "hdn_facno")
    private String hdnFacno;
    @Size(max = 255)
    @Column(name = "jbdept")
    private String jbdept;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;

    public HKGL034() {
    }

    public HKGL034(String oid) {
        this.oid = oid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getJbnr() {
        return jbnr;
    }

    public void setJbnr(String jbnr) {
        this.jbnr = jbnr;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFacno() {
        return facno;
    }

    public void setFacno(String facno) {
        this.facno = facno;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHdnDinner() {
        return hdnDinner;
    }

    public void setHdnDinner(String hdnDinner) {
        this.hdnDinner = hdnDinner;
    }

    public String getHdnEmply() {
        return hdnEmply;
    }

    public void setHdnEmply(String hdnEmply) {
        this.hdnEmply = hdnEmply;
    }

    public String getEmply() {
        return emply;
    }

    public void setEmply(String emply) {
        this.emply = emply;
    }

    public String getEmplname() {
        return emplname;
    }

    public void setEmplname(String emplname) {
        this.emplname = emplname;
    }

    public String getHdnType() {
        return hdnType;
    }

    public void setHdnType(String hdnType) {
        this.hdnType = hdnType;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getProcessSerialNumber() {
        return processSerialNumber;
    }

    public void setProcessSerialNumber(String processSerialNumber) {
        this.processSerialNumber = processSerialNumber;
    }

    public String getEmpl() {
        return empl;
    }

    public void setEmpl(String empl) {
        this.empl = empl;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Date getJbtime() {
        return jbtime;
    }

    public void setJbtime(Date jbtime) {
        this.jbtime = jbtime;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }

    public String getHdnDept() {
        return hdnDept;
    }

    public void setHdnDept(String hdnDept) {
        this.hdnDept = hdnDept;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getHdnLunch() {
        return hdnLunch;
    }

    public void setHdnLunch(String hdnLunch) {
        this.hdnLunch = hdnLunch;
    }

    public String getHdnFacno() {
        return hdnFacno;
    }

    public void setHdnFacno(String hdnFacno) {
        this.hdnFacno = hdnFacno;
    }

    public String getJbdept() {
        return jbdept;
    }

    public void setJbdept(String jbdept) {
        this.jbdept = jbdept;
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
        if (!(object instanceof HKGL034)) {
            return false;
        }
        HKGL034 other = (HKGL034) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.HKGL034[ oid=" + oid + " ]";
    }
    
}
