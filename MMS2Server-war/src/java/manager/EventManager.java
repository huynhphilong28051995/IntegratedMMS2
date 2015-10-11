/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
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
    
    
}
