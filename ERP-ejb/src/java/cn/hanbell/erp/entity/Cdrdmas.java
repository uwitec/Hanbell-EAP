/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "cdrdmas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cdrdmas.findAll", query = "SELECT c FROM Cdrdmas c"),
    @NamedQuery(name = "Cdrdmas.findByCdrno", query = "SELECT c FROM Cdrdmas c WHERE c.cdrdmasPK.cdrno = :cdrno"),
    @NamedQuery(name = "Cdrdmas.findByCusno", query = "SELECT c FROM Cdrdmas c,Cdrhmas h WHERE c.cdrdmasPK.facno = h.cdrhmasPK.facno AND c.cdrdmasPK.cdrno = h.cdrhmasPK.cdrno AND h.cusno = :cusno"),
    @NamedQuery(name = "Cdrdmas.findByCusnoAndItnbrcus", query = "SELECT c FROM Cdrdmas c,Cdrhmas h WHERE c.cdrdmasPK.facno = h.cdrhmasPK.facno AND c.cdrdmasPK.cdrno = h.cdrhmasPK.cdrno AND h.hrecsta <> 'W' AND h.cusno = :cusno AND c.itnbrcus like :itnbrcus ORDER BY h.recdate DESC ,c.itnbrcus ASC" ),
    @NamedQuery(name = "Cdrdmas.findByItnbr", query = "SELECT c FROM Cdrdmas c WHERE c.itnbr = :itnbr"),
    @NamedQuery(name = "Cdrdmas.findByItnbrcus", query = "SELECT c FROM Cdrdmas c WHERE c.itnbrcus = :itnbrcus"),
    @NamedQuery(name = "Cdrdmas.findByOutdate", query = "SELECT c FROM Cdrdmas c WHERE c.outdate = :outdate"),
    @NamedQuery(name = "Cdrdmas.findByCdrdate", query = "SELECT c FROM Cdrdmas c WHERE c.cdrdate = :cdrdate"),
    @NamedQuery(name = "Cdrdmas.findByDrecsta", query = "SELECT c FROM Cdrdmas c WHERE c.drecsta = :drecsta"),
    @NamedQuery(name = "Cdrdmas.findByCuspono", query = "SELECT c FROM Cdrdmas c WHERE c.cuspono = :cuspono")})
