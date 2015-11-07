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
        String serviceRequestDateString = request.getParameter("serviceRequestDate") + " 00:00:00";
        Timestamp serviceRequestDate = Timestamp.valueOf(serviceRequestDateString);
        String serviceRequestDetail = request.getParameter("serviceRequestDetail");
        String servicingDateString = request.getParameter("servicingDate") + " 00:00:00";
        Timestamp servicingDate = Timestamp.valueOf(servicingDateString);
        int serviceFee = Integer.parseInt(request.getParameter("serviceFee"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        return serviceManagerSessionLocal.addService(serviceType, serviceRequestStatus,
                serviceRequestDate, serviceRequestDetail, serviceFee, servicingDate, mallName);
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
        String serviceRequestDateString = request.getParameter("serviceRequestDate") + " 00:00:00";
        Timestamp serviceRequestDate = Timestamp.valueOf(serviceRequestDateString);
        String serviceRequestDetail = request.getParameter("serviceRequestDetail");
        int serviceFee = Integer.parseInt(request.getParameter("serviceFee"));
        String servicingDateString = request.getParameter("servicingDate") + " 00:00:00";
        Timestamp servicingDate = Timestamp.valueOf(servicingDateString);      
        return serviceManagerSessionLocal.editServiceRequest(serviceId, serviceType, serviceRequestStatus,
                serviceRequestDate, serviceRequestDetail, serviceFee, servicingDate);
    }

    public void deleteServiceRequest(HttpServletRequest request) {
        Long serviceId = Long.parseLong(request.getParameter("serviceId"));
        serviceManagerSessionLocal.deleteServiceRequest(serviceId);
    }
}
