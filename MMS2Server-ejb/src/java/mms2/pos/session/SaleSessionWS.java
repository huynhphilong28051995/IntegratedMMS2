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
    public String createSale(@WebParam(name="sale")SaleEntity sale){
        try{
            ArrayList<Long> itemIdList =  sale.getItemIdList();
            for(int i=0; i< itemIdList.size(); i++){
                ItemEntity item = em.find(ItemEntity.class, itemIdList.get(i));
                if(item.getQuantity()==0)
                    throw new NullPointerException();
            }
            for(int i=0; i< itemIdList.size(); i++){
                ItemEntity item = em.find(ItemEntity.class, itemIdList.get(i));
                item.setQuantity(item.getQuantity()-1);
                em.merge(item);
            }
            em.persist(sale);
            em.flush();
            return sale.getId().toString();
        }catch(Exception ex){
            System.err.println("SaleSessionWS_createSale throw exception");
            ex.printStackTrace();
            return "Error";
        }
    }
}
