/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.LevelEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.leasing.entity.TenantEntity;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.Local;

/**
 *
 * @author PhiLong
 */
@Local
public interface UnitManagerSessionLocal {
    public Vector getAllUnitColorForCurrentMall(String mallName);

    public UnitEntity getUnitById(Long id);

    public boolean checkLocationFitForAddTenant(String locationCode);

    public UnitEntity createUnit(LevelEntity level, String mallName, String locationCode);

    public UnitEntity getUnitByMallNameAndLocationCode(String mallName, String locationCode);

    public void addPushCartsToEachLevel(String mallName, ArrayList<String> numOfPushCartPerLevelList);
    public void addKiosksToEachLevel(String mallName, ArrayList<String> numOfKioskPerLevelList);

    public void addEventsToEachLevel(String mallName, ArrayList<String> numOfEventPerLevelList);

    public void addStoresToEachLevel(String mallName, ArrayList<String> numOfStorePerLevelList);

    public void declareUnitPrototypeCategory(String mallName, String locationCode, String category);

    public Vector getAllPrototypeUnitColorForCurrentMall(String mallName);

    public String setUnitDelete(String mallName, String locationCode);

    public void setOpenPublicBiddingPrototype(String mallName, ArrayList<String> chosenUnitList);
}
