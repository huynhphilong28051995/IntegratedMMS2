/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import mms2.leasing.entity.EventEntity;
import mms2.leasing.session.EventManagerSessionLocal;

/**
 *
 * @author PhiLong
 */
public class EventManager {
    EventManagerSessionLocal eventManagerSessionLocal;

    public EventManager(EventManagerSessionLocal eventManagerSessionLocal) {
        this.eventManagerSessionLocal = eventManagerSessionLocal;
    }
    public ArrayList<String> getAvailableEventUnit(HttpServletRequest request){
        String mallName = (String)request.getSession().getAttribute("mallName");
        Timestamp eventStart = Timestamp.valueOf((
                (String)request.getSession().getAttribute("eventStart"))+" 00:00:00");
        Timestamp eventEnd = Timestamp.valueOf(
                ((String)request.getSession().getAttribute("eventEnd"))+" 00:00:00");
        return eventManagerSessionLocal.getAvailableEventUnit(mallName,eventStart, eventEnd);
    }
    public void submitEventApplication(HttpServletRequest request){
        String mallName = (String)request.getSession().getAttribute("mallName");
        String applicantName = (String)request.getSession().getAttribute("applicantName");
        String eventDescription = (String)request.getSession().getAttribute("eventDescription");
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while (eventDescription.length() > 0) {
            int length = eventDescription.length();
            if (length > 255) {
                String tempString = eventDescription.substring(0, 255);
                descriptionStringList.add(tempString);
                eventDescription = eventDescription.substring(255, length);
            } else {
                descriptionStringList.add(eventDescription);
                eventDescription = "";
            }
        }
        String applicantEmail = (String)request.getSession().getAttribute("applicantEmail");
        String applicantTel = (String)request.getSession().getAttribute("applicantTel");
        ArrayList<String> unitList = (ArrayList<String>)request.getSession().getAttribute("applyUnitList");
        Timestamp eventStart = Timestamp.valueOf((
                (String)request.getSession().getAttribute("eventStart"))+" 00:00:00");
        Timestamp eventEnd = Timestamp.valueOf(
                ((String)request.getSession().getAttribute("eventEnd"))+" 00:00:00");
        eventManagerSessionLocal.createEvent(applicantName, applicantTel,applicantEmail,
                descriptionStringList, eventStart,eventEnd, mallName, unitList);
    }
    public ArrayList<EventEntity> getAllEventApplication(HttpServletRequest request){
        String mallName = (String)request.getSession().getAttribute("mallName");
        return eventManagerSessionLocal.getAllEventApplication(mallName);
    }
    public String acceptEventApplication(HttpServletRequest request){
        long eventId = (long)request.getSession().getAttribute("acceptEventId");
        request.removeAttribute("acceptEventId");
        double contractAmount = Double.parseDouble(request.getParameter("contractAmount"));
        return eventManagerSessionLocal.acceptEventApplication(eventId, contractAmount);
    }
    public EventEntity getEventById(HttpServletRequest request){
        long eventId = Long.parseLong((String)request.getParameter("eventId"));
        return eventManagerSessionLocal.getEventById(eventId);
    }
    public ArrayList<EventEntity> getAllEvent(HttpServletRequest request){
        String mallName = (String)request.getSession().getAttribute("mallName");
        return eventManagerSessionLocal.getAllEvent(mallName);
    }
    public String deleteEventWithId(HttpServletRequest request){
        long eventId = Long.parseLong(request.getParameter("eventId"));
        return eventManagerSessionLocal.deleteEventWithId(eventId);
    }
}
