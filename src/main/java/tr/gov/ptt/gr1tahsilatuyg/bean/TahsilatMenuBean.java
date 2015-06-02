
package tr.gov.ptt.gr1tahsilatuyg.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Administrator
 */
@ManagedBean
@SessionScoped
public class TahsilatMenuBean {
    
    private MenuModel simpleMenuModel = new DefaultMenuModel();
   
    public TahsilatMenuBean() {
        
        DefaultSubMenu subMenu= new DefaultSubMenu();
        subMenu.setLabel("Kullanıcı İşlemleri");
        
        
        DefaultMenuItem menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Giriş");
        menuItem.setUrl("tahsilatGiris.xhtml");
        subMenu.addElement(menuItem);
        
        menuItem = new DefaultMenuItem();
        menuItem.setValue("Tahsilat Sonuç");
        menuItem.setUrl("tahsilatSonuc.xhtml");
        subMenu.addElement(menuItem);
        
        simpleMenuModel.addElement(subMenu);
       
       
    }
    
    public MenuModel getSimpleMenuModel() {
        return simpleMenuModel;
    }
    
}
