/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.awt.Color;
import java.io.File;
import mms2.leasing.entity.LeasingSystemRequestEntity;
import mms2.leasing.entity.LevelEntity;
import mms2.leasing.entity.LongTermApplicationEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.leasing.entity.TenantEntity;
import mms2.leasing.session.LeasingSystemRequestManagerSessionLocal;
import mms2.leasing.session.LevelManagerSessionLocal;
import mms2.leasing.session.LongTermApplicationManagerSessionLocal;
import mms2.leasing.session.UnitManagerSessionLocal;
import mms2.leasing.session.TenantContractManagerSessionLocal;
import mms2.leasing.session.TenantManagerSessionLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager.LeasingRequestManager;
import manager.LevelManager;
import manager.LongTermApplicationManager;
import manager.UnitManager;
import manager.TenantManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author PhiLong
 */
public class LeasingControllerServlet extends HttpServlet {

    @EJB
    TenantContractManagerSessionLocal tenantContractManagerSessionLocal;
    @EJB
    UnitManagerSessionLocal unitManagerSessionLocal;
    @EJB
    TenantManagerSessionLocal tenantManagerSessionLocal;
    @EJB
    LevelManagerSessionLocal levelManagerSessionLocal;
    @EJB
    LeasingSystemRequestManagerSessionLocal leasingSystemRequestManagerSessionLocal;
    @EJB
    LongTermApplicationManagerSessionLocal longTermApplicationManagerSessionLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String page = request.getPathInfo().substring(1);
            String mallName = (String) request.getSession().getAttribute("mallName");
            int numOfLevel;

            String staffPosition;
            String staffUserName;
            String levelCode;
            ArrayList<String> unitListToAddTenant;
            String leasingRequestType;
            Long leasingRequestId;
            ArrayList<LeasingSystemRequestEntity> requestList;

