
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatMenu;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class TahsilatMenuBean {
    
    private MenuModel simpleMenuModel = new DefaultMenuModel();
    
    @ManagedProperty("#{tahsilatKisiBean}")
    private TahsilatKisiBean kisiBean;
   
    public TahsilatMenuBean() {
        
    }
    
    @PostConstruct
    public void init()
    {
         List<TahsilatMenu> menuListesi=kisiBean.getKisi().getTahsilatMenuList();
        
        DefaultSubMenu subMenu= new DefaultSubMenu();
        subMenu.setLabel("Kullanıcı İşlemleri");
        
        
        DefaultMenuItem menuItem;
        
        for (TahsilatMenu menu : menuListesi) {
            
            menuItem=new DefaultMenuItem();
            menuItem.setValue(menu.getBaslik());
            menuItem.setCommand(menu.getLink());
            subMenu.addElement(menuItem);
            
        }
               
        simpleMenuModel.addElement(subMenu);
    }

    public TahsilatKisiBean getKisiBean() {
        return kisiBean;
    }

    public void setKisiBean(TahsilatKisiBean kisiBean) {
        this.kisiBean = kisiBean;
    }
    
    
    
    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }
    
}
