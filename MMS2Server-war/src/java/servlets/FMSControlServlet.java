/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import mms.facility.entity.AssetEntity;
import mms.facility.entity.ContractorEntity;
import mms.facility.entity.FacilityEntity;
import mms.facility.entity.FeedbackEntity;
import mms.facility.entity.OutsourcingEntity;
import mms.facility.entity.ServiceEntity;
import mms.facility.session.AssetManagerSessionLocal;
import mms.facility.session.ContractorManagerSessionLocal;
import mms.facility.session.FacilityManagerSessionLocal;
import mms.facility.session.FeedbackManagerSessionLocal;
import mms.facility.session.OutsourcingManagerSessionLocal;
import mms.facility.session.ServiceManagerSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.facility.AssetManager;
import manager.facility.ContractorManager;
import manager.facility.FacilityManager;
import manager.facility.FeedbackManager;
import manager.facility.OutsourcingManager;
import manager.facility.ServiceManager;

/**
 *
 * @author linjiao_Zoe
 */
@WebServlet(name = "FMSOfficer", urlPatterns = {"/FMSOfficer"})
public class FMSControlServlet extends HttpServlet {

    @EJB
    private FacilityManagerSessionLocal facilityManagerSessionLocal;
    @EJB
    private ContractorManagerSessionLocal contractorManagerSessionLocal;
    @EJB
    private ServiceManagerSessionLocal serviceManagerSessionLocal;
    @EJB
    private AssetManagerSessionLocal assetManagerSessionLocal;
    @EJB
    private FeedbackManagerSessionLocal feedbackManagerSessionLocal;
    @EJB
    private OutsourcingManagerSessionLocal outsourcingManagerSessionLocal;

