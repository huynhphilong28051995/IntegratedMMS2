/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.pos.session;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms2.leasing.entity.TenantEntity;
import mms2.leasing.entity.UnitEntity;

/**
 *
 * @author PhiLong
 */
@WebService
@Stateless
public class TenantSessionWS {
    @PersistenceContext
    private EntityManager em;
    
    public TenantSessionWS(){    
    }
    
    @WebMethod
    public boolean tenantLogin(@WebParam(name = "email")String email, @WebParam(name="password")String password) {
        String encryptPassword = encryptPassword(password);
        Query q = em.createQuery("SELECT te FROM TenantEntity te "
                + "WHERE te.email= :inEmail AND te.password= :inPassword");
        q.setParameter("inEmail", email);
        q.setParameter("inPassword", encryptPassword);
        return !q.getResultList().isEmpty();
    }
    
    @WebMethod
    public TenantEntity getTenantByEmail(@WebParam(name="email")String email){
        Query q = em.createQuery("SELECT te.id FROM TenantEntity te "
                + "WHERE te.email = :inEmail");
        q.setParameter("inEmail", email);
        Long tenantId  = (Long)q.getResultList().get(0);
        TenantEntity tenant  =em.find(TenantEntity.class, tenantId);
        em.detach(tenant);
        tenant.getTenantContract().setTenant(null);
        ArrayList<UnitEntity> units = new ArrayList<UnitEntity>(tenant.getUnits());
        for(int i=0; i< units.size(); i++){
            UnitEntity unit = units.get(i);
            em.detach(unit);
            unit.setTenant(null);
            unit.setLevel(null);
        }
        return tenant;
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
}
