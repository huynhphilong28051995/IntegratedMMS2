/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import mms.leasing.entity.LevelEntity;
import mms.leasing.entity.UnitEntity;
import mms2.leasing.session.LevelManagerSessionLocal;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PhiLong
 */
public class LevelManager {
    LevelManagerSessionLocal levelManagerSessionLocal;

    public LevelManager(LevelManagerSessionLocal levelManagerSessionLocal) {
        this.levelManagerSessionLocal = levelManagerSessionLocal;
    }
    public LevelEntity createLevel(String mallName, String levelCode, String floorplanBackground){
        return levelManagerSessionLocal.createLevel(mallName, levelCode, floorplanBackground);
    }
    public void linkUnitToLevel(LevelEntity level, UnitEntity unit){
        levelManagerSessionLocal.linkUnitToLevel(level, unit);
    }
    public void addLocalStorageInfo(HttpServletRequest request){
        String positionString = request.getParameter("positionString");
        
        String mallName= (String)request.getSession().getAttribute("mallName");
        String levelCode= (String)request.getSession().getAttribute("levelCode");
        System.out.println("&&&&&&&&&&&&&&&&&&&"+positionString+"&&&&&&&&&&&&&&&&&&&&&&&&"+mallName+"&&&"+levelCode);
        levelManagerSessionLocal.saveLevelUnitPosition(mallName, levelCode, positionString);
    }
 
    public LevelEntity getLevel(HttpServletRequest request){
        String mallName= (String)request.getSession().getAttribute("mallName");
        String levelCode = (String)request.getSession().getAttribute("levelCode");
        return levelManagerSessionLocal.getLevel(mallName, levelCode);
    }
    
    public int getNumOfLevel(String mallName){
        return levelManagerSessionLocal.getNumOfLevelForMall(mallName);
    }
    public void implementPrototypeFloorPlan(String mallName){
        System.out.println("STAGE 1.0.1");
        levelManagerSessionLocal.implementFloorPlanPrototype(mallName);
        System.out.println("STAGE 1.0.2");
    }
    public void implementPrototypeCategory(String mallName){
        levelManagerSessionLocal.implementPrototypeCategory(mallName);
    }
    public void implementPrototypePublicOpenBid(String mallName,Long leasingRequestId){
        levelManagerSessionLocal.implementPrototypePublicOpenBid(mallName, leasingRequestId);
    }
    public void rejectPrototypePublicOpenBid(String mallName,Long leasingRequestId){
        levelManagerSessionLocal.rejectPrototypePublicOpenBid(mallName, leasingRequestId);
    }
    public void rejectPrototypeFloorPlan(String mallName){
        levelManagerSessionLocal.rejectPrototypeFloorPlan(mallName);
    }
    public boolean checkInitialization(HttpServletRequest request){
        String mallName =(String)request.getSession().getAttribute("mallName");
        return levelManagerSessionLocal.checkInitialization(mallName);
    }
    
}
