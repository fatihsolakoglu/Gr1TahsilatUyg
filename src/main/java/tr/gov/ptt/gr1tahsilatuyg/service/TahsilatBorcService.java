/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKurum;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatBorcFacade;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKurumFacade;

/**
 *
 * @author Administrator
 */
@Stateless
public class TahsilatBorcService {
    
    @EJB
    private TahsilatKurumFacade tahsilatKurumFacade;
    @EJB
    private TahsilatBorcFacade tahsilatBorcFacade;
    
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
    
    
    
}
