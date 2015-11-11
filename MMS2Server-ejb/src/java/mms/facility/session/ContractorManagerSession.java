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
import mms.facility.entity.ContractorEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Stateless
public class ContractorManagerSession implements ContractorManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    //create new contractor
    @Override
    public ContractorEntity addContractor(String contractorName, String companyName,
            String serviceType, String contractorTel, String contractEmail, Timestamp contractStartDate,
            Timestamp contractEndDate, String mallName) {
        ContractorEntity contractorEntity;
        contractorEntity = new ContractorEntity(contractorName, companyName,
                serviceType, contractorTel, contractEmail, contractStartDate, 
                contractEndDate);
        contractorEntity.setMallName(mallName);
        em.persist(contractorEntity);
        em.flush();
        return contractorEntity;
    }

    //list all contractors
    @Override
    public ArrayList<ContractorEntity> listContractor(String mallName) {
        ArrayList<ContractorEntity> contractorList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT c FROM ContractorEntity c WHERE c.mallName=:inMallName");
            q.setParameter("inMallName", mallName);
            contractorList = new ArrayList<ContractorEntity>(q.getResultList());
            Timestamp currentTime  = new Timestamp(System.currentTimeMillis());
            for(int i=0; i<contractorList.size();i++){
                ContractorEntity contractor = contractorList.get(i);
                Timestamp start  = contractor.getContractStartDate();
                Timestamp end = contractorList.get(i).getContractEndDate();
                if(start.after(currentTime) || end.before(currentTime)){
                    contractor.setContractorStatus("Inactive");
                }else{
                    contractor.setContractorStatus("Active");
                }
                em.merge(contractor);
                em.flush();
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contractorList;
    }

    //get details for 1 existing contractor
    @Override
    public ContractorEntity getContractor(Long contractorId) {
        ArrayList<ContractorEntity> contractorList = new ArrayList();
        ContractorEntity ce;
        try {
            Query q = em.createQuery("SELECT c FROM ContractorEntity c WHERE c.contractorId=:inId");
            q.setParameter("inId", contractorId);
            contractorList = new ArrayList<ContractorEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (ContractorEntity) contractorList.get(0);
    }

    //edit an existing contractor
    @Override
    public ContractorEntity editContractor(Long contractorId, String contractorName, String serviceType, 
            Timestamp contractStartDate, Timestamp contractEndDate, String contractorTel, 
            String contractEmail) {
        ContractorEntity contractorEntity = em.find(ContractorEntity.class, contractorId);
        contractorEntity.setContractorName(contractorName);
        contractorEntity.setServiceType(serviceType);
        contractorEntity.setContractStartDate(contractStartDate);
        contractorEntity.setContractEndDate(contractEndDate);
        contractorEntity.setContractorTel(contractorTel);
        contractorEntity.setContractorEmail(contractEmail);
        em.flush();
        return contractorEntity;
    }

    //delete the contractor  
    @Override
    public void deleteContractor(Long contractorId) {
        ContractorEntity contractorEntity = em.find(ContractorEntity.class, contractorId);
        if (contractorEntity == null) {
            System.out.println("Error! Contractor does not exist!");
        } else {
            em.remove(contractorEntity);
            System.out.println("Facility has been successfully deleted!");
        }
        em.flush();
    }

    //check duplicated contractors within the same mall
    @Override
    public boolean verifyContractor(String contractorName, String mallName) {
        boolean check = false;
        Query q = em.createQuery("SELECT ce FROM ContractorEntity ce WHERE "
                + "ce.contractorName =:inContractorName AND ce.mallName =:inMallName");
        q.setParameter("inContractorName", contractorName);
        q.setParameter("inMallName", mallName);
        if (q.getResultList().size() == 0) {
            check = true; //no dulpicated names existed
        }
        return check;
    }
}
