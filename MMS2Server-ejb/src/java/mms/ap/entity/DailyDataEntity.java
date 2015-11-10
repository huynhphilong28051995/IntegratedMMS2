/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.ap.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author khushnaz
 */
@Entity
public class DailyDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyDataId;
    private String description; // describe campaign results 
    private String rating; // choose (1) poor, (2) fair, (3) good
    private Timestamp dateRecord;
    
    // many to one with campaignEntity
    @ManyToOne(fetch= FetchType.EAGER)
    private CampaignEntity campaign;
    
    public DailyDataEntity() {
    }

    public DailyDataEntity(String description, String rating) {
        this.description = description;
        this.rating = rating;
    }
    
    public CampaignEntity getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignEntity campaign) {
        this.campaign = campaign;
    }

    public Long getDailyDataId() {
        return dailyDataId;
    }

    public void setDailyDataId(Long dailyDataId) {
        this.dailyDataId = dailyDataId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(Timestamp dateRecord) {
        this.dateRecord = dateRecord;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dailyDataId != null ? dailyDataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the dailyDataId fields are not set
        if (!(object instanceof DailyDataEntity)) {
            return false;
        }
        DailyDataEntity other = (DailyDataEntity) object;
        if ((this.dailyDataId == null && other.dailyDataId != null) || (this.dailyDataId != null && !this.dailyDataId.equals(other.dailyDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DailyDataEntity[ id=" + dailyDataId + " ]";
    }
    
}
