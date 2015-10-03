package mms2.ejb.administration.session;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms2.administration.entity.DepartmentEntity;
import mms2.administration.entity.EmployeeEntity;
import mms2.administration.entity.LevelTypeEntity;
import mms2.administration.entity.PositionEntity;

/**
 *
 * @author GOHENGCHI
 */
@Stateless
public class CorporateAdministrationBean implements CorporateAdministrationBeanLocal {
    @PersistenceContext(unitName = "MMS2Server-ejbPU")
    private EntityManager em;
    private LevelTypeEntity levelTypeEntity;
    private DepartmentEntity departmentEntity;
    private PositionEntity positionEntity;
    private EmployeeEntity employeeEntity;
    
        public CorporateAdministrationBean() {

        }

        /** ------------------------ Web Functions ------------------------------**/
        //WF 1: Retrieve Employee ID/Email and Employee Password to Validate for Login
        //Get Employee ID/Email and Password
        //WF 1.1 Find Employee, return employee first name, id, email, password if exist
        @Override
        public List<String> findEmployeeByIDEmailPassword(String employeeIDEmail, String employeePassword) {
            List<String> list = new ArrayList();
            try {
                Query q = em.createQuery("SELECT e FROM Employee AS e"
                        + " WHERE e.email=:employeeIDEmail AND e.password=:employeePassword");
                q.setParameter("employeeIDEmail", employeeIDEmail);
                String encryptedPassword = encryptPassword(employeePassword);
                q.setParameter("employeePassword", encryptedPassword);
                EmployeeEntity employee = (EmployeeEntity)q.getResultList().get(0);
                String firstName = employee.getFirstName();
                String employeeID =  employee.getEmployeeID();
                String email = employee.getEmail();
                String password  = employee.getPassword();
                String timestamp = employee.getTimestamp();
                String position = employee.getPositions().getPositionTitle();
                String employeeLevel = employee.getLevelTypes().getLevelName();
                list.add(firstName);
                list.add(employeeID);
                list.add(email);
                list.add(password);
                list.add(timestamp);
                list.add(position);
                list.add(employeeLevel);
                
            } catch (Exception ex) {
                  return list;
            }

            return list;
        }

        @Override
        public void updateNewTimestamp(String employeeIDEmail) {
            //Set Format for Timestamp
            Date now = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, d MMMM yyyy 'at' h:m:s a z");
            EmployeeEntity employee = new EmployeeEntity();
            
            try {
            Query q = em.createQuery("SELECT e FROM Employee "
                    + "AS e WHERE e.email=:employeeIDEmail");
            q.setParameter("employeeIDEmail", employeeIDEmail);
            employee = (EmployeeEntity)q.getSingleResult();
            
            String newTimestamp = dateFormatter.format(now);
            employee.setTimestamp(newTimestamp);
            
            } catch (Exception ex) {

            }
            
        }
        
        @Override
        public void updatePassword(String email, String newPassword) {
        
        EmployeeEntity e = new EmployeeEntity();
        try {
            Query q = em.createQuery("SELECT e FROM Employee "
                    + "AS e WHERE e.email=:email");
            q.setParameter("email", email);
            e = (EmployeeEntity)q.getSingleResult();
            e.setPassword(encryptPassword(newPassword));
            
        } catch (Exception ex) {
              
        }
        
        }
        
        //WF 1.2: Encrypt Password
        @Override
        public String encryptPassword(String password) {
                String encrypted = null;
            if (password == null)
                return null;
            else {
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes(), 0 , password.length());
                    encrypted = new BigInteger(1, md.digest()).toString(16);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            return encrypted;
        }
        
        @Override
        public void createLevelType(String levelType, String levelName, 
            String levelCountry, String levelAddress, String levelPostalCode) {
        System.out.println("Performing Creation of Level Type");
        levelTypeEntity = new LevelTypeEntity();
        levelTypeEntity.create(levelType, levelName, levelCountry, 
                levelAddress, levelPostalCode);
        em.persist(levelTypeEntity);
        }
        
        @Override
        public void createDepartment(String departmentName, int departmentPhoneNumber,
                String departmentAddress, String departmentPostalCode,
                String departmentUnitNumber) {
            System.out.println("Performing Creation of Department");
            departmentEntity = new DepartmentEntity();
            departmentEntity.create(departmentName, departmentPhoneNumber,
                    departmentAddress, departmentPostalCode, departmentUnitNumber);
            em.persist(departmentEntity);
        }
        
