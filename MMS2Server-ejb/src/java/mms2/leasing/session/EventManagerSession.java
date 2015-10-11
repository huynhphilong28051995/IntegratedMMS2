/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms2.leasing.entity.EventEntity;
import mms2.leasing.entity.UnitEntity;

/**
 *
 * @author PhiLong
 */
@Stateless
public class EventManagerSession implements EventManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public EventEntity createEvent(String hostName, String tel, String email, ArrayList<String> description,
            Timestamp startDate, Timestamp endDate, String mallName, ArrayList<String> unitList) {
        EventEntity event = new EventEntity(hostName, tel, email, description,
                startDate, endDate, mallName, unitList);
        em.persist(event);
        return event;
    }

    @Override
    public ArrayList<String> getAvailableEventUnit(String mallName, Timestamp eventStart, Timestamp eventEnd) {
        ArrayList<String> returnList = new ArrayList();
        try {
            Query query1 = em.createQuery("SELECT u FROM UnitEntity u "
                    + "WHERE u.mallName = :inMallName "
                    + "AND u.event = :inEvent");
            query1.setParameter("inMallName", mallName);
            query1.setParameter("inEvent", true);
            ArrayList<UnitEntity> unitList = new ArrayList<UnitEntity>(query1.getResultList());
            
            Query query2 = em.createQuery("SELECT e FROM EventEntity e "
                    + "WHERE e.mallName =:inMallName "
                    + "AND e.approved = :inApproved");
            query2.setParameter("inMallName", mallName);
            query2.setParameter("inApproved", true);
            ArrayList<EventEntity> eventList = new ArrayList<EventEntity>(query2.getResultList());
            
            
            for (int i = 0; i < unitList.size(); i++) {
                boolean available = true;
                String locationCode = unitList.get(i).getLocationCode();
                for (int j = 0; j < eventList.size(); j++) {
                    EventEntity event = eventList.get(j);
                    Timestamp tempStart = event.getStartDate();
                    Timestamp tempEnd = event.getEndDate();
                    ArrayList<String> tempUnitList = event.getUnitList();
                    if (tempUnitList.contains(locationCode)) {
                        if ((eventStart.after(tempStart) && eventStart.before(tempEnd))
                                || (eventEnd.after(tempStart)&&eventEnd.before(tempEnd))) {
                            available = false;
                            break;
                        }
                    }
                }
                if (available) {
                    returnList.add(locationCode);
                }
            }
            
        } catch (Exception e) {
            System.err.println("EventManagerSession_getAvailableEventUnit throw exception");
            e.printStackTrace();
        }
        
        return returnList;
    }
    
}
