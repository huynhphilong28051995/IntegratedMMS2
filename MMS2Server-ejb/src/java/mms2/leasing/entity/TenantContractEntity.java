/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PhiLong
 */
@Entity
@XmlRootElement
public class TenantContractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private String status="Active";
    private double deposit;
    double rate;
    private ArrayList<String> contractDescription = new ArrayList<>();
    @OneToOne(mappedBy="tenantContract")
    private TenantEntity tenant;
    
    public TenantContractEntity() {
    }

    public TenantContractEntity(Timestamp startTimestamp, Timestamp endTimestamp,
            double deposit, double rate) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.deposit = deposit;
        this.rate = rate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenantContractEntity)) {
            return false;
        }
        TenantContractEntity other = (TenantContractEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.TenantContractEntity[ id=" + id + " ]";
    }

    /**
     * @return the startTimestamp
     */
    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * @param startTimestamp the startTimestamp to set
     */
    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    /**
     * @return the endTimestamp
     */
    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * @param endTimestamp the endTimestamp to set
     */
    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the tenant
     */
    public TenantEntity getTenant() {
        return tenant;
    }

    /**
     * @param tenant the tenant to set
     */
    public void setTenant(TenantEntity tenant) {
        this.tenant = tenant;
    }

    /**
     * @return the deposit
     */
    public double getDeposit() {
        return deposit;
    }

    /**
     * @param deposit the deposit to set
     */
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return the contractDescription
     */
    public ArrayList<String> getContractDescription() {
        return contractDescription;
    }

    /**
     * @param contractDescription the contractDescription to set
     */
    public void setContractDescription(ArrayList<String> contractDescription) {
        this.contractDescription = contractDescription;
    }

}
