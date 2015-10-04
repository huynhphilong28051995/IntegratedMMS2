/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms2.leasing.entity.LevelEntity;
import mms2.leasing.entity.UnitEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PhiLong
 */
@Stateless
public class UnitManagerSession implements UnitManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    public UnitManagerSession() {
    }

    @Override
    public UnitEntity createUnit(LevelEntity level, String mallName, String locationCode) {
        UnitEntity unit = new UnitEntity(level, mallName, locationCode);
        level.getUnits().add(unit);
        em.persist(unit);
        em.merge(level);
        return unit;
    }


    @Override
    public UnitEntity getUnitById(Long id) {
        return em.find(UnitEntity.class, id);
    }

    @Override
    public UnitEntity getUnitByMallNameAndLocationCode(String mallName, String locationCode) {

        Query query = em.createQuery("SELECT u FROM UnitEntity u "
                + "WHERE u.locationCode = :inLocationCode "
                + "AND u.mallName = :inMallName");
        query.setParameter("inLocationCode", locationCode);
        query.setParameter("inMallName", mallName);
        UnitEntity unit = (UnitEntity) (query.getResultList().get(0));
        return unit;
    }

    @Override
    public void declareUnitPrototypeCategory(String mallName, String locationCode, String category) {
        Query query = em.createQuery("SELECT u FROM UnitEntity u "
                + "WHERE u.mallName=:inMallName AND u.locationCode=:inLocationCode");
        query.setParameter("inMallName", mallName);
        query.setParameter("inLocationCode", locationCode);
        UnitEntity unit = (UnitEntity) (query.getResultList().get(0));
        unit.setCategoryPrototype(category);
        em.merge(unit);
    }

    private boolean unitExistAlready(String locationCode) {
        Query query = em.createQuery("SELECT u FROM UnitEntity u "
                + "WHERE u.locationCode=:inLocationCode");
        query.setParameter("inLocationCode", locationCode);
        List resultList = query.getResultList();
        if (resultList.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Vector getAllUnitColorForCurrentMall(String mallName) {
        Query query = em.createQuery("SELECT u FROM UnitEntity u WHERE u.mallName=:inMallName");
        query.setParameter("inMallName", mallName);
        ArrayList<UnitEntity> resultList = new ArrayList<>();
        ArrayList<String> arrayLocationCode = new ArrayList();
        ArrayList<String> arrayColor = new ArrayList();
        Vector resultVector = new Vector();

        if (query.getResultList().size() != 0) {
            for (Object o : query.getResultList()) {
                UnitEntity s = (UnitEntity) o;
                resultList.add(s);
            }
            for (int i = 0; i < resultList.size(); i++) {
                String locationCode = resultList.get(i).getLocationCode();
                String category = resultList.get(i).getCategory();
                boolean hasTenant = resultList.get(i).isHasTenant();
                String color = "";
                if (hasTenant) {
                    switch (category) {
                        case "Food&Beverage":
                            color = "#247be7";
                            break;
                        case "Retail":
                            color = "#0ee77f";
                            break;
                        case "Entertainment":
                            color = "#f1af30";
                            break;
                        case "Event":
                            color = "#ff55d9";
                            break;
                    }
                } else {
                    switch (category) {
                        case "Food&Beverage":
                            color = "#6bacfb";
                            break;
                        case "Retail":
                            color = "#55f1a6";
                            break;
                        case "Entertainment":
                            color = "#f3c66f";
                            break;
                        case "Event":
                            color = "#ff55d9";
                            break;

                    }
                }

                arrayLocationCode.add(locationCode);
                arrayColor.add(color);
            }
            resultVector.add(0, arrayLocationCode);
            resultVector.add(1, arrayColor);
        }
        return resultVector;
    }
    
    @Override
    public Vector getAllPrototypeUnitColorForCurrentMall(String mallName) {
        Query query = em.createQuery("SELECT u FROM UnitEntity u WHERE u.mallName=:inMallName");
        query.setParameter("inMallName", mallName);
        ArrayList<UnitEntity> resultList = new ArrayList<>();
        ArrayList<String> arrayLocationCode = new ArrayList();
        ArrayList<String> arrayColor = new ArrayList();
        Vector resultVector = new Vector();

        if (query.getResultList().size() != 0) {
            for (Object o : query.getResultList()) {
                UnitEntity s = (UnitEntity) o;
                resultList.add(s);
            }
            for (int i = 0; i < resultList.size(); i++) {
                String locationCode = resultList.get(i).getLocationCode();
                String category = resultList.get(i).getCategoryPrototype();
                boolean hasTenant = resultList.get(i).isHasTenant();
                String color = "";
                if (hasTenant) {
                    switch (category) {
                        case "Food&Beverage":
                            color = "#247be7";
                            break;
                        case "Retail":
                            color = "#0ee77f";
                            break;
                        case "Entertainment":
                            color = "#f1af30";
                            break;
                        case "Event":
                            color = "#ff55d9";
                            break;
                    }
                } else {
                    switch (category) {
                        case "Food&Beverage":
                            color = "#6bacfb";
                            break;
                        case "Retail":
                            color = "#55f1a6";
                            break;
                        case "Entertainment":
                            color = "#f3c66f";
                            break;
                        case "Event":
                            color = "#ff55d9";
                            break;

                    }
                }

                arrayLocationCode.add(locationCode);
                arrayColor.add(color);
            }
            resultVector.add(0, arrayLocationCode);
            resultVector.add(1, arrayColor);
        }
        return resultVector;
    }

    @Override
    public boolean checkLocationFitForAddTenant(String locationCode) {
        Query query = em.createQuery("SELECT s FROM UnitEntity s "
                + "WHERE s.locationCode=:inLocationCode");
        query.setParameter("inLocationCode", locationCode);
        List resultList = query.getResultList();
        //iff unit not exist
        if (resultList.size() == 0) {
            return false;
        }
        //iff unit already have tenant
        UnitEntity unit = (UnitEntity) resultList.get(0);
        if (unit.getTenant() != null) {
            return false;
        }
        return true;
    }

    @Override
    public void addPushCartsToEachLevel(String mallName, ArrayList<String> numOfPushCartPerLevelList) {
        for (int i = 1; i <= numOfPushCartPerLevelList.size(); i++) {
            String levelCode = "LV" + i;
            int numOfPushCart = Integer.parseInt(numOfPushCartPerLevelList.get(i - 1));
            //get level
            Query query1 = em.createQuery("SELECT l FROM LevelEntity l WHERE "
                    + "l.mallName=:inMallName AND l.levelCode=:inLevelCode");
            query1.setParameter("inMallName", mallName);
            query1.setParameter("inLevelCode", levelCode);
            LevelEntity level = (LevelEntity) query1.getResultList().get(0);
            //get number of existing pushcart on the level
            Query query2 = em.createQuery("SELECT u FROM UnitEntity u WHERE "
                    + "u.mallName=:inMallName AND u.level=:inLevel AND u.pushCart=:inPushCart");
            query2.setParameter("inMallName", mallName);
            query2.setParameter("inLevel", level);
            query2.setParameter("inPushCart", true);
            int numOfExistingPushCart = query2.getResultList().size();
            for (int j = (numOfExistingPushCart+1); j <= (numOfExistingPushCart + numOfPushCart); j++) {
                String locationCode = "LV"+i+"PC" + j;
                System.out.println("0000000000000000000000000000000000000000000000000000000000"+j);
                UnitEntity unit = new UnitEntity(level, mallName, locationCode, true, false, false);
                level.getUnits().add(unit);
                em.persist(unit);
                em.merge(level);
            }
        }
    }
    @Override
    public void addKiosksToEachLevel(String mallName, ArrayList<String> numOfKioskPerLevelList) {
        for (int i = 1; i <= numOfKioskPerLevelList.size(); i++) {
            String levelCode = "LV" + i;
            int numOfKiosk = Integer.parseInt(numOfKioskPerLevelList.get(i - 1));
            //get level
            Query query1 = em.createQuery("SELECT l FROM LevelEntity l WHERE "
                    + "l.mallName=:inMallName AND l.levelCode=:inLevelCode");
            query1.setParameter("inMallName", mallName);
            query1.setParameter("inLevelCode", levelCode);
            LevelEntity level = (LevelEntity) query1.getResultList().get(0);
            //get number of existing pushcart on the level
            Query query2 = em.createQuery("SELECT u FROM UnitEntity u WHERE "
                    + "u.mallName=:inMallName AND u.level=:inLevel AND u.kiosk=:inKiosk");
            query2.setParameter("inMallName", mallName);
            query2.setParameter("inLevel", level);
            query2.setParameter("inKiosk", true);
            int numOfExistingKiosk = query2.getResultList().size();
            for (int j = (numOfExistingKiosk+1); j <= (numOfExistingKiosk + numOfKiosk); j++) {
                String locationCode = "LV"+i+"KS" + j;
                System.out.println("0000000000000000000000000000000000000000000000000000000000"+j);
                UnitEntity unit = new UnitEntity(level, mallName, locationCode, false, true,false);
                level.getUnits().add(unit);
                em.persist(unit);
                em.merge(level);
            }
        }
    }
    @Override
    public void addEventsToEachLevel(String mallName, ArrayList<String> numOfEventPerLevelList) {
        for (int i = 1; i <= numOfEventPerLevelList.size(); i++) {
            String levelCode = "LV" + i;
            int numOfEvent = Integer.parseInt(numOfEventPerLevelList.get(i - 1));
            //get level
            Query query1 = em.createQuery("SELECT l FROM LevelEntity l WHERE "
                    + "l.mallName=:inMallName AND l.levelCode=:inLevelCode");
            query1.setParameter("inMallName", mallName);
            query1.setParameter("inLevelCode", levelCode);
            LevelEntity level = (LevelEntity) query1.getResultList().get(0);
            //get number of existing pushcart on the level
            Query query2 = em.createQuery("SELECT u FROM UnitEntity u WHERE "
                    + "u.mallName=:inMallName AND u.level=:inLevel AND u.event=:inEvent");
            query2.setParameter("inMallName", mallName);
            query2.setParameter("inLevel", level);
            query2.setParameter("inEvent", true);
            int numOfExistingEvent = query2.getResultList().size();
            for (int j = (numOfExistingEvent+1); j <= (numOfExistingEvent + numOfEvent); j++) {
                String locationCode = "LV"+i+"EV" + j;
                System.out.println("0000000000000000000000000000000000000000000000000000000000"+j);
                UnitEntity unit = new UnitEntity(level, mallName, locationCode, false, false, true);
                unit.setCategory("Event");
                unit.setCategoryPrototype("Event");
                level.getUnits().add(unit);
                em.persist(unit);
                em.merge(level);
            }
        }
    }
    @Override
    public void addStoresToEachLevel(String mallName, ArrayList<String> numOfStorePerLevelList) {
        for (int i = 1; i <= numOfStorePerLevelList.size(); i++) {
            String levelCode = "LV" + i;
            int numOfStore = Integer.parseInt(numOfStorePerLevelList.get(i - 1));
            //get level
            Query query1 = em.createQuery("SELECT l FROM LevelEntity l WHERE "
                    + "l.mallName=:inMallName AND l.levelCode=:inLevelCode ");
            query1.setParameter("inMallName", mallName);
            query1.setParameter("inLevelCode", levelCode);
            LevelEntity level = (LevelEntity) query1.getResultList().get(0);
            //get number of existing pushcart on the level
            Query query2 = em.createQuery("SELECT u FROM UnitEntity u WHERE "
                    + "u.mallName=:inMallName AND u.level=:inLevel AND u.event=:inEvent"
                    + "  AND u.pushCart=:inPushCart  AND u.kiosk=:inKiosk");
            query2.setParameter("inMallName", mallName);
            query2.setParameter("inLevel", level);
            query2.setParameter("inEvent", false);
            query2.setParameter("inPushCart", false);
            query2.setParameter("inKiosk", false);
            int numOfExistingStore = query2.getResultList().size();
            for (int j = (numOfExistingStore+1); j <= (numOfExistingStore + numOfStore); j++) {
                String locationCode = "LV"+i+"ST" + j;
                System.out.println("0000000000000000000000000000000000000000000000000000000000"+j);
                UnitEntity unit = new UnitEntity(level, mallName, locationCode, false, false, false);
                level.getUnits().add(unit);
                em.persist(unit);
                em.merge(level);
            }
        }
    }
    
    @Override
    public String setUnitDelete(String mallName, String locationCode){
        Query query = em.createQuery("SELECT u FROM UnitEntity u WHERE "
                    + "u.mallName=:inMallName AND u.locationCode=:inLocationCode ");
            query.setParameter("inMallName", mallName);
            query.setParameter("inLocationCode", locationCode);
        UnitEntity unit =(UnitEntity) query.getResultList().get(0);
        if(unit.isHasTenant())
            return "Unsuccessful! This unit already had tenant";
        if(unit.isOpenForPublicBidding())
            return "Unsuccessful! This unit already opened for public bidding";
        if(unit.isOpenForPublicBiddingPrototype())
            return "Unsuccessful! This unit is being proposed for public bidding";
        if(unit.isOpenForInternalBidding())
            return "Unsuccessful! This unit already opened for internl bidding";
        if(unit.isOpenForInternalBiddingPrototype())
            return "Unsuccessful! This unit is being proposed for internal bidding";
        unit.setDeleteProposed(true);
        LevelEntity level = unit.getLevel();
        em.merge(unit);
        em.merge(level);
        em.flush();
        return "Successful! Unit recorded as delete in prototype floor plan";
    }
    
    @Override
    public void setOpenPublicBiddingPrototype(String mallName,ArrayList<String> chosenUnitList){
        for(int i=0; i<chosenUnitList.size(); i++){
            String locationCode = chosenUnitList.get(i);
            Query query = em.createQuery("SELECT u FROM UnitEntity u "
                    + "WHERE u.mallName=:inMallName AND u.locationCode=:inLocationCode");
            query.setParameter("inMallName", mallName);
            query.setParameter("inLocationCode", locationCode);
            UnitEntity unit = (UnitEntity) query.getResultList().get(0);
            unit.setOpenForPublicBiddingPrototype(true);
            em.merge(unit);
            em.flush();
        }
    }
}
