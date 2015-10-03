/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import mms.leasing.entity.LevelEntity;
import mms2.leasing.session.LeasingSystemRequestManagerSessionLocal;
import mms2.leasing.session.LevelManagerSessionLocal;
import mms2.leasing.session.LongTermApplicationManagerSessionLocal;
import mms2.leasing.session.TenantContractManagerSessionLocal;
import mms2.leasing.session.TenantManagerSessionLocal;
import mms2.leasing.session.UnitManagerSessionLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.LevelManager;
import manager.LongTermApplicationManager;
import manager.UnitManager;

/**
 *
 * @author PhiLong
 */
@WebServlet(name = "TenantApplicationPortalServlet", urlPatterns = {"/TenantApplicationPortalServlet"})
public class PublicApplicationPortalServlet extends HttpServlet {

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
            String mallName;
            int numOfLevel;
            ArrayList<String> applyUnitList;
            String applicantName;
            String applicantDescription;
            String applicantAddress;
            String applicantTel;
            String applicantEmail;
            String applicantBidRate;

            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String page = request.getPathInfo().substring(1);
            switch (page) {
                case "EnterPublicPortal":
                    mallName = request.getParameter("mallName");
                    request.getSession().setAttribute("mallName", mallName);
                    numOfLevel = doGetNumOfLevel(mallName);
                    request.getSession().setAttribute("numOfLevel", numOfLevel);
                    page = "PublicApplicationPortalMain";
                    break;
                case "ApplyEvent":
                    break;
/////////////////////////////////////////////////////////////////////////LONG TERM
                case "ApplyLongTerm":
                    request.getSession().removeAttribute("applyUnitList");
                    request.getSession().removeAttribute("errorMessage");
                    request.getSession().setAttribute("levelCode", "LV1");
                    System.out.println((String) request.getSession().getAttribute("mallName")
                            + "              "
                            + (String) request.getSession().getAttribute("levelCode"));
                    page = "PublicApplicationPortalLongTermApply";
                    break;
                case "AddUnitToApplyUnitList":
                    String status = doAddUnitToApplyUnitList(request);
                    String errorMessage = status;
                    request.getSession().setAttribute("errorMessage", errorMessage);
                    page = "PublicApplicationPortalLongTermApply";
                    break;
                case "FillApplicantInformationForLongTerm":
                    page = "PublicApplicationPortalFillInformationLongTerm";
                    break;
                case "ConfirmLongTermApplicantInformation":
                    doSaveApplicantInfoToSession(request);
                    page = "PublicApplicationPortalLongTermApplicantConfirm";
                    break;
                case "SubmitLongTermApplication":
                    doSubmitLongTermApplication(request);
                    page = "PublicApplicationPortalEndPage";
                    break;
                case "ChangeFloorplanLevelChooseUnitApplyLongTerm":
                    String levelCode = request.getParameter("levelCode");
                    request.getSession().setAttribute("levelCode", levelCode);
                    page = "PublicApplicationPortalLongTermApply";
                    break;
                case "BackToPublicPortalMain":
                    request.getSession().removeAttribute("applyUnitList");
                    request.getSession().removeAttribute("errorMessage");
                    request.getSession().setAttribute("levelCode", "LV1");
                    page = "PublicApplicationPortalMain";
                    break;
            }
/////////////////////////////////////////////////////////////////////////LONG TERM
/////////////////////////////////////////////////////////////////////////ESSENTIAL
            if (page.equals("PublicApplicationPortalLongTermApply")) {
                Vector unitColorVector = doGetAllUnitColorForCurrentMall(request);
                request.getSession().setAttribute("unitColorVector", unitColorVector);
            }

            if (page.equals("PublicApplicationPortalLongTermApply")) {
                LevelEntity levelInstance = doGetLevel(request);
                request.getSession().setAttribute("levelInstance", levelInstance);
            }
            if(page.equals("PublicApplicationPortalFillInformationLongTerm")){
                doSetBusinessTypeListToAttribute(request);
            }
/////////////////////////////////////////////////////////////////////////ESSENTIAL
            dispatcher = servletContext.getNamedDispatcher(page);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void doSetBusinessTypeListToAttribute(HttpServletRequest request){
         UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
         String mallName = (String)request.getSession().getAttribute("mallName");
         String locationCode = 
                 ((ArrayList<String>)request.getSession().getAttribute("applyUnitList")).get(0);
        ArrayList<String> businessTypeList = 
                unitManager.getUnitBusinessTypeList(mallName, locationCode); 
        request.setAttribute("businessTypeList", businessTypeList);
    }
    public void doSubmitLongTermApplication(HttpServletRequest request){
        LongTermApplicationManager longTermManager= new LongTermApplicationManager(longTermApplicationManagerSessionLocal); 
        longTermManager.createLongTermApplication(request);
        System.out.println("CONFIRMED");
        
    }
    public String doAddUnitToApplyUnitList(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.AddUnitToApplyUnitList(request);
    }

    public int doGetNumOfLevel(String mallName) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        return levelManager.getNumOfLevel(mallName);
    }

    public Vector doGetAllUnitColorForCurrentMall(HttpServletRequest request) {
        UnitManager unitManager = new UnitManager(unitManagerSessionLocal);
        return unitManager.getAllUnitColorForCurrentMall(request);
    }

    public LevelEntity doGetLevel(HttpServletRequest request) {
        LevelManager levelManager = new LevelManager(levelManagerSessionLocal);
        return levelManager.getLevel(request);
    }
    public void doSaveApplicantInfoToSession(HttpServletRequest request) {
        String applicantName = request.getParameter("applicantName");
        String applicantBusinessType = request.getParameter("applicantBusinessType");
        System.out.println("Servlet doSaveApplicantInfoToSession "+applicantBusinessType);
        String applicantDescription = request.getParameter("applicantDescription");
        String applicantAddress = request.getParameter("applicantAddress");
        String applicantTel = request.getParameter("applicantTel");
        String applicantEmail = request.getParameter("applicantEmail");
        String applicantBidRate = request.getParameter("applicantBidRate");
        request.getSession().setAttribute("applicantName", applicantName);
        request.getSession().setAttribute("applicantBusinessType", applicantBusinessType);
        request.getSession().setAttribute("applicantDescription", applicantDescription);
        request.getSession().setAttribute("applicantAddress", applicantAddress);
        request.getSession().setAttribute("applicantTel", applicantTel);
        request.getSession().setAttribute("applicantEmail", applicantEmail);
        request.getSession().setAttribute("applicantBidRate", applicantBidRate);
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
    }

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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
