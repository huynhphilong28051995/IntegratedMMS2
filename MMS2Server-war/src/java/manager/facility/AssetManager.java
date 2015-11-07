/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.facility;

import javax.servlet.http.HttpServletRequest;
import mms.facility.entity.AssetEntity;
import mms.facility.session.AssetManagerSessionLocal;

/**
 *
 * @author linjiao_Zoe
 */
public class AssetManager {

    AssetManagerSessionLocal assetManagerSessionLocal;

    public AssetManager(AssetManagerSessionLocal assetManagerSessionLocal) {
        this.assetManagerSessionLocal = assetManagerSessionLocal;
    }

    public AssetEntity createNewAsset(HttpServletRequest request) {
        long facilityId = Long.parseLong((String) request.getSession().getAttribute("facilityId"));
        request.removeAttribute("facilityId");
        String assetName = request.getParameter("assetName");
        int assetQuantity = Integer.parseInt(request.getParameter("assetQuantity"));
        String assetCondition = request.getParameter("assetCondition");
        double assetCost = Double.parseDouble(request.getParameter("assetCost"));
        return assetManagerSessionLocal.addAsset(assetName, assetQuantity, assetCondition, assetCost, facilityId);
    }

    public AssetEntity getAsset(HttpServletRequest request) {
        Long assetId = Long.parseLong(request.getParameter("assetId"));
        return assetManagerSessionLocal.getAsset(assetId);
    }

    public AssetEntity editAsset(HttpServletRequest request) {
        Long assetId = (long) request.getSession().getAttribute("assetId");
        request.removeAttribute("assetId");
        String assetName = request.getParameter("assetName");
        int assetQuantity = Integer.parseInt(request.getParameter("assetQuantity"));
        String assetCondition = request.getParameter("assetCondition");
        double assetCost = Double.parseDouble(request.getParameter("assetCost"));
        return assetManagerSessionLocal.editAsset(assetId, assetName, assetQuantity, assetCondition, assetCost);
    }

    public void deleteAsset(HttpServletRequest request) {
        Long assetId = Long.parseLong(request.getParameter("assetId"));
        assetManagerSessionLocal.deleteAsset(assetId);
    }

}
