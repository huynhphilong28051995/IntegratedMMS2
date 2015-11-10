/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.ap.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.ap.entity.CampaignEntity;
import mms.ap.entity.DailyDataEntity;

/**
 *
 * @author khushnaz
 */
@Stateless
public class DailyDataSession implements DailyDataSessionLocal {

    @PersistenceContext
    private EntityManager em;

    // add daily data
    @Override
    public Long addDailyData(Long campaignId, String description, String rating) {

        DailyDataEntity dailyDataEntity = new DailyDataEntity(description, rating);
        CampaignEntity campaign = em.find(CampaignEntity.class, campaignId);        
        dailyDataEntity.setCampaign(campaign);
        Timestamp current = new Timestamp(System.currentTimeMillis());
        dailyDataEntity.setDateRecord(current);
        em.persist(dailyDataEntity);
        
        Collection<DailyDataEntity> datas = campaign.getDatas();
        datas.add(dailyDataEntity);
        campaign.setDatas(datas);
        
        em.flush();
        return dailyDataEntity.getDailyDataId();
    }

    // list all Daily data
    @Override
    public ArrayList<DailyDataEntity> listDailyData() {
        ArrayList<DailyDataEntity> dailyDataList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT d FROM DailyDataEntity d");
            dailyDataList = new ArrayList<DailyDataEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dailyDataList;
    }
    
    // get all daily data of a campaign 
    @Override
    public ArrayList<DailyDataEntity> listDailyDataFromCampaign(Long campaignId) {
        ArrayList<DailyDataEntity> dataList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT d FROM DailyDataEntity d WHERE d.campaignId=:inCampaignId");
            q.setParameter("inCampaignId", campaignId);
            dataList = new ArrayList<DailyDataEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataList;
    }
    
    // get a daily data
    @Override
    public DailyDataEntity getDailyData(Long dailyDataId) {
        ArrayList<DailyDataEntity> dataList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT d FROM DailyDataEntity d WHERE d.dailyDataId=:inId");
            q.setParameter("inId", dailyDataId);
            dataList = new ArrayList<DailyDataEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (DailyDataEntity) dataList.get(0);
    }
    
    // edit daily data 
    @Override
    public DailyDataEntity editDailyData(Long dailyDataId, String description) {
        DailyDataEntity dailyDataEntity = em.find(DailyDataEntity.class, dailyDataId);
        dailyDataEntity.setDescription(description);
       // dailyDataEntity.setDateRecord(dateRecord);

        em.flush();
        return dailyDataEntity;
    }

    // delete daily data
    @Override
    public void deleteDailyData(Long dailyDataId) {
        DailyDataEntity dailyDataEntity = em.find(DailyDataEntity.class, dailyDataId);
        if (dailyDataEntity == null) {
            System.out.println("Sorry! Daily Data does not exist!");
        } else {
            em.remove(dailyDataEntity);
            System.out.println("Daily Data is successfully deleted!");
        }
        em.flush();
    }
    
}
