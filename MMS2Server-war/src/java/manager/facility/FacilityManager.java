/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.facility;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.FacilityEntity;
import mms.facility.session.FacilityManagerSessionLocal;

/**
 *
 * @author linjiao_Zoe
 */
public class FacilityManager {

    FacilityManagerSessionLocal facilityManagerSessionLocal;

    public FacilityManager(FacilityManagerSessionLocal facilityManagerSessionLocal) {
        this.facilityManagerSessionLocal = facilityManagerSessionLocal;
    }

    public FacilityEntity createNewFacility(String facilityName,String facilityLocation,HttpServletRequest request) {
       
        String facilityCategory = request.getParameter("facilityCategory");
        int facilityQuantity = Integer.parseInt(request.getParameter("facilityQuantity"));
        String facilityCondition = request.getParameter("facilityCondition");
        //String facilityLocation = request.getParameter("facilityLocation");
        String facilityPurchaseDateString = request.getParameter("facilityPurchaseDate") + " 00:00:00";
        Timestamp facilityPurchaseDate = Timestamp.valueOf(facilityPurchaseDateString);
        String facilityExpiryDateString = request.getParameter("facilityExpiryDate") + " 00:00:00";
        Timestamp facilityExpiryDate = Timestamp.valueOf(facilityExpiryDateString);
        String mallName = (String) request.getSession().getAttribute("mallName");
        double facilityCost = Double.parseDouble(request.getParameter("facilityCost"));
        Long contractorId = Long.parseLong(request.getParameter("contractorId"));
        return facilityManagerSessionLocal.addFacility(contractorId,facilityName, facilityCategory, facilityQuantity,
                facilityCondition, facilityLocation, facilityPurchaseDate, facilityExpiryDate, facilityCost, mallName);
    }
    
    public FacilityEntity getFacility(HttpServletRequest request) {
        Long facilityId = Long.parseLong(request.getParameter("facilityId"));
        return facilityManagerSessionLocal.getFacility(facilityId);
    }

    public FacilityEntity editFacility(HttpServletRequest request) {
        Long facilityId = (long) request.getSession().getAttribute("facilityId");
        request.removeAttribute("facilityId");
        String facilityName = request.getParameter("facilityName");
        String facilityCategory = request.getParameter("facilityCategory");
        int facilityQuantity = Integer.parseInt(request.getParameter("facilityQuantity"));
        String facilityCondition = request.getParameter("facilityCondition");
        String facilityLocation = request.getParameter("facilityLocation");
        String facilityPurchaseDateString = request.getParameter("facilityPurchaseDate") + " 00:00:00";
        Timestamp facilityPurchaseDate = Timestamp.valueOf(facilityPurchaseDateString);
        String facilityExpiryDateString = request.getParameter("facilityExpiryDate") + " 00:00:00";
        Timestamp facilityExpiryDate = Timestamp.valueOf(facilityExpiryDateString);
        String mallName = (String) request.getSession().getAttribute("mallName");
        double facilityCost = Double.parseDouble(request.getParameter("facilityCost"));
        return facilityManagerSessionLocal.editFacility(facilityId, facilityName, facilityCategory, facilityQuantity,
                facilityCondition, facilityLocation, facilityPurchaseDate, facilityExpiryDate, facilityCost);
    }

    public void deleteFacility(HttpServletRequest request) {
        Long facilityId = Long.parseLong(request.getParameter("facilityId"));
        facilityManagerSessionLocal.deleteFacility(facilityId);
    }
}
