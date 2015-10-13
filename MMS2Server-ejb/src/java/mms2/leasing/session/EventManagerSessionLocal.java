/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
import mms2.leasing.entity.EventEntity;

/**
 *
 * @author PhiLong
 */
@Local
public interface EventManagerSessionLocal {

    public EventEntity createEvent(String hostName, String tel, String email, ArrayList<String> description, Timestamp startDate, Timestamp endDate, String mallName, ArrayList<String> unitList);

    public ArrayList<String> getAvailableEventUnit(String mallName, Timestamp eventStart, Timestamp eventEnd);

    public ArrayList<EventEntity> getAllEventApplication(String mallName);

    public EventEntity getEventById(long eventId);

    public String acceptEventApplication(long eventId, double contractAmount);

    public ArrayList<EventEntity> getAllEvent(String mallName);

    public String deleteEventWithId(long eventId);
    
}
