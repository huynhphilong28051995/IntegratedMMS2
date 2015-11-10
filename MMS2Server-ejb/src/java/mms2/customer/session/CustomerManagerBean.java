/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.customer.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;
import java.util.*;

import java.sql.Timestamp;
import mms2.customer.entity.CustomerEntity;
import mms2.customer.entity.GiftEntity;
import mms2.customer.entity.RedemptionHistoryEntity;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
@Stateless
public class CustomerManagerBean implements CustomerManagerBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public CustomerManagerBean() {
    }

    @Override
    public CustomerEntity getCustomer(String email) {
        Query query = em.createQuery("SELECT u FROM CustomerEntity u WHERE u.email = :inEmail");
        query.setParameter("inEmail", email);

        CustomerEntity customer = (CustomerEntity) query.getResultList().get(0);

        return customer;
    }

    public boolean verifyLogin(String email, String password) {
        //query or em.find to retrieve data from database
        Query query = em.createQuery("SELECT u FROM CustomerEntity u  "
                + "WHERE u.email=:inUsername AND u.password=:inPassword");
        query.setParameter("inUsername", email);
        query.setParameter("inPassword", password);
        List<CustomerEntity> users = (List<CustomerEntity>) query.getResultList();
        if (users.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String changePassword(String email, String oldpassword, String newPassword1, String newPassword2) {
        //query or em.find to retrieve data from database
        System.out.println("REACH 1");
        Query query = em.createQuery("SELECT u FROM CustomerEntity u  "
                + "WHERE u.email=:inUsername AND u.password=:inPassword");
        query.setParameter("inUsername", email);
        query.setParameter("inPassword", oldpassword);
        List<CustomerEntity> users = (List<CustomerEntity>) query.getResultList();
        if (users.size() == 0) {
            return "Wrong Password Entered";
        }
        if (newPassword1.equals(newPassword2)) {
            CustomerEntity customer = users.get(0);
            customer.setPassword(newPassword1);
            em.merge(customer);
            return "Password successfully changed";
        } else {
            return "New Passwords do not match!";
        }

    }

    @Override
    public String changeAddress(String email, String address, String newaddress) {
        //query or em.find to retrieve data from database
        Query query = em.createQuery("SELECT u FROM CustomerEntity u  "
                + "WHERE u.email=:inUsername AND u.address=:inAddress");
        query.setParameter("inUsername", email);
        query.setParameter("inAddress", address);
        List<CustomerEntity> users = (List<CustomerEntity>) query.getResultList();
        if (users.size() == 0) {
            return "Entered wrong address";
        }
        CustomerEntity customer = users.get(0);
        customer.setAddress(newaddress);
        em.merge(customer);
        return "Address Successfully changed!";
    }

    public String changeNumber(String email, String oldnumber, String newnumber) {
        //query or em.find to retrieve data from database
        Query query = em.createQuery("SELECT u FROM CustomerEntity u  "
                + "WHERE u.email=:inUsername AND u.telephone=:inTelephone");
        query.setParameter("inUsername", email);
        query.setParameter("inTelephone", oldnumber);
        System.out.println("email" + email + "telephone" + oldnumber);
        List<CustomerEntity> users = (List<CustomerEntity>) query.getResultList();
        if (users.size() == 0) {
            return "Entered wrong number";
        }
        CustomerEntity customer = users.get(0);
        customer.setTelephone(newnumber);
        em.merge(customer);
        return "Number successfully changed!";
    }

    @Override
    public List<GiftEntity> getGifts(String country) {
        Query query = em.createQuery("SELECT u FROM GiftEntity u  "
                    + "WHERE u.country=:inCountry");
            query.setParameter("inCountry", country);
        List<GiftEntity> gifts = (List<GiftEntity>) query.getResultList();
        return gifts;
    }

    @Override
    public String redeemGift(Long giftID, String email) {
        try {
            GiftEntity gift = em.find(GiftEntity.class, giftID);
            String giftname = gift.getName();
            Query query = em.createQuery("SELECT u FROM CustomerEntity u  "
                    + "WHERE u.email=:inUsername");
            query.setParameter("inUsername", email);
            List<CustomerEntity> users = (List<CustomerEntity>) query.getResultList();
            CustomerEntity user = users.get(0);
            String country = user.getCountry();
            Timestamp dateRedeemed = new Timestamp(System.currentTimeMillis());
            String address = user.getAddress();
            int userpoints = user.getPoints();
            int giftpoints = gift.getPoints();
            int giftquantity = gift.getQuantity();
            if (userpoints > giftpoints) {
                userpoints = userpoints - giftpoints;
                user.setPoints(userpoints);
                em.merge(user);
                giftquantity = giftquantity - 1;
                gift.setQuantity(giftquantity);
                em.merge(gift);
                RedemptionHistoryEntity record = new RedemptionHistoryEntity();
                record.setGiftName(giftname);
                record.setCountry(country);
                record.setCustomerAddress(address);
                record.setCustomerEmail(email);
                record.setDateRedeemed(dateRedeemed);
                record.setStatus("Processing");
                em.merge(record);                
                return "Successfully redeemed. We will send the gift to your address. Thank you!";
            } else {
                return "Insufficient points!";
            }
        } catch (Exception ex) {
            return "Error accessing database, please try again later";
        }
    }
    
    @Override
    public ArrayList<SaleEntity> getPurchases(String email) {
        Query query = em.createQuery("SELECT u FROM SaleEntity u WHERE u.customerEmail = :inEmail");
        query.setParameter("inEmail", email);

         ArrayList<SaleEntity> purchaseList = new ArrayList<SaleEntity>(query.getResultList());

        return purchaseList;
    }
    
    public ArrayList<RedemptionHistoryEntity> getRedemptionHistory(String email) {
        Query query = em.createQuery("SELECT u FROM RedemptionHistoryEntity u WHERE u.customerEmail = :inEmail");
        query.setParameter("inEmail", email);

         ArrayList<RedemptionHistoryEntity> redemptionHistoryList = new ArrayList<RedemptionHistoryEntity>(query.getResultList());

        return redemptionHistoryList;
    }

}
