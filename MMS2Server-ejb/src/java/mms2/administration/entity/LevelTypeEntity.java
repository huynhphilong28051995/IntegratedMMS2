package mms2.administration.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author GOHENGCHI
 */
@Entity(name="LevelType")
public class LevelTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long levelID; 
    private String levelName;
    private String levelTypeChar;
    private String levelCountry;
    private String levelAddress;
    private String levelPostalCode;
    
    @OneToMany(cascade={CascadeType.ALL}, mappedBy="levelTypes")
    private Collection<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
    public Collection<EmployeeEntity> getEmployees() {
        return employees;
    }
    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employees = employees;
    }
    
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<DepartmentEntity> departments = 
            new ArrayList<DepartmentEntity>();
    
    //Department Entity - 1 Level to Many Department(s) (UNI)
    public Collection<DepartmentEntity> getDepartments() {
        return departments;
    }
    public void setDepartments(Collection<DepartmentEntity> departments) {
        this.departments = departments;
    } 
    
    public LevelTypeEntity() {
        setLevelID(System.nanoTime());
    }
    
    public void create(String levelTypeChar, String levelName, String levelCountry, 
            String levelAddress, String levelPostalCode) {
        this.setLevelTypeChar(levelTypeChar);
        this.setLevelName(levelName);
        this.setLevelCountry(levelCountry);
        this.setLevelAddress(levelAddress);
        this.setLevelPostalCode(levelPostalCode);
    }
    
    public Long getLevelID() {
        return levelID;
    }

    public void setLevelID(Long levelID) {
        this.levelID = levelID;
    }
    
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    
    public String getLevelTypeChar() {
        return levelTypeChar;
    }

    public void setLevelTypeChar(String levelTypeChar) {
        this.levelTypeChar = levelTypeChar;
    }
    
    public String getLevelCountry() {
        return levelCountry;
    }

    public void setLevelCountry(String levelCountry) {
        this.levelCountry = levelCountry;
    }
    
    public String getLevelAddress() {
        return levelAddress;
    }

    public void setLevelAddress(String levelAddress) {
        this.levelAddress = levelAddress;
    }
    
    public String getLevelPostalCode() {
        return levelPostalCode;
    }

    public void setLevelPostalCode(String levelPostalCode) {
        this.levelPostalCode = levelPostalCode;
    }
    
}
