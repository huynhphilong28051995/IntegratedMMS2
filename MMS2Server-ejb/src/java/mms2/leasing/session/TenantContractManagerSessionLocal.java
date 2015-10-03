/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface TenantContractManagerSessionLocal {

  
    public TenantContractEntity getTenantContractById(Long id);
   
    public TenantContractEntity createTenantContract(Timestamp startDate, Timestamp endDate, double deposit, double rate);
    
}
