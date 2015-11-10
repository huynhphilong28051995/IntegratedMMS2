/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.tenant.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
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
@Local
public interface TenantManagerBeanLocal {

    public boolean verifyLogin(String email, String password);

    public TenantEntity getTenant(String email);
    
    public TenantContractEntity getContract(Long contractid);
    
    public ArrayList<OutsourcingEntity> getOutsourceRequest(Long tenantid);
    
    public ArrayList<RentRenewalRequestEntity> getRenewalList(Long tenantid);
    
    public ArrayList<UnitEntity> getUnits(Long tenantid);
    
    public ArrayList<SaleEntity> getSaleList(Long tenantid);
    
    public ArrayList<RentInvoiceEntity> getInvoices(Long tenantid);

    public String submitOutsourcingRequest(Long tenantID, String tenantEmail, String mallName, String unitNumber, String contractorName, String description, int numStaff, Timestamp outsourcingDate, Timestamp oServicingStartDate, Timestamp oServicingEndDate, String docFileLink);
    
    public String submitRentRenewalRequest(Long tenantID, String name, String mall, String unit, String description);

    public ArrayList<RentRenewalRequestEntity> getRentReqList(String mallName);

    public void changeRentReqStatus(Long reqId, String status);

}
