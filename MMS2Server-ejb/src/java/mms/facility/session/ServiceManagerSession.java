/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.facility.entity.FacilityEntity;
import mms.facility.entity.ServiceEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Stateless
public class ServiceManagerSession implements ServiceManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    //create new service request
    @Override
    public ServiceEntity addService(String serviceType, String serviceRequestDetail,
            Timestamp servicingStartDate, Timestamp servicingEndDate,
            double serviceFee, String mallName, Long facilityId) {
        FacilityEntity facility  = em.find(FacilityEntity.class, facilityId);
        String facilityIdName  = (facility.getFacilityId().toString())+"_"+facility.getFacilityName();
        ServiceEntity serviceEntity = new ServiceEntity(serviceType,
                serviceRequestDetail, servicingStartDate, servicingEndDate, serviceFee);
        serviceEntity.setMallName(mallName);
        serviceEntity.setFacilityIdName(facilityIdName);
        em.persist(serviceEntity);
        return serviceEntity;
    }

    //list service requests
    @Override
    public ArrayList<ServiceEntity> listServiceRequest(String mallName) {
        ArrayList<ServiceEntity> serviceRequestList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT s FROM ServiceEntity s WHERE s.mallName=:inMallName");
            q.setParameter("inMallName", mallName);
            serviceRequestList = new ArrayList<ServiceEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serviceRequestList;
    }

    //get details for 1 existing service request
    @Override
    public ServiceEntity getServiceRequest(Long serviceId) {
        ArrayList<ServiceEntity> serviceRequestList = new ArrayList();
        ServiceEntity se;
        try {
            Query q = em.createQuery("SELECT s FROM ServiceEntity s WHERE s.serviceId=:inId");
            q.setParameter("inId", serviceId);
            serviceRequestList = new ArrayList<ServiceEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (ServiceEntity) serviceRequestList.get(0);
    }

    //edit an existing service request
    @Override
    public ServiceEntity editServiceRequest(Long serviceId, String serviceType,
            String serviceRequestStatus, String serviceRequestDetail, 
            Timestamp servicingStartDate, Timestamp servicingEndDate,
            double serviceFee) {
        ServiceEntity serviceEntity = em.find(ServiceEntity.class, serviceId);
        serviceEntity.setServiceType(serviceType);
        serviceEntity.setServiceRequestStatus(serviceRequestStatus);
        serviceEntity.setServiceRequestDetail(serviceRequestDetail);
        serviceEntity.setServicingStartDate(servicingStartDate);
        serviceEntity.setServicingEndDate(servicingEndDate);
        serviceEntity.setServiceFee(serviceFee);
        em.merge(serviceEntity);
        em.flush();
        return serviceEntity;
    }

    //delete the service request    
    @Override
    public void deleteServiceRequest(Long serviceId) {
        ServiceEntity serviceEntity = em.find(ServiceEntity.class, serviceId);
        if (serviceEntity != null) {
            em.remove(serviceEntity);
            System.out.println("Service request has been successfully deleted!");
        }
        em.flush();
    }
}
