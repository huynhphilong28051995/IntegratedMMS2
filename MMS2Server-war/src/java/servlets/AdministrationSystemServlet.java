package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mms2.ejb.administration.session.CorporateAdministrationBeanLocal;
import mms2.message.sendsms.SmsSessionBeanLocal;
import mms2.mail.sendmail.SendMailLocal;

/**
 *
 * @author GOHENGCHI
 */
public class AdministrationSystemServlet extends HttpServlet {

    @EJB
    private CorporateAdministrationBeanLocal cabl;
    private SmsSessionBeanLocal ssbl;
    private SendMailLocal sml;
    private String firstName;
    private String lastName;
    private String employeeID;
    private final String timestamp = null;
    private List<String> list = new ArrayList<String>();
    private int index = -1;

    @Override
    public void init() {
        System.out.println("AdministrationSystemServlet: init()");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("AdministrationSystemServlet: processRequest()");
        try {

            //Declare and Initialize Variables
            String message = "";
            String formField = "";
            String levelNameSelected = "";
            String departmentNameSelected = "";
            List<String> data1 = new ArrayList();
            List<String> levelList;
            List<String> departmentList;
            List<String> positionList;
            List<String> employeeList;
            List<String> levelNameList;
            List<String> departmentNameList;
            List<String> deptInfoList;
            List<String> departmentPositionList;

            HttpSession session = request.getSession(true);
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();

            //Define JSP Path
            String page = request.getPathInfo();
            page = page.substring(1);
            String query = request.getQueryString();
            //System.out.println(query);
            if ("adminHome".equals(page)) {
                if (session.getAttribute("Session") != null) {
                    System.out.println("Entering Page: " + page);
                    //For Counting Purposes on adminHome
                    int totalLevelSize = levelListInfo().size();
                    request.setAttribute("totalLevelSize", totalLevelSize);
                    int totalDepartmentSize = DepartmentListInfo().size();
                    request.setAttribute("totalDepartmentSize", totalDepartmentSize);
                    int totalEmployeeSize = EmployeeListInfo().size();
                    request.setAttribute("totalEmployeeSize", totalEmployeeSize);

                    //BEGIN LEASING SYSTEM REROUTE WHEN USER RELOGIN
                    String staffPosition = (String)request.getSession().getAttribute("staffPosition");
                    if (staffPosition.equals("Leasing Officer")
                            || staffPosition.equals("Leasing Manager")
                            || staffPosition.equals("Space Plan Officer")) {
                         page = "leasingSystemReroute";
                    }
                    //END LEASING SYSTEM REROUTE WHEN USER RELOGIN

                } //User is not logged in
                else {
                    data1 = validateEmployeeIDEmailPassword(request);
                    System.out.println("Session: " + session.getAttribute("Session"));

                    if (data1 == null) {
                        //Login Unsuccessful
                        page = "login";
                        System.out.println("Entering Page: " + page);
                    } else {

                        //Login Successful
                        page = "adminHome";
                        System.out.println("Entering Page: " + page);
                        //For Counting Purposes on adminHome
                        int totalLevelSize = levelListInfo().size();
                        request.setAttribute("totalLevelSize", totalLevelSize);
                        int totalDepartmentSize = DepartmentListInfo().size();
                        request.setAttribute("totalDepartmentSize", totalDepartmentSize);
                        int totalEmployeeSize = EmployeeListInfo().size();
                        request.setAttribute("totalEmployeeSize", totalEmployeeSize);
                        System.out.println("TIMESTAMP IS" + data1.get(4));
                        if (data1.get(4) == null) {
                            //First Time user login - redirect change password
                            page = "changePassword";
                        } else {
                            //Session 5: Temporary Timestamp for User Upon Login
                            session.setAttribute("Session5", data1.get(4));
                            //Update New Timestsamp Here
                            cabl.updateNewTimestamp(session.getAttribute("SessionEmail").toString());

                            //BEGIN LEASING SYSTEM REROUTE
                            if (data1.get(5).equals("Leasing Officer")
                                    || data1.get(5).equals("Leasing Manager")
                                    || data1.get(5).equals("Space Plan Officer")) {
                                String mallName = data1.get(6);
                                String staffPosition = data1.get(5);
                                String staffUserName = data1.get(2);
                                String staffFirstName = data1.get(0);
                                request.getSession().setAttribute("mallName", mallName);
                                request.getSession().setAttribute("staffPosition", staffPosition);
                                request.getSession().setAttribute("staffUserName", staffUserName);
                                request.getSession().setAttribute("staffFirstName", staffFirstName);
                                page = "leasingSystemReroute";
                            }
                            //END LEASING SYSTEM REROUTE  
                        }
                    }
                }
                request.setAttribute("data", data1);
                System.out.println("Timestamp is " + session.getAttribute("Session5"));

            } else if ("login".equals(page)) {
                System.out.println("Entering Page: " + page);

            } else if ("changePassword".equals(page)) {
                System.out.println("Entering Page: " + page);
                String check = new String();
                if ("change=true".equals(query)) {
                    if (request.getParameter("password1") != null && request.getParameter("password2") != null) {
                        check = compareUserPassword(request);
                        System.out.println(check);
                        cabl.updateNewTimestamp(session.getAttribute("SessionEmail").toString());
                    }
                }
                request.setAttribute("data", check);
            } else if ("addEmployee".equals(page)) {
                System.out.println("You are in addEmployee Page");
                System.out.println("Entering Page: " + page);
                if (query == null) {
                    generatePassword(request);
                }
                System.out.println("Generating Employee ID");
                String newEmployeeID = generateNewEmployeeID(request);
                request.setAttribute("newEmployeeID", newEmployeeID);

                if ("create=true".equals(query)) {
                    System.out.println("Initiating Create New Employee...");
                    String preCheck = "";
                    String email = "";

                    String checkResult = createNewEmployee(request);
                    if (checkResult.equals("new")) {
                        preCheck = checkResult;
                        email = request.getParameter("email");
                        System.out.println("Email is" + email);
                        firstName = request.getParameter("firstName");
                        System.out.println("First Name is" + firstName);
                        lastName = request.getParameter("lastName");
                        System.out.println("Last Name is" + lastName);
                    }
                    request.setAttribute("message", preCheck);
                    request.setAttribute("email", email);
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                }

            } else if ("manageEmployee".equals(page)) {
                System.out.println("Entering Page: " + page);
                employeeList = EmployeeListInfo();
                System.out.println(employeeList.get(0));
                request.setAttribute("employeeList", employeeList);
            } else if ("addLevel".equals(page)) {
                System.out.println("Entering Page: " + page);
                if ("true".equals(query)) {
                    System.out.println("Entering Query Page: " + query);
                    String preCheck;

                    if (request.getParameter("level").isEmpty()
                            || request.getParameter("name").isEmpty()
                            || request.getParameter("country").isEmpty()
                            || request.getParameter("address").isEmpty()
                            || request.getParameter("postal").isEmpty()) {
                        message = "missing";
                    }

                    if (!"missing".equals(message)) {
                        preCheck = validateAddLevel(request);
                        if (preCheck.equals("existed")) {
                            message = preCheck;
                        } else {
                            //preCheck is new (level name is not previously created)
                            message = preCheck;
                            System.out.println("Creating Level...");
                            createLevelType(request);
                        }
                    }

                    request.setAttribute("message", message);

                }
                //Pre-Selection parsed for corresponding addLevel fields
                if ("corporate".equals(query)) {
                    formField = "Corporate";
                } else if ("local".equals(query)) {
                    formField = "Local";
                } else if ("mall".equals(query)) {
                    formField = "Mall";
                }
                request.setAttribute("formField", formField);

            } else if ("manageLevel".equals(page)) {
                System.out.println("Entering Page: " + page);
                levelList = levelListInfo();
                //System.out.println(levelList.get(0));
                request.setAttribute("levelList", levelList);
                System.out.println("Query is " + query);

                if (query != null) {
                    System.err.println("Query is not NULL");
                    //del=<Number>
                    if (query.length() > 3) {
                        //Only retrieve the index count
                        index = Integer.parseInt(query.substring(4));
                        System.out.println("Index obtained is " + index);
                    } //del (Confirmation for delete)
                    else {
                        System.out.println("Index to delete is " + index);
                        //Confirm delete
                        deleteLevel(index);
                        index = -1;
                    }
                }

            } else if ("addDepartment".equals(page)) {
                System.out.println("Entering Page: " + page);
                if ("retrieveLevel".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    String levelTypeSelected = request.getParameter("levelType");
                    request.setAttribute("levelType", levelTypeSelected);
                    levelNameList = retrieveAllLevelName(request);
                    //System.out.println("Name: " +levelNameList.get(0));

                    request.setAttribute("levelNameList", levelNameList);

                } else if ("retrieveDept".equals(query)) {
                    levelNameSelected = request.getParameter("levelName");
                    System.out.println("Level Name: " + levelNameSelected);

                    departmentNameList = retrieveLevelTypeDepartmentName(request);
                    request.setAttribute("departmentNameList", departmentNameList);

                    deptInfoList = retrieveLevelDepartmentAddressPostal(request);
                    request.setAttribute("deptInfoList", deptInfoList);
                } else if ("addDept".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    String preCheck = "";

                    if (request.getParameter("departmentName").isEmpty()
                            || request.getParameter("departmentAddress").isEmpty()
                            || request.getParameter("departmentPostalCode").isEmpty()
                            || request.getParameter("departmentUnitNumber").isEmpty()
                            || request.getParameter("departmentPhoneNumber").isEmpty()) {
                        message = "missing";
                    }

                    if (!"missing".equals(message)) {
                        preCheck = validateAddDepartment(request);
                        if (preCheck.equals("existed")) {
                            message = preCheck;
                        } else {
                            //preCheck is new (dept. name is not previously created)
                            message = preCheck;
                            System.out.println("Creating Department and Assigning to Level...");
                            createDepartmentAssignLevel(request);
                        }
                    }

                    request.setAttribute("message", message);

                }
            } else if ("manageDepartment".equals(page)) {
                System.out.println("Entering Page: " + page);
                departmentList = LevelDepartmentListInfo();
                System.out.println(departmentList.get(0));
                request.setAttribute("departmentList", departmentList);
                System.out.println("Query is " + query);

                if (query != null) {
                    System.err.println("Query is not NULL");
                    //del=<Number>
                    if (query.length() > 3) {
                        //Only retrieve the index count
                        index = Integer.parseInt(query.substring(4));
                        System.out.println("Index obtained is " + index);
                    } //del (Confirmation for delete)
                    else {
                        System.out.println("Index to delete is " + index);
                        //Confirm delete
                        deleteDepartment(index);
                        index = -1;
                    }
                }
            } else if ("addPosition".equals(page)) {
                System.out.println("Entering Page: " + page);
                if ("retrieveLevel".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    String levelTypeSelected = request.getParameter("levelType");
                    request.setAttribute("levelType", levelTypeSelected);
                    levelNameList = retrieveAllLevelName(request);
                    //System.out.println("Name: " +levelNameList.get(0));
                    request.setAttribute("levelNameList", levelNameList);

                } else if ("retrieveDept".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    levelNameSelected = request.getParameter("levelName");
                    System.out.println("Level Name: " + levelNameSelected);
                    request.setAttribute("levelName", levelNameSelected);
                    departmentNameList = retrieveLevelTypeDepartmentName(request);
                    request.setAttribute("departmentNameList", departmentNameList);

                } else if ("retrievePos".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    levelNameSelected = session.getAttribute("TempLevelName").toString();
                    request.setAttribute("levelName", levelNameSelected);
                    departmentNameSelected = request.getParameter("departmentName");
                    request.setAttribute("departmentName", departmentNameSelected);
                    departmentPositionList = retrieveDepartmentPosition(request);
                    request.setAttribute("departmentPositionList", departmentPositionList);
                } else if ("true".equals(query)) {
                    System.out.println("Entering Page: " + page);
                    //System.out.println("Performing crossCheckPosition");
                    //crossCheckPosition(request);
                    System.out.println("Performing Association of Position(s)");
                    associateDepartmentPosition(request);
                }

            } else if ("managePosition".equals(page)) {
                System.out.println("Entering Page: " + page);
                positionList = LevelDepartmentPositionListInfo();
                //System.out.println(positionList.get(0));
                request.setAttribute("positionList", positionList);
                System.out.println("Query is " + query);

                if (query != null) {
                    System.err.println("Query is not NULL");
                    //del=<Number>
                    if (query.length() > 3) {
                        //Only retrieve the index count
                        index = Integer.parseInt(query.substring(4));
                        System.out.println("Index obtained is " + index);
                    } //del (Confirmation for delete)
                    else {
                        System.out.println("Index to delete is " + index);
                        //Confirm delete
                        deletePosition(index);
                        index = -1;
                    }
                }
            } else if ("ldpSelect".equals(page)) {
                System.out.println("Entering Page: " + page);
                if ("retrieveLevel".equals(query)) {
                    System.out.println("Entering Query Page: " + query);
                    String levelTypeSelected = request.getParameter("levelType");
                    request.setAttribute("levelType", levelTypeSelected);
                    levelNameList = retrieveAllLevelName(request);
                    if (levelTypeSelected.equals("C")) {
                        String tempLevelType = "Corporate Headquarter";
                        session.setAttribute("TempLevelType", tempLevelType);
                    } else if (levelTypeSelected.equals("L")) {
                        String tempLevelType = "Local Branch Office";
                        session.setAttribute("TempLevelType", tempLevelType);
                    } else if (levelTypeSelected.equals("M")) {
                        String tempLevelType = "Shopping Mall";
                        session.setAttribute("TempLevelType", tempLevelType);
                    }
                    //System.out.println("Name: " +levelNameList.get(0));
                    request.setAttribute("levelNameList", levelNameList);

                } else if ("retrieveDept".equals(query)) {
                    System.out.println("Entering Query Page: " + query);
                    levelNameSelected = request.getParameter("levelName");
                    System.out.println("Level Name: " + levelNameSelected);
                    request.setAttribute("levelName", levelNameSelected);
                    departmentNameList = retrieveLevelTypeDepartmentName(request);
                    request.setAttribute("departmentNameList", departmentNameList);

                } else if ("retrievePos".equals(query)) {
                    System.out.println("Entering Query Page: " + query);
                    levelNameSelected = session.getAttribute("TempLevelName").toString();
                    request.setAttribute("levelName", levelNameSelected);
                    departmentNameSelected = request.getParameter("departmentName");
                    request.setAttribute("departmentName", departmentNameSelected);
                    departmentPositionList = retrieveDepartmentPosition(request);
                    request.setAttribute("departmentPositionList", departmentPositionList);
                } else if ("retrievePos=true".equals(query)) {
                    System.out.println("Entering Query Page: " + query);
                    String positionName = request.getParameter("positionName");
                    session.setAttribute("TempPositionName", positionName);
                    System.out.println("TempPositionName is: " + session.getAttribute("TempPositionName"));
                } else if ("restart".equals(query)) {
                    System.out.println("Entering Query Page: " + query);
                    session.removeAttribute("TempLevelName");
                    session.removeAttribute("TempDepartmentName");
                    session.removeAttribute("TempPositionName");
                }
            } else if ("forgot".equals(page)) {

            } else if ("sendSms".equals(page)) {

            } else if ("postSms".equals(page)) {
                sendSMS(request);
                System.out.println("Done 1.");
            } else if ("addMembership".equals(page)) {

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
            } else {
                page = "error";
                System.out.println("Entering Page: " + page);
            }

            dispatcher = servletContext.getNamedDispatcher(page);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            log("Exception in AdministrationSystemServlet.processRequest()");
        }
    }

