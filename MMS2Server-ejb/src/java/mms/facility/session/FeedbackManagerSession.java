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
import mms.facility.entity.FeedbackEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Stateless
public class FeedbackManagerSession implements FeedbackManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;
   
    //List all feedbacks from database
    @Override
    public ArrayList<FeedbackEntity> listFeedback(String mallName) {
        ArrayList<FeedbackEntity> feedbackList = new ArrayList();            
        try {         
            Query q = em.createQuery("SELECT fb FROM FeedbackEntity fb WHERE fb.mallName=:inMallName");
            q.setParameter("inMallName", mallName);
            feedbackList = new ArrayList<FeedbackEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return feedbackList;
    }
    
    //get the status of the existing feedback
    @Override
    public FeedbackEntity getFeedbacks(Long feedbackId){
        ArrayList<FeedbackEntity> feedbackList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT fb FROM FeedbackEntity fb WHERE fb.feedbackId=:inId");
            q.setParameter("inId", feedbackId);
            feedbackList = new ArrayList<FeedbackEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (FeedbackEntity) feedbackList.get(0);
    }
    
    //Update the status of feedback
    @Override
    public FeedbackEntity updateStatus(Long feedbackId, String status){
        FeedbackEntity feedbackEntity = em.find(FeedbackEntity.class, feedbackId);
        feedbackEntity.setStatus(status);
        em.merge(feedbackEntity);
        em.flush();
        return feedbackEntity;
    }
    
    
    //Delete feedback
    @Override
    public void deleteFeedback(Long feedbackId) {
        FeedbackEntity feedbackEntity = em.find(FeedbackEntity.class, feedbackId);
        if (feedbackEntity != null) {           
            em.remove(feedbackEntity);
            System.out.println("Feedback has been successfully deleted!");
        }
        em.flush();
    }
}
