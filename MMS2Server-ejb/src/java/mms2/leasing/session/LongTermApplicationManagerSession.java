/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.LongTermApplicationEntity;
import java.sql.Timestamp;
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
public class LongTermApplicationManagerSession implements LongTermApplicationManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param mallName
     * @param applicantName
     * @param businessType
     * @param applicantDescription
     * @param applicantAddress
     * @param applicantEmail
     * @param applicantTel
     * @param applyUnitList
     * @param applicantBidRate
     */
    @Override
    public void createLongTermApplication(String mallName, String applicantName,
            String applicantBusinessType,ArrayList<String> applicantDescription, 
            String applicantAddress, String applicantEmail,
            String applicantTel, ArrayList<String> applyUnitList, double applicantBidRate) {
        LongTermApplicationEntity application = new LongTermApplicationEntity(mallName,
                applicantName,applicantBusinessType,
                applicantAddress, applicantEmail, applicantTel, applicantBidRate);
        application.setApplicantDescription(applicantDescription);
        application.setApplyUnitList(applyUnitList);
        em.merge(application);
    }

    @Override
    public ArrayList<LongTermApplicationEntity> getLongTermApplicationList(String mallName) {
        Query query = em.createQuery("SELECT a FROM LongTermApplicationEntity a "
                + "WHERE a.mallName = :inMallName");
        query.setParameter("inMallName", mallName);
        return new ArrayList<LongTermApplicationEntity>(query.getResultList());
    }

    @Override
    public void addContractToLongTermApplication(Long applicantId, String contractStartString,
            String contractEndString, String depositString, String description) {
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while (description.length() > 0) {
            int length = description.length();
            if (length > 255) {
                String tempString = description.substring(0, 255);
                descriptionStringList.add(tempString);
                description = description.substring(255, length);
            } else {
                descriptionStringList.add(description);
                description = "";
            }
        }
        Timestamp contractStart  =Timestamp.valueOf(contractStartString+" 00:00:00");
        Timestamp contractEnd = Timestamp.valueOf(contractEndString+" 00:00:00");
        Double deposit = Double.valueOf(depositString);
        LongTermApplicationEntity longTermApplication = em.find(LongTermApplicationEntity.class,
                applicantId);
        longTermApplication.setContractStart(contractStart);
        longTermApplication.setContractEnd(contractEnd);
        longTermApplication.setContractDeposit(deposit);
        longTermApplication.setContractDescription(descriptionStringList);
        em.merge(longTermApplication);
    }
    @Override
    public LongTermApplicationEntity getLongTermApplicationById(Long applicationId){
        return em.find(LongTermApplicationEntity.class, applicationId);
    }
}
