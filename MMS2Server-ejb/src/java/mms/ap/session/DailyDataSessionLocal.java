/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.ap.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ejb.Local;
import mms.ap.entity.DailyDataEntity;

/**
 *
 * @author khushnaz
 */
@Local
public interface DailyDataSessionLocal {
    
    public Long addDailyData(Long campaignId, String description, String rating);
    
    public ArrayList<DailyDataEntity> listDailyData();

    public ArrayList<DailyDataEntity> listDailyDataFromCampaign(Long campaignId);

    public DailyDataEntity getDailyData(Long dailyDataId);

    public DailyDataEntity editDailyData(Long dailyDataId, String description);
    
    public void deleteDailyData(Long dailyDataId);

}
