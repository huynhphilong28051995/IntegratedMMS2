/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GOHENGCHI
 */
public class EmployeeServlet extends HttpServlet {
    
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
            
            //Define JSP Path
            String page = request.getPathInfo();
            page        = page.substring(1);
        
            System.out.println("Page is:" +page);
            if ("elogin".equals(page)) {
                //if (session.getAttribute("Session") != null) {
                    System.out.println("eLogin Entering Page: " +page);
                //}
            }
            
            else if ("login".equals(page)) {
                //if (session.getAttribute("Session") != null) {
                    System.out.println("Login Entering Page: " +page);
                //}
            }
            
            dispatcher = servletContext.getNamedDispatcher(page); 
            dispatcher.forward(request, response);
        }
        
        
        catch (Exception e) {
            log("Exception in EmployeeServlet.processRequest()");
        }
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