public class Cdrdmas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CdrdmasPK cdrdmasPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "itnbr")
    private String itnbr;
    @Size(max = 20)
    @Column(name = "itnbrcus")
    private String itnbrcus;
    @Size(max = 3)
    @Column(name = "prono")
    private String prono;
    @Column(name = "shptrseq")
    private Short shptrseq;
    @Column(name = "ivotrseq")
    private Short ivotrseq;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdrqy1")
    private BigDecimal cdrqy1;
    @Column(name = "cdrqy2")
    private BigDecimal cdrqy2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preqy1")
    private BigDecimal preqy1;
    @Column(name = "preqy2")
    private BigDecimal preqy2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shpqy1")
    private BigDecimal shpqy1;
    @Column(name = "shpqy2")
    private BigDecimal shpqy2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "armqy")
    private BigDecimal armqy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unpris")
    private BigDecimal unpris;
    @Column(name = "outdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cdrdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdrdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tramts")
    private BigDecimal tramts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lcamts")
    private BigDecimal lcamts;
    @Size(max = 8)
    @Column(name = "dmark1")
    private String dmark1;
    @Size(max = 8)
    @Column(name = "dmark2")
    private String dmark2;
    @Size(max = 8)
    @Column(name = "dmark3")
    private String dmark3;
    @Size(max = 8)
    @Column(name = "dmark4")
    private String dmark4;
    @Size(max = 2)
    @Column(name = "drecsta")
    private String drecsta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "newyn")
    private Character newyn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modnum")
    private short modnum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "moyn")
    private Character moyn;
    @Size(max = 18)
    @Column(name = "pino")
    private String pino;
    @Size(max = 18)
    @Column(name = "lcsno")
    private String lcsno;
    @Size(max = 18)
    @Column(name = "sftno")
    private String sftno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "moqy")
    private BigDecimal moqy;
    @Size(max = 20)
    @Column(name = "cuspono")
    private String cuspono;
    @Size(max = 8)
    @Column(name = "enduserno")
    private String enduserno;
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Column(name = "unprisrccode")
    private Character unprisrccode;
    @Column(name = "focdrqy1")
    private BigDecimal focdrqy1;
    @Column(name = "focdrqy2")
    private BigDecimal focdrqy2;
    @Size(max = 60)
    @Column(name = "cusmark")
    private String cusmark;
    @Size(max = 30)
    @Column(name = "cuslable")
    private String cuslable;
    @Column(name = "islableprt")
    private Character islableprt;
    @Size(max = 30)
    @Column(name = "matecode")
    private String matecode;
    @Column(name = "spcode")
    private Character spcode;
    @Column(name = "outdcfm")
    private Character outdcfm;
    @Size(max = 8)
    @Column(name = "norm")
    private String norm;

    public Cdrdmas() {
    }

    public Cdrdmas(CdrdmasPK cdrdmasPK) {
        this.cdrdmasPK = cdrdmasPK;
    }

    public Cdrdmas(CdrdmasPK cdrdmasPK, String itnbr, BigDecimal cdrqy1, BigDecimal preqy1, BigDecimal shpqy1, BigDecimal armqy, BigDecimal unpris, Date cdrdate, BigDecimal tramts, BigDecimal lcamts, Character newyn, short modnum, Character moyn, BigDecimal moqy) {
        this.cdrdmasPK = cdrdmasPK;
        this.itnbr = itnbr;
        this.cdrqy1 = cdrqy1;
        this.preqy1 = preqy1;
        this.shpqy1 = shpqy1;
        this.armqy = armqy;
        this.unpris = unpris;
        this.cdrdate = cdrdate;
        this.tramts = tramts;
        this.lcamts = lcamts;
        this.newyn = newyn;
        this.modnum = modnum;
        this.moyn = moyn;
        this.moqy = moqy;
    }

    public Cdrdmas(String facno, String cdrno, short trseq) {
        this.cdrdmasPK = new CdrdmasPK(facno, cdrno, trseq);
    }

    public CdrdmasPK getCdrdmasPK() {
        return cdrdmasPK;
    }

    public void setCdrdmasPK(CdrdmasPK cdrdmasPK) {
        this.cdrdmasPK = cdrdmasPK;
    }

    public String getItnbr() {
        return itnbr;
    }

    public void setItnbr(String itnbr) {
        this.itnbr = itnbr;
    }

    public String getItnbrcus() {
        return itnbrcus;
    }

    public void setItnbrcus(String itnbrcus) {
        this.itnbrcus = itnbrcus;
    }

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono;
    }

    public Short getShptrseq() {
        return shptrseq;
    }

    public void setShptrseq(Short shptrseq) {
        this.shptrseq = shptrseq;
    }

    public Short getIvotrseq() {
        return ivotrseq;
    }

    public void setIvotrseq(Short ivotrseq) {
        this.ivotrseq = ivotrseq;
    }

    public BigDecimal getCdrqy1() {
        return cdrqy1;
    }

    public void setCdrqy1(BigDecimal cdrqy1) {
        this.cdrqy1 = cdrqy1;
    }

    public BigDecimal getCdrqy2() {
        return cdrqy2;
    }

    public void setCdrqy2(BigDecimal cdrqy2) {
        this.cdrqy2 = cdrqy2;
    }

    public BigDecimal getPreqy1() {
        return preqy1;
    }

    public void setPreqy1(BigDecimal preqy1) {
        this.preqy1 = preqy1;
    }

    public BigDecimal getPreqy2() {
        return preqy2;
    }

    public void setPreqy2(BigDecimal preqy2) {
        this.preqy2 = preqy2;
    }

    public BigDecimal getShpqy1() {
        return shpqy1;
    }

    public void setShpqy1(BigDecimal shpqy1) {
        this.shpqy1 = shpqy1;
    }

    public BigDecimal getShpqy2() {
        return shpqy2;
    }

    public void setShpqy2(BigDecimal shpqy2) {
        this.shpqy2 = shpqy2;
    }

    public BigDecimal getArmqy() {
        return armqy;
    }

    public void setArmqy(BigDecimal armqy) {
        this.armqy = armqy;
    }

    public BigDecimal getUnpris() {
        return unpris;
    }

    public void setUnpris(BigDecimal unpris) {
        this.unpris = unpris;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
    }

    public Date getCdrdate() {
        return cdrdate;
    }

    public void setCdrdate(Date cdrdate) {
        this.cdrdate = cdrdate;
    }

    public BigDecimal getTramts() {
        return tramts;
    }

    public void setTramts(BigDecimal tramts) {
        this.tramts = tramts;
    }

    public BigDecimal getLcamts() {
        return lcamts;
    }

    public void setLcamts(BigDecimal lcamts) {
        this.lcamts = lcamts;
    }

    public String getDmark1() {
        return dmark1;
    }

    public void setDmark1(String dmark1) {
        this.dmark1 = dmark1;
    }

    public String getDmark2() {
        return dmark2;
    }

    public void setDmark2(String dmark2) {
        this.dmark2 = dmark2;
    }

    public String getDmark3() {
        return dmark3;
    }

    public void setDmark3(String dmark3) {
        this.dmark3 = dmark3;
    }

    public String getDmark4() {
        return dmark4;
    }

    public void setDmark4(String dmark4) {
        this.dmark4 = dmark4;
    }

    public String getDrecsta() {
        return drecsta;
    }

    public void setDrecsta(String drecsta) {
        this.drecsta = drecsta;
    }

    public Character getNewyn() {
        return newyn;
    }

    public void setNewyn(Character newyn) {
        this.newyn = newyn;
    }

    public short getModnum() {
        return modnum;
    }

    public void setModnum(short modnum) {
        this.modnum = modnum;
    }

    public Character getMoyn() {
        return moyn;
    }

    public void setMoyn(Character moyn) {
        this.moyn = moyn;
    }

    public String getPino() {
        return pino;
    }

    public void setPino(String pino) {
        this.pino = pino;
    }

    public String getLcsno() {
        return lcsno;
    }

    public void setLcsno(String lcsno) {
        this.lcsno = lcsno;
    }

    public String getSftno() {
        return sftno;
    }

    public void setSftno(String sftno) {
        this.sftno = sftno;
    }

    public BigDecimal getMoqy() {
        return moqy;
    }

    public void setMoqy(BigDecimal moqy) {
        this.moqy = moqy;
    }

    public String getCuspono() {
        return cuspono;
    }

    public void setCuspono(String cuspono) {
        this.cuspono = cuspono;
    }

    public String getEnduserno() {
        return enduserno;
    }

    public void setEnduserno(String enduserno) {
        this.enduserno = enduserno;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Character getUnprisrccode() {
        return unprisrccode;
    }

    public void setUnprisrccode(Character unprisrccode) {
        this.unprisrccode = unprisrccode;
    }

    public BigDecimal getFocdrqy1() {
        return focdrqy1;
    }

    public void setFocdrqy1(BigDecimal focdrqy1) {
        this.focdrqy1 = focdrqy1;
    }

    public BigDecimal getFocdrqy2() {
        return focdrqy2;
    }

    public void setFocdrqy2(BigDecimal focdrqy2) {
        this.focdrqy2 = focdrqy2;
    }

    public String getCusmark() {
        return cusmark;
    }

    public void setCusmark(String cusmark) {
        this.cusmark = cusmark;
    }

    public String getCuslable() {
        return cuslable;
    }

    public void setCuslable(String cuslable) {
        this.cuslable = cuslable;
    }

    public Character getIslableprt() {
        return islableprt;
    }

    public void setIslableprt(Character islableprt) {
        this.islableprt = islableprt;
    }

    public String getMatecode() {
        return matecode;
    }

    public void setMatecode(String matecode) {
        this.matecode = matecode;
    }

    public Character getSpcode() {
        return spcode;
    }

    public void setSpcode(Character spcode) {
        this.spcode = spcode;
    }

    public Character getOutdcfm() {
        return outdcfm;
    }

    public void setOutdcfm(Character outdcfm) {
        this.outdcfm = outdcfm;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdrdmasPK != null ? cdrdmasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cdrdmas)) {
            return false;
        }
        Cdrdmas other = (Cdrdmas) object;
        if ((this.cdrdmasPK == null && other.cdrdmasPK != null) || (this.cdrdmasPK != null && !this.cdrdmasPK.equals(other.cdrdmasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shberp.entity.Cdrdmas[ cdrdmasPK=" + cdrdmasPK + " ]";
    }

}
