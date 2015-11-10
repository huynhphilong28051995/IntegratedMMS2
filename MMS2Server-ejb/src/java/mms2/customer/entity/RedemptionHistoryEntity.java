/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.customer.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author AdminNUS
 */
@Entity
public class RedemptionHistoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerEmail;
    private String country;
    private String customerAddress;
    private String giftName;
    private Timestamp dateRedeemed;
    private String status;

    public RedemptionHistoryEntity() {
    }

    public RedemptionHistoryEntity(String customerEmail, String country, String customerAddress, String giftName, Timestamp dateRedeemed, String status) {
        this.customerEmail = customerEmail;
        this.country = country;
        this.customerAddress = customerAddress;
        this.giftName = giftName;
        this.dateRedeemed = dateRedeemed;
        this.status = status;
    }
    
                
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Timestamp getDateRedeemed() {
        return dateRedeemed;
    }

    public void setDateRedeemed(Timestamp dateRedeemed) {
        this.dateRedeemed = dateRedeemed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof RedemptionHistoryEntity)) {
            return false;
        }
        RedemptionHistoryEntity other = (RedemptionHistoryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RedemptionHistoryEntity[ id=" + id + " ]";
    }
    
}
