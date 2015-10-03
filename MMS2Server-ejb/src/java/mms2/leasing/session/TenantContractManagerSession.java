/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.UnitEntity;
import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;
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
public class TenantContractManagerSession implements TenantContractManagerSessionLocal {
    @PersistenceContext
    private EntityManager em;
    

    public TenantContractManagerSession() {
    }
    
    @Override
    public TenantContractEntity createTenantContract(Timestamp startDate, Timestamp endDate, double deposit, 
            double rate){
        TenantContractEntity tenantContract = new TenantContractEntity(startDate, endDate, deposit, rate);
        em.persist(tenantContract);
        return tenantContract;
    }
     @Override
    public TenantContractEntity getTenantContractById(Long id) {
        return em.find(TenantContractEntity.class, id);
    }
}