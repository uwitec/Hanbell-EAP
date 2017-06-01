/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.entity;

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
 * @author C1368
 */
@Entity
@Table(name = "am_tb_asset_apply_had")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AmTbAssetApplyHad.findAll", query = "SELECT a FROM AmTbAssetApplyHad a"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByApplyHadSn", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.applyHadSn = :applyHadSn"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByDepno", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.depno = :depno"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByCreateTime", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.createTime = :createTime"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByApplyState", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.applyState = :applyState"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByCreatePeople", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.createPeople = :createPeople"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByReplyPeople", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.replyPeople = :replyPeople"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByReplyTime", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.replyTime = :replyTime"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByReplyMemo", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.replyMemo = :replyMemo"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByEndPeople", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.endPeople = :endPeople"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByEndTime", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.endTime = :endTime"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByDispatchState", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.dispatchState = :dispatchState"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByRecheckPeople", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.recheckPeople = :recheckPeople"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByRecheckTime", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.recheckTime = :recheckTime"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByRecheckMemo", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.recheckMemo = :recheckMemo"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByApplyType", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.applyType = :applyType"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByHmark2", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.hmark2 = :hmark2"),
    @NamedQuery(name = "AmTbAssetApplyHad.findByFormid", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.formid = :formid"),
    @NamedQuery(name = "AmTbAssetApplyHad.findBySheetno", query = "SELECT a FROM AmTbAssetApplyHad a WHERE a.sheetno = :sheetno")})
public class AmTbAssetApplyHad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "apply_had_sn")
    private String applyHadSn;
    @Size(max = 8)
    @Column(name = "depno")
    private String depno;
    @Size(max = 8)
    @Column(name = "apply_people")
    private String applyPeople;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "apply_state")
    private Integer applyState;
    @Size(max = 8)
    @Column(name = "create_people")
    private String createPeople;
    @Size(max = 20)
    @Column(name = "co_code")
    private String cocode;
    @Size(max = 8)
    @Column(name = "reply_people")
    private String replyPeople;
    @Column(name = "reply_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date replyTime;
    @Size(max = 100)
    @Column(name = "reply_memo")
    private String replyMemo;
    @Size(max = 8)
    @Column(name = "end_people")
    private String endPeople;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "dispatch_state")
    private Integer dispatchState;
    @Size(max = 20)
    @Column(name = "recheck_people")
    private String recheckPeople;
    @Column(name = "recheck_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recheckTime;
    @Size(max = 100)
    @Column(name = "recheck_memo")
    private String recheckMemo;
    @Size(max = 4)
    @Column(name = "apply_type")
    private String applyType;
    @Size(max = 10)
    @Column(name = "hmark2")
    private String hmark2;
    @Size(max = 10)
    @Column(name = "formid")
    private String formid;
    @Size(max = 20)
    @Column(name = "sheetno")
    private String sheetno;

    public AmTbAssetApplyHad() {
    }

    public AmTbAssetApplyHad(String applyHadSn) {
        this.applyHadSn = applyHadSn;
    }

    public String getApplyHadSn() {
        return applyHadSn;
    }

    public void setApplyHadSn(String applyHadSn) {
        this.applyHadSn = applyHadSn;
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno;
    }

    public String getApplyPeople() {
        return applyPeople;
    }

    public void setApplyPeople(String applyPeople) {
        this.applyPeople = applyPeople;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public String getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    public String getReplyPeople() {
        return replyPeople;
    }

    public void setReplyPeople(String replyPeople) {
        this.replyPeople = replyPeople;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyMemo() {
        return replyMemo;
    }

    public void setReplyMemo(String replyMemo) {
        this.replyMemo = replyMemo;
    }

    public String getEndPeople() {
        return endPeople;
    }

    public void setEndPeople(String endPeople) {
        this.endPeople = endPeople;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDispatchState() {
        return dispatchState;
    }

    public void setDispatchState(Integer dispatchState) {
        this.dispatchState = dispatchState;
    }

    public String getRecheckPeople() {
        return recheckPeople;
    }

    public void setRecheckPeople(String recheckPeople) {
        this.recheckPeople = recheckPeople;
    }

    public Date getRecheckTime() {
        return recheckTime;
    }

    public void setRecheckTime(Date recheckTime) {
        this.recheckTime = recheckTime;
    }

    public String getRecheckMemo() {
        return recheckMemo;
    }

    public void setRecheckMemo(String recheckMemo) {
        this.recheckMemo = recheckMemo;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getHmark2() {
        return hmark2;
    }

    public void setHmark2(String hmark2) {
        this.hmark2 = hmark2;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getSheetno() {
        return sheetno;
    }

    public void setSheetno(String sheetno) {
        this.sheetno = sheetno;
    }

    public String getCocode() {
        return cocode;
    }

    public void setCocode(String cocode) {
        this.cocode = cocode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applyHadSn != null ? applyHadSn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmTbAssetApplyHad)) {
            return false;
        }
        AmTbAssetApplyHad other = (AmTbAssetApplyHad) object;
        if ((this.applyHadSn == null && other.applyHadSn != null) || (this.applyHadSn != null && !this.applyHadSn.equals(other.applyHadSn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.erp.entity.AmTbAssetApplyHad[ applyHadSn=" + applyHadSn + " ]";
    }

}
