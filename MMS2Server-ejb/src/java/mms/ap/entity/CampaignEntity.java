/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.ap.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author khushnaz
 */
@Entity
public class CampaignEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long campaignId;
    private String campaignName;
    private String objective;
    private String channel;
    private Timestamp startDate;
    private Timestamp endDate;
    private String target; 
    private String coupon; // coupon code
    private String point; // loyalty points 
    
    private String campaignSuccess; // campaignSuccess
    
    // campaign to daily data one to many relation
    @OneToMany(fetch= FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy = "campaign")
    private Collection<DailyDataEntity> datas;
        
    public Collection<DailyDataEntity> getDatas() {
        return datas;
    }

    public void setDatas(Collection<DailyDataEntity> datas) {
        this.datas = datas;
    }
    
    public CampaignEntity() {
    }

    public CampaignEntity(String campaignName, String objective, String channel, Timestamp startDate, Timestamp endDate, String target) {
        this.campaignName = campaignName;
        this.objective = objective;
        this.channel = channel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.target = target;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getCampaignSuccess() {
        return campaignSuccess;
    }

    public void setCampaignSuccess(String campaignSuccess) {
        this.campaignSuccess = campaignSuccess;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (campaignId != null ? campaignId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the campaignId fields are not set
        if (!(object instanceof CampaignEntity)) {
            return false;
        }
        CampaignEntity other = (CampaignEntity) object;
        if ((this.campaignId == null && other.campaignId != null) || (this.campaignId != null && !this.campaignId.equals(other.campaignId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CampaignEntity[ id=" + campaignId + " ]";
    }

}
