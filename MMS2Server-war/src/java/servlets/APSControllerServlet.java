/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager.ap.CampaignManager;
import manager.ap.DailyDataManager;
import mms.ap.entity.CampaignEntity;
import mms.ap.entity.DailyDataEntity;
import mms.ap.session.CampaignSessionLocal;
import mms.ap.session.DailyDataSessionLocal;

/**
 *
 * @author khushnaz
 */
@WebServlet(name = "APSControllerServlet", urlPatterns = {"/APSControllerServlet"})
public class APSControllerServlet extends HttpServlet {

    @EJB
    private CampaignSessionLocal campaignSessionLocal;
    @EJB
    private DailyDataSessionLocal dataSessionLocal;

    @Override
    public void init() {
        System.out.println("APSControllerServlet: init()");
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        switch ((String) request.getSession().getAttribute("action")) {
            
            // get current campaigns
            case ("GetCampaigns"): {
                List<CampaignEntity> list = new CampaignManager(campaignSessionLocal).listCampaign();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APListCampaign").forward(request, response);
                break;
            }
            
            //get all daily data
            case ("GetData"): {
                List<DailyDataEntity> list = new DailyDataManager(dataSessionLocal).listDailyData();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APViewDailyData").forward(request, response);
                break;
            }
            
            // get all daily data after adding
            case ("GetDataAfterAdd"): {
                List<DailyDataEntity> list = new DailyDataManager(dataSessionLocal).listDailyData();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APViewDailyDataAdd").forward(request, response);
                break;
            }
                    
            // show edit campaign 
            case ("EditCampaign"): {
                request.setAttribute("Id", request.getSession().getAttribute("Id"));
                request.getRequestDispatcher("APEditCampaign").forward(request, response);
                break;
            }
            
            // show edit daily data 
            case ("EditDailyData"): {
                request.setAttribute("Id", request.getSession().getAttribute("Id"));
                request.getRequestDispatcher("APEditDailyData").forward(request, response);
                break;
            }
            
            // get past campaigns
            case ("GetOldCampaigns"): {
                List<CampaignEntity> list = new CampaignManager(campaignSessionLocal).retrieveOldCampaign();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APListOldCampaign").forward(request, response);
                break;
            }
            
            // show campaign after it is reactivated
            case ("restartCampaign"): {
                request.setAttribute("Id", request.getSession().getAttribute("Id"));
                request.getRequestDispatcher("APRestartCampaign").forward(request, response);
                break;
            }
            
            // show recent campaigns to ap officer
            case ("GetCampaignsO"): {
                List<CampaignEntity> list = new CampaignManager(campaignSessionLocal).listCampaign();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APOfficerListCampaign").forward(request, response);
                break;            
            }
            
            // show past campaigns to ap officer
            case ("GetPastCampaignsO"): {
                List<CampaignEntity> list = new CampaignManager(campaignSessionLocal).retrieveOldCampaign();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APOfficerListOldCampaign").forward(request, response);
                break;  
            }
            
            // show add daily data
            case ("AddDailyDataForCampaign"): {
                request.setAttribute("Id", request.getSession().getAttribute("Id"));
                request.getRequestDispatcher("APAddDailyData").forward(request, response);
                break;
            }
                             
            // show seasonal campaigns
            case ("GetSeasonCampaigns"): {
                List<CampaignEntity> list = new CampaignManager(campaignSessionLocal).retrieveSeasonCampaign();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APListSeasonCampaign").forward(request, response);
                break;
            }
                        
            // view all daily data for manager
            case ("GetDailyDataM") : {
                List<DailyDataEntity> list = new DailyDataManager(dataSessionLocal).listDailyData();
                request.setAttribute("list", list);
                request.getRequestDispatcher("APManagerViewDailyData").forward(request, response);
                break;
            
            }
            
        } // switch ends 
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getParameter("action")) {
            
            case ("Login"): { //login
                String role = request.getParameter("userRole");
                String mallName = request.getParameter("mallName");
                request.getSession().setAttribute("userRole", role);
                request.getSession().setAttribute("mallName", mallName);

                if (role.contains("anager")) { // if role is that of a manager
                    request.getRequestDispatcher("APManagerIndex").forward(request, response);
                    request.getSession().setAttribute("action", "GetCampaigns");
                    response.sendRedirect("APSControllerServlet");
                } else { // ap officer 
                    request.getRequestDispatcher("APOfficerIndex").forward(request, response);
                }
                break;
            }
            
            // add new campaign
            case ("addCampaign"): {
                String startDateString = request.getParameter("startDate") + " 00:00:00";
                Timestamp startDate = Timestamp.valueOf(startDateString);

                String endDateString = request.getParameter("endDate") + " 23:59:59";
                Timestamp endDate = Timestamp.valueOf(endDateString);

                Long answer = campaignSessionLocal.addCampaign(
                        request.getParameter("campaignName"),
                        request.getParameter("objective"),
                        request.getParameter("channel"),
                        startDate, endDate,
                        request.getParameter("target"));

                if (answer != null) {
                    request.getSession().setAttribute("action", "GetCampaigns");
                    response.sendRedirect("APSControllerServlet");
                } else {
                    request.getRequestDispatcher("ErrorPage").forward(request, response);
                }
                break;
            }

            // delete campaign
            case ("Delete Campaign"): {
                Long campaignId = Long.parseLong(request.getParameter("campaignId"));
                campaignSessionLocal.deleteCampaign(campaignId);
                request.getSession().setAttribute("action", "GetCampaigns");
                response.sendRedirect("APSControllerServlet");
                break;
            }

            // edit campaign
            case ("Edit Campaign"): {
                request.getSession().setAttribute("Id", Long.parseLong(request.getParameter("campaignId")));
                request.getSession().setAttribute("action", "EditCampaign");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // save changes to edit campaign
            case ("saveEditCampaign"): {
                campaignSessionLocal.editCampaign((Long) request.getSession().getAttribute("Id"), request.getParameter("objective"),
                        request.getParameter("channel"));
                request.getSession().setAttribute("action", "GetCampaigns");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // add daily data from campaigns view 
            case ("Add Campaign Daily Data"): {
                request.getSession().setAttribute("cId", Long.parseLong(request.getParameter("campaignId")));
                request.getSession().setAttribute("action", "AddDailyDataForCampaign");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // confirm save to add daily data 
            case ("addDailyData"): {
                Long ans = dataSessionLocal.addDailyData((Long) request.getSession().getAttribute("cId"), 
                        request.getParameter("description"),
                        request.getParameter("rating"));
                
                if (ans != null) {
                    request.getSession().setAttribute("action", "GetDataAfterAdd");
                    response.sendRedirect("APSControllerServlet");
                } else {
                    request.getRequestDispatcher("ErrorPage").forward(request, response);
                }
                break;
            }
            
            // delete daily data
            case ("Delete Daily Data"): {
                Long dailyDataId = Long.parseLong(request.getParameter("dailyDataId"));
                dataSessionLocal.deleteDailyData(dailyDataId);
                request.getSession().setAttribute("action", "GetData");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            //edit daily data
            case ("Edit Daily Data"): {
                request.getSession().setAttribute("Id", Long.parseLong(request.getParameter("dailyDataId")));
                request.getSession().setAttribute("action", "EditDailyData");
                response.sendRedirect("APSControllerServlet");
                break;
            }

            // save changes to daily data edited
            case ("saveEditDailyData"): {
                dataSessionLocal.editDailyData((Long) request.getSession().getAttribute("Id"), request.getParameter("description"));
                request.getSession().setAttribute("action", "GetData");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // view current campaigns 
            case ("View Current Campaigns"): {
                request.getSession().setAttribute("action", "GetCampaigns");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // view past expired campaigns
            case ("View Past Campaigns") : {
                request.getSession().setAttribute("action", "GetOldCampaigns");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // view all daily data for manager
            case ("View Daily Data M") : {
                request.getSession().setAttribute("action", "GetDailyDataM");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            
            // reactivate a campaign from past campaigns view or seasonal campaigns view
            case ("Reactivate"): {
                request.getSession().setAttribute("Id", Long.parseLong(request.getParameter("campaignId")));
                request.getSession().setAttribute("action", "restartCampaign");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // make changes to activate campaign 
            case ("startPastCampaign"): {
                String startDateString = request.getParameter("startDate") + " 00:00:00";
                Timestamp startDate = Timestamp.valueOf(startDateString);

                String endDateString = request.getParameter("endDate") + " 23:59:59";
                Timestamp endDate = Timestamp.valueOf(endDateString);
                
                campaignSessionLocal.renewCampaign((Long) request.getSession().getAttribute("Id"), startDate, endDate,
                        request.getParameter("channel"));
                request.getSession().setAttribute("action", "GetCampaigns");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // view current campaigns for ap officer
            case ("ViewCurrentCampaignsO"): {
                request.getSession().setAttribute("action", "GetCampaignsO");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // view past campaigns for ap officer
            case ("ViewPastCampaignsO"): {
                request.getSession().setAttribute("action", "GetPastCampaignsO");
                response.sendRedirect("APSControllerServlet");
                break;
            }
                   
            // view seasonal campaign
            case ("View Season Campaigns"): {
                request.getSession().setAttribute("action", "GetSeasonCampaigns");
                response.sendRedirect("APSControllerServlet");
                break;
            }
            
            // view daily data
            case ("View Daily Data"): {
                request.getSession().setAttribute("action", "GetData");
                response.sendRedirect("APSControllerServlet");
                break;                
            }
            
        } //end switch
    }

    @Override
    public String getServletInfo() {
        return "APS Servlet";
    }

    @Override
    public void destroy() {
        System.out.println("APS Servlet: destroy()");
    }// </editor-fold

}