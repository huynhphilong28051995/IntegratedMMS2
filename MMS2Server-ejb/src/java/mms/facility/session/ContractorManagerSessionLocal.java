/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
import mms.facility.entity.ContractorEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Local
public interface ContractorManagerSessionLocal {
    public ContractorEntity addContractor(String contractorName, String companyName, String serviceType, 
            String contractorTel, String contractEmail, Timestamp contractStartDate, 
            Timestamp contractEndDate,  String mallName);

    public ArrayList<ContractorEntity> listContractor(String mallName);   

    public void deleteContractor(Long contractorId);

    public ContractorEntity getContractor(Long contractorId);

    public ContractorEntity editContractor(Long contractorId, String contractorName, String serviceType, 
            Timestamp contractStartDate, Timestamp contractEndDate,String contractorTel, 
            String contractEmail);

    public boolean verifyContractor(String contractorName, String mallName);   
}
