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
    @Column(name = "emply")
    private String emply;
    @Size(max = 255)
    @Column(name = "hdn_emply")
    private String hdnEmply;
    @Size(max = 255)
    @Column(name = "dept")
    private String dept;
    @Size(max = 255)
    @Column(name = "hdn_dept")
    private String hdnDept;

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;

    @Size(max = 255)
    @Column(name = "type")
    private String type;
    @Size(max = 255)
    @Column(name = "hdn_type")
    private String hdnType;

    public HKGL034Model() {
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

    public String getHdnDept() {
        return hdnDept;
    }

    public void setHdnDept(String hdnDept) {
        this.hdnDept = hdnDept;
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
