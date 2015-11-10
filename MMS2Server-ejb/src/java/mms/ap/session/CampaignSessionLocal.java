/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.ap.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
import mms.ap.entity.CampaignEntity;

/**
 *
 * @author khushnaz
 */
@Local
public interface CampaignSessionLocal {

    // add campaign
    public Long addCampaign(String campaignName, String objective, String channel, Timestamp startDate,
            Timestamp endDate, String target);

    //get all current campaign
    public ArrayList<CampaignEntity> listCampaign();

    // get 1 campaign
    public CampaignEntity getCampaign(Long campaignId);

    // edit only certain fields
    public CampaignEntity editCampaign(Long campaignId, String objective, String channel);

    //delete campaign  
    public void deleteCampaign(Long campaignId);

    // get old campaigns which have expired 
    public ArrayList<CampaignEntity> retrieveOldCampaign();

    // reactive an old or seasonal campaign
    public CampaignEntity renewCampaign(Long campaignId, Timestamp startDate, Timestamp endDate, String channel);

    // get seasonal campaigns - eg. GSS or CNY
    public ArrayList<CampaignEntity> retrieveSeasonCampaign();
      
}
