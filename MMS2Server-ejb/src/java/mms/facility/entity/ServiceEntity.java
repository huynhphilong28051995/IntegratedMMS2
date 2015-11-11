/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author linjiao_Zoe
 */
@Entity
public class ServiceEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceId;
    private String serviceType;    
    private Timestamp serviceRequestDate;
    private String serviceRequestDetail;   
    private Timestamp servicingStartDate;
    private Timestamp servicingEndDate;
    private double serviceFee;
    private String serviceRequestStatus = "Service Pending";
    private String mallName;
    private String facilityIdName;
    
    public ServiceEntity() {
    }

    public ServiceEntity(String serviceType,
            String serviceRequestDetail, Timestamp servicingStartDate, 
            Timestamp servicingEndDate, double serviceFee) {       
        this.serviceType = serviceType;
        this.serviceRequestDate = new Timestamp(System.currentTimeMillis());
        this.serviceRequestDetail = serviceRequestDetail;
        this.servicingStartDate = servicingStartDate;
        this.servicingEndDate = servicingEndDate;
        this.serviceFee = serviceFee;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceRequestStatus() {
        return serviceRequestStatus;
    }

    public void setServiceRequestStatus(String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }

    public Timestamp getServiceRequestDate() {
        return serviceRequestDate;
    }

    public void setServiceRequestDate(Timestamp serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }

    public String getServiceRequestDetail() {
        return serviceRequestDetail;
    }

    public void setServiceRequestDetail(String serviceRequestDetail) {
        this.serviceRequestDetail = serviceRequestDetail;
    }

    public Timestamp getServicingStartDate() {
        return servicingStartDate;
    }

    public void setServicingStartDate(Timestamp servicingStartDate) {
        this.servicingStartDate = servicingStartDate;
    }

    public Timestamp getServicingEndDate() {
        return servicingEndDate;
    }

    public void setServicingEndDate(Timestamp servicingEndDate) {
        this.servicingEndDate = servicingEndDate;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }
       
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the serviceId fields are not set
        if (!(object instanceof ServiceEntity)) {
            return false;
        }
        ServiceEntity other = (ServiceEntity) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ServiceEntity[ serviceId=" + serviceId + " ]";
    }    

    /**
     * @return the facilityIdName
     */
    public String getFacilityIdName() {
        return facilityIdName;
    }

    /**
     * @param facilityIdName the facilityIdName to set
     */
    public void setFacilityIdName(String facilityIdName) {
        this.facilityIdName = facilityIdName;
    }
}
