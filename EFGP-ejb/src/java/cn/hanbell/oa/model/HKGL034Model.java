/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@XmlRootElement
public class HKGL034Model implements Serializable {

    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
    @Size(max = 255)
    @Column(name = "hdn_facno")
    private String hdnFacno;
    @Column(name = "applyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyDate;
    @Size(max = 255)
    @Column(name = "applyUser")
    private String applyUser;
    @Size(max = 255)
    @Column(name = "hdn_applyUser")
    private String hdnApplyUser;
    @Size(max = 255)
    @Column(name = "applyDept")
    private String applyDept;
    @Size(max = 255)
    @Column(name = "hdn_applyDept")
    private String hdnApplyDept;

    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "hdn_type")
    private String hdnType;

    public HKGL034Model() {
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    /**
     * @return the hdnFacno
     */
    public String getHdnFacno() {
        return hdnFacno;
    }

    /**
     * @param hdnFacno the hdnFacno to set
     */
    public void setHdnFacno(String hdnFacno) {
        this.hdnFacno = hdnFacno;
    }

    public String getHdnApplyUser() {
        return hdnApplyUser;
    }

    public void setHdnApplyUser(String hdnApplyUser) {
        this.hdnApplyUser = hdnApplyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getHdnType() {
        return hdnType;
    }

    public void setHdnType(String hdnType) {
        this.hdnType = hdnType;
    }

    public String getApplyDept() {
        return applyDept;
    }

    public void setApplyDept(String applyDept) {
        this.applyDept = applyDept;
    }

    public String getHdnApplyDept() {
        return hdnApplyDept;
    }

    public void setHdnApplyDept(String hdnApplyDept) {
        this.hdnApplyDept = hdnApplyDept;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HKGL034Model)) {
            return false;
        }
        HKGL034Model other = (HKGL034Model) object;
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.HKGL034";
    }

}
