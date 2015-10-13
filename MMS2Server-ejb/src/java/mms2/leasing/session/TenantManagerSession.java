/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import java.math.BigInteger;
import java.security.MessageDigest;
import mms2.leasing.entity.LongTermApplicationEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mms2.leasing.entity.LeasingSystemRequestEntity;

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
        Query query = em.createQuery("SELECT t FROM TenantEntity t WHERE t.mallName=:inMallName "
                + "AND t.status = :inStatus");
        query.setParameter("inMallName", mallName);
        query.setParameter("inStatus", "Official");
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
        newTenant.setPendingUnitList(unitListString);
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
            unit.setOpenForPublicBidding(false);
            unit.setOpenForPublicBiddingPrototype(false);
            unit.setHasPendingTenant(true);
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

    @Override
    public ArrayList<Object[]> getExpiringTenant(String mallName) {
        Query query = em.createQuery("SELECT t FROM TenantEntity t "
                + "WHERE t.mallName = :inMallName");
        query.setParameter("inMallName", mallName);
        ArrayList<Object[]> returnList = new ArrayList();

        for (Object o : query.getResultList()) {
            TenantEntity tenant = (TenantEntity) o;
            Long contractEnd = tenant.getTenantContract().getEndTimestamp().getTime();
            long currentDate = (new java.util.Date()).getTime();
            int difference = (int) ((((contractEnd - currentDate) / 1000) / 86400) / 30);
            if (difference <= 6) {
                Object[] objArr = new Object[2];
                objArr[0] = tenant;
                objArr[1] = difference;
                returnList.add(objArr);
            }
        }
        return returnList;
    }

    @Override
    public int sendContractRenewalEmail(long tenantId) {
        TenantEntity tenant = em.find(TenantEntity.class, tenantId);
        SendMail_LeaseRenewal mail = new SendMail_LeaseRenewal();
        return mail.sendMail(tenant);
    }

    @Override
    public ArrayList<TenantEntity> getAllPendingTenant(String mallName) {
        try {
            Query query = em.createQuery("SELECT t FROM TenantEntity t "
                    + "WHERE t.mallName =:inMallName AND t.status = :inStatus");
            query.setParameter("inMallName", mallName);
            query.setParameter("inStatus", "Pending");
            return new ArrayList<TenantEntity>(query.getResultList());
        } catch (Exception e) {
            System.err.println("TenantManagerSession_getAllPendingTenant throws exception");
            e.printStackTrace();
            return new ArrayList<TenantEntity>();
        }
    }

    @Override
    public String officializePendingTenant(long tenantId) {
        try {
            TenantEntity tenant = em.find(TenantEntity.class, tenantId);
            String mallName = tenant.getMallName();
            ArrayList<String> unitList = tenant.getPendingUnitList();
            for (int i = 0; i < unitList.size(); i++) {
                String locationCode = unitList.get(i);
                Query query = em.createQuery("SELECT u FROM UnitEntity u "
                        + "WHERE u.mallName = :inMallName AND u.locationCode = :inLocationCode");
                query.setParameter("inMallName", mallName);
                query.setParameter("inLocationCode", locationCode);
                UnitEntity unit = (UnitEntity) query.getResultList().get(0);
                if (unit.isHasTenant()) {
                    return "Unsuccessful ! Unit(s) is still occupied";
                }
            }
            for (int i = 0; i < unitList.size(); i++) {
                String locationCode = unitList.get(i);
                Query query = em.createQuery("SELECT u FROM UnitEntity u "
                        + "WHERE u.mallName = :inMallName AND u.locationCode = :inLocationCode");
                query.setParameter("inMallName", mallName);
                query.setParameter("inLocationCode", locationCode);
                UnitEntity unit = (UnitEntity) query.getResultList().get(0);
                unit.setTenant(tenant);
                unit.setHasTenant(true);
                unit.setHasPendingTenant(false);
                em.merge(unit);
                tenant.getUnits().add(unit);
            }
            tenant.setStatus("Official");
            String password = generatePassword();
            String encryptedPassword = encryptPassword(password);
            tenant.setPassword(encryptedPassword);
            em.merge(tenant);
            SendMail_OfficializeTenant mail  = new SendMail_OfficializeTenant();
            mail.sendMail(tenant, password);
            return "Successful ! Pending tenant is now official";
        } catch (Exception ex) {
            System.err.println("TenantManagerSession__officializePendingTenant throws exception");
            ex.printStackTrace();
            return "Unsuccessful ! TenantManagerSession__officializePendingTenant throws exception";
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
    private String generatePassword() {
        //Initialize Variables for Random Password Generator
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbols = "!@#$%^&*";
        String integers = "0123456789";
        Random r = new Random();
        String password = "";

        while (password.length() != 8) {
            int rPick = r.nextInt(4);
            if (rPick == 0) {
                int spot = r.nextInt(25);
                password += lowerCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt(25);
                password += upperCase.charAt(spot);
            } else if (rPick == 2) {
                int spot = r.nextInt(7);
                password += symbols.charAt(spot);
            } else if (rPick == 3) {
                int spot = r.nextInt(9);
                password += integers.charAt(spot);
            }
        }
        return password;
    }
    
    @Override
    public String deleteExpireTenant(long tenantId){
        try{
            TenantEntity tenant = em.find(TenantEntity.class, tenantId);
            ArrayList<UnitEntity> units = new ArrayList<UnitEntity>(tenant.getUnits());
            for(int i=0; i<units.size(); i++){
                UnitEntity unit = units.get(i);
                unit.setTenant(null);
                unit.setHasTenant(false);
                em.merge(unit);
            }
            TenantContractEntity contract = tenant.getTenantContract();
            contract.setTenant(null);
            tenant.setUnits(new ArrayList<UnitEntity>());
            em.merge(tenant);
            em.merge(contract);
            em.remove(contract);
            em.remove(tenant);
            return "Successful ! The record has successfully been deleted";
        }catch(Exception e){
            System.err.println("TenantManagerSession_deleteExpireTenant throw exception");
            e.printStackTrace();
            return "Unsuccessful ! There has been error while trying to delete this record";
        }
    }
    
    @Override
    public void acceptContractRenewRequest(long leasingRequestId){
        LeasingSystemRequestEntity leaseRequest = em.find(LeasingSystemRequestEntity.class, leasingRequestId);
        long tenantId = leaseRequest.getApplicationId();
        TenantEntity tenant = em.find(TenantEntity.class, tenantId);
        TenantContractEntity contract = tenant.getTenantContract();
        ArrayList<String> newDescription = leaseRequest.getRenewDescription();
        Timestamp newStart  = leaseRequest.getRenewStartDate();
        Timestamp newEnd = leaseRequest.getRenewEndDate();
        double newRate = leaseRequest.getRenewRate();
        double newDeposit = leaseRequest.getRenewDeposit();
        contract.setContractDescription(newDescription);
        contract.setStartTimestamp(newStart);
        contract.setEndTimestamp(newEnd);
        contract.setRate(newRate);
        contract.setDeposit(newDeposit);
        leaseRequest.setStatus("ACCEPTED");
        em.merge(contract);
        em.flush();
        em.merge(tenant);
        em.flush();
    }
}