    public String generatePassword(HttpServletRequest request) {
        //Initialize Variables for Random Password Generator
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbols = "!@#$%^&*";
        String integers = "0123456789";
        Random r = new Random();
        String password = "";

        while (password.length() != 8) {
            int rPick = r.nextInt(4);
            if (rPick == 0) {
                int spot = r.nextInt(25);
                password += lowerCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt(25);
                password += upperCase.charAt(spot);
            } else if (rPick == 2) {
                int spot = r.nextInt(7);
                password += symbols.charAt(spot);
            } else if (rPick == 3) {
                int spot = r.nextInt(9);
                password += integers.charAt(spot);
            }
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("TempEmployeePW", password);
        return password;
    }

    private String compareUserPassword(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        String check = "";
        String email = session.getAttribute("SessionEmail").toString();
        //Current Password
        //String password = request.getParameter("password");
        //New Password
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        String password1Encrypted = cabl.encryptPassword(password1);
        String password2Encrypted = cabl.encryptPassword(password2);

        System.out.println(email);
        System.out.println(password1);
        System.out.println(password2);

        //Check new passwords if they are the same
        if (password1Encrypted.equals(password2Encrypted)) {
            //check if current password matches the database password
            System.out.println("Profile Editing in Progress");
            cabl.updatePassword(email, password1);
            check = "Success: Password has been updated.";

        } else {
            check = "Error: The passwords you entered do not match.";
        }

        return check;

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

                session.setAttribute("staffPosition", list.get(5));
                session.setAttribute("mallName", list.get(6));

            }

            if (list.isEmpty()) {
                System.out.println("Employee cannot be found.");
                return null;
            }
        } catch (Exception ex) {

        }

