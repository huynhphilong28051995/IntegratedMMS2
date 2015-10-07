package mms2.dataloader.setup;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mms2.administration.entity.DepartmentEntity;
import mms2.administration.entity.EmployeeEntity;
import mms2.administration.entity.PositionEntity;
import mms2.administration.entity.LevelTypeEntity;

/**
 *
 * @author GOHENGCHI
 */
@Stateless
public class LoadEmployeeDataBean implements LoadEmployeeDataBeanRemote {
    @PersistenceContext(unitName = "MMS2Server-ejbPU")
    private EntityManager em;
    private DepartmentEntity departmentEntity;
    private LevelTypeEntity levelTypeEntity;
    private PositionEntity positionEntity;
    private EmployeeEntity employeeEntity;
    
    //Set Format for Timestamp
    Date now = new Date();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, d MMMM yyyy 'at' h:m:s a z");
    
    //Initialize and declare variables
    String encryptedPW = encryptPassword("password");
    
    //Define Date Format Srandards as dd/MM/yyyy
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public LoadEmployeeDataBean () {
        
    }
    
    @Override
    public void setUp() {

        //Setup Organization Levels
        createLevelType("C", "Merlion Headquarter", "SG", "2 Orchard Turn", "238801");
        createLevelType("L", "Merlion Local Office", "SG", "2 West Coast Drive", "239901");
        createLevelType("M", "Merlion West", "SG", "2 Jurong Lake District", "236601");

        //Setup Organization Departments and Associate Department(s) to HQ(s)
        createDepartment("Advertising and Promotion", 67263721, "2 Orchard Turn", "238801", "06-01");
        associateLevelDepartment("Merlion Headquarter");
        
        createDepartment("Computer Services (IT)", 67263722, "2 Orchard Turn", "238801", "06-01");
        associateLevelDepartment("Merlion Headquarter");
        
        createDepartment("Facilities", 67263723, "2 Orchard Turn", "238801", "06-01");
        associateLevelDepartment("Merlion Headquarter");
        
        createDepartment("Leasing", 67263726, "2 Jurong Lake District", "236601", "06-01");
        associateLevelDepartment("Merlion West");
        createPosition("Manager");
        associateDepartmentPosition("Leasing");
        try {
            createEmployee("SG0001M", "Ada", "Lim", 89588958, 63140027, 'F',
                    "Blk 512 Clementi Road #05-422", "", sdf.parse("23/05/2013"), sdf.parse("23/05/2016"),
                    "adalim89@gmail.com", encryptedPW, "SG",
                    sdf.parse("01/10/1989"), "", 4200, dateFormatter.format(now));
        } catch (ParseException ex) {
            Logger.getLogger(LoadEmployeeDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        createPosition("Leasing Officer");
        associateDepartmentPosition("Leasing");
        try {
            createEmployee("SG0002M", "Long Officer", "Huynh", 87821819, 63140127, 'M',
                    "Blk 465 Jurong West Road #09-12", "", sdf.parse("23/05/2012"), sdf.parse("23/12/2015"),
                    "long@gmail.com", encryptedPW, "SG",
                    sdf.parse("13/06/1986"), "", 3800, dateFormatter.format(now));
        } catch (ParseException ex) {
            Logger.getLogger(LoadEmployeeDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        createPosition("Leasing Manager");
        associateDepartmentPosition("Leasing");
        try {
            createEmployee("SG0002K", "Long Manager", "Wong", 87821819, 63140127, 'M',
                    "Blk 465 Jurong West Road #09-12", "", sdf.parse("23/05/2012"), sdf.parse("23/12/2015"),
                    "hplong95@gmail.com", encryptedPW, "SG",
                    sdf.parse("13/06/1986"), "", 3800, dateFormatter.format(now));
        } catch (ParseException ex) {
            Logger.getLogger(LoadEmployeeDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        createPosition("Space Plan Officer");
        associateDepartmentPosition("Leasing");
        try {
            createEmployee("SG0002F", "Long Space", "Wong", 87821819, 63140127, 'M',
                    "Blk 465 Jurong West Road #09-12", "", sdf.parse("23/05/2012"), sdf.parse("23/12/2015"),
                    "huynhphilong28051995@gmail.com", encryptedPW, "SG",
                    sdf.parse("13/06/1986"), "", 3800, dateFormatter.format(now));
        } catch (ParseException ex) {
            Logger.getLogger(LoadEmployeeDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //createDepartment("Finance", 67263724, "2 Orchard Turn", "238801", "06-01"); 
        
        //createDepartment("Human Resource", 67263725, "2 Orchard Turn", "238801", "06-01");
        
        createDepartment("Leasing", 67263726, "2 Orchard Turn", "238801", "06-01");
        associateLevelDepartment("Merlion Headquarter");
        
        createDepartment("Security", 67263727, "2 Orchard Turn", "238801", "06-01");
        associateLevelDepartment("Merlion Headquarter");
        
        //Setup Organization Position and Associate Position(s) to Department(s)
        createPosition("Administrator");
        associateDepartmentPosition("Computer Services (IT)");
        try {
            createEmployee("SG0001C", "Alfred", "Tan", 89588958, 63140027, 'M',
                    "Blk 412 Saujana Road #02-231", "", sdf.parse("23/05/2013"), sdf.parse("23/05/2016"),
                    "alfredtan90@gmail.com", encryptedPW, "SG",
                    sdf.parse("01/01/1982"), "", 3800, dateFormatter.format(now));
        } catch (ParseException ex) {
            Logger.getLogger(LoadEmployeeDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        createPosition("Manager");
        associateDepartmentPosition("Advertising and Promotion");
        try {
            createEmployee("SG0002C", "Adrain", "Tan", 87821819, 63140127, 'M',
                    "Blk 123 Bukit Panjang Ring Road #12-212", "", sdf.parse("23/05/2012"), sdf.parse("23/05/2015"),
                    "adraintan@gmail.com", encryptedPW, "SG",
                    sdf.parse("23/01/1991"), "", 3800, dateFormatter.format(now));
        } catch (ParseException ex) {
            Logger.getLogger(LoadEmployeeDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        createPosition("Manager");
        associateDepartmentPosition("Facilities");
        createPosition("Officer");
        associateDepartmentPosition("Facilities");
        //associateDepartmentPosition("Finance");
        //associateDepartmentPosition("Human Resource");
        createPosition("Manager");
        associateDepartmentPosition("Leasing");
        createPosition("Officer");
        associateDepartmentPosition("Leasing");
        createPosition("Manager");
        associateDepartmentPosition("Security");
        createPosition("Officer");
        associateDepartmentPosition("Security");
        createPosition("Officer");
        associateDepartmentPosition("Advertising and Promotion");
        createPosition("Officer");
        associateDepartmentPosition("Computer Services (IT)");    
        //associateDepartmentPosition("Finance");
        //associateDepartmentPosition("Human Resource");


        
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
    
    public void createLevelType(String levelType, String levelName, 
            String levelCountry, String levelAddress, String levelPostalCode) {
        levelTypeEntity = new LevelTypeEntity();
        levelTypeEntity.create(levelType, levelName, levelCountry, 
                levelAddress, levelPostalCode);
        em.persist(levelTypeEntity);
    }
    
    public void createDepartment(String departmentName, int departmentPhoneNumber,
            String departmentAddress, String departmentPostalCode,
            String departmentUnitNumber) {
        departmentEntity = new DepartmentEntity();
        departmentEntity.create(departmentName, departmentPhoneNumber,
                departmentAddress, departmentPostalCode, departmentUnitNumber);
        em.persist(departmentEntity);
    }
    
    public void createPosition(String positionTitle) {
        positionEntity = new PositionEntity();
        positionEntity.create(positionTitle);
        em.persist(positionEntity);
    }
    
    public void createEmployee(String employeeID, String firstName, 
            String lastName, int phoneNumber, int officeNumber, 
            char gender, String address, String city, 
            Date beginContract, Date endContract, String email, 
            String password, String nationality, Date dateOfBirth, 
            String photo, double salary, String timestamp) {
        employeeEntity = new EmployeeEntity();
        employeeEntity.create(employeeID, firstName, lastName, phoneNumber, 
                officeNumber, gender, address, city, beginContract, endContract, 
                email, password, nationality, dateOfBirth, photo, salary, timestamp);
        employeeEntity.setLevelTypes(levelTypeEntity);
        employeeEntity.setDepartments(departmentEntity);
        employeeEntity.setPositions(positionEntity);
    em.persist(employeeEntity);
    }
    
    public void associateLevelDepartment(String levelName) {
        levelTypeEntity = findByLevelName(levelName);
        
        Collection<DepartmentEntity> departments = levelTypeEntity.getDepartments();
        departments.add(departmentEntity);
        levelTypeEntity.setDepartments(departments);
        
        em.merge(levelTypeEntity);
        em.flush();
    }
    
    public void associateDepartmentPosition(String departmentName) {
        departmentEntity = findByDepartmentName(departmentName);
        
        Collection<PositionEntity> positions = departmentEntity.getPositions();
        positions.add(positionEntity);
        departmentEntity.setPositions(positions);
        
        em.merge(departmentEntity);
        em.flush();
    }
    
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

}