        @Override
        public void associateLevelDepartment(String levelName) {
            System.out.println("Level Name is:" + levelName);
            levelTypeEntity = findByLevelName(levelName);

            Collection<DepartmentEntity> departments = levelTypeEntity.getDepartments();
            departments.add(departmentEntity);
            levelTypeEntity.setDepartments(departments);

            em.merge(levelTypeEntity);
            em.flush();
        }
        
        //Search/Find by Level Name
        private LevelTypeEntity findByLevelName(String levelName) {

            LevelTypeEntity m = new LevelTypeEntity();
            try {
                Query q = em.createQuery("SELECT m FROM LevelType " +
                            "AS m WHERE m.levelName=:levelName");
                q.setParameter("levelName", levelName);
                m = (LevelTypeEntity)q.getSingleResult();

            } catch (EntityNotFoundException enfe) {
                  System.out.println("\nEntity Not Found error: " + 
                          enfe.getMessage());
            } catch (NonUniqueResultException nure) {
                   System.out.println("\nNon Unique Result error: " + 
                          nure.getMessage());           
            }
            return m;
        }
        
        //Search/Find by Department Name
        private DepartmentEntity findByDepartmentName(String departmentName) {
            DepartmentEntity d = new DepartmentEntity();
            try {
                Query q = em.createQuery("SELECT d FROM Department " +
                            "AS d WHERE d.departmentName=:departmentName");
                q.setParameter("departmentName", departmentName);
                d = (DepartmentEntity)q.getSingleResult();

            } catch (EntityNotFoundException enfe) {
                  System.out.println("\nEntity Not Found error: " + 
                          enfe.getMessage());
            } catch (NonUniqueResultException nure) {
                   System.out.println("\nNon Unique Result error: " + 
                          nure.getMessage());           
            }
            return d;
        }
        
