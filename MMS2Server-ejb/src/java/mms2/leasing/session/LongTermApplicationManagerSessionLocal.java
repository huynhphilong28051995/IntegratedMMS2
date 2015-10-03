/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.LongTermApplicationEntity;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface LongTermApplicationManagerSessionLocal {

    public void createLongTermApplication(String mallName, 
            String applicantName,String businessType, ArrayList<String> applicantDescription,
            String applicantAddress, String applicantEmail, String applicantTel,
            ArrayList<String> applyUnitList, double applicantBidRate);

    public ArrayList<LongTermApplicationEntity> getLongTermApplicationList(String mallName);

    public void addContractToLongTermApplication(Long applicantId, String contractStartString, String contractEndString, String depositString, String description);

    public LongTermApplicationEntity getLongTermApplicationById(Long applicationId);

}
