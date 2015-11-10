/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Manager.TenantManager;
import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import mms.facility.entity.OutsourcingEntity;
import mms.tenant.entity.RentInvoiceEntity;
import mms.tenant.entity.RentRenewalRequestEntity;
import mms.tenant.session.TenantManagerBeanLocal;
import mms2.leasing.entity.TenantContractEntity;
import mms2.leasing.entity.TenantEntity;
import mms2.leasing.entity.UnitEntity;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
public class TenantSystemServlet extends HttpServlet {

    @EJB
    private TenantManagerBeanLocal tenantManagerBeanLocal;
    private TenantEntity tenant;
    private TenantContractEntity contract;
    private ArrayList<UnitEntity> tenantunit;
    private ArrayList<OutsourcingEntity> outsourcerequest;
    private ArrayList<RentRenewalRequestEntity> renewalList;
    private ArrayList<SaleEntity> saleList;
    private ArrayList<RentInvoiceEntity> rentInvoiceList;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Long tenantid;

        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            String page = request.getPathInfo().substring(1);
            switch (page) {
                case "logout":
                    request.getSession().invalidate();
                    page = "ThankYou";
                    break;
                case "verifyLogin":
                    if (verifyLogin(request)) {
                        tenant = getTenant(request);
                        request.getSession().setAttribute("tenant", tenant);
                        page = "home";
                    } else {
                        page = "invalidLogin";
                    }
                    break;

                case "ViewRentInformation":
                    tenant = getTenant(request);
                    request.setAttribute("tenant", tenant);
                    Long contractid = tenant.getTenantContract().getId();
                    tenantid = tenant.getId();
                    contract = getContract(contractid);
                    request.setAttribute("contract", contract);
                    tenantunit = getUnit(tenantid);
                    request.setAttribute("tenantunit", tenantunit);
                    break;

                case "RentRenewalRequest":
                    break;
                case "SubmitRentRenewalRequest":
                    String renewalStatus = submitRentRenewalRequest(request);
                    request.setAttribute("renewalStatus", renewalStatus);
                    page = "RentRenewalRequest";
                    break;
                case "RentRenewalStatus":
                    tenantid = ((TenantEntity) request.getSession().getAttribute("tenant")).getId();
                    renewalList = getRenewalList(tenantid);
                    request.setAttribute("renewalList", renewalList);
                    break;

                case "OutsourceRequest":
                    break;
                case "SubmitOutsourceRequest":
                    String outsourceStatus = submitOutsourcingRequest(request);
                    request.setAttribute("outsourceStatus", outsourceStatus);
                    page = "OutsourceRequest";
                    break;
                case "OutsourceStatus":
                    tenantid = ((TenantEntity) request.getSession().getAttribute("tenant")).getId();
                    outsourcerequest = getOutsourceRequest(tenantid);
                    request.setAttribute("outsourcerequest", outsourcerequest);
                    break;
                    
                case "ViewSales":
                    tenantid = ((TenantEntity) request.getSession().getAttribute("tenant")).getId();
                    saleList = getSaleList(tenantid);
                    request.setAttribute("saleList", saleList);
                    break;
                case "OverallSales":
                    tenantid = ((TenantEntity) request.getSession().getAttribute("tenant")).getId();
                    saleList = getSaleList(tenantid);
                    request.setAttribute("saleList", saleList);
                    break;                    
                    
                case "RentInvoice":
                    tenantid = ((TenantEntity) request.getSession().getAttribute("tenant")).getId();
                    rentInvoiceList = getInvoices(tenantid);
                    request.setAttribute("rentInvoiceList", rentInvoiceList);
                    break;
                    
                case "SalesChart":
                    tenantid = ((TenantEntity) request.getSession().getAttribute("tenant")).getId();
                    saleList = getSaleList(tenantid);
                    request.setAttribute("saleList", saleList);
                    break;
                    

                case "GoBackIndex":
                    tenant = getTenant(request);
                    request.setAttribute("tenant", tenant);
                    page = "home";
                    break;

            }

            dispatcher = servletContext.getNamedDispatcher(page);
            //error page
            if (dispatcher == null) {
                dispatcher = servletContext.getNamedDispatcher("Error");
            }
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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

    public boolean verifyLogin(HttpServletRequest request) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.verifyLogin(request);
    }

    private TenantEntity getTenant(HttpServletRequest request) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getTenant(request);
    }

    private TenantContractEntity getContract(Long contractid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getContract(contractid);
    }

    private ArrayList<UnitEntity> getUnit(Long tenantid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getUnits(tenantid);
    }

    private String submitOutsourcingRequest(HttpServletRequest request) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.submitOutsourcingRequest(request);
    }

    private String submitRentRenewalRequest(HttpServletRequest request) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.submitRentRenewalRequest(request);
    }

    private ArrayList<OutsourcingEntity> getOutsourceRequest(Long tenantid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getOutsourceRequest(tenantid);
    }

    private ArrayList<RentRenewalRequestEntity> getRenewalList(Long tenantid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getRenewalList(tenantid);
    }
    
    private ArrayList<SaleEntity> getSaleList(Long tenantid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getSaleList(tenantid);
    }
    
    private ArrayList<RentInvoiceEntity> getInvoices(Long tenantid) {
        TenantManager tenantManager = new TenantManager(tenantManagerBeanLocal);
        return tenantManager.getInvoices(tenantid);
    }

}
