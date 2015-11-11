/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.pos.session;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mms2.pos.entity.ItemEntity;
import mms2.pos.entity.SaleEntity;
import java.lang.NullPointerException;
import java.sql.Timestamp;
import javax.persistence.Query;
import mms2.customer.entity.CustomerEntity;
import mms2.leasing.entity.TenantEntity;
import mms2.leasing.entity.UnitEntity;

/**
 *
 * @author PhiLong
 */
@WebService
@Stateless
public class SaleSessionWS {

    @PersistenceContext
    private EntityManager em;

    @WebMethod
    public String createSale(@WebParam(name = "sale") SaleEntity sale) {
        try {
            TenantEntity tenant = em.find(TenantEntity.class, sale.getTenantId());
           String category  = (new ArrayList<UnitEntity>(tenant.getUnits())).get(0).getCategory();
           sale.setBusinessType(category);
            ArrayList<Long> itemIdList = sale.getItemIdList();
            for (int i = 0; i < itemIdList.size(); i++) {
                ItemEntity item = em.find(ItemEntity.class, itemIdList.get(i));
                if (item.getQuantity() == 0) {
                    throw new NullPointerException();
                }
            }
            for (int i = 0; i < itemIdList.size(); i++) {
                ItemEntity item = em.find(ItemEntity.class, itemIdList.get(i));
                item.setQuantity(item.getQuantity() - 1);
                em.merge(item);
            }
            //SET TIME STAMP
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            sale.setDate(currentTime);
            //CALCULATE POINT
            if (!sale.getCustomerEmail().isEmpty()) {
                Query q = em.createQuery("SELECT cu FROM CustomerEntity cu WHERE cu.email = :inEmail");
                q.setParameter("inEmail", sale.getCustomerEmail());
                CustomerEntity customer = (CustomerEntity)q.getResultList().get(0);
                String memberType  = customer.getMemberType();
                int roundDown =  (int)  Math.floor(sale.getPrice());
                int pointToAdd = 0;
                if(memberType.equals("silver")){
                    pointToAdd = roundDown;
                }else if(memberType.equals("gold")){
                    pointToAdd = roundDown*2;
                }else{
                    pointToAdd = roundDown*3;
                }
                customer.setPoints(pointToAdd+customer.getPoints());
                sale.setPointsEarned(pointToAdd);
                em.merge(customer);
            }
            em.persist(sale);
            em.flush();
            return sale.getId().toString();
        } catch (Exception ex) {
            System.err.println("SaleSessionWS_createSale throw exception");
            ex.printStackTrace();
            return "Error";
        }
    }
}
