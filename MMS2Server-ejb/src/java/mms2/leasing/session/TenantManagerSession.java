/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.LongTermApplicationEntity;
import mms.leasing.entity.UnitEntity;
import mms.leasing.entity.TenantContractEntity;
import mms.leasing.entity.TenantEntity;
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
public class TenantManagerSession implements TenantManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    public TenantManagerSession() {
    }

    @Override
    public TenantEntity createTenant(String mallName, String name, String businessType, String descriptionString,
            String address, String email, String tel) {
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
        TenantEntity tenant = new TenantEntity(mallName, name, businessType, descriptionStringList, address, email, tel);
        em.persist(tenant);
        return tenant;
    }

    @Override
    public TenantEntity getTenantById(Long id) {
        return em.find(TenantEntity.class, id);
    }

    @Override
    public ArrayList<TenantEntity> getAllTenants(String mallName) {
        Query query = em.createQuery("SELECT t FROM TenantEntity t WHERE t.mallName=:inMallName");
        query.setParameter("inMallName", mallName);
        ArrayList<TenantEntity> allTenantList = new ArrayList<TenantEntity>();
        for (Object o : query.getResultList()) {
            TenantEntity t = (TenantEntity) o;
            allTenantList.add(t);
        }
        return allTenantList;
    }

    @Override
    public void linkTenantToUnit(TenantEntity tenant, UnitEntity unit) {
        tenant.getUnits().add(unit);
        unit.setHasTenant(true);
        unit.setTenant(tenant);
        em.merge(tenant);
        em.merge(unit);
    }

    @Override
    public void linkTenantToContract(TenantEntity tenant, TenantContractEntity tenantContract) {
        tenant.setTenantContract(tenantContract);
        tenantContract.setTenant(tenant);
        em.merge(tenant);
        em.merge(tenantContract);
    }

    @Override
    public void createTenantAndContractForApprovedLongTermApplication(Long applicationId) {
        LongTermApplicationEntity longTermApplication
                = em.find(LongTermApplicationEntity.class, applicationId);
        ArrayList<String> unitListString = longTermApplication.getApplyUnitList();
        String name = longTermApplication.getApplicantName();
        String mallName = longTermApplication.getMallName();
        String businessType = longTermApplication.getApplicantBusinessType();
        ArrayList<String> applicantDescription = longTermApplication.getApplicantDescription();
        String address = longTermApplication.getApplicantAddress();
        String email = longTermApplication.getApplicantEmail();
        String tel = longTermApplication.getApplicantTel();
        double bidRate = longTermApplication.getApplicantBidRate();
        Timestamp startDate = longTermApplication.getContractStart();
        Timestamp endDate = longTermApplication.getContractEnd();
        double deposit = longTermApplication.getContractDeposit();
        ArrayList<String> contractDescription = longTermApplication.getContractDescription();
        TenantEntity newTenant = new TenantEntity(mallName, name, businessType,
                applicantDescription, address, email, tel);

        em.persist(newTenant);
        ArrayList<UnitEntity> applyUnitList = new ArrayList();
        for (int i = 0; i < unitListString.size(); i++) {
            String locationCode = unitListString.get(i);
            Query query = em.createQuery("SELECT u FROM UnitEntity u "
                    + "WHERE u.locationCode = :inLocationCode "
                    + "AND u.mallName = :inMallName");
            query.setParameter("inLocationCode", locationCode);
            query.setParameter("inMallName", mallName);
            UnitEntity unit = (UnitEntity) (query.getResultList().get(0));
            unit.setTenant(newTenant);
            em.merge(unit);
            em.flush();
            applyUnitList.add(unit);
        }
        TenantContractEntity contract = new TenantContractEntity(startDate, endDate, 
                deposit, bidRate);
        contract.setContractDescription(contractDescription);
        em.persist(contract);
        contract.setTenant(newTenant);
        newTenant.setUnits(applyUnitList);
        newTenant.setTenantContract(contract);
        em.merge(newTenant);
        em.merge(contract);
    }
}
