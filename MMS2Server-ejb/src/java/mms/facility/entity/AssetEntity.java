/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author linjiao_Zoe
 */
@Entity
public class AssetEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long assetId;
    private String assetName;
    private int assetQuantity;
    private String assetCondition;
    private double assetCost;
    private String mallName;

    @ManyToOne
    private FacilityEntity facility = new FacilityEntity();

    public AssetEntity() {
    }

    public AssetEntity(String assetName, int assetQuantity, String assetCondition, double assetCost) {
        this.assetName = assetName;
        this.assetQuantity = assetQuantity;
        this.assetCondition = assetCondition;
        this.assetCost = assetCost;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public int getAssetQuantity() {
        return assetQuantity;
    }

    public void setAssetQuantity(int assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    public String getAssetCondition() {
        return assetCondition;
    }

    public void setAssetCondition(String assetCondition) {
        this.assetCondition = assetCondition;
    }

    public double getAssetCost() {
        return assetCost;
    }

    public void setAssetCost(double assetCost) {
        this.assetCost = assetCost;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public FacilityEntity getFacility() {
        return facility;
    }

    public void setFacility(FacilityEntity facility) {
        this.facility = facility;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetId != null ? assetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the assetId fields are not set
        if (!(object instanceof AssetEntity)) {
            return false;
        }
        AssetEntity other = (AssetEntity) object;
        if ((this.assetId == null && other.assetId != null) || (this.assetId != null && !this.assetId.equals(other.assetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.AssetEntity[ assetId=" + assetId + " ]";
    }
}
