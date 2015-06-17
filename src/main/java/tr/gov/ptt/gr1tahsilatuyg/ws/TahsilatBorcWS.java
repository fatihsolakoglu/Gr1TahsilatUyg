/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatBorc;
import tr.gov.ptt.gr1tahsilatuyg.entity.TahsilatKisi;
import tr.gov.ptt.gr1tahsilatuyg.service.TahsilatBorcService;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "TahsilatBorcWS")
public class TahsilatBorcWS {
    @EJB
    private TahsilatBorcService ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "kurumAdlariGetir")
    public List<String> kurumAdlariGetir(@WebParam(name = "p_sorgu") String p_sorgu) {
        return ejbRef.kurumAdlariGetir(p_sorgu);
    }

    @WebMethod(operationName = "borclariGetir")
    public List<TahsilatBorc> borclariGetir(@WebParam(name = "p_kurumAd") String p_kurumAd, @WebParam(name = "p_aboneNo") String p_aboneNo) {
        return ejbRef.borclariGetir(p_kurumAd, p_aboneNo);
    }

    
}
