/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@XmlRootElement
public class HKGL004Model implements Serializable {
    
    @Size(max = 255)
    @Column(name = "facno")
    private String facno;
    @Column(name = "applyDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyDate;
    @Size(max = 255)
    @Column(name = "applyUser")
    private String applyUser;
    @Size(max = 255)
    @Column(name = "applyDept")
    private String applyDept;
    
    @Size(max = 255)
    @Column(name = "hdn_facno")
    private String hdnFacno;
    @Size(max = 255)
    @Column(name = "hdn_applyDept")
    private String hdnApplyDept;
    @Size(max = 255)
    @Column(name = "hdn_applyUser")
    private String hdnApplyUser;
    
    @Size(max = 255)
    @Column(name = "leana")
    private String leana;
    @Size(max = 255)
    @Column(name = "leano")
    private String leano;
    @Size(max = 255)
    @Column(name = "leatp")
    private String leatp;
    
    @Size(max = 255)
    @Column(name = "hdn_leana")
    private String hdnLeana;
    @Size(max = 255)
    @Column(name = "hdn_leano")
    private String hdnLeano;
    @Size(max = 255)
    @Column(name = "hdn_leatp")
    private String hdnLeatp;
    
    @Size(max = 255)
    @Column(name = "day")
    private String day;
    @Size(max = 255)
    @Column(name = "userTitle")
    private String userTitle;
    @Column(name = "leaday1")
    private Double leaday1;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "leaday2")
    private Double leaday2;
    @Column(name = "leaday3")
    private Double leaday3;
    
    @Column(name = "date1")
    @Temporal(TemporalType.DATE)
    private Date date1;
    @Column(name = "date2")
    @Temporal(TemporalType.DATE)
    private Date date2;
    @Size(max = 255)
    @Column(name = "time1")
    private String time1;
    @Size(max = 255)
    @Column(name = "time2")
    private String time2;
    
    @Id
    @Basic(optional = false)
    @Size(max = 255)
    @Column(name = "employee")
    private String employee;
    @Size(max = 255)
    @Column(name = "reason")
    private String reason;
    
    @Size(max = 255)
    @Column(name = "hdn_employee")
    private String hdnEmployee;
    
    public HKGL004Model() {
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HKGL004Model)) {
            return false;
        }
        HKGL004Model other = (HKGL004Model) object;
        if ((this.getEmployee() != null) && (other.getEmployee() != null)) {
            return this.getEmployee().equals(other.getEmployee());
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.HKGL004";
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

    /**
     * @return the applyDate
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * @param applyDate the applyDate to set
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return the applyUser
     */
    public String getApplyUser() {
        return applyUser;
    }

    /**
     * @param applyUser the applyUser to set
     */
    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    /**
     * @return the applyDept
     */
    public String getApplyDept() {
        return applyDept;
    }

    /**
     * @param applyDept the applyDept to set
     */
    public void setApplyDept(String applyDept) {
        this.applyDept = applyDept;
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

    /**
     * @return the hdnApplyDept
     */
    public String getHdnApplyDept() {
        return hdnApplyDept;
    }

    /**
     * @param hdnApplyDept the hdnApplyDept to set
     */
    public void setHdnApplyDept(String hdnApplyDept) {
        this.hdnApplyDept = hdnApplyDept;
    }

    /**
     * @return the hdnApplyUser
     */
    public String getHdnApplyUser() {
        return hdnApplyUser;
    }

    /**
     * @param hdnApplyUser the hdnApplyUser to set
     */
    public void setHdnApplyUser(String hdnApplyUser) {
        this.hdnApplyUser = hdnApplyUser;
    }

    /**
     * @return the leana
     */
    public String getLeana() {
        return leana;
    }

    /**
     * @param leana the leana to set
     */
    public void setLeana(String leana) {
        this.leana = leana;
    }

    /**
     * @return the leano
     */
    public String getLeano() {
        return leano;
    }

    /**
     * @param leano the leano to set
     */
    public void setLeano(String leano) {
        this.leano = leano;
    }

    /**
     * @return the leatp
     */
    public String getLeatp() {
        return leatp;
    }

    /**
     * @param leatp the leatp to set
     */
    public void setLeatp(String leatp) {
        this.leatp = leatp;
    }

    /**
     * @return the hdnLeana
     */
    public String getHdnLeana() {
        return hdnLeana;
    }

    /**
     * @param hdnLeana the hdnLeana to set
     */
    public void setHdnLeana(String hdnLeana) {
        this.hdnLeana = hdnLeana;
    }

    /**
     * @return the hdnLeano
     */
    public String getHdnLeano() {
        return hdnLeano;
    }

    /**
     * @param hdnLeano the hdnLeano to set
     */
    public void setHdnLeano(String hdnLeano) {
        this.hdnLeano = hdnLeano;
    }

    /**
     * @return the hdnLeatp
     */
    public String getHdnLeatp() {
        return hdnLeatp;
    }

    /**
     * @param hdnLeatp the hdnLeatp to set
     */
    public void setHdnLeatp(String hdnLeatp) {
        this.hdnLeatp = hdnLeatp;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the userTitle
     */
    public String getUserTitle() {
        return userTitle;
    }

    /**
     * @param userTitle the userTitle to set
     */
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    /**
     * @return the leaday1
     */
    public Double getLeaday1() {
        return leaday1;
    }

    /**
     * @param leaday1 the leaday1 to set
     */
    public void setLeaday1(Double leaday1) {
        this.leaday1 = leaday1;
    }

    /**
     * @return the leaday2
     */
    public Double getLeaday2() {
        return leaday2;
    }

    /**
     * @param leaday2 the leaday2 to set
     */
    public void setLeaday2(Double leaday2) {
        this.leaday2 = leaday2;
    }

    /**
     * @return the leaday3
     */
    public Double getLeaday3() {
        return leaday3;
    }

    /**
     * @param leaday3 the leaday3 to set
     */
    public void setLeaday3(Double leaday3) {
        this.leaday3 = leaday3;
    }

    /**
     * @return the date1
     */
    public Date getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    /**
     * @return the date2
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    /**
     * @return the time1
     */
    public String getTime1() {
        return time1;
    }

    /**
     * @param time1 the time1 to set
     */
    public void setTime1(String time1) {
        this.time1 = time1;
    }

    /**
     * @return the time2
     */
    public String getTime2() {
        return time2;
    }

    /**
     * @param time2 the time2 to set
     */
    public void setTime2(String time2) {
        this.time2 = time2;
    }

    /**
     * @return the employee
     */
    public String getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(String employee) {
        this.employee = employee;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the hdnEmployee
     */
    public String getHdnEmployee() {
        return hdnEmployee;
    }

    /**
     * @param hdnEmployee the hdnEmployee to set
     */
    public void setHdnEmployee(String hdnEmployee) {
        this.hdnEmployee = hdnEmployee;
    }
    
}
