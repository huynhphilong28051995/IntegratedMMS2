/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;


import java.util.ArrayList;
import javax.ejb.Local;
import mms.facility.entity.FeedbackEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Local
public interface FeedbackManagerSessionLocal {

    public ArrayList<FeedbackEntity> listFeedback(String mallName);

    public FeedbackEntity getFeedbacks(Long feedbackId);

    public FeedbackEntity updateStatus(Long feedbackId, String status);
    
    public void deleteFeedback(Long feedbackId);        
}
