/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.LeasingSystemRequestEntity;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface LeasingSystemRequestManagerSessionLocal {

    public void updateLeasingRequestStatus(Long leasingRequestId, String status);

    public ArrayList<LeasingSystemRequestEntity> getListOfAllPendingLeasingRequests(String mallName);

    public void addFloorplanRequest(String staffUserName, String mallName, String descriptionString);

    public void addCategoryRequest(String staffUserName, String mallName, String descriptionString);

    public void addPublicOpenBidRequest(String staffUserName, String mallName, String descriptionString, ArrayList<String> listOfUnitOpenForBidding);

    public LeasingSystemRequestEntity getLeasingRequestById(Long requestId);

    public void deleteLeasingRequestById(Long requestId);

    public void addPublicApplicationApprovalRequest(String staffUserName, String mallName, Long applicantId, String descriptionString);

    public void deleteAllCollideRequestAndApplication(Long requestId, Long applicationId);

    public ArrayList<LeasingSystemRequestEntity> getRequestsByUserName(String userName);
    
}
