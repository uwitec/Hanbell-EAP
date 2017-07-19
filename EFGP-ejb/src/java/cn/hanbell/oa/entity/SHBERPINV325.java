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
@Table(name = "SHB_ERP_INV325")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SHBERPINV325.findAll", query = "SELECT s FROM SHBERPINV325 s"),
    @NamedQuery(name = "SHBERPINV325.findByOID", query = "SELECT s FROM SHBERPINV325 s WHERE s.oid = :oid"),
    @NamedQuery(name = "SHBERPINV325.findByPSN", query = "SELECT s FROM SHBERPINV325 s WHERE s.processSerialNumber = :psn"),
    @NamedQuery(name = "SHBERPINV325.findByFSN", query = "SELECT s FROM SHBERPINV325 s WHERE s.formSerialNumber = :fsn")})
public class SHBERPINV325 implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OID")
    private String oid;
    @Size(max = 255)
    @Column(name = "resno")
    private String resno;
    @Size(max = 255)
    @Column(name = "towareh")
    private String towareh;
    @Size(max = 255)
    @Column(name = "prono")
    private String prono;
    @Size(max = 255)
    @Column(name = "bz")
    private String bz;
    @Size(max = 255)
    @Column(name = "dtowareh")
    private String dtowareh;
    @Size(max = 255)
    @Column(name = "itdsc")
    private String itdsc;
    @Size(max = 255)
    @Column(name = "trtype")
    private String trtype;
    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
    @Size(max = 255)
    @Column(name = "dfromwareh")
    private String dfromwareh;
    @Size(max = 255)
    @Column(name = "fromwareh")
    private String fromwareh;
    @Size(max = 255)
    @Column(name = "applyuser")
    private String applyuser;
    @Size(max = 255)
    @Column(name = "itnbr")
    private String itnbr;
    @Size(max = 255)
    @Column(name = "dept")
    private String dept;
    @Size(max = 255)
    @Column(name = "processSerialNumber")
    private String processSerialNumber;
    @Size(max = 255)
    @Column(name = "formSerialNumber")
    private String formSerialNumber;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Size(max = 255)
    @Column(name = "relformid")
    private String relformid;

    public SHBERPINV325() {
    }

    public SHBERPINV325(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getResno() {
        return resno;
    }

    public void setResno(String resno) {
        this.resno = resno;
    }

    public String getTowareh() {
        return towareh;
    }

    public void setTowareh(String towareh) {
        this.towareh = towareh;
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getDtowareh() {
        return dtowareh;
    }

    public void setDtowareh(String dtowareh) {
        this.dtowareh = dtowareh;
    }

    public String getItdsc() {
        return itdsc;
    }

    public void setItdsc(String itdsc) {
        this.itdsc = itdsc;
    }

    public String getTrtype() {
        return trtype;
    }

    public void setTrtype(String trtype) {
        this.trtype = trtype;
    }

    public String getFacno() {
        return facno;
    }

    public void setFacno(String facno) {
        this.facno = facno;
    }

    public String getDfromwareh() {
        return dfromwareh;
    }

    public void setDfromwareh(String dfromwareh) {
        this.dfromwareh = dfromwareh;
    }

    public String getFromwareh() {
        return fromwareh;
    }

    public void setFromwareh(String fromwareh) {
        this.fromwareh = fromwareh;
    }

    public String getApplyuser() {
        return applyuser;
    }

    public void setApplyuser(String applyuser) {
        this.applyuser = applyuser;
    }

    public String getItnbr() {
        return itnbr;
    }

    public void setItnbr(String itnbr) {
        this.itnbr = itnbr;
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
        if (!(object instanceof SHBERPINV325)) {
            return false;
        }
        SHBERPINV325 other = (SHBERPINV325) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.SHBERPINV325[ oid=" + oid + " ]";
    }

    /**
     * @return the createdate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return the relformid
     */
    public String getRelformid() {
        return relformid;
    }

    /**
     * @param relformid the relformid to set
     */
    public void setRelformid(String relformid) {
        this.relformid = relformid;
    }

}
