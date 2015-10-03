/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import mms2.leasing.entity.LeasingSystemRequestEntity;
import mms2.leasing.session.LeasingSystemRequestManagerSessionLocal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PhiLong
 */
public class LeasingRequestManager {
     LeasingSystemRequestManagerSessionLocal leasingSystemRequestManagerSessionLocal;

    public LeasingRequestManager(LeasingSystemRequestManagerSessionLocal leasingSystemRequestManagerSessionLocal) {
        this.leasingSystemRequestManagerSessionLocal = leasingSystemRequestManagerSessionLocal;
    }
    public void addFloorplanModificationRequest(HttpServletRequest request){
        String mallName = (String) request.getSession().getAttribute("mallName");
        String staffUserName = (String) request.getSession().getAttribute("staffUserName");
        String requestDescription = (String)request.getParameter("requestDescription");
        leasingSystemRequestManagerSessionLocal.addFloorplanRequest(staffUserName, mallName, requestDescription);
    }
    public void addCategoryModificationRequest(HttpServletRequest request){
        String mallName = (String) request.getSession().getAttribute("mallName");
        String staffUserName = (String) request.getSession().getAttribute("staffUserName");
        String requestDescription = (String)request.getParameter("requestDescription");
        leasingSystemRequestManagerSessionLocal.addCategoryRequest(staffUserName, mallName, requestDescription);
    }
    public void addPublicOpenBidRequest(HttpServletRequest request){
        String mallName = (String) request.getSession().getAttribute("mallName");
        String staffUserName = (String) request.getSession().getAttribute("staffUserName");
        String requestDescription = (String)request.getParameter("requestDescription");
        ArrayList<String> unitListToAddTenant  = 
                (ArrayList<String>)request.getSession().getAttribute("unitListToAddTenant");
        leasingSystemRequestManagerSessionLocal.addPublicOpenBidRequest(staffUserName,
                mallName, requestDescription, unitListToAddTenant);
    }
    //only return pending request
    public ArrayList<LeasingSystemRequestEntity> getListOfAllLeasingRequests(HttpServletRequest request){
        String mallName = (String) request.getSession().getAttribute("mallName");
        return leasingSystemRequestManagerSessionLocal.getListOfAllPendingLeasingRequests(mallName);
    }
    public void updateLeasingRequestStatus(Long leasingRequestId, String status){
        leasingSystemRequestManagerSessionLocal.updateLeasingRequestStatus(leasingRequestId, status);
    }
    public LeasingSystemRequestEntity getRequestById(Long leasingRequestId){
        return leasingSystemRequestManagerSessionLocal.getLeasingRequestById(leasingRequestId);
    }
    public void addPublicApplicationApprovalRequest(HttpServletRequest request){
        Long applicantId = (Long)request.getSession().getAttribute("applicantId");
        String mallName = (String) request.getSession().getAttribute("mallName");
        String staffUserName = (String) request.getSession().getAttribute("staffUserName");
        String requestDescription = request.getParameter("requestDescription");
        System.out.println(requestDescription);
        leasingSystemRequestManagerSessionLocal.addPublicApplicationApprovalRequest(
                staffUserName, mallName,applicantId,requestDescription);
    }
    public Long getLongTermApplicationId(HttpServletRequest request){
        Long requestId = Long.parseLong(request.getParameter("leasingRequestId"));
        ArrayList<LeasingSystemRequestEntity> requestList = (ArrayList<LeasingSystemRequestEntity>)
                request.getSession().getAttribute("leasingRequestList");
        for(int i=0; i<requestList.size(); i++){
            LeasingSystemRequestEntity leasingRequest = requestList.get(i);
            if(requestId == leasingRequest.getId())
                return leasingRequest.getApplicationId();
            
        }
        return new Long(0);
    }
    public void deleteAllCollideRequestAndApplication(Long leasingRequestId, Long applicationId){
         leasingSystemRequestManagerSessionLocal.deleteAllCollideRequestAndApplication(
                 leasingRequestId, applicationId);
    }
}
