/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.TenantApplicantEntity;
import mms.leasing.entity.TenantEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PhiLong
 */
@Stateless
public class TenantApplicantManagerSession implements TenantApplicantManagerSessionLocal {
    @PersistenceContext
    private EntityManager em;

    public TenantApplicantManagerSession() {
    }
    @Override
    public TenantApplicantEntity createTenantApplicant(String mallName, String name, String businessType, String descriptionString,
            String address, String email, String tel) {
        ArrayList<String> descriptionStringList = new ArrayList<String>();
        while(descriptionString.length()>0){
            int length=descriptionString.length();
            if(length > 255){
                String tempString = descriptionString.substring(0,255);
                descriptionStringList.add(tempString);
                descriptionString = descriptionString.substring(255, length);
            }
            else{
                descriptionStringList.add(descriptionString);
                descriptionString="";
            }
        }
        TenantApplicantEntity tenantApplicant = new TenantApplicantEntity(mallName, businessType,name,
                descriptionStringList,address,email,tel);
        em.persist(tenantApplicant);
        return tenantApplicant;
    }   
}
