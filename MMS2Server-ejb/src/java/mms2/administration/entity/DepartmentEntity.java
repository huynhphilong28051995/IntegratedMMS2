package mms2.administration.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author GOHENGCHI
 */
@Entity(name="Department")
public class DepartmentEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long departmentID; 
    private String departmentName;
    private int departmentPhoneNumber;
    private String departmentAddress;
    private String departmentPostalCode;
    private String departmentUnitNumber;
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="departments")
    private Collection<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }
    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }
    
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<PositionEntity> positions = 
            new ArrayList<PositionEntity>();
    
    //Position Entity - 1 Department to Many Positions(s) (UNI)
    public Collection<PositionEntity> getPositions() {
        return positions;
    }
    public void setPositions(Collection<PositionEntity> positions) {
        this.positions = positions;
    } 
    
    public DepartmentEntity() {
        setDepartmentID(System.nanoTime());
    }
    
    public void create(String departmentName, int departmentPhoneNumber,
            String departmentAddress, String departmentPostalCode,
            String departmentUnitNumber) {
        this.setDepartmentName(departmentName);
        this.setDepartmentPhoneNumber(departmentPhoneNumber);
        this.setDepartmentAddress(departmentAddress);
        this.setDepartmentPostalCode(departmentPostalCode);
        this.setDepartmentUnitNumber(departmentUnitNumber);
    }
    
    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public int getDepartmentPhoneNumber() {
        return departmentPhoneNumber;
    }

    public void setDepartmentPhoneNumber(int departmentPhoneNumber) {
        this.departmentPhoneNumber = departmentPhoneNumber;
    }
    
    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }
    
    public String getDepartmentPostalCode() {
        return departmentPostalCode;
    }

    public void setDepartmentPostalCode(String departmentPostalCode) {
        this.departmentPostalCode = departmentPostalCode;
    }
    
    public String getDepartmentUnitNumber() {
        return departmentUnitNumber;
    }

    public void setDepartmentUnitNumber(String departmentUnitNumber) {
        this.departmentUnitNumber = departmentUnitNumber;
    }
}