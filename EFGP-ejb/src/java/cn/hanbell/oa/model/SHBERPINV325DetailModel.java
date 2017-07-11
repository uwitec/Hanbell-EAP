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
    @Column(name = "count")
    private String count;
    @Size(max = 255)
    @Column(name = "sum")
    private String sum;
    @Size(max = 255)
    @Column(name = "dfromwareh")
    private String dfromwareh;
    @Size(max = 255)
    @Column(name = "dtowareh")
    private String dtowareh;
    @Size(max = 255)
    @Column(name = "bz")
    private String bz;

    @Size(max = 255)

    public SHBERPINV325DetailModel() {
        this.seq = "";
        this.itnbr = "";
        this.itdsc = "";
        this.count = "";
        this.sum = "";
        this.dfromwareh = "";
        this.dtowareh = "";
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
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the sum
     */
    public String getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(String sum) {
        this.sum = sum;
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
