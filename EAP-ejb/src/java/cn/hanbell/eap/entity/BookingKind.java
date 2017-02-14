/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.entity;

import com.lightshell.comm.SuperEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "bookingkind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookingKind.getRowCount", query = "SELECT COUNT(b) FROM BookingKind b"),
    @NamedQuery(name = "BookingKind.findAll", query = "SELECT b FROM BookingKind b"),
    @NamedQuery(name = "BookingKind.findById", query = "SELECT b FROM BookingKind b WHERE b.id = :id"),
    @NamedQuery(name = "BookingKind.findByKind", query = "SELECT b FROM BookingKind b WHERE b.kind = :kind"),
    @NamedQuery(name = "BookingKind.findByName", query = "SELECT b FROM BookingKind b WHERE b.name = :name"),
    @NamedQuery(name = "BookingKind.findBySubject", query = "SELECT b FROM BookingKind b WHERE b.subject = :subject"),
    @NamedQuery(name = "BookingKind.findBySubtitle", query = "SELECT b FROM BookingKind b WHERE b.subtitle = :subtitle"),
    @NamedQuery(name = "BookingKind.findByStatus", query = "SELECT b FROM BookingKind b WHERE b.status = :status")})
public class BookingKind extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "kind")
    private String kind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "subject")
    private String subject;
    @Size(max = 200)
    @Column(name = "subtitle")
    private String subtitle;
    @Size(max = 800)
    @Column(name = "content")
    private String content;
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Size(max = 45)
    @Column(name = "promoter")
    private String promoter;
    @Size(max = 45)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "fax")
    private String fax;
    @Size(max = 45)
    @Column(name = "contacter")
    private String contacter;
    @Size(max = 200)
    @Column(name = "address")
    private String address;
    @Size(max = 800)
    @Column(name = "addressDescription")
    private String addressDescription;
    @Size(max = 400)
    @Column(name = "remark")
    private String remark;

    public BookingKind() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPromoter() {
        return promoter;
    }

    public void setPromoter(String promoter) {
        this.promoter = promoter;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        this.addressDescription = addressDescription;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(object instanceof BookingKind)) {
            return false;
        }
        BookingKind other = (BookingKind) object;
        if (this.id != null && other.id != null && !this.id.equals(other.id)) {
            return false;
        }
        return Objects.equals(this.kind, other.kind); 
    }

    @Override
    public String toString() {
        return "cn.hanbell.eap.entity.BookingKind[ id=" + id + " ]";
    }

}
