

package tr.gov.ptt.gr1tahsilatuyg.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tr.gov.ptt.gr1tahsilatuyg.facade.TahsilatKisiFacade;

@Stateless
public class TahsilatKisiService {
    
    @EJB
    private TahsilatKisiFacade tahsilatKisiFacade;
    
}
