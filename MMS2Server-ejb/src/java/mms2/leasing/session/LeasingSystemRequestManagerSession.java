/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.LeasingSystemRequestEntity;
import mms2.leasing.entity.LongTermApplicationEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PhiLong
 */
@Stateless
public class LeasingSystemRequestManagerSession implements LeasingSystemRequestManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addFloorplanRequest(String staffUserName, String mallName, String descriptionString) {
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while (descriptionString.length() > 0) {
            int length = descriptionString.length();
            if (length > 255) {
                String tempString = descriptionString.substring(0, 255);
                descriptionStringList.add(tempString);
                descriptionString = descriptionString.substring(255, length);
            } else {
                descriptionStringList.add(descriptionString);
                descriptionString = "";
            }
        }
        Query query = em.createQuery("SELECT r FROM LeasingSystemRequestEntity r "
                + "WHERE r.sender=:inSender");
        query.setParameter("inSender", "SpacePlanOfficer");
        if (!query.getResultList().isEmpty()) {
            LeasingSystemRequestEntity leasingRequest = (LeasingSystemRequestEntity) query.getResultList().get(0);
            leasingRequest.setDescription(descriptionStringList);
            leasingRequest.setStatus("PENDING");
            em.merge(leasingRequest);
        } else {
            LeasingSystemRequestEntity leasingRequest = new LeasingSystemRequestEntity("SpacePlanOfficer",
                    staffUserName, "LeasingManager", "FloorplanModify", mallName);
            leasingRequest.setDescription(descriptionStringList);
            em.persist(leasingRequest);
        }
    }

    @Override
    public void addCategoryRequest(String staffUserName, String mallName, String descriptionString) {
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while (descriptionString.length() > 0) {
            int length = descriptionString.length();
            if (length > 255) {
                String tempString = descriptionString.substring(0, 255);
                descriptionStringList.add(tempString);
                descriptionString = descriptionString.substring(255, length);
            } else {
                descriptionStringList.add(descriptionString);
                descriptionString = "";
            }
        }
        Query query = em.createQuery("SELECT r FROM LeasingSystemRequestEntity r "
                + "WHERE r.sender=:inSender AND r.type = :inType");
        query.setParameter("inSender", "LeasingOfficer");
        query.setParameter("inType", "CategoryModify");
        if (!query.getResultList().isEmpty()) {
            LeasingSystemRequestEntity leasingRequest = (LeasingSystemRequestEntity) query.getResultList().get(0);
            leasingRequest.setDescription(descriptionStringList);
            leasingRequest.setStatus("PENDING");
            em.merge(leasingRequest);
        } else {
            LeasingSystemRequestEntity leasingRequest
                    = new LeasingSystemRequestEntity("LeasingOfficer", staffUserName,
                            "LeasingManager", "CategoryModify", mallName);
            leasingRequest.setDescription(descriptionStringList);
            em.persist(leasingRequest);
        }
    }

    @Override
    public void addPublicOpenBidRequest(String staffUserName, String mallName,
            String descriptionString, ArrayList<String> listOfUnitOpenForBidding) {
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while (descriptionString.length() > 0) {
            int length = descriptionString.length();
            if (length > 255) {
                String tempString = descriptionString.substring(0, 255);
                descriptionStringList.add(tempString);
                descriptionString = descriptionString.substring(255, length);
            } else {
                descriptionStringList.add(descriptionString);
                descriptionString = "";
            }
        }
        LeasingSystemRequestEntity leasingRequest
                = new LeasingSystemRequestEntity("LeasingOfficer", staffUserName,
                        "LeasingManager", "PublicOpenBid", mallName);
        leasingRequest.setListOfUnitOpenForBidding(listOfUnitOpenForBidding);
        leasingRequest.setDescription(descriptionStringList);
        em.persist(leasingRequest);

    }

    @Override
    public ArrayList<LeasingSystemRequestEntity> getListOfAllPendingLeasingRequests(String mallName) {
        Query query = em.createQuery("SELECT r FROM LeasingSystemRequestEntity r "
                + "WHERE r.mallName = :inMallName AND r.status= :inPending");
        query.setParameter("inMallName", mallName);
        query.setParameter("inPending", "PENDING");
        ArrayList<LeasingSystemRequestEntity> leasingRequestList = new ArrayList();
        for (Object o : query.getResultList()) {
            LeasingSystemRequestEntity leasingRequest = (LeasingSystemRequestEntity) o;
            leasingRequestList.add(leasingRequest);
        }
        return leasingRequestList;
    }

    @Override
    public void updateLeasingRequestStatus(Long leasingRequestId, String status) {
        LeasingSystemRequestEntity leasingRequest
                = em.find(LeasingSystemRequestEntity.class, leasingRequestId);
        leasingRequest.setStatus(status);
        em.merge(leasingRequest);
    }

    @Override
    public LeasingSystemRequestEntity getLeasingRequestById(Long requestId) {
        return em.find(LeasingSystemRequestEntity.class, requestId);
    }
    @Override
    public void deleteLeasingRequestById(Long requestId){
        Query query = em.createQuery("SELECT r FROM LeasingSystemRequestEntity r "
                + "WHERE r.id = :inId");
        query.setParameter("inId", requestId);
        LeasingSystemRequestEntity re =(LeasingSystemRequestEntity) query.getResultList().get(0);
        em.remove(re);
        em.flush();
    }
    
    @Override
    public ArrayList<LeasingSystemRequestEntity> getRequestsByUserName(String userName){
        ArrayList<LeasingSystemRequestEntity> resultList = new ArrayList();
        Query query = em.createQuery("SELECT re FROM LeasingSystemRequestEntity re "
                + "WHERE re.senderUserName =:inUserName");
        query.setParameter("inUserName", userName);
        for (Object o: query.getResultList()){
            LeasingSystemRequestEntity re = (LeasingSystemRequestEntity) o;
            resultList.add(re);
        }
        return resultList;
    }
    
    @Override
    public void addPublicApplicationApprovalRequest(String staffUserName, 
            String mallName,Long applicantId, String descriptionString){
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while (descriptionString.length() > 0) {
            int length = descriptionString.length();
            if (length > 255) {
                String tempString = descriptionString.substring(0, 255);
                descriptionStringList.add(tempString);
                descriptionString = descriptionString.substring(255, length);
            } else {
                descriptionStringList.add(descriptionString);
                descriptionString = "";
            }
        }
        LeasingSystemRequestEntity leasingRequest
                = new LeasingSystemRequestEntity("LeasingOfficer", staffUserName,
                        "LeasingManager", "LongTermApplicationApproval", mallName);
        leasingRequest.setDescription(descriptionStringList);
        leasingRequest.setApplicationId(applicantId);
        LongTermApplicationEntity applicant = em.find(LongTermApplicationEntity.class, applicantId);
        leasingRequest.setApplyUnitList(applicant.getApplyUnitList());
        em.persist(leasingRequest);
    }
    
    @Override
    public void deleteAllCollideRequestAndApplication(Long requestId, Long applicationId){
        LeasingSystemRequestEntity leasingRequest  =em.find(LeasingSystemRequestEntity.class, requestId);
        leasingRequest.setStatus("ACCEPTED");
        ArrayList<String> applyUnitList = leasingRequest.getApplyUnitList();
        
        String mallName = leasingRequest.getMallName();
        
        Query query = em.createQuery("SELECT a FROM LongTermApplicationEntity a "
                + "WHERE a.mallName =:inMallName");
        query.setParameter("inMallName", mallName);
       
        for(Object o: query.getResultList()){
            LongTermApplicationEntity tempApplication =(LongTermApplicationEntity) o;
            ArrayList<String> tempUnitList = tempApplication.getApplyUnitList();
            for (int j=0; j<applyUnitList.size(); j++){
                if(tempUnitList.contains(applyUnitList.get(j))){
                    em.remove(tempApplication);
                    em.flush();
                    break;
                }
            }
        }
        
        Query query2 = em.createQuery("SELECT a FROM LeasingSystemRequestEntity a "
                + "WHERE a.mallName =:inMallName AND a.id <> :inId");
        query2.setParameter("inMallName", mallName);
        query2.setParameter("inId", requestId);
        ArrayList<LeasingSystemRequestEntity> resultList2 = new
            ArrayList<>(query2.getResultList());
        for(int i=0; i<resultList2.size(); i++){
            LeasingSystemRequestEntity tempRequest = resultList2.get(i);
            ArrayList<String> tempUnitList = tempRequest.getApplyUnitList();
            for (int j=0; j<applyUnitList.size(); j++){
                if(tempUnitList.contains(applyUnitList.get(j)))
                    em.remove(tempRequest);
                em.flush();
            }
        }
    }
}