        return list;
    }

    private void createLevelType(HttpServletRequest request) {
        String level = request.getParameter("level");
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        String postal = request.getParameter("postal");
        try {
            System.out.println(level + name + country + address + postal);
            System.out.println("Calling Create Level Type Function");
            cabl.createLevelType(level, name, country, address, postal);
        } catch (Exception ex) {

        }

    }

    //Create a Department and assigned to a Level
    private void createDepartmentAssignLevel(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String levelNameSelected = session.getAttribute("TempLevelName").toString();
        String departmentName = request.getParameter("departmentName");
        int departmentPhoneNumber = Integer.parseInt(request.getParameter("departmentPhoneNumber"));
        String departmentAddress = request.getParameter("departmentAddress");
        String departmentPostalCode = request.getParameter("departmentPostalCode");
        String departmentUnitNumber = request.getParameter("departmentUnitNumber");
        try {
            System.out.println("Calling Create Department Function");
            cabl.createDepartment(departmentName, departmentPhoneNumber, departmentAddress, departmentPostalCode, departmentUnitNumber);
            cabl.associateLevelDepartment(levelNameSelected);
        } catch (Exception ex) {

        }

    }

    private List<String> levelListInfo() {

        List<String> levelList = new ArrayList();
        System.out.println("Performing Level(s) Search in MMA");
        try {
            levelList = cabl.findLevelType();
            System.out.println("LevelList Size is:" + levelList.size());

            if (levelList.isEmpty()) {
                System.out.println("No level found.");
            } else {
                return levelList;
            }
        } catch (Exception ex) {

        }

        return levelList;
    }

    private List<String> DepartmentListInfo() {

        List<String> departmentList = new ArrayList();
        System.out.println("Performing Department(s) Search in MMA");
        try {
            departmentList = cabl.findDepartment();
            System.out.println("departmentList Size is:" + departmentList.size());

            if (departmentList.isEmpty()) {
                System.out.println("No department found.");
            } else {
                return departmentList;
            }
        } catch (Exception ex) {

        }

        return departmentList;
    }

    private List<String> LevelDepartmentListInfo() {

        List<String> levelDepartmentList = new ArrayList();
        System.out.println("Performing Department(s) Search for all levels in MMA");
        try {
            levelDepartmentList = cabl.findLevelDepartment();
            System.out.println("levelDepartmentList Size is:" + levelDepartmentList.size());

            if (levelDepartmentList.isEmpty()) {
                System.out.println("No department found.");
            } else {
                return levelDepartmentList;
            }
        } catch (Exception ex) {

        }

        return levelDepartmentList;
    }

    private List<String> LevelDepartmentPositionListInfo() {

        List<String> levelDepartmentPositionList = new ArrayList();
        System.out.println("Performing Position(s) Search for all Department(s) in all levels in MMA");
        try {
            levelDepartmentPositionList = cabl.findLevelDepartmentPosition();
            System.out.println("levelDepartmentPositionList Size is:" + levelDepartmentPositionList.size());

            if (levelDepartmentPositionList.isEmpty()) {
                System.out.println("No department found.");
            } else {
                return levelDepartmentPositionList;
            }
        } catch (Exception ex) {

        }

        return levelDepartmentPositionList;
    }

    private List<String> EmployeeListInfo() {

        List<String> employeetList = new ArrayList();
        System.out.println("Performing Employee(s) Search in MMA");
        try {
            employeetList = cabl.findEmployee();
            System.out.println("employeetList Size is:" + employeetList.size());

            if (employeetList.isEmpty()) {
                System.out.println("No employee found.");
            } else {
                return employeetList;
            }
        } catch (Exception ex) {

        }

        return employeetList;
    }

    private List<String> retrieveAllLevelName(HttpServletRequest request) {
        String levelTypeChar = request.getParameter("levelType");
        HttpSession session = request.getSession(true);
        session.setAttribute("TempLevelType", levelTypeChar);

        List<String> levelList = new ArrayList();
        System.out.println("Performing Retrieval for All Level(s) name in a given level");
        try {
            levelList = cabl.retrieveAllLevelName(levelTypeChar);
            session.setAttribute("TempLevelNameList", levelList);
        } catch (Exception ex) {

        }

        return levelList;
    }

    private List<String> retrieveLevelTypeDepartmentName(HttpServletRequest request) {
        String levelNameSelected = request.getParameter("levelName");
        HttpSession session = request.getSession(true);
        session.setAttribute("TempLevelName", levelNameSelected);

        List<String> departmentList = new ArrayList();
        System.out.println("Performing Retrieval for All department' name in a given level");
        try {
            departmentList = cabl.retrieveLevelTypeDepartment(levelNameSelected);
            session.setAttribute("TempDepartmentNameList", departmentList);
        } catch (Exception ex) {

        }

        return departmentList;
    }

    private List<String> retrieveLevelDepartmentAddressPostal(HttpServletRequest request) {
        String levelNameSelected = request.getParameter("levelName");

        List<String> deptInfoList = new ArrayList();
        System.out.println("Performing Retrieval for All department' name in a given level");
        try {
            deptInfoList = cabl.findLevelAddressPostalCode(levelNameSelected);
        } catch (Exception ex) {

        }

        return deptInfoList;
    }

    private List<String> retrieveDepartmentPosition(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        String levelName = session.getAttribute("TempLevelName").toString();
        String departmentName = request.getParameter("departmentName");
        session.setAttribute("TempDepartmentName", departmentName);
        System.out.println("Level name: " + levelName);
        System.out.println("Department name: " + departmentName);

        List<String> positionList = new ArrayList();
        System.out.println("Performing Retrieval for All department' name in a given level");
        try {
            positionList = cabl.retrieveDepartmentPosition(levelName, departmentName);
            System.out.println("Position Size is " + positionList.size());
            for (int i = 0; i < positionList.size(); i++) {
                System.out.println("Position name2: " + positionList.get(i));
            }
        } catch (Exception ex) {

        }

        return positionList;
    }

    //Validate by checking if level name exist for a specified level (i.e. Corporate/Office/Shopping Mall)
    private String validateAddLevel(HttpServletRequest request) {
        String checkResult = "";
        List entityList;
        String levelChar = request.getParameter("level");
        String levelName = request.getParameter("name");
        System.out.println("Level Name:" + levelName);
        //entityList contains information (level name) about all level in a specific level
        entityList = cabl.retrieveAllLevelName(levelChar);
        //Do Checking Here
        System.out.println("Size of EntityList" + entityList.size());
        //No Level Name is found for this level
        if (entityList.isEmpty()) {
            checkResult = "new";
            System.out.println("Level name is not found!");
        }
        //Level Name is found for this level, check to prevent duplicates
        for (int i = 0; i < entityList.size(); i++) {
            System.out.println("EntityList:" + entityList.get(i));
            if (entityList.get(i).equals(levelName)) {
                checkResult = "existed";
                System.out.println("Level name is already existed!");
                break;
            } else {
                checkResult = "new";
                System.out.println("Level name is not found!");
            }
        }
        System.out.println("Return" + " " + checkResult);
        return checkResult;
    }

    //Validate by checking if department name exist for a specified level (i.e. Corporate/Office/Shopping Mall)
    private String validateAddDepartment(HttpServletRequest request) {
        String checkResult = "";
        HttpSession session = request.getSession(true);
        String tempLevelName = session.getAttribute("TempLevelName").toString();
        String departmentName = request.getParameter("departmentName");

        //entityList contains information (level name) about all level in a specific level
        List<String> departmentList = new ArrayList();
        try {
            System.out.println("Performing Retrieval for All department' name in a given level");
            departmentList = cabl.retrieveLevelTypeDepartment(tempLevelName);
            //No Department Name is found for this level
            if (departmentList.isEmpty()) {
                checkResult = "new";
                System.out.println("Department name is not found!");
            }
            //Department Name is found for this level, check to prevent duplicates
            for (int i = 0; i < departmentList.size(); i++) {
                if (departmentList.get(i).equals(departmentName)) {
                    checkResult = "existed";
                    System.out.println("Dept. name is already existed!");
                    break;
                } else {
                    checkResult = "new";
                    System.out.println("Dept. name is not found!");
                }
            }
        } catch (Exception ex) {

        }
        System.out.println("No of Dept:" + " " + departmentList.size() + checkResult);

        return checkResult;
    }

    //Create and Associate Position to Relevant Department
    private String associateDepartmentPosition(HttpServletRequest request) {
        String message = "";
        HttpSession session = request.getSession(true);
        String levelName = session.getAttribute("TempLevelName").toString();
        String departmentName = session.getAttribute("TempDepartmentName").toString();
        System.out.println("Cross Checking Position");
        List<String> newPositionList = crossCheckPosition(request);

        try {
            System.err.println("servlet: associateDepartmentPosition");
            message = cabl.associateDepartmentPosition(newPositionList, levelName, departmentName);
            System.out.println("Message is " + message);

        } catch (Exception ex) {

        }

        return message;
    }

    //Check position for difference between new and current (in database)
    private List<String> crossCheckPosition(HttpServletRequest request) {
        List<String> newPositionList = new ArrayList();
        List<String> oldPositionList = new ArrayList();
        HttpSession session = request.getSession(true);
        String levelName = session.getAttribute("TempLevelName").toString();
        System.out.println("Level Name" + levelName);

        String departmentName = session.getAttribute("TempDepartmentName").toString();
        System.out.println("Department Name" + departmentName);
        String position;
        position = request.getParameter("positionName");

        //Count number of commas (breakpoint(s))
        int count = position.length() - position.replace(",", "").length();
        System.out.println("Count is " + count);
        String[] parts = position.split(",");

        for (int i = 0; i < count + 1; i++) {
            newPositionList.add(parts[i]);
            System.out.println("Position added" + parts[i]);
        }
        System.out.println("New Position Size is: " + newPositionList);

        try {
            System.out.println("Performing crossCheckPosition");
            oldPositionList = cabl.retrieveDepartmentPosition(levelName, departmentName);
            System.out.println("Old Position Size is " + oldPositionList.size());
            newPositionList.removeAll(oldPositionList);
            System.out.println("Difference" + newPositionList);
        } catch (Exception ex) {

        }

        return newPositionList;

    }

    private void sendSMS(HttpServletRequest request) {
        String message = "";
        System.out.println("7");
        String smsNumber = request.getParameter("SMSNumber");
        System.out.println("Number is" + smsNumber);
        ssbl.sendNewCustomerPassword(smsNumber);
        System.out.println("OK");

    }

    private String generateNewEmployeeID(HttpServletRequest request) {
        String newEmployeeID = new String();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("TempLevelName") != null) {
            String levelName = session.getAttribute("TempLevelName").toString();
            System.out.println("Level Name" + levelName);
            newEmployeeID = cabl.generateEmployeeID(levelName);
            session.setAttribute("TempEmployeeID", newEmployeeID);
        }
        return newEmployeeID;
    }

    private String createNewEmployee(HttpServletRequest request) throws ParseException {

        //Define Date Format Srandards as dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String message = "";
        HttpSession session = request.getSession(true);
        String levelNameSelected = session.getAttribute("TempLevelName").toString();
        String departmentNameSelected = session.getAttribute("TempDepartmentName").toString();
        String positionApplied = session.getAttribute("TempPositionName").toString();

        String employeeID = session.getAttribute("TempEmployeeID").toString();
        System.out.println("Employee ID " + employeeID);
        String employeePassword = session.getAttribute("TempEmployeePW").toString();
        System.out.println("Password " + employeePassword);
        String employeeEmail = request.getParameter("email");
        System.out.println("Email " + employeeEmail);
        String firstName = request.getParameter("firstName");
        System.out.println("First Name " + firstName);
        String lastName = request.getParameter("lastName");
        System.out.println("Last Name " + firstName);
        int mobileNumber = Integer.parseInt(request.getParameter("mobileNumber"));
        System.out.println("Mobile No. " + mobileNumber);
        int officeNumber = Integer.parseInt(request.getParameter("officeNumber"));
        System.out.println("Office No. " + officeNumber);
        String gender = request.getParameter("gender");
        char genderChar = '\0';
        genderChar = gender.charAt(genderChar);
        System.out.println("Gender " + genderChar);
        String dob = request.getParameter("dateOfBirth");
        Date dateOfBirth = sdf.parse(dob);
        System.out.println("DoB " + dob);
        String country = request.getParameter("country");
        System.out.println("Nationality " + country);
        String address = request.getParameter("address");
        System.out.println("Address " + address);
        String city = null;
        city = request.getParameter("city");
        System.out.println("City " + city);
        String bgct = request.getParameter("beginContract");
        Date beginContract = sdf.parse(bgct);
        System.out.println("Begin Contract " + beginContract);
        String ect = request.getParameter("endContract");
        Date endContract = sdf.parse(ect);
        System.out.println("End Contract " + endContract);
        double salary = 0;
        String timestamp = null;
        String photo = null;

        try {
            System.out.println("Creating New Employee...");
            cabl.createEmployee(employeeID, firstName, lastName, mobileNumber, officeNumber,
                    genderChar, address, city, beginContract, endContract, employeeEmail,
                    cabl.encryptPassword(employeePassword), country, dateOfBirth, photo, salary, timestamp, levelNameSelected,
                    departmentNameSelected, positionApplied);
            message = "new";
        } catch (Exception ex) {

        }
        System.out.println("Employee is" + message);
        return message;
    }

    private String deleteLevel(int index) {
        String checkStatus = "";
        try {
            List<String> levelList = levelListInfo();
            String selectedLevel = levelList.get(index);
            String levelName = "";
            StringTokenizer st = new StringTokenizer(selectedLevel, "#");
            st.nextToken(); //Skipping the first level classification
            levelName = st.nextToken();
            System.out.println("Level Name is: " + levelName);
            cabl.deleteLevel(levelName);
            checkStatus = "Level Deleted.";
        } catch (Exception ex) {

        }
        System.out.println(checkStatus);
        return checkStatus;
    }

    private String deleteDepartment(int index) {
        String checkStatus = "";
        try {
            List<String> departmentList = cabl.findLevelDepartment();
            String selectedLevel = departmentList.get(index);
            String departmentName, levelName = "";
            StringTokenizer st = new StringTokenizer(selectedLevel, "#");
            st.nextToken(); //Skipping for levelTypeChar
            levelName = st.nextToken();
            st.nextToken(); //Skipping for departmentID
            departmentName = st.nextToken();
            System.out.println("Level & Department Name is: " + levelName + departmentName);
            cabl.deleteDepartment(levelName, departmentName);
            checkStatus = "Department Deleted.";
        } catch (Exception ex) {

        }
        System.out.println(checkStatus);
        return checkStatus;
    }

    //Fixing
    private String deletePosition(int index) {
        String checkStatus = "";
        try {
            List<String> departmentList = cabl.findLevelDepartmentPosition();
            String selectedLevel = departmentList.get(index);
            String departmentName, positionName = "";
            StringTokenizer st = new StringTokenizer(selectedLevel, "#");
            st.nextToken(); //Skipping for levelTypeChar
            st.nextToken(); //Skipping for levelName
            departmentName = st.nextToken();
            st.nextToken(); //Skipping for departmentID
            positionName = st.nextToken();
            System.out.println("Department & Position Name is: " + departmentName + positionName);
            cabl.deletePosition(departmentName, positionName);
            checkStatus = "Position Deleted.";
        } catch (Exception ex) {

        }
        System.out.println(checkStatus);
        return checkStatus;
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
