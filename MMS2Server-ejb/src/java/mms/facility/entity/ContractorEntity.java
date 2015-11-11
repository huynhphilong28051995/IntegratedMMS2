/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author linjiao_Zoe
 */
@Entity
public class ContractorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractorId;
    private String contractorName;
    private String companyName;
    private String serviceType;
    private String contractorTel;
    private String contractorEmail;
    private Timestamp contractStartDate;
    private Timestamp contractEndDate;
    private String contractorStatus = "Inactive";
    private String mallName;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private Collection<FacilityEntity> facility = new ArrayList<FacilityEntity>();

    public Collection<FacilityEntity> getFacility() {
        return facility;
    }

    public void setFacility(Collection<FacilityEntity> facility) {
        this.facility = facility;
    }

    public ContractorEntity() {
    }

    public ContractorEntity(String contractorName, String companyName,
            String serviceType, String contractorTel, String contractorEmail,
            Timestamp contractStartDate, Timestamp contractEndDate) {
        this.contractorName = contractorName;
        this.companyName = companyName;
        this.serviceType = serviceType;
        this.contractorTel = contractorTel;
        this.contractorEmail = contractorEmail;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }

    public Long getContractorId() {
        return contractorId;
    }

    public void setContractorId(Long contractorId) {
        this.contractorId = contractorId;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getContractorTel() {
        return contractorTel;
    }

    public void setContractorTel(String contractorTel) {
        this.contractorTel = contractorTel;
    }

    public String getContractorEmail() {
        return contractorEmail;
    }

    public void setContractorEmail(String contractorEmail) {
        this.contractorEmail = contractorEmail;
    }

    public Timestamp getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Timestamp contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Timestamp getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Timestamp contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractorStatus() {
        return contractorStatus;
    }

    public void setContractorStatus(String contractorStatus) {
        this.contractorStatus = contractorStatus;
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
        hash += (contractorId != null ? contractorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the contractorId fields are not set
        if (!(object instanceof ContractorEntity)) {
            return false;
        }
        ContractorEntity other = (ContractorEntity) object;
        if ((this.contractorId == null && other.contractorId != null) || (this.contractorId != null && !this.contractorId.equals(other.contractorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ContractorEntity[ contractorId=" + contractorId + " ]";
    }
}
