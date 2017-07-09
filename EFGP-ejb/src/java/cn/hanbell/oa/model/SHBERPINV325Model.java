/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C1587
 */
@XmlRootElement
public class SHBERPINV325Model implements Serializable {

    private static final long serialVersionUID = 1L;
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
    @Column(name = "count")
    private String count;
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
    @Column(name = "sum")
    private String sum;
    @Size(max = 255)
    @Column(name = "itdsc")
    private String itdsc;
    @Size(max = 255)
    @Column(name = "trtype")
    private String trtype;
    @Size(max = 255)
    @Column(name = "dfromwareh")
    private String dfromwareh;
    @Size(max = 255)
    @Column(name = "fromwareh")
    private String fromwareh;
    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
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

    public SHBERPINV325Model() {
        this.prono = "1";
        this.resno = "0JQ01";
        this.towareh = "ZP01";
        this.trtype = "IAC";
        this.fromwareh = "ASRS01";
        this.facno = "C";
        this.applyuser = "C1587";
        this.dept = "13000";
    }

//    public SHBERPINV325Model(String oid) {
//        this.oid = oid;
//    }
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
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
        if (!(object instanceof SHBERPINV325Model)) {
            return false;
        }
        SHBERPINV325Model other = (SHBERPINV325Model) object;
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
     * @return the facno
     */
    public String getFacno() {
        return facno;
    }

    /**
     * @param facno the facno to set
     */
    public void setFacno(String facno) {
        this.facno = facno;
    }

}
