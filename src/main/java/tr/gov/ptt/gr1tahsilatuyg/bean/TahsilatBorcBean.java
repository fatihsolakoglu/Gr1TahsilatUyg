/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class TahsilatBorcBean {
    
    private TahsilatBorc tahsilatBorc;
    
    private List<TahsilatBorc> borcListesi;
    
    private List<TahsilatBorc> seciliBorclar;
    
    private BigDecimal toplam;
    private BigDecimal alinan;
    private BigDecimal para_ustu;
    
    
    @EJB
    private TahsilatBorcService tahsilatBorcService;

    public TahsilatBorcBean() {
        tahsilatBorc=new TahsilatBorc();
        borcListesi=new ArrayList<>();
        seciliBorclar=new ArrayList<>();
        
        toplam=new BigDecimal(0.0);
        alinan=new BigDecimal(0.0);
        para_ustu=new BigDecimal(0.0);
    }

    public BigDecimal getToplam() {
        return toplam;
    }

    public void setToplam(BigDecimal toplam) {
        this.toplam = toplam;
    }

    public BigDecimal getAlinan() {
        return alinan;
    }

    public void setAlinan(BigDecimal alinan) {
        this.alinan = alinan;
    }

    public BigDecimal getPara_ustu() {
        return para_ustu;
    }

    public void setPara_ustu(BigDecimal para_ustu) {
        this.para_ustu = para_ustu;
    }
    
    

    public List<TahsilatBorc> getSeciliBorclar() {
        return seciliBorclar;
    }

    public void setSeciliBorclar(List<TahsilatBorc> seciliBorclar) {
        this.seciliBorclar = seciliBorclar;
    }
    
    

    public List<TahsilatBorc> getBorcListesi() {
        return borcListesi;
    }

    public void setBorcListesi(List<TahsilatBorc> borcListesi) {
        this.borcListesi = borcListesi;
    }

    
    public TahsilatBorc getTahsilatBorc() {
        return tahsilatBorc;
    }

    public void setTahsilatBorc(TahsilatBorc tahsilatBorc) {
        this.tahsilatBorc = tahsilatBorc;
    }
    
    public String faturaSorgula()
    {
        borcListesi= tahsilatBorcService.borclariGetir(tahsilatBorc.getKurum().getAd(),
                      tahsilatBorc.getAboneNo());
        
        
        
        return "tahsilatListele.xhtml?faces-redirect=true";
    }
    
    public List<String> kurumAdiTamamla(String p_sorgu)
    {
        return tahsilatBorcService.kurumAdlariGetir(p_sorgu);
    }
    
    public void hesapla()
    {
        toplam=new BigDecimal(0.0);
        alinan=new BigDecimal(0.0);
        para_ustu=new BigDecimal(0.0);
        
        for (TahsilatBorc borc : seciliBorclar) {
            
            toplam=toplam.add(borc.getFaturaTutar());
            
        }
    }
    
    public void paraUstuHesapla()
    {
        para_ustu=toplam.subtract(alinan);
    }
    
    
    public String yildizliGetir(String gelenDeger)
    {
        String tempDeger="";
        for(Integer i=2;i<gelenDeger.length();i++)
        {
            tempDeger=tempDeger+"*";
        }
        
        return gelenDeger.substring(0, 2)+tempDeger;
    }
    
}