            switch (page) {
//START-FUNCTION FOR LEASING MANAGER
                case "ViewAllRequests":
                    page = "LeasingManagerViewAllRequests";
                    break;
                case "ViewLeasingRequestDetail":
                    System.out.println("TEST 0");
                    leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
                    System.out.println("TEST 0.1");
                    leasingRequestType = determineLeasingRequestType(leasingRequestId, request);
                    System.out.println("TEST 0.2 " + leasingRequestType);
                    if (leasingRequestType.equals("FloorplanModify")) {
                        request.getSession().setAttribute("levelCode", "LV1");
                        page = "LeasingManagerReviewFloorPlanPrototype";
                    }
                    if (leasingRequestType.equals("CategoryModify")) {
                        request.getSession().setAttribute("levelCode", "LV1");
                        page = "LeasingManagerReviewCategoryPrototype";
                    }
                    if (leasingRequestType.equals("PublicOpenBid")) {
                        request.getSession().setAttribute("levelCode", "LV1");
                        LeasingSystemRequestEntity leasingRequestInstance
                                = doGetLeasingRequestById(leasingRequestId);
                        request.getSession().setAttribute("leasingRequestInstance", leasingRequestInstance);
                        page = "LeasingManagerReviewOpenPublicBidPrototype";
                    }
                    if (leasingRequestType.equals("LongTermApplicationApproval")) {
                        LongTermApplicationEntity longTermApplication
                                = doGetLongTermApplication(request);
                        request.setAttribute("longTermApplication", longTermApplication);
                        page = "LeasingManagerReviewLongTermApplicationApproval";
                    }
                    break;
                case "ChangeFloorplanLevelReviewFloorPlanPrototype":
                    levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    page = "LeasingManagerReviewFloorPlanPrototype";
                    break;
                case "ChangeFloorplanLevelReviewCategoryPrototype":
                    levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    page = "LeasingManagerReviewCategoryPrototype";
                    break;
                case "ChangeFloorplanLevelReviewPublicOpenBidPrototype":
                    levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    page = "LeasingManagerReviewOpenPublicBidPrototype";
                    break;
                case "BackToViewAllLeasingRequests":
                    page = "LeasingManagerViewAllRequests";
                    break;
                case "AcceptLeasingRequest":
                    leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
                    leasingRequestType = determineLeasingRequestType(leasingRequestId, request);
                    if (leasingRequestType.equals("FloorplanModify")) {
                        doAcceptUpdateFloorPlanAndUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    if (leasingRequestType.equals("CategoryModify")) {
                        doAcceptUpdateCategoryAndUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    if (leasingRequestType.equals("PublicOpenBid")) {
                        doAcceptUpdateOpenBidAndUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    if (leasingRequestType.equals("LongTermApplicationApproval")) {
                        doAcceptUpdateLongTermApplicationAndUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    break;
                case "RejectLeasingRequest":
                    leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
                    leasingRequestType = determineLeasingRequestType(leasingRequestId, request);
                    if (leasingRequestType.equals("FloorplanModify")) {
                        doRejectUpdateFloorPlanAndUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    if (leasingRequestType.equals("CategoryModify")
                            || leasingRequestType.equals("LongTermApplicationApproval")) {
                        doRejectUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    if (leasingRequestType.equals("PublicOpenBid")) {
                        doRejectUpdateOpenBidAndUpdateRequest(request);
                        page = "LeasingManagerViewAllRequests";
                    }
                    break;
//END-FUNCTION FOR LEASING MANAGER
//START-FUNCTION FOR LEASING OFFICER
                case "LeasingOfficerMain":
                    request.getSession().removeAttribute("unitListToAddTenant");
                    request.getSession().removeAttribute("errorMessage");
                    request.getSession().setAttribute("levelCode", "LV1");
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "DeclareZone":
                    request.getSession().setAttribute("levelCode", "LV1");
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "SaveStoreZonePrototypeCategory":
                    String saveZoneStatus = doSaveStoreZonePrototypeCategory(request);
                    request.setAttribute("saveZoneStatus", saveZoneStatus);
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "SavePushCartOrKioskPrototypeCategory":
                    String savePushCartOrKioskStatus = doSavePushCartOrKioskPrototypeCategory(request);
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "ChangeFloorplanLevelZoneDeclare":
                    levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "AddUnitToListToAddTenant":
                    String chooseUnitStatus = doAddUnitsToListToAddTenant(request);
                    request.setAttribute("chooseUnitStatus", chooseUnitStatus);
                    page = "LeasingOfficerChooseUnitForPublicBidding";
                    break;

                case "ChooseUnitForPublicBidding":
                    request.getSession().setAttribute("levelCode", "LV1");
                    request.getSession().removeAttribute("unitListToAddTenant");
                    request.getSession().removeAttribute("errorMessage");
                    page = "LeasingOfficerChooseUnitForPublicBidding";
                    break;
                case "ChangeFloorplanLevelChooseUnitPublicBidding":
                    levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    request.getSession().removeAttribute("errorMessage");
                    page = "LeasingOfficerChooseUnitForPublicBidding";
                    break;
                //this code will reuse unitListToAddTenant for convenience
                //this list is generated in AddUnitToListToAddTenant
                case "RequestOpenUnitForPublicBidding":
                    doSetOpenPublicBiddingPrototype(request);
                    page = "LeasingOfficerComposePublicOpenBidRequest";
                    break;
                case "SendPublicOpenBidRequest":
                    doSendPublicOpenBidRequest(request);
                    request.setAttribute("sendRequestStatus", "Your request has successfully been sent");
                    request.getSession().setAttribute("levelCode", "LV1");
                    page = "LeasingOfficerZoneDeclare";
                    break;

                case "ViewAllTenants":
                    ArrayList<TenantEntity> allTenantList = doGetAllTenants(request);
                    request.getSession().setAttribute("allTenantList", allTenantList);
                    page = "LeasingOfficerViewAllTenants";
                    break;
                case "ViewTenantDetail":
                    TenantEntity tenant = doGetTenantById(request);
                    request.setAttribute("detailedTenant", tenant);
                    page = "LeasingOfficerViewTenantDetail";
                    break;
                case "ComposeCategoryRequest":
                    page = "LeasingOfficerComposeCategoryRequest";
                    break;
                case "SendCategoryRequest":
                    doSendCategoryRequest(request);
                    request.setAttribute("sendRequestStatus", "Your request has successfully been sent");
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "ViewAllPublicLongTermApplication":
                    page = "LeasingOfficerViewAllPublicLongTerm";
                    break;
                case "PrepareContractForLongTermApplicant":
                    Long applicantId = Long.parseLong(request.getParameter("applicantId"));
                    request.getSession().setAttribute("applicantId", applicantId);
                    page = "LeasingOfficerPrepareLongTermContract";
                    break;
                case "ComposeLongTermApplicationRequest":
                    doProposeLongTermApplication(request);
                    page = "LeasingOfficerComposeLongTermApplicationRequest";
                    break;
                case "SendLongTermApplicationApprovalRequest":
                    doSendLongTermApplicationApprovalRequest(request);
                    request.setAttribute("sendRequestStatus", "Your request has successfully been sent");
                    page = "LeasingOfficerZoneDeclare";
                    break;
                case "CheckLeasingOfficerRequestStatus":
                    requestList = doGetRequestsByUserName(request);
                    request.setAttribute("requestList", requestList);
                    page = "LeasingOfficerCheckRequest";
                    break;
                case "DeleteLeasingOfficerRequest":
                    doDeleteLeasingRequestById(request);
                    request.setAttribute("deleteRequestStatus", "Your request has successfully been deleted");
                    requestList = doGetRequestsByUserName(request);
                    request.setAttribute("requestList", requestList);
                    page = "LeasingOfficerCheckRequest";
                    break;
                /////case of delete and (function)list request is on space planner side
//END-FUNCTION FOR LEASING OFFICER
//START-FUNCTION FOR SPACE PLAN OFFICER
                case "SpacePlanMain":
                    boolean alreadyInitialized = doCheckInitialization(request);
                    if (!alreadyInitialized) {
                        page = "SpacePlanDeclare";
                    } else {
                        page = "SpacePlanLocate";
                        request.getSession().setAttribute("levelCode", "LV1");
                    }
                    request.getSession().setAttribute("actionToTake", "UploadFloorplanBackground");
//if already initialized, then go straight to main menu
                    break;
                case "InitializeSpacePlan":
                    request.getSession().setAttribute("actionToTake", "DeclareNumOfLevel");
                    page = "SpacePlanDeclare";
                    break;
                case "DeclareNumOfLevel":
                    if (request.getParameter("numOfLevel") != null) {
                        String numOfLevelString = (String) request.getParameter("numOfLevel");
                        numOfLevel = Integer.valueOf(numOfLevelString.trim());
                        request.getSession().setAttribute("numOfLevel", numOfLevel);
                    }
                    request.getSession().setAttribute("actionToTake", "DeclareNumOfUnitPerLevel");
                    page = "SpacePlanDeclare";
                    break;
                case "DeclareNumOfUnitPerLevel":
                    ArrayList<ArrayList<String>> levelNameNumUnitList = doGetLevelNameNumUnitList(request);
                    request.getSession().setAttribute("levelNameNumUnitList", levelNameNumUnitList);
                    for (int i = 0; i < levelNameNumUnitList.size(); i++) {
                        ArrayList<String> test = levelNameNumUnitList.get(i);
                    }
                    request.getSession().setAttribute("actionToTake", "DeclareFloorplanBackground");
                    page = "SpacePlanDeclare";
                    break;
                case "DeclareFloorplanBackground":
                    ArrayList<ArrayList<String>> levelNameNumUnitBackgroundList = doGetLevelNameNumUnitBackgroundList(request);
                    request.getSession().setAttribute("levelNameNumUnitBackgroundList", levelNameNumUnitBackgroundList);
                    request.getSession().setAttribute("actionToTake", "FinalConfirmation");
                    page = "SpacePlanDeclare";
                    break;
                case "DeclareAgain":
                    request.getSession().setAttribute("actionToTake", "DeclareNumOfLevel");
                    page = "SpacePlanDeclare";
                    break;
                case "SaveUnitPosition":
                    doAddLocalStorageInfo(request);
                    page = "SpacePlanLocate";
                    break;
                case "FinalConfirmation":
                    doCreateAllUnit(request);
                    request.getSession().setAttribute("levelCode", "LV1");
                    page = "SpacePlanLocate";
                    break;
                //////////////////////////////////ACCESS ONLY FOR REPOSITION
                case "LocateSpacePlan":
                    if (request.getSession().getAttribute("levelCode") == null) {
                        request.getSession().setAttribute("levelCode", "LV1");
                    }
                    page = "SpacePlanLocate";
                    break;
                case "ChangeFloorplanLevelSpacePlanning":
                    levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    page = "SpacePlanLocate";
                    break;
                case "AddPushCart":
                    page = "SpacePlanPushCartDeclare";
                    break;
                case "AddKiosk":
                    page = "SpacePlanKioskDeclare";
                    break;
                case "AddNumOfKioskPerLevel":
                    doAddNumOfKioskPerLevel(request);
                    page = "SpacePlanLocate";
                    break;
                case "AddEvent":
                    page = "SpacePlanEventDeclare";
                    break;
                case "AddNumOfEventPerLevel":
                    doAddNumOfEventPerLevel(request);
                    page = "SpacePlanLocate";
                    break;
                case "AddStore":
                    page = "SpacePlanStoreDeclare";
                    break;
                case "AddNumOfStorePerLevel":
                    doAddNumOfStorePerLevel(request);
                    page = "SpacePlanLocate";
                    break;
                case "AddNumOfPushCartPerLevel":
                    doAddNumOfPushCartPerLevel(request);
                    page = "SpacePlanLocate";
                    break;

                case "ComposeFloorplanRequest":
                    page = "SpacePlanComposeRequest";
                    break;
                case "SendFloorplanRequest":
                    doSendFloorplanRequest(request);
                    request.setAttribute("sendRequestStatus", "Your request has successfully been sent");
                    page = "SpacePlanLocate";
                    break;
                case "ProposeDeleteSingleUnit":
                    request.setAttribute("unitDeleteStatus", doSetUnitDelete(request));
                    page = "SpacePlanLocate";
                    break;
                case "CheckSpacePlanRequestStatus":
                    requestList = doGetRequestsByUserName(request);
                    request.setAttribute("requestList", requestList);
                    page = "SpacePlanCheckRequest";
                    break;
                case "DeleteFloorPlanRequest":
                    doDeleteLeasingRequestById(request);
                    request.setAttribute("deleteRequestStatus", "Your request has successfully been deleted");
                    requestList = doGetRequestsByUserName(request);
                    request.setAttribute("requestList", requestList);
                    page = "SpacePlanCheckRequest";
                    break;
            }

//END-FUNCTION FOR SPACE PLAN OFFICER
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////START ESSENTIALS ZONE///////////////////////////////////////////////////////////
            if (page.equals("LeasingOfficerChooseUnitForTenant")
                    || page.equals("LeasingOfficerChooseUnitForPublicBidding")) {
                numOfLevel = doGetNumOfLevel((String) request.getSession().getAttribute("mallName"));
                request.getSession().setAttribute("numOfLevel", numOfLevel);
                Vector unitColorVector = doGetAllUnitColorForCurrentMall(request);
                request.getSession().setAttribute("unitColorVector", unitColorVector);
            }
            if (page.equals("LeasingOfficerZoneDeclare")
                    || page.equals("LeasingManagerReviewCategoryPrototype")
                    || page.equals("LeasingManagerReviewOpenPublicBidPrototype")) {
                numOfLevel = doGetNumOfLevel((String) request.getSession().getAttribute("mallName"));
                request.getSession().setAttribute("numOfLevel", numOfLevel);
                Vector unitColorVector = doGetAllPrototypeUnitColorForCurrentMall(request);
                request.setAttribute("unitColorVector", unitColorVector);
            }
            if (page.equals("SpacePlanLocate") || page.equals("LeasingOfficerZoneDeclare")
                    || page.equals("LeasingOfficerChooseUnitForTenant")
                    || page.equals("LeasingManagerReviewFloorPlanPrototype")
                    || page.equals("LeasingManagerReviewCategoryPrototype")
                    || page.equals("LeasingOfficerChooseUnitForPublicBidding")
                    || page.equals("LeasingManagerReviewOpenPublicBidPrototype")) {
                numOfLevel = doGetNumOfLevel((String) request.getSession().getAttribute("mallName"));
                request.getSession().setAttribute("numOfLevel", numOfLevel);
                LevelEntity levelInstance = doGetLevel(request);
                request.getSession().setAttribute("levelInstance", levelInstance);
            }
//            if (page.equals("LeasingOfficerAddTenantAndContractInformation")) {
//                mallName = (String) request.getSession().getAttribute("mallName");
//                String locationCode = ((ArrayList<String>) request.getSession().getAttribute("unitListToAddTenant")).get(0);
//
//                ArrayList<String> businessTypeList = doGetUnitBusinessTypeList(mallName, locationCode);
//                request.getSession().setAttribute("businessTypeList", businessTypeList);
//            }
            if (page.equals("LeasingManagerViewAllRequests")) {
                numOfLevel = doGetNumOfLevel((String) request.getSession().getAttribute("mallName"));
                request.getSession().setAttribute("numOfLevel", numOfLevel);
                ArrayList<LeasingSystemRequestEntity> leasingRequestList
                        = doGetListOfAllLeasingRequests(request);
                request.getSession().setAttribute("leasingRequestList", leasingRequestList);
            }
            if (page.equals("LeasingOfficerViewAllPublicLongTerm")) {
                numOfLevel = doGetNumOfLevel((String) request.getSession().getAttribute("mallName"));
                request.getSession().setAttribute("numOfLevel", numOfLevel);
                ArrayList<LongTermApplicationEntity> longTermApplicationList
                        = doGetLongTermApplicationList(request);
                request.setAttribute("longTermApplicationList", longTermApplicationList);
            }

            dispatcher = servletContext.getNamedDispatcher(page);
            dispatcher.forward(request, response);
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////END ESSENTIALS ZONE///////////////////////////////////////////////////////////
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////START FOR LEASING MANAGER///////////////////////////////////////////////////

    public ArrayList<LeasingSystemRequestEntity> doGetListOfAllLeasingRequests(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        return leasingRequestManager.getListOfAllLeasingRequests(request);
    }

    public String determineLeasingRequestType(Long leasingRequestId, HttpServletRequest request) {
        LeasingSystemRequestEntity lre = leasingSystemRequestManagerSessionLocal.getLeasingRequestById(leasingRequestId);
        return lre.getType();
    }

    public void doAcceptUpdateFloorPlanAndUpdateRequest(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        levelManager.implementPrototypeFloorPlan(mallName);
        leasingRequestManager.updateLeasingRequestStatus(leasingRequestId, "ACCEPTED");
    }

    public void doAcceptUpdateCategoryAndUpdateRequest(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        levelManager.implementPrototypeCategory(mallName);
        leasingRequestManager.updateLeasingRequestStatus(leasingRequestId, "ACCEPTED");
    }

    public void doAcceptUpdateOpenBidAndUpdateRequest(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        levelManager.implementPrototypePublicOpenBid(mallName, leasingRequestId);
        leasingRequestManager.updateLeasingRequestStatus(leasingRequestId, "ACCEPTED");
    }

    public void doRejectUpdateOpenBidAndUpdateRequest(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        levelManager.rejectPrototypePublicOpenBid(mallName, leasingRequestId);
        leasingRequestManager.updateLeasingRequestStatus(leasingRequestId, "ACCEPTED");
    }

    public void doRejectUpdateFloorPlanAndUpdateRequest(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        String mallName = (String) request.getSession().getAttribute("mallName");
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        levelManager.rejectPrototypeFloorPlan(mallName);
        leasingRequestManager.updateLeasingRequestStatus(leasingRequestId, "REJECTED");
    }

    public void doRejectUpdateRequest(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        leasingRequestManager.updateLeasingRequestStatus(leasingRequestId, "REJECTED");
    }

    public LeasingSystemRequestEntity doGetLeasingRequestById(Long leasingRequestId) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        return leasingRequestManager.getRequestById(leasingRequestId);
    }

    public LongTermApplicationEntity doGetLongTermApplication(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        Long applicationId = leasingRequestManager.getLongTermApplicationId(request);
        LongTermApplicationManager longTermManager = new LongTermApplicationManager(longTermApplicationManagerSessionLocal);
        LongTermApplicationEntity longTermApplication
                = longTermManager.getLongTermApplicationById(applicationId);
        return longTermApplication;
    }

    public void doAcceptUpdateLongTermApplicationAndUpdateRequest(HttpServletRequest request) {
        Long leasingRequestId = Long.parseLong(request.getParameter("leasingRequestId"));
        String mallName = (String) request.getSession().getAttribute("mallName");
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        LeasingSystemRequestEntity leasingRequest = leasingRequestManager.getRequestById(leasingRequestId);
        Long applicationId = leasingRequest.getApplicationId();
        TenantManager tenantManager = new TenantManager(tenantManagerSessionLocal);
        tenantManager.createPendingTenantAndContractForApprovedLongTermApplication(applicationId);
        leasingRequestManager.deleteAllCollideRequestAndApplication(leasingRequestId, applicationId);
    }
///////////////////////////////////END FOR LEASING MANAGER/////////////////////////////////////////////////// 
///////////////////////////////////START FOR SPACE PLANNER///////////////////////////////////////////////////

    public int doGetNumOfLevel(String mallName) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        return levelManager.getNumOfLevel(mallName);
    }

    public void doAddLocalStorageInfo(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        levelManager.addLocalStorageInfo(request);
    }

    public LevelEntity doGetLevel(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        return levelManager.getLevel(request);
    }

    public ArrayList<ArrayList<String>> doGetLevelNameNumUnitList(HttpServletRequest request) {
        ArrayList<ArrayList<String>> result = new ArrayList();
        int numOfLevel = (int) request.getSession().getAttribute("numOfLevel");
        for (int i = 1; i <= numOfLevel; i++) {
            ArrayList<String> levelCodeList = new ArrayList();
            levelCodeList.add("LV" + "" + i);
            result.add(levelCodeList);
        }
        for (int i = 0; i < result.size(); i++) {
            String levelCode = result.get(i).get(0);
            String numOfUnit = request.getParameter("numOfUnitOn" + levelCode).trim();
            result.get(i).add(numOfUnit);
        }
        return result;
    }

    public ArrayList<ArrayList<String>> doGetLevelNameNumUnitBackgroundList(HttpServletRequest request) {
        ArrayList<ArrayList<String>> result = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("levelNameNumUnitList");

        for (int i = 0; i < result.size(); i++) {
            String levelCode = result.get(i).get(0);
            String floorplanBackground = request.getParameter("floorplanBackground" + levelCode);
            result.get(i).add(floorplanBackground);
        }
        return result;
    }

    public void doCreateAllUnit(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        String mallName = (String) request.getSession().getAttribute("mallName");
        ArrayList<ArrayList<String>> levelNameNumUnitBackgroundList = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("levelNameNumUnitBackgroundList");
        for (int i = 0; i < levelNameNumUnitBackgroundList.size(); i++) {
            ArrayList<String> levelNameNumUnitBackground = levelNameNumUnitBackgroundList.get(i);
            String levelCode = levelNameNumUnitBackground.get(0);
            int numOfUnit = Integer.parseInt(levelNameNumUnitBackground.get(1));
            String floorplanBackground = levelNameNumUnitBackground.get(2);
            LevelEntity level = levelManager.createLevel(mallName, levelCode, floorplanBackground);
            for (int j = 1; j <= numOfUnit; j++) {
                String locationCode = levelCode + "ST" + j;
                UnitEntity unit = unitManager.createUnit(level, mallName, locationCode);
            }
        }
    }

    public void doAddNumOfPushCartPerLevel(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        unitManager.addPushCartsToEachLevel(request);
    }

    public void doAddNumOfKioskPerLevel(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        unitManager.addKiosksToEachLevel(request);
    }

    public void doAddNumOfEventPerLevel(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        unitManager.addEventsToEachLevel(request);
    }

    public void doAddNumOfStorePerLevel(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        unitManager.addStoresToEachLevel(request);
    }

    public void doSendFloorplanRequest(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        leasingRequestManager.addFloorplanModificationRequest(request);
    }

    public String doSetUnitDelete(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.setUnitDelete(request);
    }

    public boolean doCheckInitialization(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        return levelManager.checkInitialization(request);
    }

    public ArrayList<LeasingSystemRequestEntity> doGetRequestsByUserName(HttpServletRequest request) {
        LeasingRequestManager requestManager = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        return requestManager.getRequestsByUserName(request);
    }

    public void doDeleteLeasingRequestById(HttpServletRequest request) {
        LeasingRequestManager requestManager = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        requestManager.deleteLeasingRequestById(request);
    }

    public void doGetChart() {
        PieDataset pieDataset = createDataset();
        JFreeChart chart = ChartFactory.createPieChart("Tenant Mix ", 
                pieDataset, true, true, false);
        //chart.setBackgroundPaint(new Color(222, 222, 255));
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}"));
        plot.setCircular(true);
        plot.setSectionPaint("F&B", new Color(15,192,252));
        try {
            final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
            final File file1 = new File(getServletContext().getRealPath("")+ "/leasingSystem/leasingSystemAssets/chart/piechart.png");
            ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    private PieDataset createDataset(){
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("F&B", 25);
        result.setValue("Retail", 0);
        result.setValue("Entertainment", 25);
        result.setValue("Event", 25);
        return result;
    }
///////////////////////////////////END FOR SPACE PLANNER///////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////START FOR LEASING OFFICER///////////////////////////////////////////////

    public String doSaveStoreZonePrototypeCategory(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.saveStoreZonePrototypeCategory(request);
    }

    public void doSetOpenPublicBiddingPrototype(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        unitManager.setOpenPublicBiddingPrototype(request);
    }

    public String doSavePushCartOrKioskPrototypeCategory(HttpServletRequest servlet) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.savePushCartOrKioskPrototypeCategory(servlet);
    }

    public Vector doGetAllUnitColorForCurrentMall(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.getAllUnitColorForCurrentMall(request);
    }

    public Vector doGetAllPrototypeUnitColorForCurrentMall(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.getAllPrototypeUnitColorForCurrentMall(request);
    }

    public String doAddUnitsToListToAddTenant(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.AddUnitsToListToAddTenant(request);
    }

    public ArrayList<String> doGetUnitBusinessTypeList(String mallName, String locationCode) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.getUnitBusinessTypeList(mallName, locationCode);
    }

    public ArrayList<TenantEntity> doGetAllTenants(HttpServletRequest request) {
        TenantManager tenantManager = new TenantManager(tenantManagerSessionLocal);
        return tenantManager.getAllTenants(request);
    }

    public TenantEntity doGetTenantById(HttpServletRequest request) {
        TenantManager tenantManager = new TenantManager(tenantManagerSessionLocal);
        return tenantManager.getTenantById(request);
    }

    public void doSendCategoryRequest(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        leasingRequestManager.addCategoryModificationRequest(request);
    }

    public void doSendPublicOpenBidRequest(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        leasingRequestManager.addPublicOpenBidRequest(request);
    }

    public ArrayList<LongTermApplicationEntity> doGetLongTermApplicationList(HttpServletRequest request) {
        LongTermApplicationManager applicationManager
                = new LongTermApplicationManager(longTermApplicationManagerSessionLocal);
        return applicationManager.getLongTermApplicationList(request);
    }

    public void doProposeLongTermApplication(HttpServletRequest request) {
        Long applicantId = (Long) request.getSession().getAttribute("applicantId");
        LongTermApplicationManager applicationManager
                = new LongTermApplicationManager(longTermApplicationManagerSessionLocal);
        applicationManager.addContractToLongTermApplication(request);

    }

    public void doSendLongTermApplicationApprovalRequest(HttpServletRequest request) {
        LeasingRequestManager leasingRequestManager
                = new LeasingRequestManager(leasingSystemRequestManagerSessionLocal);
        leasingRequestManager.addPublicApplicationApprovalRequest(request);
    }

///////////////////////////////////END FOR LEASING OFFICER///////////////////////////////////////////////
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
