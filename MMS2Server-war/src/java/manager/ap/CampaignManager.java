/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.ap;


import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import mms.ap.entity.CampaignEntity;
import mms.ap.session.CampaignSessionLocal;

/**
 *
 * @author khushnaz
 */
public class CampaignManager {

    CampaignSessionLocal campaignSessionLocal;

    public CampaignManager(CampaignSessionLocal campaignSessionLocal) {
        this.campaignSessionLocal = campaignSessionLocal;
    }

    public Long createNewCampaign(HttpServletRequest request) {
        String campaignName = request.getParameter("campaignName");
        String objective = request.getParameter("objective");
        String channel = request.getParameter("channel");
        String startDateString = request.getParameter("startDate") + " 00:00:00";
        Timestamp startDate = Timestamp.valueOf(startDateString);
        String endDateString = request.getParameter("endDate") + " 00:00:00";
        Timestamp endDate = Timestamp.valueOf(endDateString);
        String target = request.getParameter("target");

        return campaignSessionLocal.addCampaign(campaignName, objective, channel, startDate, endDate, target);
    }
    
    public ArrayList<CampaignEntity> listCampaign(){
        ArrayList<CampaignEntity> campaigns = new ArrayList<>();
        campaigns = campaignSessionLocal.listCampaign();
        return campaigns;
    }
    
    public ArrayList<CampaignEntity> retrieveOldCampaign(){
        ArrayList<CampaignEntity> list = new ArrayList<>();
        list = campaignSessionLocal.retrieveOldCampaign();
        return list;
    }
    
    public ArrayList<CampaignEntity> retrieveSeasonCampaign(){
        ArrayList<CampaignEntity> list = new ArrayList<>();
        list = campaignSessionLocal.retrieveSeasonCampaign();
        return list;
    }
    public CampaignEntity getCampaign(HttpServletRequest request) {
        Long campaignId = Long.parseLong(request.getParameter("campaignId"));
        return campaignSessionLocal.getCampaign(campaignId);
    }

    public CampaignEntity editCampaign(HttpServletRequest request) {
        Long campaignId = Long.parseLong(request.getParameter("campaignId"));
        String objective = request.getParameter("objective");
        String channel = request.getParameter("channel");

        return campaignSessionLocal.editCampaign(campaignId, objective, channel);
    }
    
    public void deleteCampaign(HttpServletRequest request) {
        Long campaignId = Long.parseLong(request.getParameter("campaignId"));
        campaignSessionLocal.deleteCampaign(campaignId);
    }

}
