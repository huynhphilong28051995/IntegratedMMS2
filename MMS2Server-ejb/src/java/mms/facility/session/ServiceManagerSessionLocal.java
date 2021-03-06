/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
import mms.facility.entity.ServiceEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Local
public interface ServiceManagerSessionLocal {

    public ServiceEntity addService(String serviceType, String serviceRequestDetail,
            Timestamp servicingStartDate, Timestamp servicingEndDate, double serviceFee, String mallName, Long faciityId);

    public ArrayList<ServiceEntity> listServiceRequest(String mallName);

    public ServiceEntity getServiceRequest(Long serviceId);

    public ServiceEntity editServiceRequest(Long serviceId, String serviceType, String serviceRequestStatus,
            String serviceRequestDetail, Timestamp servicingStartDate, Timestamp servicingEndDate,
            double serviceFee);

    public void deleteServiceRequest(Long serviceId);
}
