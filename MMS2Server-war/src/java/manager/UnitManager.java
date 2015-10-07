/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import mms2.leasing.entity.LevelEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.leasing.session.UnitManagerSessionLocal;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;

/**
 *
 * @author PhiLong
 */
public class UnitManager {

    UnitManagerSessionLocal unitManagerSessionLocal;

    public UnitManager(UnitManagerSessionLocal unitManagerSessionLocal) {
        this.unitManagerSessionLocal = unitManagerSessionLocal;
    }

    public UnitEntity createUnit(LevelEntity level, String mallName, String locationCode) {
        return unitManagerSessionLocal.createUnit(level, mallName, locationCode);
    }

    public void declareUnitInformation(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        String locationCode = (String) request.getSession().getAttribute("locationCode");
        String category = (String) request.getSession().getAttribute("category");
        unitManagerSessionLocal.declareUnitPrototypeCategory(mallName, locationCode, category);
    }

    public String saveStoreZonePrototypeCategory(HttpServletRequest request) {
        String firstLocationCode = request.getParameter("firstLocationCode").substring(5);
        String lastLocationCode = request.getParameter("lastLocationCode").substring(5);
        String LVST = request.getSession().getAttribute("levelCode") + "ST";
        String mallName = (String) request.getSession().getAttribute("mallName");
        String category = request.getParameter("category");
        Integer first = Integer.valueOf(firstLocationCode);
        Integer last = Integer.valueOf(lastLocationCode);
        if (last < first) {
            Integer temp = last;
            last = first;
            first = temp;
        }
        for (int i = first; i <= last; i++) {
            String locationCode = LVST + "" + String.valueOf(i);
            UnitEntity unit = getUnitByMallNameAndLocationCode(mallName, locationCode);
            if (unit.isHasTenant()) 
                return "Unsuccessful! Cannot recategorize unavailable unit(s)";
            if(unit.isHasPendingTenant())
                return "Unsuccessful! Cannot recategorize unit(s) with pending tenant";
            if(unit.isOpenForInternalBidding())
                return "Unsuccessful! Cannot recategorize unit(s) that is already opened for internal bidding";
            if(unit.isOpenForPublicBidding())
                return "Unsuccessful! Cannot recategorize unit(s) that is already opened for open bidding";
        }
        for (int i = first; i <= last; i++) {
            String locationCode = LVST + "" + String.valueOf(i);
            unitManagerSessionLocal.declareUnitPrototypeCategory(mallName, locationCode, category);
        }
        return "Successful! Zone category prototype has been modified";
    }

    public Vector getAllUnitColorForCurrentMall(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        return unitManagerSessionLocal.getAllUnitColorForCurrentMall(mallName);
    }

    public Vector getAllPrototypeUnitColorForCurrentMall(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        return unitManagerSessionLocal.getAllPrototypeUnitColorForCurrentMall(mallName);
    }

    public ArrayList<String> getUnitBusinessTypeList(String mallName, String locationCode) {
        UnitEntity unit = getUnitByMallNameAndLocationCode(mallName, locationCode);
        String category = unit.getCategory();
        ArrayList<String> list = new ArrayList();
        if(unit.isKiosk()){
            list.add("Kiosk");
            return list;
        }
        if(unit.isPushCart()){
            list.add("PushCart");
            return list;
        }
        if (category.equals("Food&Beverage")) {
            list.add("Food court");
            list.add("Restaurant/Outlet");
        } else if (category.equals("Retail")) {
            list.add("Supermarket");
            list.add("Department store");
            list.add("Outlet");
        } else if (category.equals("Entertainment")) {
            list.add("Entertainment");
        } else {
            list.add("Event");
        }
        return list;
    }

