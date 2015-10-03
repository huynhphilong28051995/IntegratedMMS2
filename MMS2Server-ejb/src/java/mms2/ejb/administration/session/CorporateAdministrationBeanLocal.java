/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.ejb.administration.session;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author huynhphilong
 */
@Local
public interface CorporateAdministrationBeanLocal {

    public List<String> findEmployeeByIDEmailPassword(String employeeIDEmail, String employeePassword);

    public String encryptPassword(String password);
    public void createLevelType(String levelType, String levelName, 
            String levelCountry, String levelAddress, String levelPostalCode);
    public List<String> findLevelType();
    public void updateNewTimestamp(String employeeIDEmail);
    public List<String> retrieveAllLevelName(String levelTypeChar);
    public List<String> retrieveLevelTypeDepartment(String levelNameSelected);
    public List<String> findLevelAddressPostalCode(String levelNameSelected);
    public void createDepartment(String departmentName, int departmentPhoneNumber,
                String departmentAddress, String departmentPostalCode,
                String departmentUnitNumber);
    public void associateLevelDepartment(String levelName);
    public List<String> findDepartment();
    public List<String> findEmployee();
    public List<String> retrieveDepartmentPosition(String levelName, String departmentName);
    public String associateDepartmentPosition(List<String> newPositionList, String levelName, String departmentName);
    public String generateEmployeeID(String levelName);
    public void createEmployee(String employeeID, String firstName, 
            String lastName, int phoneNumber, int officeNumber, 
            char gender, String address, String city, 
            Date beginContract, Date endContract, String email, 
            String password, String nationality, Date dateOfBirth, 
            String photo, double salary, String timestamp, String levelNameSelected,
            String departmentNameSelected, String positionApplied);
     public void deleteLevel(String levelName);
     public List<String> findLevelDepartment();
     public List<String> findLevelDepartmentPosition();
     public void deleteDepartment(String levelName, String departmentName);
     public void deletePosition(String departmentName, String positionName);
     public void updatePassword(String email, String newPassword);
}
