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
import javax.persistence.Query;
import mms2.pos.entity.ItemEntity;

/**
 *
 * @author PhiLong
 */
@WebService
@Stateless
public class ItemSessionWS {
    @PersistenceContext
    private EntityManager em;

    public ItemSessionWS() {
    }

    @WebMethod
    public ArrayList<ItemEntity> getAllItemByTenantId(@WebParam(name="tenantId")Long tenantId) {
        Query q = em.createQuery("SELECT i FROM ItemEntity i WHERE i.tenantId = :inTenantId");
        q.setParameter("inTenantId", tenantId);
        return new ArrayList<ItemEntity>(q.getResultList());
    }
    
    @WebMethod
    public String addItemStockByTenantId(@WebParam(name="item")ItemEntity item){
        try{
            em.persist(item);
            return String.valueOf(item.getId());
        }catch(Exception ex){
            System.err.println("ItemSessionWS_getAllItemByTenant throw exception");
            ex.printStackTrace();
            return "Error";
        }
    }
    
    @WebMethod
    public boolean deleteItem(@WebParam(name="itemId")Long itemId){
        try{
            ItemEntity item =  em.find(ItemEntity.class, itemId);
            em.remove(item);
            return true;
        }catch(Exception ex){
            System.err.println("ItemSessionWS_deleteItem throw exception");
            ex.printStackTrace();
            return false;
        }
    }
    @WebMethod
    public ItemEntity getItemByTenantIdAndCode(@WebParam(name="tenantId")Long tenantId,
            @WebParam(name="itemCode") String itemCode){
        try{
        Query q = em.createQuery("SELECT i FROM ItemEntity i WHERE "
                + "i.tenantId = :inId AND i.itemCode = :inCode");
        q.setParameter("inId", tenantId);
        q.setParameter("inCode", itemCode);
        return (ItemEntity)q.getResultList().get(0);
        }catch(Exception ex){
            System.err.println("ItemSessionWS_getItemByTenantIdAndCode throw exception");
            ex.printStackTrace();
            return null;
        }
    }
}
