/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import mms.leasing.entity.LeasingSystemRequestEntity;
import mms.leasing.entity.LevelEntity;
import mms.leasing.entity.UnitEntity;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author PhiLong
 */
@Stateless
public class LevelManagerSession implements LevelManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    public LevelManagerSession() {
    }

    @Override
    public LevelEntity createLevel(String mallName, String levelCode, String floorplanBackground) {
        LevelEntity level = new LevelEntity(mallName, levelCode, floorplanBackground);
        em.persist(level);
        return level;
    }

    @Override
    public void linkUnitToLevel(LevelEntity level, UnitEntity unit) {
        level.getUnits().add(unit);
        unit.setLevel(level);
        em.merge(unit);
        em.merge(level);
    }

    @Override
    public LevelEntity getLevel(String mallName, String levelCode) {
        Query query = em.createQuery("SELECT lv FROM LevelEntity lv "
                + "WHERE lv.mallName=:inMallName AND lv.levelCode=:inLevelCode");
        query.setParameter("inMallName", mallName);
        query.setParameter("inLevelCode", levelCode);
        return (LevelEntity) query.getResultList().get(0);
    }

    //This method will save the position to Prototype Attribute
    @Override
    public void saveLevelUnitPosition(String mallName, String levelCode, String positionString) {
        Query query = em.createQuery("SELECT lv FROM LevelEntity lv "
                + "WHERE lv.mallName=:inMallName AND lv.levelCode=:inLevelCode");
        query.setParameter("inMallName", mallName);
        query.setParameter("inLevelCode", levelCode);
        LevelEntity level = (LevelEntity) query.getResultList().get(0);
        ArrayList<String> positionStringList = new ArrayList<String>();
        while (positionString.length() > 0) {
            int length = positionString.length();
            if (length > 255) {
                String tempString = positionString.substring(0, 255);
                positionStringList.add(tempString);
                positionString = positionString.substring(255, length);
            } else {
                positionStringList.add(positionString);
                positionString = "";
            }
        }
        level.setUnitPositionListPrototype(positionStringList);
        em.merge(level);
    }

    @Override
    public int getNumOfLevelForMall(String mallName) {
        Query query = em.createQuery("SELECT lv FROM LevelEntity lv "
                + "WHERE lv.mallName=:inMallName");
        query.setParameter("inMallName", mallName);
        return query.getResultList().size();
    }

    @Override
    public void implementFloorPlanPrototype(String mallName) {
        System.out.println("SHITTTTT " + mallName);
        System.out.println("SHITTTTT " + mallName);
        System.out.println("SHITTTTT " + mallName);
        Query query1 = em.createQuery("SELECT l FROM LevelEntity l "
                + "WHERE l.mallName = :inMallName");
        query1.setParameter("inMallName", mallName);
        for (Object o : query1.getResultList()) {
            LevelEntity level = (LevelEntity) o;
            level.setShow(true);
            level.setUnitPositionList(level.getUnitPositionListPrototype());
            ArrayList<UnitEntity> unitList = new ArrayList<UnitEntity>(level.getUnits());
            for(int i=0; i<unitList.size(); i++){
                UnitEntity unit = unitList.get(i);
                unit.setShow(true);
                em.merge(unit);
            }
            em.merge(level);
        }
        System.out.println("SHITTTTT " + mallName);
        Query query2 = em.createQuery("SELECT u FROM UnitEntity u "
                + "WHERE u.mallName = :inMallName AND u.deleteProposed =:inDelete");
        query2.setParameter("inMallName", mallName);
        query2.setParameter("inDelete", true);
        System.out.println("-----------------------" + query2.getResultList().size());

        for (Object o : query2.getResultList()) {
            UnitEntity unit = (UnitEntity) o;
            LevelEntity level=unit.getLevel();
            ArrayList<UnitEntity> unitList = new ArrayList<UnitEntity>(level.getUnits());
            unitList.remove(unit);
            level.setUnits(unitList);
            em.merge(level);
            
            em.remove(unit);
            em.flush();
        }
        em.flush();
    }
    
    @Override
    public void implementPrototypePublicOpenBid(String mallName, Long leasingRequestId){
        LeasingSystemRequestEntity req = em.find(LeasingSystemRequestEntity.class, leasingRequestId);
        ArrayList<String> unitList = req.getListOfUnitOpenForBidding();
        for(int i=0; i < unitList.size(); i++){
            Query query = em.createQuery("SELECT u FROM UnitEntity u "
                + "WHERE u.mallName = :inMallName AND u.locationCode =:inLocationCode");
            query.setParameter("inMallName", mallName);
            query.setParameter("inLocationCode", unitList.get(i));
            UnitEntity unit =(UnitEntity)query.getResultList().get(0);
            unit.setOpenForPublicBidding(true);
            LevelEntity level  = unit.getLevel();
            em.merge(unit);
            em.merge(level);
        }
        em.flush();
    }
     @Override
    public void rejectPrototypePublicOpenBid(String mallName, Long leasingRequestId){
        LeasingSystemRequestEntity req = em.find(LeasingSystemRequestEntity.class, leasingRequestId);
        ArrayList<String> unitList = req.getListOfUnitOpenForBidding();
        for(int i=0; i < unitList.size(); i++){
            Query query = em.createQuery("SELECT u FROM UnitEntity u "
                + "WHERE u.mallName = :inMallName AND u.locationCode =:inLocationCode");
            query.setParameter("inMallName", mallName);
            query.setParameter("inLocationCode", unitList.get(i));
            UnitEntity unit =(UnitEntity)query.getResultList().get(0);
            unit.setOpenForPublicBiddingPrototype(false);
            LevelEntity level  = unit.getLevel();
            em.merge(unit);
            em.merge(level);
        }
        em.flush();
    }
    @Override
    public void rejectPrototypeFloorPlan(String mallName) {
        Query query = em.createQuery("SELECT l FROM LevelEntity l "
                + "WHERE l.mallName = :inMallName");
        query.setParameter("inMallName", mallName);
        for (Object o : query.getResultList()) {
            LevelEntity level = (LevelEntity) o;
            level.setShow(true);
            level.setUnitPositionList(level.getUnitPositionListPrototype());
            ArrayList<UnitEntity> unitList = new ArrayList<UnitEntity>(level.getUnits());
            for (int i = 0; i < unitList.size(); i++) {
                UnitEntity unit = (UnitEntity) unitList.get(i);
                if (unit.isDeleteProposed()) {
                    unit.setDeleteProposed(false);
                    em.merge(unit);
                }
                em.flush();
                em.merge(level);
            }
        }
    }

    @Override
    public void implementPrototypeCategory(String mallName) {
        Query query = em.createQuery("SELECT l FROM LevelEntity l "
                + "WHERE l.mallName = :inMallName");
        query.setParameter("inMallName", mallName);
        em.flush();
        for (Object o : query.getResultList()) {
            LevelEntity level = (LevelEntity) o;
            ArrayList<UnitEntity> unitList = new ArrayList<UnitEntity>(level.getUnits());
            for (int i = 0; i < unitList.size(); i++) {
                UnitEntity unit = (UnitEntity) unitList.get(i);
                unit.setCategory(unit.getCategoryPrototype());
                em.merge(unit);
            }
            em.merge(level);
        }
    }
    
    @Override
    public boolean checkInitialization(String mallName){
        Query query = em.createQuery("SELECT l FROM LevelEntity l WHERE l.mallName= :inMallName");
        query.setParameter("inMallName", mallName);
        if((query.getResultList()).size() == 0)
            return false;
        return true;
    }
}
