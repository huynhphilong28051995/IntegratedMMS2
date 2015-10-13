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
import mms2.leasing.entity.EventContractEntity;
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
                                || (eventEnd.after(tempStart) && eventEnd.before(tempEnd))
                                || (eventStart.before(tempStart) && eventEnd.after(tempEnd))
                                || (eventStart.equals(tempStart))
                                || (eventStart.equals(tempEnd))
                                || (eventEnd.equals(tempStart))
                                || (eventEnd.equals(tempEnd))) {
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

    @Override
    public ArrayList<EventEntity> getAllEventApplication(String mallName) {
        Query query = em.createQuery("SELECT e FROM EventEntity e "
                + "WHERE e.mallName = :inMallName AND "
                + "e.approved = :inApproved");
        query.setParameter("inMallName", mallName);
        query.setParameter("inApproved", false);
        return new ArrayList<EventEntity>(query.getResultList());
    }

    @Override
    public String acceptEventApplication(long eventId, double contractAmount) {
        try {
            EventEntity event = em.find(EventEntity.class, eventId);
            String mallName = event.getMallName();
            ArrayList<String> unitList = event.getUnitList();
            Timestamp eventStart = event.getStartDate();
            Timestamp eventEnd = event.getEndDate();
            Query query = em.createQuery("SELECT e FROM EventEntity e "
                    + "WHERE e.mallName =:inMallName AND e.approved =  :inApproved "
                    + "AND e.id <> :inId");
            query.setParameter("inMallName", mallName);
            query.setParameter("inApproved", false);
            query.setParameter("inId", eventId);
            for (Object o : query.getResultList()) {
                boolean delete = false;
                EventEntity tempEvent = (EventEntity) o;
                ArrayList<String> tempUnitList = tempEvent.getUnitList();
                Timestamp tempStart = tempEvent.getStartDate();
                Timestamp tempEnd = tempEvent.getEndDate();
                if (((tempStart.before(eventEnd)) && (tempStart.after(eventStart)))
                        || ((tempEnd.before(eventEnd)) && (tempEnd.after(eventStart)))
                        || ((tempStart.before(eventStart)) && (tempEnd.after(eventEnd)))
                        || (tempStart.equals(eventStart))
                        || (tempStart.equals(eventEnd))
                        || (tempEnd.equals(eventStart))
                        || (tempEnd.equals(eventEnd))) {
                    for (int i = 0; i < unitList.size(); i++) {
                        String locationCode = unitList.get(i);
                        if (tempUnitList.contains(locationCode)) {
                            delete = true;
                        }
                    }
                }
                if (delete) {
                    em.remove(tempEvent);
                    em.flush();
                }
            }
            event.setApproved(true);
            EventContractEntity contract = new EventContractEntity(eventStart, eventEnd, contractAmount);
            em.persist(contract);
            em.flush();
            event.setContract(contract);
            em.merge(event);
            SendMail_AcceptEvent mail = new SendMail_AcceptEvent();
            mail.sendMail(event);
            return "Successfully added event to official database";

        } catch (Exception e) {
            System.err.println("EventManagerSession_acceptEventApplication throw exception");
            e.printStackTrace();
            return "Unsuccessful ! There has been error while modifying database";
        }
    }

    @Override
    public EventEntity getEventById(long eventId) {
        return em.find(EventEntity.class, eventId);
    }

    @Override
    public ArrayList<EventEntity> getAllEvent(String mallName) {
        try {
            Query query = em.createQuery("SELECT e FROM EventEntity e "
                    + "WHERE e.mallName = :inMallName AND e.approved = :inApproved");
            query.setParameter("inMallName", mallName);
            query.setParameter("inApproved", true);
            return new ArrayList<EventEntity>(query.getResultList());
        } catch (Exception e) {
            System.err.println("EventManagerSession_getAllEvent throw exception");
            e.printStackTrace();
            return new ArrayList<EventEntity>();
        }
    }

    @Override
    public String deleteEventWithId(long eventId) {
        try {
            EventEntity event = em.find(EventEntity.class, eventId);
            EventContractEntity contract = event.getContract();
            event.setContract(null);
            em.remove(contract);
            em.remove(event);
            em.flush();
            return "Successful ! Event record has been removed";
        } catch (Exception e) {
            System.err.println("EventManagerSession_deleteEventWithId throw exception");
            e.printStackTrace();
            return "Unsuccessful ! There is error while accessing database";
        }
    }
}
