/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.commerce.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PhiLong
 */
@Stateless
public class CustomerCommerceSession implements CustomerCommerceSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean login(String email, String password) {
        try {
            Query q = em.createQuery("SELECT cu FROM CustomerEntity cu WHERE cu.email= :inEmail AND cu.password= :inPassword");
            q.setParameter("inEmail", email);
            q.setParameter("inPassword", password);
            return !q.getResultList().isEmpty();
        } catch (Exception ex) {
            System.err.println("CustomerCommerceSession_login throw exception");
            return false;
        }
    }
}
