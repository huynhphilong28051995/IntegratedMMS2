package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms2.ejb.administration.session.CorporateAdministrationBeanLocal;

/**
 *
 * @author GOHENGCHI
 */
public class EmployeeServlet extends HttpServlet {

    @EJB
    private CorporateAdministrationBeanLocal cabl;
    private String firstName;
    private String lastName;
    private String employeeID;

    @Override
    public void init() {
        System.out.println("EmployeeServlet: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("EmployeeServlet: processRequest()");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            //Declare and Initialize Variables
            String message = "";
            List<String> data1 = new ArrayList();

            //Define JSP Path
            String page = request.getPathInfo();
            page = page.substring(1);
            String query = request.getQueryString();

            if ("pageRedirect".equals(page)) {
                System.out.println("Entering Page: " + page);
                //Already Logged in
                if ("continue".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    request.setAttribute("message", session.getAttribute("link").toString() + "?continue");
                } //Not Logged in
                else {
                    data1 = validateEmployeeIDEmailPassword(request);
                    //Login Unsuccessful
                    if (data1.isEmpty()) {
                        System.out.println("Login Unsuccessful");
                        //Return false login attempt message to pageRedirect
                        message = "false";
                    } //Login Successful
                    else {
                        System.out.println("Login Successful");
                        System.out.println("Level: " + session.getAttribute("levelTypeChar"));
                        System.out.println("Department: " + session.getAttribute("staffDept"));

                        //
                        if (session.getAttribute("timestamp") == null) {
                            System.out.println("Timestamp is NULL");
                            //First Time user login - redirect change password
                            message = "../administration/adminHome";
                        } else {

                            //
                            //Set Page Redirection to appropriate pages
                            //Corporate Pages
                            if (session.getAttribute("levelTypeChar").equals("C")) {
                                System.out.println("User belongs to Level: Corporate Headquarters");
                                if (session.getAttribute("staffDept").toString().toLowerCase().contains("computer")) {
                                    System.out.println("User belongs to Computer Services (IT) Department");
                                    if (session.getAttribute("staffPosition").toString().toLowerCase().contains("admin")) {
                                        System.out.println("User is Administrator");
                                        message = "../administration/adminHome";
                                    }
                                }
                            } //Local Branch Office Pages
                            else if (session.getAttribute("levelTypeChar").equals("L")) {
                                System.out.println("User belongs to Level: Local Branch Office");
                            } //Shopping Mall Pages
                            else if (session.getAttribute("levelTypeChar").equals("M")) {
                                System.out.println("User belongs to Level: Shopping Mall");
                                if (session.getAttribute("staffDept").toString().toLowerCase().contains("leasing")) {
                                    String mallName = data1.get(6);
                                    String staffPosition = data1.get(5);
                                    String staffUserName = data1.get(2);
                                    String staffFirstName = data1.get(0);
                                    request.getSession().setAttribute("mallName", mallName);
                                    request.getSession().setAttribute("staffPosition", staffPosition);
                                    request.getSession().setAttribute("staffUserName", staffUserName);
                                    request.getSession().setAttribute("staffFirstName", staffFirstName);

                                    System.err.println("LeasingSystemReroute : " + staffPosition + " " + mallName + " " + staffFirstName);

                                    if (staffPosition.equals("Leasing Manager")) {
                                        message = "../LeasingControllerServlet/ViewAllRequests";
                                        //mainPage = "LeasingManagerViewAllRequests";
                                    } else if (staffPosition.equals("Leasing Officer")) {
                                        message = "../LeasingControllerServlet/LeasingOfficerMain";
                                        //mainPage = "LeasingOfficerMain";
                                    } else {
                                        message = "../LeasingControllerServlet/SpacePlanMain";
                                        //mainPage = "SpacePlanMain";
                                    }
                                }
                                
                                if (session.getAttribute("staffDept").toString().toLowerCase().contains("facilities")) {
                                    String mallName = data1.get(6);
                                    String staffPosition = data1.get(5);
                                    String staffUserName = data1.get(2);
                                    String staffFirstName = data1.get(0);
                                    request.getSession().setAttribute("mallName", mallName);
                                    request.getSession().setAttribute("staffPosition", staffPosition);
                                    request.getSession().setAttribute("staffUserName", staffUserName);
                                    request.getSession().setAttribute("staffFirstName", staffFirstName);

                                    System.err.println("LeasingSystemReroute : " + staffPosition + " " + mallName + " " + staffFirstName);

                                    
                                        message = "../FMSControlServlet/directLogin?userRole="+staffPosition+"&mallName="+mallName;
                                     
 
                                }
                            }
                        }

                        session.setAttribute("link", message);
                    }
                    request.setAttribute("message", message);
                }

            } else if ("login".equals(page)) {
                //if (session.getAttribute("Session") != null) {
                System.out.println("Entering Page: " + page);
                //}
            } else if ("forgot".equals(page)) {
                System.out.println("Entering Page: " + page);
            } else if ("logout".equals(page)) {
                if (session == null) {
                    System.err.println("SESSION IS NULL");
                } else {
                    System.err.println("SESSION IS NOT NULL");
                }

                try {
                    session.invalidate();
                } catch (Exception ex) {

                }

                page = "login";
                System.out.println("Entering Page: " + page);
            }

            dispatcher = servletContext.getNamedDispatcher(page);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            log("Exception in EmployeeServlet.processRequest()");
        }
    }

//Validate Employee Credentials
    private List<String> validateEmployeeIDEmailPassword(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        session.setAttribute("SessionEmail", email);
        String password = request.getParameter("password");
        System.out.println(email);
        System.out.println(password);

        List<String> list = new ArrayList();
        try {
            list = cabl.findEmployeeByIDEmailPassword(email, password);
            System.out.println("Initiating Employee Search...");
            if (!list.isEmpty()) {
                System.out.println("Employee Data Found.");
                //Declare Name as Session Attribute
                session.setAttribute("Session2", list.get(0));
                //Employee ID
                session.setAttribute("Session", list.get(1));
                //Employee Email
                session.setAttribute("staffUserName", list.get(2));
                session.setAttribute("Session4", list.get(3));

                firstName = list.get(0);
                employeeID = list.get(1);
                //Temporary Timestamp for User Upon Login
                session.setAttribute("timestamp", list.get(4));
                session.setAttribute("staffPosition", list.get(5));
                session.setAttribute("levelName", list.get(6));
                //C for Corporate, L for Local, M for Mall
                session.setAttribute("levelTypeChar", list.get(7));
                session.setAttribute("staffDept", list.get(8));

            }

            if (list.isEmpty()) {
                System.out.println("Employee cannot be found.");
                return list;
            }
        } catch (Exception ex) {

        }

        return list;
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
