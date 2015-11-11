/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
import mms.facility.entity.FacilityEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Local
public interface FacilityManagerSessionLocal {
    public FacilityEntity addFacility(Long contractorId, String facilityName, String facilityCategory, 
            int facilityQuantity, String facilityCondition, String facilityLocation, 
            Timestamp facilityPurchaseDate, Timestamp facilityExpiryDate, double facilityCost,
            String mallName); 
    
    public ArrayList<FacilityEntity> listFacility(String mallName);
    
    public FacilityEntity getFacility(Long facilityId);

    public FacilityEntity editFacility(Long facilityId, String facilityName, String facilityCategory, 
            int facilityQuantity, String facilityCondition, String facilityLocation,
            Timestamp facilityPurchaseDate, Timestamp facilityExpiryDate, double facilityCost);

    public void deleteFacility(Long facilityId);  

    public boolean verifyFacility(String facilityName, String mallName, String facilityLocation);
    }