    public String AddUnitsToListToAddTenant(HttpServletRequest request) {
        ArrayList<String> unitListToAddTenant = new ArrayList();
        if (request.getSession().getAttribute("unitListToAddTenant") != null) {
            unitListToAddTenant = (ArrayList<String>) request.getSession().getAttribute("unitListToAddTenant");
        }
        ArrayList<UnitEntity> pendingUnitList = new ArrayList();
        String firstLocationCode = request.getParameter("firstLocationCode").substring(5);
        String lastLocationCode = request.getParameter("lastLocationCode").substring(5);
        String unitTypeCode = request.getSession().getAttribute("levelCode") + request.getParameter("firstLocationCode").substring(3, 5);
        String mallName = (String) request.getSession().getAttribute("mallName");
        Integer first = Integer.valueOf(firstLocationCode);
        Integer last = Integer.valueOf(lastLocationCode);
        if (last < first) {
            Integer temp = last;
            last = first;
            first = temp;
        }
        for (int i = first; i <= last; i++) {
            String locationCode = unitTypeCode + "" + String.valueOf(i);
            UnitEntity unit = unitManagerSessionLocal.getUnitByMallNameAndLocationCode(mallName, locationCode);
            pendingUnitList.add(unit);
        }
        boolean currentTenantExpireSoon= checkCurrentTenantExpireSoon(pendingUnitList);
        if(!currentTenantExpireSoon){
            return "Unsuccessful! Tenant of unit is not expiring";
        }
        
        boolean alreadyInOfficialList = checkPendingUnitListAlreadyInUnitListToAddTenant(unitListToAddTenant,
                pendingUnitList);
        if (alreadyInOfficialList) {
            return "Unsuccessful! Unit(s) collide with chosen ones";
        }
        boolean alreadyHasTenant = checkPendingUnitListAlreadyHasPendingTenant(pendingUnitList);
        if (alreadyHasTenant) {
            return "Unsuccessful! Unit(s) already have pending tenant";
        }
        boolean categoryNotDefined = checkCategoryNotDefined(pendingUnitList);
        if (categoryNotDefined) {
            return "Unsuccessful! Category of unit(s) not defined yet";
        }
        boolean alreadyOpenForPublicBidding = checkPublicBiddingOpen(pendingUnitList);
        if (alreadyOpenForPublicBidding) {
            return "Unsuccessful! Unit(s) is already opened for public bidding";
        }
        boolean alreadySuggestPublicBidding = checkPublicBiddingSuggest(pendingUnitList);
        if (alreadySuggestPublicBidding) {
            return "Unsuccessful Unit(s) already suggested for public bidding";
        }
        boolean alreadyOpenForInternalBidding = checkInternalBiddingOpen(pendingUnitList);
        if (alreadyOpenForInternalBidding) {
            return "Unsuccessful! Unit(s) is already opened for internal bidding";
        }
        boolean alreadySuggestInternalidding = checkInternalBiddingSuggest(pendingUnitList);
        if (alreadySuggestInternalidding) {
            return "Unsuccessful! Unit(s) already suggested for internal bidding";
        }
        //if the list is qualified
        for (int i = 0; i < pendingUnitList.size(); i++) {
            UnitEntity unit = pendingUnitList.get(i);
            unitListToAddTenant.add(unit.getLocationCode());
        }
        request.getSession().setAttribute("unitListToAddTenant", unitListToAddTenant);
        return "Successful!";
    }

