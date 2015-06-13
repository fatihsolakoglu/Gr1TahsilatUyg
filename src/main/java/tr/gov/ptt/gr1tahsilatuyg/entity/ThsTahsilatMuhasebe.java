/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "THS_TAHSILAT_MUHASEBE")
@NamedQueries({
    @NamedQuery(name = "ThsTahsilatMuhasebe.findAll", query = "SELECT t FROM ThsTahsilatMuhasebe t"),
    @NamedQuery(name = "ThsTahsilatMuhasebe.findById", query = "SELECT t FROM ThsTahsilatMuhasebe t WHERE t.id = :id"),
    @NamedQuery(name = "ThsTahsilatMuhasebe.findByHesapNo", query = "SELECT t FROM ThsTahsilatMuhasebe t WHERE t.hesapNo = :hesapNo"),
    @NamedQuery(name = "ThsTahsilatMuhasebe.findByTutar", query = "SELECT t FROM ThsTahsilatMuhasebe t WHERE t.tutar = :tutar")})
public class ThsTahsilatMuhasebe implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "tahsilatmuhasebeseq",sequenceName = "SEQ_THS_TAHSILAT_MUHASEBE",initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "tahsilatmuhasebeseq",strategy = GenerationType.SEQUENCE)
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "HESAP_NO")
    private String hesapNo;
    @Column(name = "TUTAR")
    private BigDecimal tutar;
    @JoinColumn(name = "TAHSILAT_ID", referencedColumnName = "ID")
    @ManyToOne
    private ThsTahsilat tahsilat;

    public ThsTahsilatMuhasebe() {
    }

    public ThsTahsilatMuhasebe(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getHesapNo() {
        return hesapNo;
    }

    public void setHesapNo(String hesapNo) {
        this.hesapNo = hesapNo;
    }

    public BigDecimal getTutar() {
        return tutar;
    }

    public void setTutar(BigDecimal tutar) {
        this.tutar = tutar;
    }

    public ThsTahsilat getTahsilat() {
        return tahsilat;
    }

    public void setTahsilat(ThsTahsilat tahsilat) {
        this.tahsilat = tahsilat;
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
        if (!(object instanceof ThsTahsilatMuhasebe)) {
            return false;
        }
        ThsTahsilatMuhasebe other = (ThsTahsilatMuhasebe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatMuhasebe[ id=" + id + " ]";
    }
    
}