        //List all level information
        @Override
        public List<String> findLevelType() {
        
        List entityList = new ArrayList();
        LevelTypeEntity lt;

        try {
            
            Query q = em.createQuery("SELECT lt FROM LevelType lt");
            for (Object o: q.getResultList()) {
                lt = (LevelTypeEntity)o;
                String entity = new String();
                entity = entity + lt.getLevelTypeChar() + "#";
                entity = entity + lt.getLevelName() + "#";
                entity = entity + lt.getLevelCountry() + "#";
                entity = entity + lt.getLevelAddress() + "#";
                entity = entity + lt.getLevelPostalCode() + "#";
                entityList.add(entity);
            }
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //List all department information
        @Override
        public List<String> findDepartment() {
        
        List entityList = new ArrayList();
        DepartmentEntity dept;

        try {
            
            Query q = em.createQuery("SELECT d FROM Department d");
            for (Object o: q.getResultList()) {
                dept = (DepartmentEntity)o;
                String entity = new String();
                entity = entity + dept.getDepartmentID() + "#";
                entity = entity + dept.getDepartmentName() + "#";
                entity = entity + dept.getDepartmentAddress() + "#";
                entity = entity + dept.getDepartmentPostalCode() + "#";
                entity = entity + dept.getDepartmentUnitNumber() + "#";
                entity = entity + dept.getDepartmentPhoneNumber() + "#";
                entityList.add(entity);
            }
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //List all level' department information
        @Override
        public List<String> findLevelDepartment() {
        
        List entityList = new ArrayList();
        LevelTypeEntity lt;
        String entity = new String();
        
        try {
            
            Query q = em.createQuery("SELECT lt FROM LevelType lt");
                    
            for (Object o: q.getResultList()) {
                lt = (LevelTypeEntity)o;
                //All Departments in Corporate Headquarter
                if (lt.getLevelTypeChar().equals("C")) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        //System.out.println(departmentEntity.getDepartmentName());
                        entity = entity + lt.getLevelTypeChar() + "#";
                        entity = entity + lt.getLevelName() + "#";
                        entity = entity + departmentEntity.getDepartmentID() + "#";
                        entity = entity + departmentEntity.getDepartmentName() + "#";
                        entity = entity + departmentEntity.getDepartmentAddress() + "#";
                        entity = entity + departmentEntity.getDepartmentPostalCode() + "#";
                        entity = entity + departmentEntity.getDepartmentUnitNumber() + "#";
                        entity = entity + departmentEntity.getDepartmentPhoneNumber() + "#";
                        entityList.add(entity);
                        entity = "";
                    }
                }
                //All Departments in Local Branch Office
                if (lt.getLevelTypeChar().equals("L")) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        //System.out.println(departmentEntity.getDepartmentName());
                        entity = entity + lt.getLevelTypeChar() + "#";
                        entity = entity + lt.getLevelName() + "#";
                        entity = entity + departmentEntity.getDepartmentID() + "#";
                        entity = entity + departmentEntity.getDepartmentName() + "#";
                        entity = entity + departmentEntity.getDepartmentAddress() + "#";
                        entity = entity + departmentEntity.getDepartmentPostalCode() + "#";
                        entity = entity + departmentEntity.getDepartmentUnitNumber() + "#";
                        entity = entity + departmentEntity.getDepartmentPhoneNumber() + "#";
                        entityList.add(entity);
                        entity = "";
                    }

                }
                //All Departments in Shopping Mall
                if (lt.getLevelTypeChar().equals("M")) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        //System.out.println(departmentEntity.getDepartmentName());
                        entity = entity + lt.getLevelTypeChar() + "#";
                        entity = entity + lt.getLevelName() + "#";
                        entity = entity + departmentEntity.getDepartmentID() + "#";
                        entity = entity + departmentEntity.getDepartmentName() + "#";
                        entity = entity + departmentEntity.getDepartmentAddress() + "#";
                        entity = entity + departmentEntity.getDepartmentPostalCode() + "#";
                        entity = entity + departmentEntity.getDepartmentUnitNumber() + "#";
                        entity = entity + departmentEntity.getDepartmentPhoneNumber() + "#";
                        entityList.add(entity);
                        entity = "";
                    }

                }
            }        
                    
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //List all level' department' position information
        @Override
        public List<String> findLevelDepartmentPosition() {
        
        List entityList = new ArrayList();
        LevelTypeEntity lt;
        String entity = new String();
        
        try {
            
            Query q = em.createQuery("SELECT lt FROM LevelType lt");
                    
            for (Object o: q.getResultList()) {
                lt = (LevelTypeEntity)o;
                //All Departments in Corporate Headquarter
                if (lt.getLevelTypeChar().equals("C")) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        Iterator ir2 = departmentEntity.getPositions().iterator();
                        while (ir2.hasNext()) {
                            positionEntity = (PositionEntity) ir2.next();
                            entity = entity + lt.getLevelTypeChar() + "#";
                            entity = entity + lt.getLevelName() + "#";
                            entity = entity + departmentEntity.getDepartmentName() + "#";
                            entity = entity + positionEntity.getPositionID() + "#";
                            entity = entity + positionEntity.getPositionTitle() + "#";
                            entityList.add(entity);
                            entity = "";
                        }
                        
                    }
                }
                //All Departments in Local Branch Office
                if (lt.getLevelTypeChar().equals("L")) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        Iterator ir2 = departmentEntity.getPositions().iterator();
                        while (ir2.hasNext()) {
                            positionEntity = (PositionEntity) ir2.next();
                            entity = entity + lt.getLevelTypeChar() + "#";
                            entity = entity + lt.getLevelName() + "#";
                            entity = entity + departmentEntity.getDepartmentName() + "#";
                            entity = entity + positionEntity.getPositionID() + "#";
                            entity = entity + positionEntity.getPositionTitle() + "#";
                            entityList.add(entity);
                            entity = "";
                        }
                        
                    }
                }
                //All Departments in Shopping Mall
                if (lt.getLevelTypeChar().equals("M")) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        Iterator ir2 = departmentEntity.getPositions().iterator();
                        while (ir2.hasNext()) {
                            positionEntity = (PositionEntity) ir2.next();
                            entity = entity + lt.getLevelTypeChar() + "#";
                            entity = entity + lt.getLevelName() + "#";
                            entity = entity + departmentEntity.getDepartmentName() + "#";
                            entity = entity + positionEntity.getPositionID() + "#";
                            entity = entity + positionEntity.getPositionTitle() + "#";
                            entityList.add(entity);
                            entity = "";
                        }
                        
                    }
                }
            }        
                    
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //List all employee information
        @Override
        public List<String> findEmployee() {
        
        List entityList = new ArrayList();
        EmployeeEntity employee;

        try {
            
            Query q = em.createQuery("SELECT e FROM Employee e");
            for (Object o: q.getResultList()) {
                employee = (EmployeeEntity)o;
                String entity = new String();
                //To edit where needed
                entity = entity + employee.getEmployeeID() + "#";
                entity = entity + employee.getFirstName() + "#";
                entity = entity + employee.getLastName() + "#";
                entity = entity + employee.getEmail() + "#";
                //entity = entity + employee.getAddress() + "#";
                //entity = entity + employee.getCity() + "#";
                //entity = entity + employee.getPhoto() + "#";
                entity = entity + employee.getLevelTypes().getLevelTypeChar() + "#";
                entity = entity + employee.getLevelTypes().getLevelName() + "#";
                entity = entity + employee.getDepartments().getDepartmentName() + "#";
                entity = entity + employee.getPositions().getPositionTitle() + "#";
                entity = entity + employee.getEndContract() + "#";
                entityList.add(entity);
            }
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        @Override
        public List<String> findLevelAddressPostalCode(String levelNameSelected) {
        
        List entityList = new ArrayList();
        LevelTypeEntity lt = new LevelTypeEntity();

        try {
            
            Query q = em.createQuery("SELECT lt FROM LevelType "
                    + "AS lt WHERE lt.levelName=:levelNameSelected");
            q.setParameter("levelNameSelected", levelNameSelected);
            for (Object o: q.getResultList()) {
                lt = (LevelTypeEntity)o;
                String address;
                String postal;
                address = lt.getLevelAddress();
                entityList.add(address);
                postal = lt.getLevelPostalCode();
                entityList.add(postal);
                
            }
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //Retrieve all name from a given level
        @Override
        public List<String> retrieveAllLevelName(String levelTypeChar) {
        
        List entityList = new ArrayList();
        LevelTypeEntity lt;

        try {
                        
            Query q = em.createQuery("SELECT lt FROM LevelType lt");
            //q.setParameter("levelTypeChar", levelTypeChar);
            
            for (Object o: q.getResultList()) {
                lt = (LevelTypeEntity)o;
                String entity = new String();
                if (lt.getLevelTypeChar().equals(levelTypeChar)) {
                    entity = entity + lt.getLevelName();
                    entityList.add(entity);
                    System.out.println("Entity is "+entity);
                }
            }
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //Retrieve all department(s) for a given level (specific (i.e. mall))
        @Override
        public List<String> retrieveLevelTypeDepartment(String levelNameSelected) {
        
        List entityList = new ArrayList();
        LevelTypeEntity lt;

        try {
                        
            Query q = em.createQuery("SELECT lt FROM LevelType lt");
            
            for (Object o: q.getResultList()) {
                lt = (LevelTypeEntity)o;
                if (lt.getLevelName().equals(levelNameSelected)) {
                    Iterator ir = lt.getDepartments().iterator();
                    while (ir.hasNext()) {
                        departmentEntity = (DepartmentEntity) ir.next();
                        System.out.println(departmentEntity.getDepartmentName());
                        entityList.add(departmentEntity.getDepartmentName());
                    }

                }
            }
        }
        catch (Exception ex) {
            return null;
        }
        
            return entityList;
            
        }
        
        //Associate Department with Position (Given Level)
        @Override
        public String associateDepartmentPosition(List<String> newPositionList, String levelName, String departmentName) {
        
        String message = "";
        
        try {
            
            levelTypeEntity = findByLevelName(levelName);
            
            for (DepartmentEntity departmentEntity : levelTypeEntity.getDepartments()) {
                if (departmentEntity.getDepartmentName().equals(departmentName)) {
                    
                    ArrayList<PositionEntity> newPositionEntities = new ArrayList<>();                                       
 
                    for (String newPositionName : newPositionList) {                       
                        PositionEntity newPositionEntity = new PositionEntity();
                        newPositionEntity.setPositionTitle(newPositionName);
                        em.persist(newPositionEntity);
                       
                        newPositionEntities.add(newPositionEntity);                                               
                    }
                   
                    departmentEntity.getPositions().addAll(newPositionEntities);                                       
                }
            }
            
            /*for(DepartmentEntity departmentEntity:levelTypeEntity.getDepartments())
            {
                if (departmentEntity.getDepartmentName().equals(departmentName)) {
                    
                    for(String newPositionName:newPositionList)
                    {
                        System.err.println("newPositionName: " + newPositionName);
                        PositionEntity newPositionEntity = new PositionEntity();
                        newPositionEntity.setPositionTitle(newPositionName);
                        em.persist(newPositionEntity);
                        departmentEntity.getPositions().add(positionEntity);
                    }
                }
            }*/
            
            /*
            //2. Associate the Position to the Respective department
            Iterator ir = levelTypeEntity.getDepartments().iterator();
                while (ir.hasNext()) {
                    departmentEntity = (DepartmentEntity) ir.next();
                    if (departmentEntity.getDepartmentName().equals(departmentName)) {
                        //department name matches selection record (Points to correct dept)
                        //Add Position(s) to this department
                        //Retrieve all current collection of position(s) if any
                        Collection<PositionEntity> positions = departmentEntity.getPositions();
                        //1. Create a Position
                        for (int i=0; i<newPositionList.size(); i++) {
                            System.out.println("1. System is adding new Position");
                            System.out.println("Adding into position collections" + newPositionList.get(i).toString());
                            positionEntity.setPositionTitle(newPositionList.get(i).toString());
                            positions.add(positionEntity);
                        }
                        departmentEntity.setPositions(positions);
                        System.out.println("2. System is associating position to department");
                    }
                }
            
            em.merge(departmentEntity);
            em.flush();
            */
            
            //System.err.println("END FOR EACH LOOP");

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
            return message;    
        }
        
        //Retrieve all position(s) in a given department of a specified level
        @Override
        public List<String> retrieveDepartmentPosition(String levelName, String departmentName) {
        
        List positionList = new ArrayList();
            
        try {
            levelTypeEntity = findByLevelName(levelName);    
            
            Iterator ir = levelTypeEntity.getDepartments().iterator();
                while (ir.hasNext()) {
                    departmentEntity = (DepartmentEntity) ir.next();
                    if (departmentEntity.getDepartmentName().equals(departmentName)) {
                        //department name matches selection record (Points to correct dept)
                        //Retrieve Position(s) in this department
                        
                        Iterator ir2 = departmentEntity.getPositions().iterator();
                        while (ir2.hasNext()) {
                            //Retrieve all available positions in this department
                            positionEntity = (PositionEntity) ir2.next();
                            System.out.println("Position Retrieved is " +positionEntity.getPositionTitle());
                            positionList.add(positionEntity.getPositionTitle());
                        }
                    }
                }
        }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }    
            
        return positionList;    
    }
    
    private String findEmployeeID() {
        int employeeIDInt = 0;
        EmployeeEntity e;
        List entityList = new ArrayList();
        String formatEmployeeIDInt;
        Query q = em.createQuery("SELECT e FROM Employee AS e");
            for (Object o: q.getResultList()) {
                e = (EmployeeEntity)o;
                String employeeIDFull;
                
                employeeIDFull = e.getEmployeeID();
                employeeIDInt = Integer.parseInt(employeeIDFull.substring(2, 6));
                //All Integer Employee ID (e.g. SG0001M will get 0001)
                entityList.add(employeeIDInt);
                
            }
            Collections.sort(entityList);
            //Retrieve and get the largest Employee ID Integer
            employeeIDInt = Integer.parseInt(entityList.get(entityList.size()-1).toString());    
            formatEmployeeIDInt = String.format("%04d", employeeIDInt+1);
            System.out.println("Employee ID Generated (String): " + formatEmployeeIDInt);
        
        return formatEmployeeIDInt;
    }
          
    @Override
    public String generateEmployeeID(String levelName) {
        String newEmployeeID = "";
        String employeeIDNumber = findEmployeeID();
        Query q = em.createQuery("SELECT lt.levelCountry FROM LevelType AS lt"
                        + " WHERE lt.levelName=:levelName");
                q.setParameter("levelName", levelName);
                String levelCountry = (String)q.getSingleResult();
        Query q2 = em.createQuery("SELECT lt.levelTypeChar FROM LevelType AS lt"
                        + " WHERE lt.levelName=:levelName");
                q2.setParameter("levelName", levelName);
                String levelTypeChar = (String)q2.getSingleResult();
        
        //New Employee ID Generation
        newEmployeeID = levelCountry + employeeIDNumber + levelTypeChar;
        System.out.println("New Employee ID is: "+ newEmployeeID);
        return newEmployeeID;
    }
    
    @Override
    public void createEmployee(String employeeID, String firstName, 
            String lastName, int phoneNumber, int officeNumber, 
            char gender, String address, String city, 
            Date beginContract, Date endContract, String email, 
            String password, String nationality, Date dateOfBirth, 
            String photo, double salary, String timestamp, String levelNameSelected,
            String departmentNameSelected, String positionApplied) {
        try {
        System.out.println("CABL Creating New Employee");
        employeeEntity = new EmployeeEntity();
        employeeEntity.create(employeeID, firstName, lastName, phoneNumber, 
                officeNumber, gender, address, city, beginContract, endContract, 
                email, password, nationality, dateOfBirth, photo, salary, timestamp);
        
        levelTypeEntity = new LevelTypeEntity();
        try {
            Query q = em.createQuery("SELECT m FROM LevelType " +
                        "AS m WHERE m.levelName=:levelName");
            q.setParameter("levelName", levelNameSelected);
            levelTypeEntity = (LevelTypeEntity)q.getSingleResult();
            employeeEntity.setLevelTypes(levelTypeEntity);
            
            Iterator ir = levelTypeEntity.getDepartments().iterator();
                while (ir.hasNext()) {
                    departmentEntity = (DepartmentEntity) ir.next();
                    if (departmentEntity.getDepartmentName().equals(departmentNameSelected)) {
                        employeeEntity.setDepartments(departmentEntity);
                    }
                    Iterator ir2 = departmentEntity.getPositions().iterator();
                    while (ir2.hasNext()) {
                        positionEntity = (PositionEntity) ir2.next();
                        if (positionEntity.getPositionTitle().equals(positionApplied)) {
                            employeeEntity.setPositions(positionEntity);
                        }
                    }
                }
            

        } catch (EntityNotFoundException enfe) {
              System.out.println("\nEntity Not Found error: " + 
                      enfe.getMessage());
        } catch (NonUniqueResultException nure) {
               System.out.println("\nNon Unique Result error: " + 
                      nure.getMessage());           
        }
        
        em.persist(employeeEntity);
        }
        catch (Exception ex) {
            
        }
    }
    /**************************************************************************/
    // Delete an Organizational Level
    /**************************************************************************/
    @Override
    public void deleteLevel(String levelName) {
        try {
        System.out.println("Deleting Level Query CABL");
        Query q = em.createQuery("DELETE FROM LevelType lt WHERE lt.levelName =:levelName");
        q.setParameter("levelName", levelName);
        q.executeUpdate();
        }
        catch (Exception ex) {
            
        }
    }
    
    /**************************************************************************/
    // Delete a Department in a Level
    /**************************************************************************/
    @Override
    public void deleteDepartment(String levelName, String departmentName) {
        try {
        System.out.println("Deleting Department Query CABL");
        System.out.println(departmentName);
        
        /* Query q = em.createQuery("DELETE FROM Department dp WHERE dp.departmentName =:departmentName");
        q.setParameter("departmentName", departmentName);
        q.executeUpdate(); */
        
        LevelTypeEntity lt = findByLevelName(levelName);   
        Iterator ir = lt.getDepartments().iterator();
            while (ir.hasNext()) {
                departmentEntity = (DepartmentEntity) ir.next();
                if (departmentEntity.getDepartmentName().equals(departmentName)) {
                       //departmentEntity.setEmployees(null);
                       //departmentEntity.setPositions(null);
                       lt.setDepartments(null);
                       em.remove(departmentEntity); 
                       em.flush();
                }
            }
        }
        catch (Exception ex) {
        }
        //departmentEntity = findByDepartmentID(departmentID);
        //System.out.println(departmentEntity.getDepartmentName());
        //departmentEntity.setEmployees(null);
        //departmentEntity.setPositions(null);
        
        //em.persist(departmentEntity);
        //em.flush();

    }
    
    /**************************************************************************/
    // Delete a Position in a Department
    /**************************************************************************/
    @Override
    public void deletePosition(String departmentName, String positionName) {
        try {
        System.out.println("Deleting Position Query CABL");
        System.out.println(departmentName);
        
        DepartmentEntity dp = findByDepartmentName(departmentName);   
        Iterator ir = dp.getPositions().iterator();
            while (ir.hasNext()) {
                positionEntity = (PositionEntity) ir.next();
                
                    Collection<PositionEntity> positions = departmentEntity.getPositions();
                    for (int i=0; i<positions.size(); i++) {
                        if (positionEntity.getPositionTitle().equals(positionName)) {
                            positions.remove(positionEntity);
                        }
                    }

                    dp.setPositions(positions);
                    //em.remove(positionEntity); 
                    em.persist(dp);
                    em.flush();
                
            }
        }
        catch (Exception ex) {
        }

    }
    
    
    /**************************************************************************/
    // Delete an Employee
    /**************************************************************************/
    /* @Override
    public void deleteEmployee(String employeeID) {
        Query q = em.createQuery("DELETE FROM Employee e WHERE e.employeeID =:employeeID");
        q.setParameter("employeeID", employeeID);
        q.executeUpdate();
    }
    */
    
    
}

