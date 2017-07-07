/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@XmlRootElement
public class HKGL034DetailModel implements Serializable {

    @Size(max = 255)
    @Column(name = "seq")
    private String seq;
    @Size(max = 255)
    @Column(name = "dept_txt")
    private String deptTxt;
    @Size(max = 255)
    @Column(name = "dept_lbl")
    private String deptLbl;
    @Size(max = 255)
    @Column(name = "employee")
    private String employee;
    @Size(max = 255)
    @Column(name = "employeeName")
    private String employeeName;
    @Size(max = 255)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "date1_txt")
    private String date1Txt;
    @Size(max = 255)
    @Column(name = "time1_txt")
    private String time1Txt;
    @Size(max = 255)
    @Column(name = "time2_txt")
    private String time2Txt;
    @Size(max = 255)
    @Column(name = "hour")
    private String hour;
    @Size(max = 255)
    @Column(name = "hdn_dinner")
    private String hdnDinner;
    @Size(max = 255)
    @Column(name = "hdn_lunch")
    private String hdnLunch;

    public HKGL034DetailModel() {
    }

    /**
     * @return the seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    /**
     * @return the deptTxt
     */
    public String getDeptTxt() {
        return deptTxt;
    }

    /**
     * @param deptTxt the deptTxt to set
     */
    public void setDeptTxt(String deptTxt) {
        this.deptTxt = deptTxt;
    }

    /**
     * @return the deptLbl
     */
    public String getDeptLbl() {
        return deptLbl;
    }

    /**
     * @param deptLbl the deptLbl to set
     */
    public void setDeptLbl(String deptLbl) {
        this.deptLbl = deptLbl;
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
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the date1Txt
     */
    public String getDate1Txt() {
        return date1Txt;
    }

    /**
     * @param date1Txt the date1Txt to set
     */
    public void setDate1Txt(String date1Txt) {
        this.date1Txt = date1Txt;
    }

    /**
     * @return the time1Txt
     */
    public String getTime1Txt() {
        return time1Txt;
    }

    /**
     * @param time1Txt the time1Txt to set
     */
    public void setTime1Txt(String time1Txt) {
        this.time1Txt = time1Txt;
    }

    /**
     * @return the time2Txt
     */
    public String getTime2Txt() {
        return time2Txt;
    }

    /**
     * @param time2Txt the time2Txt to set
     */
    public void setTime2Txt(String time2Txt) {
        this.time2Txt = time2Txt;
    }

    /**
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * @return the hdnDinner
     */
    public String getHdnDinner() {
        return hdnDinner;
    }

    /**
     * @param hdnDinner the hdnDinner to set
     */
    public void setHdnDinner(String hdnDinner) {
        this.hdnDinner = hdnDinner;
    }

    /**
     * @return the hdnLunch
     */
    public String getHdnLunch() {
        return hdnLunch;
    }

    /**
     * @param hdnLunch the hdnLunch to set
     */
    public void setHdnLunch(String hdnLunch) {
        this.hdnLunch = hdnLunch;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HKGL034DetailModel)) {
            return false;
        }
        HKGL034DetailModel other = (HKGL034DetailModel) object;
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.oa.entity.HKGL034Detail";
    }

}
