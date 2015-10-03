/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.TenantApplicantEntity;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface TenantApplicantManagerSessionLocal {

    public TenantApplicantEntity createTenantApplicant(String mallName, String name, String businessType, String descriptionString, String address, String email, String tel);
    
}
