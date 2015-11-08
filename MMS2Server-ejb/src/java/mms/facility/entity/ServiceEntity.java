/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String serviceRequestStatus;
    private Timestamp serviceRequestDate;
    private String serviceRequestDetail;
    private int serviceFee;
    private Timestamp servicingDate;
    private String mallName;

    public ServiceEntity() {
    }

    public ServiceEntity(String serviceType, String serviceRequestStatus, 
            Timestamp serviceRequestDate, String serviceRequestDetail, int serviceFee, 
            Timestamp servicingDate) {
        this.serviceType = serviceType;
        this.serviceRequestStatus = serviceRequestStatus;
        this.serviceRequestDate = serviceRequestDate;
        this.serviceRequestDetail = serviceRequestDetail;
        this.serviceFee = serviceFee;
        this.servicingDate = servicingDate;
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

    public int getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(int serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Timestamp getServicingDate() {
        return servicingDate;
    }

    public void setServicingDate(Timestamp servicingDate) {
        this.servicingDate = servicingDate;
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
}