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
public class FacilityEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long facilityId;
    private String facilityName;
    private String facilityCategory;
    private int facilityQuantity;
    private String facilityCondition;
    private String facilityLocation;
    private Timestamp facilityPurchaseDate;
    private Timestamp facilityExpiryDate;
    //private Timestamp lastServicingDate;
    private double facilityCost;
    private String mallName;
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="facility")
    private Collection<AssetEntity> assets = new ArrayList<AssetEntity>();
    
    public Collection<AssetEntity> getAssets(){
        return assets;
    }
    
    public void setAssets(Collection<AssetEntity> assets){
        this.assets = assets;
    }
    
    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<ServiceEntity> service = new ArrayList<ServiceEntity>();
    
    public Collection<ServiceEntity> getService(){
        return service;
    }
    
    public void setService(Collection<ServiceEntity> service){
        this.service = service;
    }

    //Constructor
    public FacilityEntity() {
    }

    public FacilityEntity(String facilityName, String facilityCategory, int facilityQuantity, 
            String facilityCondition, String facilityLocation, Timestamp facilityPurchaseDate, 
            Timestamp facilityExpiryDate, double facilityCost) {
        this.facilityName = facilityName;
        this.facilityCategory = facilityCategory;
        this.facilityQuantity = facilityQuantity;
        this.facilityCondition = facilityCondition;
        this.facilityLocation = facilityLocation;
        this.facilityPurchaseDate = facilityPurchaseDate;
        this.facilityExpiryDate = facilityExpiryDate;
        this.facilityCost = facilityCost;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityCategory() {
        return facilityCategory;
    }

    public void setFacilityCategory(String facilityCategory) {
        this.facilityCategory = facilityCategory;
    }

    public int getFacilityQuantity() {
        return facilityQuantity;
    }

    public void setFacilityQuantity(int facilityQuantity) {
        this.facilityQuantity = facilityQuantity;
    }

    public String getFacilityCondition() {
        return facilityCondition;
    }

    public void setFacilityCondition(String facilityCondition) {
        this.facilityCondition = facilityCondition;
    }

    public String getFacilityLocation() {
        return facilityLocation;
    }

    public void setFacilityLocation(String facilityLocation) {
        this.facilityLocation = facilityLocation;
    }

    public Timestamp getFacilityPurchaseDate() {
        return facilityPurchaseDate;
    }

    public void setFacilityPurchaseDate(Timestamp facilityPurchaseDate) {
        this.facilityPurchaseDate = facilityPurchaseDate;
    }

    public Timestamp getFacilityExpiryDate() {
        return facilityExpiryDate;
    }

    public void setFacilityExpiryDate(Timestamp facilityExpiryDate) {
        this.facilityExpiryDate = facilityExpiryDate;
    }

    public double getFacilityCost() {
        return facilityCost;
    }

    public void setFacilityCost(double facilityCost) {
        this.facilityCost = facilityCost;
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
        hash += (facilityId != null ? facilityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the facilityId fields are not set
        if (!(object instanceof FacilityEntity)) {
            return false;
        }
        FacilityEntity other = (FacilityEntity) object;
        if ((this.facilityId == null && other.facilityId != null) || (this.facilityId != null && !this.facilityId.equals(other.facilityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.facilityEntity[ facilityId=" + facilityId + " ]";
    }
}
