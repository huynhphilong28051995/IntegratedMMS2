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
public class OutsourcingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OutsourcingId;
    private Long tenantId;
    private String tenantEmail;
    private String unitNumber;
    private String contractorName;
    private int numStaff;
    private String outsourcingStatus = "Pending";
    private Timestamp outsourcingDate;
    private String outsourcingDetail;
    private Timestamp oServicingStartDate;
    private Timestamp oServicingEndDate;
    //link to upload the technical documents
    private String docFileLink;
    private String mallName;

    public OutsourcingEntity() {
    }

    public OutsourcingEntity(Long tenantId, String tenantEmail, String unitNumber, String contractorName, int numStaff, Timestamp outsourcingDate, String outsourcingDetail, Timestamp oServicingStartDate, Timestamp oServicingEndDate, String docFileLink, String mallName) {
        
        this.tenantId = tenantId;
        this.tenantEmail = tenantEmail;
        this.unitNumber = unitNumber;
        this.contractorName = contractorName;
        this.numStaff = numStaff;
        this.outsourcingDate = outsourcingDate;
        this.outsourcingDetail = outsourcingDetail;
        this.oServicingStartDate = oServicingStartDate;
        this.oServicingEndDate = oServicingEndDate;
        this.docFileLink = docFileLink;
        this.mallName = mallName;
    }
   
    public Long getOutsourcingId() {
        return OutsourcingId;
    }

    public void setOutsourcingId(Long OutsourcingId) {
        this.OutsourcingId = OutsourcingId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public int getNumStaff() {
        return numStaff;
    }

    public void setNumStaff(int numStaff) {
        this.numStaff = numStaff;
    }

    public String getOutsourcingStatus() {
        return outsourcingStatus;
    }

    public void setOutsourcingStatus(String outsourcingStatus) {
        this.outsourcingStatus = outsourcingStatus;
    }

    public Timestamp getOutsourcingDate() {
        return outsourcingDate;
    }

    public void setOutsourcingDate(Timestamp outsourcingDate) {
        this.outsourcingDate = outsourcingDate;
    }

    public String getOutsourcingDetail() {
        return outsourcingDetail;
    }

    public void setOutsourcingDetail(String outsourcingDetail) {
        this.outsourcingDetail = outsourcingDetail;
    }

    public Timestamp getoServicingStartDate() {
        return oServicingStartDate;
    }

    public void setoServicingStartDate(Timestamp oServicingStartDate) {
        this.oServicingStartDate = oServicingStartDate;
    }

    public Timestamp getoServicingEndDate() {
        return oServicingEndDate;
    }

    public void setoServicingEndDate(Timestamp oServicingEndDate) {
        this.oServicingEndDate = oServicingEndDate;
    }

    public String getDocFileLink() {
        return docFileLink;
    }

    public void setDocFileLink(String docFileLink) {
        this.docFileLink = docFileLink;
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
        hash += (OutsourcingId != null ? OutsourcingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the OutsourcingId fields are not set
        if (!(object instanceof OutsourcingEntity)) {
            return false;
        }
        OutsourcingEntity other = (OutsourcingEntity) object;
        if ((this.OutsourcingId == null && other.OutsourcingId != null) || (this.OutsourcingId != null && !this.OutsourcingId.equals(other.OutsourcingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.OutsourcingEntity[ OutsourcingId=" + OutsourcingId + " ]";
    }
    
}
