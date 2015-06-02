
package tr.gov.ptt.gr1tahsilatuyg.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatKisiService;

@ManagedBean
@ViewScoped
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
        return null;
    }
    
    
    
}
