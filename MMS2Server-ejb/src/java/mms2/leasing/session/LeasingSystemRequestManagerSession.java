/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.LeasingSystemRequestEntity;
import mms.leasing.entity.LongTermApplicationEntity;
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
    public void addPublicApplicationApprovalRequest(String staffUserName, 
            String mallName,Long applicantId, String descriptionString){
        System.out.println(descriptionString);
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
        em.persist(leasingRequest);
    }
    
    @Override
    public void deleteAllCollideRequestAndApplication(Long requestId, Long applicationId){
        LeasingSystemRequestEntity leasingRequest  =em.find(LeasingSystemRequestEntity.class, requestId);
        leasingRequest.setStatus("ACCEPTED");
        LongTermApplicationEntity longTermApplication = em.find(LongTermApplicationEntity.class,
                applicationId);
        ArrayList<String> applyUnitList = longTermApplication.getApplyUnitList();
        String mallName = longTermApplication.getMallName();
        Query query = em.createQuery("SELECT a FROM LongTermApplicationEntity a "
                + "WHERE a.mallName =:inMallName");
        query.setParameter("inMallName", mallName);
        ArrayList<LongTermApplicationEntity> resultList = new
            ArrayList<LongTermApplicationEntity>(query.getResultList());
        for(int i=0; i<resultList.size(); i++){
            LongTermApplicationEntity tempApplication = resultList.get(i);
            ArrayList<String> tempUnitList = tempApplication.getApplyUnitList();
            for (int j=0; j<applyUnitList.size(); j++){
                if(tempUnitList.contains(applyUnitList.get(j)))
                    em.remove(tempApplication);
                em.flush();
            }
        }
    }
}
