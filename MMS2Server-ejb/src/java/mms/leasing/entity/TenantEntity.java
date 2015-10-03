/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.leasing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author PhiLong
 */
@Entity
public class TenantEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String businessType;
    private ArrayList<String> description= new ArrayList();
    private String mallName;
    private String address;
    private String tel;
    private String email;
    @OneToOne
    private TenantContractEntity tenantContract;
    @OneToMany(cascade={CascadeType.ALL},mappedBy = "tenant")
    private Collection<UnitEntity> units = new ArrayList<UnitEntity>();

    public TenantEntity() {
    }

    public TenantEntity(String mallName, String name, String businessType, ArrayList<String> description,
            String address, String email, String tel) {
        this.name = name;
        this.businessType = businessType;
        this.mallName=mallName;
        this.description = description;
        this.address=address;
        this.email=email;
        this.tel=tel;
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
        if (!(object instanceof TenantEntity)) {
            return false;
        }
        TenantEntity other = (TenantEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.TenantEntity[ id=" + id + " ]";
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
     * @return the unitNumber
     */
    /**
     * @return the tenantContract
     */
    public TenantContractEntity getTenantContract() {
        return tenantContract;
    }

    /**
     * @param tenantContract the tenantContract to set
     */
    public void setTenantContract(TenantContractEntity tenantContract) {
        this.tenantContract = tenantContract;
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
     * @return the units
     */
    public Collection<UnitEntity> getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(Collection<UnitEntity> units) {
        this.units = units;
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

}
