
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatKisiService;
import tr.gov.ptt.gr1tahsilatuyg.util.JSFUtil;

@ManagedBean
@SessionScoped
public class TahsilatKisiBean {
    
    @EJB
    private TahsilatKisiService kisiService;
    
    private TahsilatKisi kisi;
    
   

    public TahsilatKisiBean() {
        kisi= new TahsilatKisi();
        
    }


    public TahsilatKisi getKisi() {
        return kisi;
    }

    public void setKisi(TahsilatKisi kisi) {
        this.kisi = kisi;
    }
    
    public String girisKontrol()
    {
        TahsilatKisi pKisi=kisiService.giriseYetkilimi(kisi);
        
        if(pKisi!=null)
        {
            this.kisi=pKisi;
            HttpSession session=JSFUtil.getSession();
            session.setAttribute("username", pKisi.getKullaniciAd());
           
            return "menu.xhtml?faces-redirect=true";
        }
        else 
        {    
            JSFUtil.hataMesajiEkle("Hatalı Giriş", "Kullanıcı Adı yada Şifre Hatalı");
            return "giris.xhtml?faces-redirect=true";
        }
    }
    
    
    
}
