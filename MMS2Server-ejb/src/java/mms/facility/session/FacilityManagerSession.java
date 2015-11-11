/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.facility.entity.ContractorEntity;
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
    public FacilityEntity addFacility(Long contractorId,String facilityName, String facilityCategory, int facilityQuantity,
            String facilityCondition, String facilityLocation, Timestamp facilityPurchaseDate,
            Timestamp facilityExpiryDate, double facilityCost, String mallName) {
        FacilityEntity facilityEntity = new FacilityEntity(facilityName, facilityCategory, facilityQuantity,
                facilityLocation, facilityPurchaseDate, facilityExpiryDate, facilityCost);
        facilityEntity.setMallName(mallName);
        ContractorEntity contractor = em.find(ContractorEntity.class, contractorId);
        Collection<FacilityEntity> facilityList = contractor.getFacility();
        
        em.persist(facilityEntity);
        facilityList.add(facilityEntity);
        em.merge(contractor);
        
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
            Timestamp currentTime  = new Timestamp(System.currentTimeMillis());
            for(int i=0; i<facilityList.size();i++){
                FacilityEntity facility = facilityList.get(i);
                Timestamp start  = facility.getFacilityPurchaseDate();
                Timestamp end = facilityList.get(i).getFacilityExpiryDate();
                if(start.after(currentTime) || end.before(currentTime)){
                    facility.setFacilityStatus("Out of Warranty");
                }else{
                    facility.setFacilityStatus("Under Warranty");
                }
                em.merge(facility);
                em.flush();
            }
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
            Query q = em.createQuery("SELECT con FROM ContractorEntity con WHERE con.mallName = :inMallName");
            q.setParameter("inMallName", facilityEntity.getMallName());
            ArrayList<ContractorEntity> contractorList = new ArrayList<ContractorEntity>(q.getResultList());
            for(int i=0; i< contractorList.size(); i++){
                ContractorEntity contractor  =contractorList.get(i);
                ArrayList<FacilityEntity> facilityList   = new ArrayList<>(contractor.getFacility());
                if(facilityList.contains(facilityEntity)){
                    facilityList.remove(facilityEntity);
                    contractor.setFacility(facilityList);
                    em.merge(contractor);
                }
            }
            
            em.remove(facilityEntity);
            em.flush();
        }
    }
    
    //check duplicated facilities within the same mall
    @Override
    public boolean verifyFacility(String facilityName, String mallName, String facilityLocation){
        boolean check=false; 
        Query q = em.createQuery("SELECT fa FROM FacilityEntity fa WHERE "
                + "fa.facilityName =:inFacilityName AND fa.mallName =:inMallName AND fa.facilityLocation= :inLocation");
        q.setParameter("inFacilityName", facilityName);
        q.setParameter("inLocation", facilityLocation);
        q.setParameter("inMallName", mallName);
        if(q.getResultList().size()==0)
            check = true; //no dulpicated names existed
        return check;
    }
}
