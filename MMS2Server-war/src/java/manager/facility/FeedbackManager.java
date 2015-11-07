/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.facility;

import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.FeedbackEntity;
import mms.facility.session.FeedbackManagerSessionLocal;

/**
 *
 * @author linjiao_Zoe
 */
public class FeedbackManager {
    FeedbackManagerSessionLocal feedbackManagerSessionLocal;

    public FeedbackManager(FeedbackManagerSessionLocal feedbackManagerSessionLocal) {
        this.feedbackManagerSessionLocal = feedbackManagerSessionLocal;
    }
    
    public FeedbackEntity getFeedback(HttpServletRequest request) {
        Long feedbackId = Long.parseLong(request.getParameter("feedbackId"));
        return feedbackManagerSessionLocal.getFeedbacks(feedbackId);
    }
    
    public FeedbackEntity editFeedbackStatus(HttpServletRequest request){
        long feedbackId = (long)request.getSession().getAttribute("feedbackId");
        request.removeAttribute("feedbackId");
        String status = request.getParameter("status");
        return feedbackManagerSessionLocal.updateStatus(feedbackId, status);
    }
    
    public void deleteFeedback(HttpServletRequest request) {
        Long feedbackId = Long.parseLong(request.getParameter("feedbackId"));
        feedbackManagerSessionLocal.deleteFeedback(feedbackId);
    }
}
