/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.LevelEntity;
import mms.leasing.entity.UnitEntity;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface LevelManagerSessionLocal {

  
    public void linkUnitToLevel(LevelEntity level, UnitEntity unit);

    public LevelEntity createLevel(String mallName, String levelCode, String floorplanBackground);

    public LevelEntity getLevel(String mallName, String levelCode);

    public int getNumOfLevelForMall(String mallName);

    public void saveLevelUnitPosition(String mallName, String levelCode, String positionString);
    
    public void implementFloorPlanPrototype(String mallName);

    public void implementPrototypeCategory(String mallName);

    public void rejectPrototypeFloorPlan(String mallName);

    public void implementPrototypePublicOpenBid(String mallName, Long leasingRequestId);

    public void rejectPrototypePublicOpenBid(String mallName, Long leasingRequestId);

    public boolean checkInitialization(String mallName);
    
}
