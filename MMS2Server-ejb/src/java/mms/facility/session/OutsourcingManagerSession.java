/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.facility.entity.OutsourcingEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Stateless
public class OutsourcingManagerSession implements OutsourcingManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;
    
    //List all outsourcing requests from database
    @Override
    public ArrayList<OutsourcingEntity> listOutsourcing(String mallName) {
        ArrayList<OutsourcingEntity> outsourcingList = new ArrayList();
        try {         
            Query q = em.createQuery("SELECT ol FROM OutsourcingEntity ol WHERE ol.mallName=:inMallName");
            q.setParameter("inMallName", mallName);
            outsourcingList = new ArrayList<OutsourcingEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return outsourcingList;
    }
    
    //Get outsourcing by ID
     @Override
    public OutsourcingEntity getOutsourcing(Long outsourcingId) {
        ArrayList<OutsourcingEntity> outsourceList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT oe FROM OutsourcingEntity oe WHERE oe.OutsourcingId=:inId");
            q.setParameter("inId", outsourcingId);
            outsourceList = new ArrayList<OutsourcingEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (OutsourcingEntity) outsourceList.get(0);
    }
    
    //update the status of the outsourcing request
    public OutsourcingEntity updateStatus(Long outsourcingId, String status){
        OutsourcingEntity outsourcingEntity = em.find(OutsourcingEntity.class, outsourcingId);
        outsourcingEntity.setOutsourcingStatus(status);
        em.merge(outsourcingEntity);
        em.flush();
        return outsourcingEntity;
    }
    
    //delete outsourcing request
    @Override
    public void deleteOutsourcing(Long outsourcingId){
        OutsourcingEntity outsourcingEntity = em.find(OutsourcingEntity.class, outsourcingId);
        if (outsourcingEntity != null) {           
            em.remove(outsourcingEntity);
            System.out.println("Outsourcing request has been successfully deleted!");
        }
        em.flush();
    }

}
