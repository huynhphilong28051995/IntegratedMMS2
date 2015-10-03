package mms2.administration.entity;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author GOHENGCHI
 */
@Entity(name="Employee")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String employeeID;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private int officeNumber;
    private char gender;
    private String address;
    private String city;
    @Temporal(value = TemporalType.DATE)
    private Date beginContract;
    @Temporal(value = TemporalType.DATE)
    private Date endContract;
    private String email;
    private String password;
    private String nationality;
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;
    private String photo;
    private double salary;
    private String timestamp;
    
    @OneToOne(cascade={CascadeType.ALL}, orphanRemoval=true)
    private PositionEntity positions;
    
    //Synopsis Entity - One Module to One Synopsis (UNI)
    public PositionEntity getPositions() {
        return positions;
    }

    public void setPositions(PositionEntity positions) {
        this.positions = positions;
    }
    
    @ManyToOne
    private DepartmentEntity departments = new DepartmentEntity();
    public DepartmentEntity getDepartments() {
        return departments;
    }
    //Department Entity - 1 Department to Many Employee(s) (BI)
    public void setDepartments(DepartmentEntity departments) {
        this.departments = departments;
    }
    
    @ManyToOne
    private LevelTypeEntity levelTypes = new LevelTypeEntity();
    public LevelTypeEntity getLevelTypes() {
        return levelTypes;
    }
    //Department Entity - 1 Department to Many Employee(s) (BI)
    public void setLevelTypes(LevelTypeEntity levelTypes) {
        this.levelTypes = levelTypes;
    }
    
    public void create(String employeeID, String firstName, String lastName, 
            int phoneNumber, int officeNumber, char gender, String address, 
            String city, Date beginContract, Date endContract, String email, 
            String password, String nationality, Date dateOfBirth, 
            String photo, double salary, String timestamp) {
        this.setEmployeeID(employeeID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setOfficeNumber(officeNumber);
        this.setGender(gender);
        this.setAddress(address);
        this.setCity(city);
        this.setBeginContract(beginContract);
        this.setEndContract(endContract);
        this.setEmail(email);
        this.setPassword(password);
        this.setNationality(nationality);
        this.setDateOfBirth(dateOfBirth);
        this.setPhoto(photo);
        this.setSalary(salary);
        this.setTimestamp(timestamp);
    }
    
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }
    
    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public Date getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(Date beginContract) {
        this.beginContract = beginContract;
    }
    
    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