    private boolean checkCategoryDifferent(String mallName, ArrayList<String> unitListToAddTenant,
            ArrayList<UnitEntity> pendingUnitList) {
        String firstPendingUnitCategory = pendingUnitList.get(0).getCategory();
        for (int i = 1; i < pendingUnitList.size(); i++) {
            String category = pendingUnitList.get(i).getCategory();
            if (!(category.equals(firstPendingUnitCategory))) {
                return true;
            }
        }
        if (!unitListToAddTenant.isEmpty()) {

            UnitEntity firstOfficialUnit = getUnitByMallNameAndLocationCode(mallName, unitListToAddTenant.get(0));
            String currentCategoryInList = firstOfficialUnit.getCategory();
            if (!(currentCategoryInList.equals(firstPendingUnitCategory))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCategoryNotDefined(ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            if (pendingUnitList.get(i).getCategory().equals("")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPublicBiddingOpen(ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            if (pendingUnitList.get(i).isOpenForPublicBidding()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPublicBiddingSuggest(ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            if (pendingUnitList.get(i).isOpenForPublicBiddingPrototype()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInternalBiddingOpen(ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            if (pendingUnitList.get(i).isOpenForInternalBidding()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkInternalBiddingSuggest(ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            if (pendingUnitList.get(i).isOpenForInternalBiddingPrototype()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPendingUnitListAlreadyHasTenant(ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            UnitEntity unit = pendingUnitList.get(i);
            if (unit.isHasTenant()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkPendingUnitListAlreadyHasPendingTenant(ArrayList<UnitEntity> pendingUnitList){
        for (int i = 0; i < pendingUnitList.size(); i++) {
            UnitEntity unit = pendingUnitList.get(i);
            if (unit.isHasPendingTenant()) {
                return true;
            }
        }
        return false;
    }
    private boolean checkCurrentTenantExpireSoon(ArrayList<UnitEntity> pendingUnitList){
        for (int i = 0; i < pendingUnitList.size(); i++) {
            UnitEntity unit = pendingUnitList.get(i);
            if (!unit.isHasTenant()) {
                return true;
            }else{
                System.out.println("UnitManager.java: this unit has tenant, checking for expiry...");
                TenantEntity tenant = unit.getTenant();
                TenantContractEntity contract = tenant.getTenantContract();
                java.util.Date date= new java.util.Date();
                long contractEnd = contract.getEndTimestamp().getTime();
                long currentTime =date.getTime();
                int difference = (int) ((((contractEnd-currentTime)/1000)/86400)/30);
                System.out.println("UnitManager.java: expired in "+difference+"month");
                return difference <= 3;
            }
        }
        return false;
    }
    
    private boolean checkPendingUnitListAlreadyInUnitListToAddTenant(ArrayList<String> unitListToAddTenant,
            ArrayList<UnitEntity> pendingUnitList) {
        for (int i = 0; i < pendingUnitList.size(); i++) {
            UnitEntity unit = pendingUnitList.get(i);
            String locationCode = unit.getLocationCode();
            for (int j = 0; j < unitListToAddTenant.size(); j++) {
                if (locationCode.equals(unitListToAddTenant.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public UnitEntity getUnitByMallNameAndLocationCode(String mallName, String locationCode) {
        return unitManagerSessionLocal.getUnitByMallNameAndLocationCode(mallName, locationCode);
    }

    public void addPushCartsToEachLevel(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
        ArrayList<String> numPushCartPerLevelList = new ArrayList();
        for (int i = 1; i <= numOfLevel; i++) {
            String numOfPushCart = request.getParameter("numOfPushCartToAddLevel" + i);
            numPushCartPerLevelList.add(numOfPushCart);
        }
        unitManagerSessionLocal.addPushCartsToEachLevel(mallName, numPushCartPerLevelList);
    }

    public void addKiosksToEachLevel(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
        ArrayList<String> numKioskPerLevelList = new ArrayList();
        for (int i = 1; i <= numOfLevel; i++) {
            String numOfKiosk = request.getParameter("numOfKioskToAddLevel" + i);
            numKioskPerLevelList.add(numOfKiosk);
        }
        unitManagerSessionLocal.addKiosksToEachLevel(mallName, numKioskPerLevelList);
    }

    public void addEventsToEachLevel(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
        ArrayList<String> numEventPerLevelList = new ArrayList();
        for (int i = 1; i <= numOfLevel; i++) {
            String numOfEvent = request.getParameter("numOfEventToAddLevel" + i);
            numEventPerLevelList.add(numOfEvent);
        }
        unitManagerSessionLocal.addEventsToEachLevel(mallName, numEventPerLevelList);
    }

    public void addStoresToEachLevel(HttpServletRequest request) {
        String mallName = (String) request.getSession().getAttribute("mallName");
        int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
        ArrayList<String> numStorePerLevelList = new ArrayList();
        for (int i = 1; i <= numOfLevel; i++) {
            String numOfStore = request.getParameter("numOfStoreToAddLevel" + i);
            numStorePerLevelList.add(numOfStore);
        }
        unitManagerSessionLocal.addStoresToEachLevel(mallName, numStorePerLevelList);
    }

    public String savePushCartOrKioskPrototypeCategory(HttpServletRequest request) {
        String pushCartOrKioskLocationCode = request.getParameter("pushCartOrKioskLocationCode");
        String mallName = (String) request.getSession().getAttribute("mallName");
        String category = request.getParameter("pushCartOrKioskCategory");
        UnitEntity unit = getUnitByMallNameAndLocationCode(mallName, pushCartOrKioskLocationCode);
        if (unit.isHasTenant()) {
            return "Unavailable";
        }
        unitManagerSessionLocal.declareUnitPrototypeCategory(mallName, pushCartOrKioskLocationCode, category);
        return "Succeed";
    }

    public String setUnitDelete(HttpServletRequest request) {
        String locationCode = request.getParameter("locationCode");
        String mallName = (String) request.getSession().getAttribute("mallName");
        return unitManagerSessionLocal.setUnitDelete(mallName, locationCode);
    }

    public void setOpenPublicBiddingPrototype(HttpServletRequest request) {
        ArrayList<String> chosenUnitList = (ArrayList<String>) request.getSession().getAttribute("unitListToAddTenant");
        String mallName = (String) request.getSession().getAttribute("mallName");
        unitManagerSessionLocal.setOpenPublicBiddingPrototype(mallName, chosenUnitList);
    }

    public String AddUnitToApplyUnitList(HttpServletRequest request) {
        ArrayList<String> applyUnitList = new ArrayList();
        if (request.getSession().getAttribute("applyUnitList") != null) {
            applyUnitList = (ArrayList<String>) request.getSession().getAttribute("applyUnitList");
        }
        ArrayList<UnitEntity> pendingUnit = new ArrayList();
        String locationCode = request.getParameter("locationCode");
        String mallName = (String) request.getSession().getAttribute("mallName");
        ArrayList<UnitEntity> pendingUnitList = new ArrayList();
        UnitEntity unit = unitManagerSessionLocal.getUnitByMallNameAndLocationCode(mallName, locationCode);
        pendingUnitList.add(unit);
        boolean alreadyInOfficialList = checkPendingUnitListAlreadyInUnitListToAddTenant(
                applyUnitList ,pendingUnitList);
        if (alreadyInOfficialList) {
            return "Unsuccessful! Unit(s) collide with chosen ones";
        }
        boolean differentInCategory = checkCategoryDifferent(mallName, applyUnitList
                , pendingUnitList);
        if (differentInCategory) {
            return "Unsuccessful! Unit(s) are of different categories";
        }
        applyUnitList.add(unit.getLocationCode());
        request.getSession().setAttribute("applyUnitList", applyUnitList);
        return "Successful!";
    }
    
    public ArrayList<String> getMallListWithOpenPublicUnit(){
        return unitManagerSessionLocal.getMallListWithOpenPublicUnit();
    }
}
