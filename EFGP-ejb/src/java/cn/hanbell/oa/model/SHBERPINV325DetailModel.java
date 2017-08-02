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
 * @author C1587
 */
@XmlRootElement
public class SHBERPINV325DetailModel implements Serializable {

    @Size(max = 255)
    @Column(name = "seq")
    private String seq;
    @Size(max = 255)
    @Column(name = "itnbr")
    private String itnbr;
    @Size(max = 255)
    @Column(name = "itdsc")
    private String itdsc;
    @Size(max = 255)
    @Column(name = "countnum")
    private String countnum;
    @Size(max = 255)
    @Column(name = "sumnum")
    private String sumnum;
    @Size(max = 255)
    @Column(name = "dfromwareh")
    private String dfromwareh;
    @Size(max = 255)
    @Column(name = "dtowareh")
    private String dtowareh;
    @Size(max = 255)
    @Column(name = "dtowarehsum")
    private String dtowarehsum;
    @Size(max = 255)
    @Column(name = "bz")
    private String bz;

    public SHBERPINV325DetailModel() {
        this.seq = "";
        this.itnbr = "";
        this.itdsc = "";
        this.countnum = "";
        this.sumnum = "";
        this.dfromwareh = "";
        this.dtowareh = "";
        this.dtowarehsum = "";
        this.bz = "";
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
     * @return the itnbr
     */
    public String getItnbr() {
        return itnbr;
    }

    /**
     * @param itnbr the itnbr to set
     */
    public void setItnbr(String itnbr) {
        this.itnbr = itnbr;
    }

    /**
     * @return the itdsc
     */
    public String getItdsc() {
        return itdsc;
    }

    /**
     * @param itdsc the itdsc to set
     */
    public void setItdsc(String itdsc) {
        this.itdsc = itdsc;
    }

    /**
     * @return the countnum
     */
    public String getCountnum() {
        return countnum;
    }

    /**
     * @param countnum the countnum to set
     */
    public void setCountnum(String countnum) {
        this.countnum = countnum;
    }

    /**
     * @return the sumnum
     */
    public String getSumnum() {
        return sumnum;
    }

    /**
     * @param sumnum the sumnum to set
     */
    public void setSumnum(String sumnum) {
        this.sumnum = sumnum;
    }

    /**
     * @return the dfromwareh
     */
    public String getDfromwareh() {
        return dfromwareh;
    }

    /**
     * @param dfromwareh the dfromwareh to set
     */
    public void setDfromwareh(String dfromwareh) {
        this.dfromwareh = dfromwareh;
    }

    /**
     * @return the dtowareh
     */
    public String getDtowareh() {
        return dtowareh;
    }

    /**
     * @param dtowareh the dtowareh to set
     */
    public void setDtowareh(String dtowareh) {
        this.dtowareh = dtowareh;
    }

    /**
     * @return the dtowarehsum
     */
    public String getDtowarehsum() {
        return dtowarehsum;
    }

    /**
     * @param dtowarehsum the dtowarehsum to set
     */
    public void setDtowarehsum(String dtowarehsum) {
        this.dtowarehsum = dtowarehsum;
    }

    /**
     * @return the bz
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz the bz to set
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

}
