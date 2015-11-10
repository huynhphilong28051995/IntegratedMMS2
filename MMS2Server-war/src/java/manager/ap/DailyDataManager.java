/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.ap;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import mms.ap.entity.DailyDataEntity;
import mms.ap.session.DailyDataSessionLocal;

/**
 *
 * @author khushnaz
 */
public class DailyDataManager {

    DailyDataSessionLocal dataSessionLocal;

    public DailyDataManager(DailyDataSessionLocal dataSessionLocal) {
        this.dataSessionLocal = dataSessionLocal;
    }

    public Long createNewDailyData(HttpServletRequest request) {
        Long campaignId = (Long) request.getSession().getAttribute("Id");
        String description = request.getParameter("description");
        String rating = request.getParameter("rating");

        return dataSessionLocal.addDailyData(campaignId, description, rating);
    }

    public ArrayList<DailyDataEntity> listDailyData() {
        ArrayList<DailyDataEntity> dailyData = new ArrayList<>();
        dailyData = dataSessionLocal.listDailyData();
        return dailyData;
    }
    
    public ArrayList<DailyDataEntity> listDailyDataFromCampaign(Long campaignId) {
        ArrayList<DailyDataEntity> dailyData = new ArrayList<>();
        dailyData = dataSessionLocal.listDailyDataFromCampaign(campaignId);
        return dailyData;
    }
    
    public DailyDataEntity getDailyData(HttpServletRequest request) {
        Long dailyDataId = Long.parseLong(request.getParameter("dailyDataId"));
        return dataSessionLocal.getDailyData(dailyDataId);
    }

    public DailyDataEntity editDailyData(HttpServletRequest request) {
        Long dailyDataId = Long.parseLong(request.getParameter("dailyDataId"));
        String description = request.getParameter("description");
        String dateRecordString = request.getParameter("dateRecord") + " 17:00:00";
        Timestamp dateRecord = Timestamp.valueOf(dateRecordString);

        return dataSessionLocal.editDailyData(dailyDataId, description);
    }

    public void deleteDailyData(HttpServletRequest request) {
        Long dailyDataId = Long.parseLong(request.getParameter("dailyDataId"));
        dataSessionLocal.deleteDailyData(dailyDataId);
    }

}
