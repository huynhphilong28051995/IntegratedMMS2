/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;


import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms.facility.entity.AssetEntity;
import mms.facility.entity.FacilityEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Stateless
public class AssetManagerSession implements AssetManagerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    //create new asset
    @Override
    public AssetEntity addAsset(String assetName, int assetQuantity, String assetCondition, double assetCost, long facilityId) {
        AssetEntity assetEntity = new AssetEntity(assetName, assetQuantity, assetCondition, assetCost);
        FacilityEntity facility = em.find(FacilityEntity.class, facilityId);
        assetEntity.setFacility(facility);
        em.persist(assetEntity);
        facility.getAssets().add(assetEntity);
        em.merge(facility);
        return assetEntity;
    }

    //list all assets
    @Override
    public ArrayList<AssetEntity> listAsset(long facilityId) {
        ArrayList<AssetEntity> assetList = new ArrayList();
        //find the parent facility with facility Id
        FacilityEntity facility = em.find(FacilityEntity.class, facilityId);
        try { //get all the assets which belong to this particular facility        
            Query q = em.createQuery("SELECT a FROM AssetEntity a WHERE a.facility = :inFacility");
            q.setParameter("inFacility", facility);
            assetList = new ArrayList<AssetEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return assetList;
    }

    //get details for 1 existing asset
    @Override
    public AssetEntity getAsset(Long assetId) {
        ArrayList<AssetEntity> assetList = new ArrayList();
        try {
            Query q = em.createQuery("SELECT a FROM AssetEntity a WHERE a.assetId=:inId");
            q.setParameter("inId", assetId);
            assetList = new ArrayList<AssetEntity>(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (AssetEntity) assetList.get(0);
    }

    //edit an existing asset
    @Override
    public AssetEntity editAsset(Long assetId, String assetName, int assetQuantity, String assetCondition, double assetCost) {
        AssetEntity assetEntity = em.find(AssetEntity.class, assetId);
        assetEntity.setAssetName(assetName);
        assetEntity.setAssetQuantity(assetQuantity);
        assetEntity.setAssetCondition(assetCondition);
        assetEntity.setAssetCost(assetCost);
        em.flush();
        return assetEntity;
    }

    //delete the asset  
    @Override
    public void deleteAsset(Long assetId) {
        AssetEntity assetEntity = em.find(AssetEntity.class, assetId);
        if (assetEntity != null) {
            em.remove(assetEntity);
            em.flush();
        }
    }
    
    //check duplicated assets within the same facility
    @Override
    public boolean verifyAsset(String assetName, Long facilityId){
        boolean check=false;
        //get facilityId from FacilityEntity
        FacilityEntity fa  = em.find(FacilityEntity.class, facilityId);
        Query q = em.createQuery("SELECT ae FROM AssetEntity ae WHERE "
                + "ae.assetName =:inAssetName AND ae.facility =:inFacility");
        q.setParameter("inAssetName", assetName);
        q.setParameter("inFacility", fa);
        if(q.getResultList().size()==0)
            check = true; //no dulpicated names existed
        return check;
    }
}
