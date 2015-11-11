/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.facility;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.ServiceEntity;
import mms.facility.session.ServiceManagerSessionLocal;

/**
 *
 * @author linjiao_Zoe
 */
public class ServiceManager {

    ServiceManagerSessionLocal serviceManagerSessionLocal;

    public ServiceManager(ServiceManagerSessionLocal serviceManagerSessionLocal) {
        this.serviceManagerSessionLocal = serviceManagerSessionLocal;
    }

    public ServiceEntity createNewServiceRequest(HttpServletRequest request) {
        String serviceType = request.getParameter("serviceType");
        String serviceRequestStatus = request.getParameter("serviceRequestStatus");
        Timestamp serviceRequestDate = new Timestamp(System.currentTimeMillis());
        String serviceRequestDetail = request.getParameter("serviceRequestDetail");
        String servicingStartDateString = request.getParameter("servicingStartDate") + " 00:00:00";
        Timestamp servicingStartDate = Timestamp.valueOf(servicingStartDateString);
        String servicingEndDateString = request.getParameter("servicingEndDate") + " 00:00:00";
        Timestamp servicingEndDate = Timestamp.valueOf(servicingEndDateString);
        double serviceFee = Double.parseDouble(request.getParameter("serviceFee"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        Long facilityId = Long.parseLong(request.getParameter("facilityId"));
        return serviceManagerSessionLocal.addService(serviceType, serviceRequestDetail,
            servicingStartDate, servicingEndDate, serviceFee, mallName, facilityId);
    }

    public ServiceEntity getServiceRequest(HttpServletRequest request) {
        Long serviceId = Long.parseLong(request.getParameter("serviceId"));
        return serviceManagerSessionLocal.getServiceRequest(serviceId);
    }

    public ServiceEntity editServiceRequest(HttpServletRequest request) {
        Long serviceId = (long) request.getSession().getAttribute("serviceId");
        request.removeAttribute("serviceId");
        String serviceType = request.getParameter("serviceType");
        String serviceRequestStatus = request.getParameter("serviceRequestStatus");
        String serviceRequestDetail = request.getParameter("serviceRequestDetail");
        String servicingStartDateString = request.getParameter("servicingStartDate") + " 00:00:00";
        Timestamp servicingStartDate = Timestamp.valueOf(servicingStartDateString);
        String servicingEndDateString = request.getParameter("servicingEndDate") + " 00:00:00";
        Timestamp servicingEndDate = Timestamp.valueOf(servicingEndDateString);
        double serviceFee = Double.parseDouble(request.getParameter("serviceFee"));
        return serviceManagerSessionLocal.editServiceRequest(serviceId, serviceType, serviceRequestStatus,
                 serviceRequestDetail, servicingStartDate, servicingEndDate, serviceFee);
    }

    public void deleteServiceRequest(HttpServletRequest request) {
        Long serviceId = Long.parseLong(request.getParameter("serviceId"));
        serviceManagerSessionLocal.deleteServiceRequest(serviceId);
    }
}
