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
public class BudgetDetail {

    private String seq;
    private String summary;
    private String budgetacc;
    private String accname;
    private String yearleft;
    private String periodleft;

    public BudgetDetail() {

    }

    public BudgetDetail(String seq, String summary, String budgetacc, String accname, String yl, String pl) {
        this.seq = seq;
        this.summary = summary;
        this.budgetacc = budgetacc;
        this.accname = accname;
        this.yearleft = yl;
        this.periodleft = pl;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the budgetacc
     */
    public String getBudgetacc() {
        return budgetacc;
    }

    /**
     * @param budgetacc the budgetacc to set
     */
    public void setBudgetacc(String budgetacc) {
        this.budgetacc = budgetacc;
    }

    /**
     * @return the accname
     */
    public String getAccname() {
        return accname;
    }

    /**
     * @param accname the accname to set
     */
    public void setAccname(String accname) {
        this.accname = accname;
    }

    /**
     * @return the yearleft
     */
    public String getYearleft() {
        return yearleft;
    }

    /**
     * @param yearleft the yearleft to set
     */
    public void setYearleft(String yearleft) {
        this.yearleft = yearleft;
    }

    /**
     * @return the periodleft
     */
    public String getPeriodleft() {
        return periodleft;
    }

    /**
     * @param periodleft the periodleft to set
     */
    public void setPeriodleft(String periodleft) {
        this.periodleft = periodleft;
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

}
