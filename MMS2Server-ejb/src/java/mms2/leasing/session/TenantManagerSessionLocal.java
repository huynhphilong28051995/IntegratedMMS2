/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.UnitEntity;
import mms.leasing.entity.TenantContractEntity;
import mms.leasing.entity.TenantEntity;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface TenantManagerSessionLocal {

    public TenantEntity createTenant(String mallName, String name, String businessType, String descriptionString, String address, String email, String tel);

    public void linkTenantToContract(TenantEntity tenant, TenantContractEntity tenantContract);

    public TenantEntity getTenantById(Long id);

    public ArrayList<TenantEntity> getAllTenants(String mallName);

    public void linkTenantToUnit(TenantEntity tenant, UnitEntity unit);

    public void createTenantAndContractForApprovedLongTermApplication(Long applicationId);

}
