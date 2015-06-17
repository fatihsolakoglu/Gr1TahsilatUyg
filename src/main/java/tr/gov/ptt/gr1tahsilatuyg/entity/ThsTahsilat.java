/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "THS_TAHSILAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ThsTahsilat.findAll", query = "SELECT t FROM ThsTahsilat t"),
    @NamedQuery(name = "ThsTahsilat.findById", query = "SELECT t FROM ThsTahsilat t WHERE t.id = :id"),
    @NamedQuery(name = "ThsTahsilat.findByIslemTrh", query = "SELECT t FROM ThsTahsilat t WHERE t.islemTrh = :islemTrh"),
    @NamedQuery(name = "ThsTahsilat.findByKisiSiraNo", query = "SELECT t FROM ThsTahsilat t WHERE t.kisiSiraNo = :kisiSiraNo"),
    @NamedQuery(name = "ThsTahsilat.findByTutar", query = "SELECT t FROM ThsTahsilat t WHERE t.tutar = :tutar")})
public class ThsTahsilat implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "tahsilatseq",sequenceName = "SEQ_THS_TAHSILAT",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tahsilatseq",strategy = GenerationType.SEQUENCE)
    private BigDecimal id;
    @Column(name = "ISLEM_TRH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date islemTrh;
    @Column(name = "KISI_SIRA_NO")
    private Integer kisiSiraNo;
    @Column(name = "TUTAR")
    private BigDecimal tutar;
    @OneToMany(mappedBy = "tahsilat",cascade = CascadeType.ALL)
    private List<ThsTahsilatDetay> thsTahsilatDetayList;
    @OneToMany(mappedBy = "tahsilat",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<ThsTahsilatMuhasebe> thsTahsilatMuhasebeList;

    @ManyToOne
    private TahsilatKisi kisi;
    
    @ManyToOne
    private TahsilatKurum kurum;
    
    public ThsTahsilat() {
    }

    public TahsilatKisi getKisi() {
        return kisi;
    }

    public void setKisi(TahsilatKisi kisi) {
        this.kisi = kisi;
    }

    public TahsilatKurum getKurum() {
        return kurum;
    }

    public void setKurum(TahsilatKurum kurum) {
        this.kurum = kurum;
    }

   

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getIslemTrh() {
        return islemTrh;
    }

    public void setIslemTrh(Date islemTrh) {
        this.islemTrh = islemTrh;
    }

    public Integer getKisiSiraNo() {
        return kisiSiraNo;
    }

    public void setKisiSiraNo(Integer kisiSiraNo) {
        this.kisiSiraNo = kisiSiraNo;
    }

    public BigDecimal getTutar() {
        return tutar;
    }

    public void setTutar(BigDecimal tutar) {
        this.tutar = tutar;
    }

    public List<ThsTahsilatDetay> getThsTahsilatDetayList() {
        return thsTahsilatDetayList;
    }

    public void setThsTahsilatDetayList(List<ThsTahsilatDetay> thsTahsilatDetayList) {
        this.thsTahsilatDetayList = thsTahsilatDetayList;
    }

    public List<ThsTahsilatMuhasebe> getThsTahsilatMuhasebeList() {
        return thsTahsilatMuhasebeList;
    }

    public void setThsTahsilatMuhasebeList(List<ThsTahsilatMuhasebe> thsTahsilatMuhasebeList) {
        this.thsTahsilatMuhasebeList = thsTahsilatMuhasebeList;
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
        if (!(object instanceof ThsTahsilat)) {
            return false;
        }
        ThsTahsilat other = (ThsTahsilat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilat[ id=" + id + " ]";
    }
    
}
