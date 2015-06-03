
package tr.gov.ptt.gr1tahsilatuyg.bean;

import java.util.List;
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
    private TahsilatKisiBean tahsilatKisiBean;
   
    public TahsilatMenuBean() {
        
        List<TahsilatMenu> menuListesi=tahsilatKisiBean.getKisi().getTahsilatMenuList();
        
        DefaultSubMenu subMenu= new DefaultSubMenu();
        subMenu.setLabel("Kullanıcı İşlemleri");
        
        
        DefaultMenuItem menuItem = new DefaultMenuItem();
        
        for (TahsilatMenu menu : menuListesi) {
            menuItem.setValue(menu.getBaslik());
            menuItem.setUrl(menu.getLink()+".xhtml?faces-redirect=true");
            subMenu.addElement(menuItem);
            
        }
               
        simpleMenuModel.addElement(subMenu);
       
       
    }
    
    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }
    
}
