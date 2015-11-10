/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.tenant.session;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.facility.entity.OutsourcingEntity;
import mms.tenant.entity.RentInvoiceEntity;
import mms.tenant.entity.RentRenewalRequestEntity;
import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
@Stateless
public class TenantManagerBean implements TenantManagerBeanLocal {
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean verifyLogin(String email, String password) {
        //query or em.find to retrieve data from database
        Query query= em.createQuery("SELECT u FROM TenantEntity u  "
                + "WHERE u.email=:inUsername AND u.password=:inPassword");
        query.setParameter("inUsername", email);
        query.setParameter("inPassword", encryptPassword(password));
        
        List<TenantEntity> users = (List<TenantEntity>) query.getResultList();
        if (users.size()==0) {
            return false;
        }
        else {
            return true;
        }
    }
    private String encryptPassword(String password) {
        String encrypted = null;
        if (password == null) {
            return null;
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes(), 0, password.length());
                encrypted = new BigInteger(1, md.digest()).toString(16);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return encrypted;
    }
    public TenantEntity getTenant(String email) {
        Query query = em.createQuery("SELECT u FROM TenantEntity u WHERE u.email = :inEmail");
        query.setParameter("inEmail", email);
    
        TenantEntity customer = (TenantEntity) query.getResultList().get(0);
        
        return customer;
    }
    
    public TenantContractEntity getContract(Long contractid) {
        Query query = em.createQuery("SELECT u FROM TenantContractEntity u WHERE u.id = :inEmail");
        query.setParameter("inEmail", contractid);
    
        TenantContractEntity contract = (TenantContractEntity) query.getResultList().get(0);
        
        return contract;
    }
    
     public ArrayList<SaleEntity> getSaleList(Long tenantid) {
        Query query = em.createQuery("SELECT u FROM SaleEntity u WHERE u.tenantId = :inEmail");
        query.setParameter("inEmail", tenantid);
    
        ArrayList<SaleEntity> saleList = new ArrayList<SaleEntity>(query.getResultList());
        
        return saleList;
    }
     
    public ArrayList<RentInvoiceEntity> getInvoices(Long tenantid) {
        Query query = em.createQuery("SELECT u FROM RentInvoiceEntity u WHERE u.tenantID = :inTenantID");
        query.setParameter("inTenantID", tenantid);
    
        ArrayList<RentInvoiceEntity> rentInvoiceList = new ArrayList<RentInvoiceEntity>(query.getResultList());
        
        return rentInvoiceList;
    }
    
    @Override
    public ArrayList<UnitEntity> getUnits(Long tenantid) {
        Query query = em.createQuery("SELECT u FROM UnitEntity u WHERE u.tenant = :inTenant");
        query.setParameter("inTenant", em.find(TenantEntity.class,tenantid));
        return new ArrayList<UnitEntity>(query.getResultList());
    }
    
    @Override
    public ArrayList<OutsourcingEntity> getOutsourceRequest(Long tenantid) {
        Query query = em.createQuery("SELECT u FROM OutsourcingEntity u WHERE u.tenantId = :inTenantId");
        query.setParameter("inTenantId", tenantid);
    
        ArrayList<OutsourcingEntity> outsourcerequest = new ArrayList<OutsourcingEntity>(query.getResultList());
        
        return outsourcerequest;
    }
    
    public ArrayList<RentRenewalRequestEntity> getRenewalList(Long tenantid) {
        Query query = em.createQuery("SELECT u FROM RentRenewalRequestEntity u WHERE u.tenantID = :inTenantId");
        query.setParameter("inTenantId", tenantid);
    
        ArrayList<RentRenewalRequestEntity> renewalList = new ArrayList<RentRenewalRequestEntity>(query.getResultList());
        
        return renewalList;
    }
    
    public String submitOutsourcingRequest(Long tenantID, String tenantEmail, String mallName, String unitNumber, String contractorName, String description, int numStaff, Timestamp outsourcingDate, Timestamp oServicingStartDate, Timestamp oServicingEndDate, String docFileLink){
        OutsourcingEntity outsourceRequest = new OutsourcingEntity(tenantID,  tenantEmail, unitNumber, 
                contractorName, numStaff,  outsourcingDate,  description, oServicingStartDate,
                oServicingEndDate, docFileLink, mallName);
        em.persist(outsourceRequest);
        return "Request Submitted! Our staff will contact you shortly";
    }

    @Override
    public String submitRentRenewalRequest(Long tenantID, String name, String mall, String unit,String description) {
        RentRenewalRequestEntity renewalrequest = new RentRenewalRequestEntity(tenantID, name, mall, unit, description); 
        em.persist(renewalrequest);
        return "Request Submitted! Our staff will contact you shortly";
    }
    
    @Override 
    public ArrayList<RentRenewalRequestEntity> getRentReqList(String mallName){
        Query q = em.createQuery("SELECT re FROM RentRenewalRequestEntity re WHERE "
                + "re.mall= :inMallName AND re.status= :inStatus");
        q.setParameter("inMallName", mallName);
        q.setParameter("inStatus", "Pending");
        return new ArrayList<RentRenewalRequestEntity>(q.getResultList());
    }
    
    @Override
    public void changeRentReqStatus(Long reqId, String status){
        RentRenewalRequestEntity req = em.find(RentRenewalRequestEntity.class, reqId);
        req.setStatus(status);
        em.merge(req);
        em.flush();
    }
}
