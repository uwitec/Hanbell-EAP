/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.demo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@XmlRootElement
public class HelloNaNa {

    private String creator;
    private String applyuser;
    private String dept;
    private String reason;
    private String centerid;

    public HelloNaNa() {

    }

    public HelloNaNa(String creator, String applyuser, String dept, String reason, String centerid) {
        this.creator = creator;
        this.applyuser = applyuser;
        this.dept = dept;
        this.reason = reason;
        this.centerid = centerid;
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return the applyuser
     */
    public String getApplyuser() {
        return applyuser;
    }

    /**
     * @param applyuser the applyuser to set
     */
    public void setApplyuser(String applyuser) {
        this.applyuser = applyuser;
    }

    /**
     * @return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
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
     * @return the centerid
     */
    public String getCenterid() {
        return centerid;
    }

    /**
     * @param centerid the centerid to set
     */
    public void setCenterid(String centerid) {
        this.centerid = centerid;
    }

}
