/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;


import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.facility.entity.FacilityEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Stateless
public class FacilityManagerSession implements FacilityManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    //create new facility
    @Override
    public FacilityEntity addFacility(String facilityName, String facilityCategory, int facilityQuantity,
            String facilityCondition, String facilityLocation, Timestamp facilityPurchaseDate,
            Timestamp facilityExpiryDate, double facilityCost, String mallName) {
        FacilityEntity facilityEntity = new FacilityEntity(facilityName, facilityCategory, facilityQuantity, facilityCondition,
                facilityLocation, facilityPurchaseDate, facilityExpiryDate, facilityCost);
        facilityEntity.setMallName(mallName);
        em.persist(facilityEntity);
        return facilityEntity;
    }

    //list all facilities
    @Override
    public ArrayList<FacilityEntity> listFacility(String mallName) {
        ArrayList<FacilityEntity> facilityList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT f FROM FacilityEntity f WHERE f.mallName=:inMallName");
            q.setParameter("inMallName", mallName);
            facilityList = new ArrayList<FacilityEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return facilityList;
    }

    //get details for 1 existing facility
    @Override
    public FacilityEntity getFacility(Long facilityId) {
        ArrayList<FacilityEntity> facilityList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT f FROM FacilityEntity f WHERE f.facilityId=:inId");
            q.setParameter("inId", facilityId);
            facilityList = new ArrayList<FacilityEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (FacilityEntity) facilityList.get(0);
    }

    //edit an existing facility
    @Override
    public FacilityEntity editFacility(Long facilityId, String facilityName, String facilityCategory, int facilityQuantity,
            String facilityCondition, String facilityLocation, Timestamp facilityPurchaseDate, 
            Timestamp facilityExpiryDate, double facilityCost) {
        FacilityEntity facilityEntity = em.find(FacilityEntity.class, facilityId);
        facilityEntity.setFacilityName(facilityName);
        facilityEntity.setFacilityCategory(facilityCategory);
        facilityEntity.setFacilityQuantity(facilityQuantity);
        facilityEntity.setFacilityCondition(facilityCondition);
        facilityEntity.setFacilityLocation(facilityLocation);
        facilityEntity.setFacilityPurchaseDate(facilityPurchaseDate);
        facilityEntity.setFacilityExpiryDate(facilityExpiryDate);
        facilityEntity.setFacilityCost(facilityCost);
        em.flush();
        return facilityEntity;
    }

    //delete the facility
    @Override
    public void deleteFacility(Long facilityId) {
        FacilityEntity facilityEntity = em.find(FacilityEntity.class, facilityId);
        if (facilityEntity != null) {          
            em.remove(facilityEntity);
            em.flush();
        }
    }
    
    //check duplicated facilities within the same mall
    @Override
    public boolean verifyFacility(String facilityName, String mallName){
        boolean check=false; 
        Query q = em.createQuery("SELECT fa FROM FacilityEntity fa WHERE "
                + "fa.facilityName =:inFacilityName AND fa.mallName =:inMallName");
        q.setParameter("inFacilityName", facilityName);
        q.setParameter("inMallName", mallName);
        if(q.getResultList().size()==0)
            check = true; //no dulpicated names existed
        return check;
    }
}
