/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilat;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatDetay;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilatMuhasebe;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatBorcFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKisiFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKurumFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.ThsTahsilatFacade;

/**
 *
 * @author Administrator
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class TahsilatBorcService {
    
    @EJB
    private TahsilatKurumFacade tahsilatKurumFacade;
    @EJB
    private TahsilatBorcFacade tahsilatBorcFacade;
    @EJB
    private ThsTahsilatFacade tahsilatFacade;   
    @EJB
    private TahsilatKisiFacade kisiFacade;
    
    public List<String> kurumAdlariGetir(String p_sorgu)
    {
        List<TahsilatKurum> kurumListesi=tahsilatKurumFacade.findAll();
        List<String> kurumAdlari=new ArrayList<String>();
        
        for (TahsilatKurum kurum : kurumListesi) {
          
            if(kurum.getAd().toUpperCase().startsWith(p_sorgu.toUpperCase()))
            {
                kurumAdlari.add(kurum.getAd());
            }
        }
        
        return kurumAdlari;
    }
    
    public List<TahsilatBorc> borclariGetir(String p_kurumAd,String p_aboneNo)
    {
       return tahsilatBorcFacade.borclariGetir(tahsilatKurumFacade.kurumIdBul(p_kurumAd), p_aboneNo);
    }
    
    @Resource
    UserTransaction userTransaction;
    public void seciliFaturalariOde(List<TahsilatBorc> p_secili_borclar,TahsilatKisi p_kisi)
    {
        
        try {
            userTransaction.begin();
            ThsTahsilat tahsilat;
            ThsTahsilatDetay tahsilatDetay;
            ThsTahsilatMuhasebe tahsilatMuhasebe;
            ThsTahsilatMuhasebe tahsilatMuhasebeKasa;
            
            for (TahsilatBorc borc : p_secili_borclar) {
                
                tahsilat=new ThsTahsilat();
                tahsilat.setIslemTrh(new java.util.Date());
                tahsilat.setKisi(p_kisi);
                tahsilat.setKisiSiraNo(p_kisi.getSiraNo());
                tahsilat.setKurum(borc.getKurum());
                tahsilat.setTutar(borc.getFaturaTutar());
                
                tahsilatDetay=new ThsTahsilatDetay();
                tahsilatDetay.setAboneNo(borc.getAboneNo());
                tahsilatDetay.setFaturaNo(borc.getFaturaNo());
                tahsilatDetay.setFaturaSonOdemeTrh(borc.getFaturaSonOdemeTrh());
                tahsilatDetay.setTahsilat(tahsilat);
                tahsilatDetay.setTutar(borc.getFaturaTutar());
                
                tahsilatMuhasebe=new ThsTahsilatMuhasebe();
                tahsilatMuhasebe.setHesapNo("HESAP-123456");
                tahsilatMuhasebe.setTahsilat(tahsilat);
                tahsilatMuhasebe.setTutar(borc.getFaturaTutar());
                
                tahsilatMuhasebeKasa=new ThsTahsilatMuhasebe();
                tahsilatMuhasebeKasa.setHesapNo("KASA-123456");
                tahsilatMuhasebeKasa.setTahsilat(tahsilat);
                tahsilatMuhasebeKasa.setTutar(borc.getFaturaTutar());
                
                
                List<ThsTahsilatDetay> detayListe=new ArrayList<>();
                detayListe.add(tahsilatDetay);
                tahsilat.setThsTahsilatDetayList(detayListe);
                
                List<ThsTahsilatMuhasebe> muhasebeListe=new ArrayList<>();
                muhasebeListe.add(tahsilatMuhasebe);
                muhasebeListe.add(tahsilatMuhasebeKasa);
                
                tahsilat.setThsTahsilatMuhasebeList(muhasebeListe);
                
                tahsilatFacade.create(tahsilat);
                p_kisi.setSiraNo(p_kisi.getSiraNo()+1);
                kisiFacade.edit(p_kisi);
                
                borc.setFaturaDurum(1);
                tahsilatBorcFacade.edit(borc);
                
               
                userTransaction.commit();
               
            }
            
            
        } catch (Exception ex) {
            try {
                userTransaction.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(TahsilatBorcService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } 
    }
    
    
}
