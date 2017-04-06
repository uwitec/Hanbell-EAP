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
    @Column(name = "lunch")
    private String lunch;
    @Size(max = 255)
    @Column(name = "dinner")
    private String dinner;

    @Size(max = 255)
    @Column(name = "jbtime_txt")
    private String jbtimeTxt;
    @Size(max = 255)
    @Column(name = "starttime_txt")
    private String starttimeTxt;
    @Size(max = 255)
    @Column(name = "endtime_txt")
    private String endtimeTxt;
    @Size(max = 255)
    @Column(name = "hour")
    private String hour;
    @Size(max = 255)
    @Column(name = "jbdept_txt")
    private String jbdeptTxt;
    @Size(max = 255)
    @Column(name = "jbdept_lbl")
    private String jbdeptLbl;
    @Size(max = 255)
    @Column(name = "empl")
    private String empl;
    @Size(max = 255)
    @Column(name = "emplname")
    private String emplname;
    @Size(max = 255)
    @Column(name = "content")
    private String content;

    public HKGL034DetailModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getStarttimeTxt() {
        return starttimeTxt;
    }

    public void setStarttimeTxt(String starttimeTxt) {
        this.starttimeTxt = starttimeTxt;
    }

    public String getJbtimeTxt() {
        return jbtimeTxt;
    }

    public void setJbtimeTxt(String jbtimeTxt) {
        this.jbtimeTxt = jbtimeTxt;
    }

    public String getEndtimeTxt() {
        return endtimeTxt;
    }

    public void setEndtimeTxt(String endtimeTxt) {
        this.endtimeTxt = endtimeTxt;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getEmplname() {
        return emplname;
    }

    public void setEmplname(String emplname) {
        this.emplname = emplname;
    }

    public String getJbdeptTxt() {
        return jbdeptTxt;
    }

    public void setJbdeptTxt(String jbdeptTxt) {
        this.jbdeptTxt = jbdeptTxt;
    }

    public String getJbdeptLbl() {
        return jbdeptLbl;
    }

    public void setJbdeptLbl(String jbdeptLbl) {
        this.jbdeptLbl = jbdeptLbl;
    }

    public String getEmpl() {
        return empl;
    }

    public void setEmpl(String empl) {
        this.empl = empl;
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
