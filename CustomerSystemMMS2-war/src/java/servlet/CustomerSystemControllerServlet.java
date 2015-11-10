/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Manager.CustomerManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import mms2.customer.entity.CustomerEntity;
import mms2.customer.entity.GiftEntity;
import mms2.customer.entity.RedemptionHistoryEntity;
import mms2.customer.session.CustomerManagerBeanLocal;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
public class CustomerSystemControllerServlet extends HttpServlet {

    @EJB
    private CustomerManagerBeanLocal customerManagerBeanLocal;
    private CustomerEntity customer;
    private List<GiftEntity> gifts;
    private Long giftID;
    private ArrayList<SaleEntity> purchaseList;
    private ArrayList<RedemptionHistoryEntity> redemptionHistoryList;
    String email;

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

        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            String page = request.getPathInfo().substring(1);
            switch (page) {
                case "logout":
                    request.getSession().invalidate();
                    page = "ThankYou";
                    break;
                case "GoBackIndex":
                    customer = getCustomer(request);
                    request.setAttribute("customer", customer);
                    page = "home";
                    break;
                case "GoBackLogin":
                    page = "login";
                    break;
                case "verifyLogin":
                    if (verifyLogin(request)) {
                        customer = getCustomer(request);
                        request.setAttribute("customer", customer);
                        page = "home";
                    } else {
                        page = "invalidLogin";
                    }
                    break;
                    
                case "ViewMembership":
                    customer = getCustomer(request);
                    request.setAttribute("customer", customer);
                    break;

                case "EditProfile":
                    break;

                case "doChangePassword":
                    String status = doChangePassword(request);
                    request.setAttribute("changePasswordStatus", status);
                    page = "ChangePassword";
                    break;
                case "ChangePassword":
                    request.setAttribute("changePasswordStatus", "Please enter your password information");
                    break;

                case "ChangeAddress":
                    String tempAddress = customer.getAddress();
                    request.setAttribute("address", tempAddress);
                    request.setAttribute("changeAddressStatus", "Please enter your address information");
                    break;
                case "SaveNewAddress":
                    String status2 = changeAddress(request);
                    request.setAttribute("changeAddressStatus", status2);
                    page = "ChangeAddress";
                    break;

                case "ChangeNumber":
                    String tempNumber = customer.getTelephone();
                    request.setAttribute("oldnumber", tempNumber);
                    request.setAttribute("changeNumberStatus", "Please enter telephone information");
                    break;
                case "doChangeNumber":
                    String status3 = changeNumber(request);
                    request.setAttribute("changeNumberStatus", status3);
                    page = "ChangeNumber";
                    break;

                case "RedeemGifts":
                    customer = getCustomer(request);
                    String country = customer.getCountry();
                    request.setAttribute("customer", customer);
                    gifts = getGifts(country);
                    request.setAttribute("gifts", gifts);
                    break;
                    
                case  "redeem":
                    String redeemStatus = redeemGift(request);
                    request.setAttribute("redeemStatus", redeemStatus);
                    customer = getCustomer(request);
                    request.setAttribute("customer", customer);
                    country = customer.getCountry();
                    gifts = getGifts(country);
                    request.setAttribute("gifts", gifts);
                    page = "RedeemGifts";
                    break;
                    
                case "RedemptionHistory":
                    email = customer.getEmail();
                    redemptionHistoryList = getRedemptionHistory(email);
                    request.setAttribute("redemptionHistoryList", redemptionHistoryList);
                    break;                
                    
                case "ViewPurchaseHistory":
                    email = customer.getEmail();
                    purchaseList = getPurchases(email);
                    request.setAttribute("purchaseList", purchaseList);
                    break;
                case "ViewAnalytics":
                    email = customer.getEmail();
                    String customername = customer.getFirstName();
                    purchaseList = getPurchases(email);
                    request.setAttribute("purchaseList", purchaseList);
                    request.setAttribute("customername", customername);
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

    private boolean verifyLogin(HttpServletRequest request) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.verifyLogin(request);
    }

    private CustomerEntity getCustomer(HttpServletRequest request) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.getCustomer(request);
    }

    private String doChangePassword(HttpServletRequest request) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.changePassword(customer.getEmail(), request);
    }

    private String changeAddress(HttpServletRequest request) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.changeAddress(customer.getEmail(), request);
    }

    private String changeNumber(HttpServletRequest request) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.changeNumber(customer.getEmail(), request);
    }
    
    private List<GiftEntity> getGifts(String country) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.getGifts(country);
    }
    
    private String redeemGift(HttpServletRequest request){
         CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.redeemGift(request);
    }
    
    private ArrayList<SaleEntity> getPurchases(String email) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.getPurchases(email);
    }
    
    private ArrayList<RedemptionHistoryEntity> getRedemptionHistory(String email) {
        CustomerManager customerManager = new CustomerManager(customerManagerBeanLocal);
        return customerManager.getRedemptionHistory(email);
    }
     
}
