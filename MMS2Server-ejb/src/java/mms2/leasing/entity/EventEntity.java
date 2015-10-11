/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author PhiLong
 */
@Entity
public class EventEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostName;
    private String tel;
    private String email;
    private ArrayList<String> description;
    private Timestamp startDate;
    private Timestamp endDate;
    private String mallName;
    private ArrayList<String> unitList;
    @OneToOne(cascade={CascadeType.ALL})
    private EventContractEntity contract;
    private boolean approved = false;
    
    public EventEntity() {
    }

    public EventEntity(String hostName, String tel, String email, ArrayList<String> description,
            Timestamp startDate, Timestamp endDate,String mallName,ArrayList<String> unitList) {
        this.hostName = hostName;
        this.tel = tel;
        this.email = email;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mallName=mallName;
        this.unitList = unitList;
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
        if (!(object instanceof EventEntity)) {
            return false;
        }
        EventEntity other = (EventEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mms2.leasing.entity.EventEntity[ id=" + id + " ]";
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
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

    /**
     * @return the startDate
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Timestamp getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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
     * @return the unitList
     */
    public ArrayList<String> getUnitList() {
        return unitList;
    }

    /**
     * @param unitList the unitList to set
     */
    public void setUnitList(ArrayList<String> unitList) {
        this.unitList = unitList;
    }

   
    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the contract
     */
    public EventContractEntity getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(EventContractEntity contract) {
        this.contract = contract;
    }

    
}
