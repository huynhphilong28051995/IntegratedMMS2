/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.ap.session;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.ap.entity.CampaignEntity;

/**
 *
 * @author khushnaz
 */
@Stateless
public class CampaignSession implements CampaignSessionLocal {

    @PersistenceContext
    private EntityManager em;

    // add new campaign
    @Override
    public Long addCampaign(String campaignName, String objective, String channel, Timestamp startDate,
            Timestamp endDate, String target) {

        CampaignEntity campaignEntity = new CampaignEntity(campaignName, objective, channel, startDate, endDate, target);
        
        // break by campaign name - campaign is discount
        if (campaignName.toLowerCase().contains("discount".toLowerCase())) {
            UUID uuid = UUID.randomUUID(); // generate a new uuid for coupon code if it is a discount
            String coupon = uuid.toString();
            campaignEntity.setCoupon(coupon); // set new coupon code
            campaignEntity.setPoint("Not applicable"); // no points to set
        } // campaign is loyalty points related
        else if (campaignName.toLowerCase().contains("loyalty".toLowerCase())) {
            String point = campaignName.substring(0, campaignName.indexOf(" "));
            campaignEntity.setPoint(point); // set the number of points 
            campaignEntity.setCoupon("Not applicable");  // no coupon to set
        } // campaign is neither discount nor loyalty points related
        else {
            campaignEntity.setPoint("Not applicable"); // no points to set
            campaignEntity.setCoupon("Not applicable"); // no coupon to set
        }
        campaignEntity.setCampaignSuccess("Not applicable"); 

        em.persist(campaignEntity);
        em.flush();
    
        return campaignEntity.getCampaignId();
    }

    //get all ongoing campaign
    @Override
    public ArrayList<CampaignEntity> listCampaign() {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        ArrayList<CampaignEntity> list = new ArrayList();
        try {
            Query q = em.createQuery("SELECT c FROM CampaignEntity c WHERE c.endDate>:currentTime ");
            q.setParameter("currentTime", current);
            list = new ArrayList<CampaignEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // get 1 campaign
    @Override
    public CampaignEntity getCampaign(Long campaignId) {
        ArrayList<CampaignEntity> campaignList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT c FROM CampaignEntity c WHERE c.campaignId=:inId");
            q.setParameter("inId", campaignId);
            campaignList = new ArrayList<CampaignEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (CampaignEntity) campaignList.get(0);
    }

    // edit only certain fields - ongoing campaign
    @Override
    public CampaignEntity editCampaign(Long campaignId, String objective, String channel) {
        CampaignEntity campaignEntity = em.find(CampaignEntity.class, campaignId);
        campaignEntity.setObjective(objective);
        campaignEntity.setChannel(channel);

        em.flush();
        return campaignEntity;
    }

    //delete campaign  
    @Override
    public void deleteCampaign(Long campaignId) {
        CampaignEntity campaignEntity = em.find(CampaignEntity.class, campaignId);
        if (campaignEntity == null) {
            System.out.println("Sorry! Campaign does not exist!");
        } else {
            em.remove(campaignEntity);
            System.out.println("Campaign is successfully deleted!");
        }
        em.flush();
    }

    //retrive old expired campaigns
    @Override
    public ArrayList<CampaignEntity> retrieveOldCampaign() {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        ArrayList<CampaignEntity> list = new ArrayList();
        try {
            Query q = em.createQuery("SELECT c FROM CampaignEntity c WHERE c.endDate<:currentTime ");
            q.setParameter("currentTime", current);
            list = new ArrayList<CampaignEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // reactivate an expired campaign or seasonal campaign
    @Override
    public CampaignEntity renewCampaign(Long campaignId, Timestamp startDate, Timestamp endDate, String channel) {
        CampaignEntity campaignEntity = em.find(CampaignEntity.class, campaignId);
        campaignEntity.setStartDate(startDate);
        campaignEntity.setEndDate(endDate);
        campaignEntity.setChannel(channel);

        em.flush();
        return campaignEntity;
    }

    // retrieve seasonal campaigns
    @Override
    public ArrayList<CampaignEntity> retrieveSeasonCampaign() {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        ArrayList<CampaignEntity> list = new ArrayList();
        
        try {
            Query q = em.createQuery("SELECT c FROM CampaignEntity c WHERE c.campaignName LIKE '%season%' AND c.endDate<:currentTime");
            q.setParameter("currentTime", current);
            list = new ArrayList<CampaignEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }    

}
