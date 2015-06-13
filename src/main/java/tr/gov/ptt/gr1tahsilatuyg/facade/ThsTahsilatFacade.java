/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.ptt.gr1tahsilatuyg.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tr.gov.ptt.gr1tahsilatuyg.entity.ThsTahsilat;

/**
 *
 * @author Administrator
 */
@Stateless
public class ThsTahsilatFacade extends AbstractFacade<ThsTahsilat> {
    @PersistenceContext(unitName = "tr.gov.ptt_Gr1TahsilatUyg_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThsTahsilatFacade() {
        super(ThsTahsilat.class);
    }
    
}
