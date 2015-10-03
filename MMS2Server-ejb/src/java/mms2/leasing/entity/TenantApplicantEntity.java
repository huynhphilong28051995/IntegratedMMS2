/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author PhiLong
 */
@Entity
public class TenantApplicantEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mallName;
    private ArrayList<String> chosenUnitList = new ArrayList();
    private String name;
    private String businessType;
    private ArrayList<String> description= new ArrayList();
    private String address;
    private String tel;
    private String email;

    public TenantApplicantEntity() {
    }

    public TenantApplicantEntity(String mallName, String businessType, String name, 
            ArrayList<String> description, String address, String email, String tel) {
        this.name = name;
        this.businessType = businessType;
        this.mallName = mallName;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.description=description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenantApplicantEntity)) {
            return false;
        }
        TenantApplicantEntity other = (TenantApplicantEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.TenantApplicantEntity[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the businessType
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * @param businessType the businessType to set
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * @return the description
     */
    public ArrayList<String> getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    /**
     * @return the mallName
     */
    public String getMallName() {
        return mallName;
    }

    /**
     * @param mallName the mallName to set
     */
    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the chosenUnitList
     */
    public ArrayList<String> getChosenUnitList() {
        return chosenUnitList;
    }

    /**
     * @param chosenUnitList the chosenUnitList to set
     */
    public void setChosenUnitList(ArrayList<String> chosenUnitList) {
        this.chosenUnitList = chosenUnitList;
    }
    
}
