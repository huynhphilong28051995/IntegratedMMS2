/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.util.ArrayList;
import javax.ejb.Local;
import mms.facility.entity.AssetEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Local
public interface AssetManagerSessionLocal {
    
    public AssetEntity addAsset(String assetName, int assetQuantity, String assetCondition, double assetCost, long facilityId);

    public ArrayList<AssetEntity> listAsset(long facilityId);
    
    public AssetEntity getAsset(Long assetId);

    public AssetEntity editAsset(Long assetId, String assetName, int assetQuantity, String assetCondition, double assetCost);
    
    public void deleteAsset(Long assetId);  

    public boolean verifyAsset(String assetName, Long facilityId);
    }
