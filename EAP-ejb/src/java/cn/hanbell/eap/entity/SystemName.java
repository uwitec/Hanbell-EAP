/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "systemname")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemName.findAll", query = "SELECT s FROM SystemName s"),
    @NamedQuery(name = "SystemName.findById", query = "SELECT s FROM SystemName s WHERE s.id = :id"),
    @NamedQuery(name = "SystemName.findByName", query = "SELECT s FROM SystemName s WHERE s.name = :name"),
    @NamedQuery(name = "SystemName.findBySortid", query = "SELECT s FROM SystemName s WHERE s.sortid = :sortid"),
    @NamedQuery(name = "SystemName.findByDescript", query = "SELECT s FROM SystemName s WHERE s.descript = :descript"),
    @NamedQuery(name = "SystemName.findByStatus", query = "SELECT s FROM SystemName s WHERE s.status = :status"),
    @NamedQuery(name = "SystemName.findByCreator", query = "SELECT s FROM SystemName s WHERE s.creator = :creator"),
    @NamedQuery(name = "SystemName.findByCredate", query = "SELECT s FROM SystemName s WHERE s.credate = :credate"),
    @NamedQuery(name = "SystemName.findByOptuser", query = "SELECT s FROM SystemName s WHERE s.optuser = :optuser"),
    @NamedQuery(name = "SystemName.findByOptdate", query = "SELECT s FROM SystemName s WHERE s.optdate = :optdate"),
    @NamedQuery(name = "SystemName.findByCfmuser", query = "SELECT s FROM SystemName s WHERE s.cfmuser = :cfmuser"),
    @NamedQuery(name = "SystemName.findByCfmdate", query = "SELECT s FROM SystemName s WHERE s.cfmdate = :cfmdate")})
public class SystemName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sortid")
    private int sortid;
    @Size(max = 50)
    @Column(name = "descript")
    private String descript;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @Size(max = 20)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @Size(max = 20)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;

    public SystemName() {
    }

    public SystemName(Integer id) {
        this.id = id;
    }

    public SystemName(Integer id, String name, int sortid, String status) {
        this.id = id;
        this.name = name;
        this.sortid = sortid;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortid() {
        return sortid;
    }

    public void setSortid(int sortid) {
        this.sortid = sortid;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemName)) {
            return false;
        }
        SystemName other = (SystemName) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.eap.entity.SystemName[ id=" + id + " ]";
    }
    
}