    @Override
    public void init() {
        System.out.println("FMSControlServlet: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("FMSControlServlet: processRequest()");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String tempFacilityId = null;

            //Define JSP Path
            String page = request.getPathInfo();
            page = page.substring(1);

            /**
             * **********************Main Page*************************
             */
            if ("directLogin".equals(page)) {
                String role = request.getParameter("userRole");
                String mallName = request.getParameter("mallName");
                request.getSession().setAttribute("userRole", role);
                request.getSession().setAttribute("mallName", mallName);
                if (role.equals("Facility Officer")) {
                    page = "FacilityOfficerIndexpage";
                } else {
                    page = "FacilityManagerIndexpage";
                }
            }

            /**
             * *****************Facility Manager Page********************
             */
            if ("FacilityManagerIndexpage".equals(page)) {
                //no attributes to set           
            } else if ("listMFacility".equals(page)) { //listFacility()
                page = "FacilityManagerListFacility";
            } else if ("listMAsset".equals(page)) { //listAsset()
                page = "FacilityManagerListAsset";
            } else if ("listMContractor".equals(page)) { //listContractor()
                page = "FacilityManagerListContractor";
            } else if ("listMServiceRequest".equals(page)) { //listServiceRequest()
                page = "FacilityManagerListServiceRequest";
            } else if ("listOutsourcingRequest".equals(page)) {
                page = "FacilityManagerListOutsourcing";
            } else if ("readOutsourcingRequest".equals(page)) { //listOutsourcingRequest()
                page = "FacilityManagerViewOutsourcing";
            } else if ("submitRespond".equals(page)) {
                String status = request.getParameter("status");
                outsourcingManagerSessionLocal.updateStatus(Long.parseLong(status.substring(8)),
                        status.substring(0, 8));
                request.setAttribute("submitRespondStatus", "Respond has been successfully submitted!");
                page = "FacilityManagerIndexpage";
            } else if ("backToManagerIndexPage".equals(page)) {
                page = "FacilityManagerIndexpage";
            }

            // START ESSENTIAL ZONE <page in this zone is the name of page want to go to>
            if (page.equals("FacilityManagerListFacility")) {
                List data = facilityManagerSessionLocal.listFacility((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityManagerListAsset")) {
                tempFacilityId = request.getParameter("facilityId");
                List data = assetManagerSessionLocal.listAsset(Long.parseLong(tempFacilityId));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityManagerListContractor")) {
                List data = contractorManagerSessionLocal.listContractor((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityManagerListServiceRequest")) {
                List data = serviceManagerSessionLocal.listServiceRequest((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityManagerListOutsourcing")) { //listOutsourcing()
                List data = outsourcingManagerSessionLocal.listOutsourcing((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityManagerViewOutsourcing")) { //listOutsourcingById()
                Long id = Long.parseLong(request.getParameter("outsourcingId"));
                request.setAttribute("data", outsourcingManagerSessionLocal.getOutsourcing(id));
            }
            // END ESSENTIAL ZONE

            /**
             * *****************Facility Office Page********************
             */
            if ("FacilityOfficerIndexpage".equals(page)) {
                //no attributes to set           
            } else if ("addFacility".equals(page)) { //addFacility()
                List data = contractorManagerSessionLocal.listContractor((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
                page = "FacilityOfficerAddFacility";
            } else if ("saveFacility".equals(page)) {
                String faciName = request.getParameter("facilityName");
                String faciLoca = request.getParameter("facilityLocation");
                if (facilityManagerSessionLocal.verifyFacility(faciName,
                        (String) request.getSession().getAttribute("mallName"), faciLoca)) { //verifyFacility()
                    addFacility(faciName, faciLoca, request);
                    request.setAttribute("addFacilityStatus", "Facility has been successfully added!");
                } else {
                    request.setAttribute("alertStatus", "Error! You are not allowed to create same facility at same location. Please check your input carefully. Thank You!");
                }
                page = "FacilityOfficerIndexpage";
            } else if ("listFacility".equals(page)) { //listFacility()
                page = "FacilityOfficerListFacility";
            } else if ("editFacility".equals(page)) { //editFacility()
                page = "FacilityOfficerEditFacility";
            } else if ("changeFacility".equals(page)) {
                editFacility(request);
                request.setAttribute("editFacilityStatus", "Edit facility successfully!");
                page = "FacilityOfficerListFacility";
            } else if ("deleteFacility".equals(page)) { //deleteFacility()
                Long id = Long.parseLong(request.getParameter("facilityId"));
                deleteFacilityById(request);
                request.setAttribute("deleteFacilityStatus", "The facility has been successfully deleted!");
                page = "FacilityOfficerListFacility";
            } else if ("addAsset".equals(page)) { //addAsset()
                tempFacilityId = request.getParameter("facilityId");
                request.getSession().setAttribute("facilityId", tempFacilityId);
                page = "FacilityOfficerAddAsset";
            } else if ("saveAsset".equals(page)) {
                String assName = request.getParameter("assetName");
                if (assetManagerSessionLocal.verifyAsset(assName,
                        Long.parseLong((String) request.getSession().getAttribute("facilityId")))) { //verifyAsset()
                    addAsset(request);
                    request.setAttribute("addAssetStatus", "Asset has been successfully added!");
                } else {
                    request.setAttribute("alertAssetStatus", "Asset name cannot be duplicated. Please input the differnt name. Thank You!");
                }
                page = "FacilityOfficerListFacility";
            } else if ("listAsset".equals(page)) { //listAsset()
                page = "FacilityOfficerListAsset";
            } else if ("editAsset".equals(page)) { //editAsset()
                page = "FacilityOfficerEditAsset";
            } else if ("changeAsset".equals(page)) {
                editAsset(request);
                request.setAttribute("editAssetStatus", "Edit asset successfully!");
                page = "FacilityOfficerListFacility";
            } else if ("deleteAsset".equals(page)) { //deleteAsset()
                Long id = Long.parseLong(request.getParameter("assetId"));
                deleteAssetById(request);
                request.setAttribute("deleteAssetStatus", "The asset has been successfully deleted!");
                page = "FacilityOfficerListFacility";
            } else if ("backToListFacilityPage".equals(page)) {
                page = "FacilityOfficerListFacility";
            } else if ("addContractor".equals(page)) { //addContractor()
                page = "FacilityOfficerAddContractor";
            } else if ("saveContractor".equals(page)) {
                String contrName = request.getParameter("contractorName");
                if (contractorManagerSessionLocal.verifyContractor(contrName,
                        (String) request.getSession().getAttribute("mallName"))) { //verifyFacility()
                    addContractor(request);
                    request.setAttribute("addContractorStatus", "Contractor has been successfully added!");
                } else {
                    request.setAttribute("alertContractorStatus", "Contractor name cannot be duplicated. Please input the differnt name. Thank You!");
                }
                page = "FacilityOfficerIndexpage";
            } else if ("listContractor".equals(page)) { //listContractor()
                page = "FacilityOfficerListContractor";
            } else if ("editContractor".equals(page)) { //editContractor()
                page = "FacilityOfficerEditContractor";
            } else if ("changeContractor".equals(page)) {
                editContractor(request);
                request.setAttribute("editContractorStatus", "Edit contractor successfully!");
                page = "FacilityOfficerListContractor";
            } else if ("deleteContractor".equals(page)) { //deleteContractor()
                Long id = Long.parseLong(request.getParameter("contractorId"));
                deleteContractorById(request);
                request.setAttribute("deleteContractorStatus", "The contractor has been successfully deleted!");
                page = "FacilityOfficerListContractor";
            } else if ("addServiceRequest".equals(page)) { //addServiceRequest()
                List data = facilityManagerSessionLocal.listFacility((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
                page = "FacilityOfficerAddServiceRequest";
            } else if ("saveServiceRequest".equals(page)) {
                addServiceRequest(request);
                request.setAttribute("addServiceRequestStatus", "ServiceRequest has been successfully added!");
                page = "FacilityOfficerIndexpage";
            } else if ("listServiceRequest".equals(page)) { //listServiceRequest()
                page = "FacilityOfficerListServiceRequest";
            } else if ("editServiceRequest".equals(page)) { //editServiceRequest()
                page = "FacilityOfficerEditServiceRequest";
            } else if ("changeServiceRequest".equals(page)) {
                editServiceRequest(request);
                request.setAttribute("editServiceRequestStatus", "Edit service request successfully!");
                page = "FacilityOfficerListServiceRequest";
            } else if ("deleteServiceRequest".equals(page)) { //deleteServiceRequest()
                Long id = Long.parseLong(request.getParameter("serviceId"));
                deleteServiceById(request);
                request.setAttribute("deleteServiceRequestStatus", "The service request has been successfully deleted!");
                page = "FacilityOfficerListServiceRequest";
            } else if ("listFeedback".equals(page)) { //listFeedback()
                page = "FacilityOfficerListFeedback";
            } else if ("deleteFeedback".equals(page)) { //deleteFeedback()
                Long id = Long.parseLong(request.getParameter("feedbackId"));
                deleteFeedbackById(request);
                request.setAttribute("deleteFeedbackStatus", "The feedback has been successfully deleted!");
                page = "FacilityOfficerListFeedback";
            } else if ("updateStatus".equals(page)) { //updateStatus()
                page = "FacilityOfficerUpdateStatus";
            } else if ("changeStatus".equals(page)) {
                editFeedbackStatus(request);
                request.setAttribute("updateStatusAck", "The status of feedback has updated successfully!");
                page = "FacilityOfficerListFeedback";
            } else if ("backToOfficerIndexPage".equals(page)) {
                page = "FacilityOfficerIndexpage";
            } else if ("backToMainPage".equals(page)) {
                page = "MainPage";
            }

            // START ESSENTIAL ZONE <page in this zone is the name of page want to go to>
            if (page.equals("FacilityOfficerListFacility")) {
                List data = facilityManagerSessionLocal.listFacility((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerEditFacility")) {
                Long id = Long.parseLong(request.getParameter("facilityId"));
                FacilityEntity data = getFacilityById(request);
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerListAsset")) {
                tempFacilityId = request.getParameter("facilityId");
                List data = assetManagerSessionLocal.listAsset(Long.parseLong(tempFacilityId));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerEditAsset")) {
                Long id = Long.parseLong(request.getParameter("assetId"));
                AssetEntity data = getAssetById(request);
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerListContractor")) {
                List data = contractorManagerSessionLocal.listContractor((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerEditContractor")) {
                Long id = Long.parseLong(request.getParameter("contractorId"));
                ContractorEntity data = getContractorById(request);
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerListServiceRequest")) {
                List data = serviceManagerSessionLocal.listServiceRequest((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerEditServiceRequest")) {
                Long id = Long.parseLong(request.getParameter("serviceId"));
                ServiceEntity data = getServiceRequestById(request);
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerListFeedback")) {
                List data = feedbackManagerSessionLocal.listFeedback((String) request.getSession().getAttribute("mallName"));
                request.setAttribute("data", data);
            }
            if (page.equals("FacilityOfficerUpdateStatus")) {
                Long id = Long.parseLong(request.getParameter("feedbackId"));
                FeedbackEntity data = getFeedbackById(request);
                request.setAttribute("data", data);
            }
            // END ESSENTIAL ZONE

            dispatcher = servletContext.getNamedDispatcher(page);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create new facility
    private FacilityEntity addFacility(String facilityName, String facilityLocation, HttpServletRequest request) {
        FacilityManager fm = new FacilityManager(facilityManagerSessionLocal);
        return fm.createNewFacility(facilityName, facilityLocation, request);
    }

    //get facility details by ID
    private FacilityEntity getFacilityById(HttpServletRequest request) {
        FacilityManager fm = new FacilityManager(facilityManagerSessionLocal);
        return fm.getFacility(request);
    }

    //edit existing facility
    private FacilityEntity editFacility(HttpServletRequest request) {
        FacilityManager fm = new FacilityManager(facilityManagerSessionLocal);
        return fm.editFacility(request);
    }

    //delete facility
    private void deleteFacilityById(HttpServletRequest request) {
        FacilityManager fm = new FacilityManager(facilityManagerSessionLocal);
        fm.deleteFacility(request);
    }

    //create new asset
    private AssetEntity addAsset(HttpServletRequest request) {
        AssetManager am = new AssetManager(assetManagerSessionLocal);
        return am.createNewAsset(request);
    }

    //get asset details by ID
    private AssetEntity getAssetById(HttpServletRequest request) {
        AssetManager am = new AssetManager(assetManagerSessionLocal);
        return am.getAsset(request);
    }

    //edit existing asset
    private AssetEntity editAsset(HttpServletRequest request) {
        AssetManager am = new AssetManager(assetManagerSessionLocal);
        return am.editAsset(request);
    }

    //delete asset
    private void deleteAssetById(HttpServletRequest request) {
        AssetManager am = new AssetManager(assetManagerSessionLocal);
        am.deleteAsset(request);
    }

    //create new contractor
    private ContractorEntity addContractor(HttpServletRequest request) {
        ContractorManager cm = new ContractorManager(contractorManagerSessionLocal);
        return cm.createNewContractor(request);
    }

    //get contractor details by ID
    private ContractorEntity getContractorById(HttpServletRequest request) {
        ContractorManager cm = new ContractorManager(contractorManagerSessionLocal);
        return cm.getContractor(request);
    }

    //edit existing contractor
    private ContractorEntity editContractor(HttpServletRequest request) {
        ContractorManager cm = new ContractorManager(contractorManagerSessionLocal);
        return cm.editContractor(request);
    }

    //delete contractor
    private void deleteContractorById(HttpServletRequest request) {
        ContractorManager cm = new ContractorManager(contractorManagerSessionLocal);
        cm.deleteContractor(request);
    }

    //create new service request
    private ServiceEntity addServiceRequest(HttpServletRequest request) {
        ServiceManager sm = new ServiceManager(serviceManagerSessionLocal);
        return sm.createNewServiceRequest(request);
    }

    //get service request details by ID
    private ServiceEntity getServiceRequestById(HttpServletRequest request) {
        ServiceManager sm = new ServiceManager(serviceManagerSessionLocal);
        return sm.getServiceRequest(request);
    }

    //edit existing service request
    private void editServiceRequest(HttpServletRequest request) {
        ServiceManager sm = new ServiceManager(serviceManagerSessionLocal);
        sm.editServiceRequest(request);
    }

    //delete service request
    private void deleteServiceById(HttpServletRequest request) {
        ServiceManager sm = new ServiceManager(serviceManagerSessionLocal);
        sm.deleteServiceRequest(request);
    }

    //delete feedback
    private void deleteFeedbackById(HttpServletRequest request) {
        FeedbackManager fbm = new FeedbackManager(feedbackManagerSessionLocal);
        fbm.deleteFeedback(request);
    }

    //get feedback details by ID
    private FeedbackEntity getFeedbackById(HttpServletRequest request) {
        FeedbackManager fbm = new FeedbackManager(feedbackManagerSessionLocal);
        return fbm.getFeedback(request);
    }

    //edit status of the existing feedback
    private void editFeedbackStatus(HttpServletRequest request) {
        FeedbackManager fbm = new FeedbackManager(feedbackManagerSessionLocal);
        fbm.editFeedbackStatus(request);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }//end of doGet

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }//end of doPost

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Facility Management System Servlet";
    }

    @Override
    public void destroy() {
        System.out.println("TheLibrarySystemServlet: destroy()");
    }// </editor-fold

}
