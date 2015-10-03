/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import mms2.leasing.entity.LongTermApplicationEntity;
import mms2.leasing.session.LongTermApplicationManagerSessionLocal;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PhiLong
 */
public class LongTermApplicationManager {

    LongTermApplicationManagerSessionLocal longTermApplicationManagerSessionLocal;

    public LongTermApplicationManager(LongTermApplicationManagerSessionLocal longTermApplicationManagerSessionLocal) {
        this.longTermApplicationManagerSessionLocal = longTermApplicationManagerSessionLocal;
    }

    public void createLongTermApplication(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        String applicantName = (String) request.getSession().getAttribute("applicantName");
        String applicantBusinessType = (String) request.getSession().getAttribute("applicantBusinessType");
        String applicantAddress = (String) request.getSession().getAttribute("applicantAddress");
        String applicantEmail = (String) request.getSession().getAttribute("applicantEmail");
        String applicantTel = (String) request.getSession().getAttribute("applicantTel");
        double applicantBidRate = Double.parseDouble(
                (String) request.getSession().getAttribute("applicantBidRate"));
        ArrayList<String> applyUnitList = (ArrayList<String>) request.getSession().getAttribute("applyUnitList");
        String applicantDescriptionString = (String) request.getSession().getAttribute("applicantDescription");
        ArrayList<String> applicantDescriptionStringList = new ArrayList<String>();
        while(applicantDescriptionString.length()>0){
            int length=applicantDescriptionString.length();
            if(length > 255){
                String tempString = applicantDescriptionString.substring(0,255);
                applicantDescriptionStringList.add(tempString);
                applicantDescriptionString = applicantDescriptionString.substring(255, length);
            }
            else{
                applicantDescriptionStringList.add(applicantDescriptionString);
                applicantDescriptionString="";
            }
        }
        longTermApplicationManagerSessionLocal.createLongTermApplication(mallName, applicantName,
                applicantBusinessType,applicantDescriptionStringList, applicantAddress,
                applicantEmail, applicantTel, applyUnitList, applicantBidRate);
    }
    public ArrayList<LongTermApplicationEntity> getLongTermApplicationList(HttpServletRequest request){
        String mallName = (String)request.getSession().getAttribute("mallName");
        return longTermApplicationManagerSessionLocal.getLongTermApplicationList(mallName);
    }
    public void addContractToLongTermApplication(HttpServletRequest request){
        Long applicantId = (Long)request.getSession().getAttribute("applicantId");
        String contractStart  = request.getParameter("contractStart");
        String contractEnd = request.getParameter("contractEnd");
        String deposit = request.getParameter("contractDeposit");
        String description = request.getParameter("contractDescription");
        longTermApplicationManagerSessionLocal.addContractToLongTermApplication(applicantId,
                contractStart, contractEnd, deposit, description);
    }
    public LongTermApplicationEntity getLongTermApplicationById(Long applicationId){
        return longTermApplicationManagerSessionLocal.getLongTermApplicationById(applicationId);
    }
}
