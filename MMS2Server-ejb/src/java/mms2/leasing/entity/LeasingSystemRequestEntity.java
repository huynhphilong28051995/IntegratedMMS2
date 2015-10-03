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
public class LeasingSystemRequestEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receiver;
    private String sender;
    private String senderUserName;
    private String type;
    private String status="PENDING";
    private String mallName;
    private Long applicationId;
    private ArrayList<String> applyUnitList;
    private ArrayList<String> description=  new ArrayList();
    private ArrayList<String> listOfUnitOpenForBidding  =new ArrayList();
    public Long getId() {
        return id;
    }

    public LeasingSystemRequestEntity(String sender, String senderUserName, String receiver, String type, String mallName) {
        this.receiver = receiver;
        this.sender = sender;
        this.senderUserName=  senderUserName;
        this.type = type;
        this.mallName= mallName;
        this.status="PENDING";
        
    }

    public LeasingSystemRequestEntity() {
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
        if (!(object instanceof LeasingSystemRequestEntity)) {
            return false;
        }
        LeasingSystemRequestEntity other = (LeasingSystemRequestEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.LeasingSystemRequest[ id=" + id + " ]";
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the senderUserName
     */
    public String getSenderUserName() {
        return senderUserName;
    }

    /**
     * @param senderUserName the senderUserName to set
     */
    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    /**
     * @return the listOfUnitOpenForBidding
     */
    public ArrayList<String> getListOfUnitOpenForBidding() {
        return listOfUnitOpenForBidding;
    }

    /**
     * @param listOfUnitOpenForBidding the listOfUnitOpenForBidding to set
     */
    public void setListOfUnitOpenForBidding(ArrayList<String> listOfUnitOpenForBidding) {
        this.listOfUnitOpenForBidding = listOfUnitOpenForBidding;
    }

    /**
     * @return the applyUnitList
     */
    public ArrayList<String> getApplyUnitList() {
        return applyUnitList;
    }

    /**
     * @param applyUnitList the applyUnitList to set
     */
    public void setApplyUnitList(ArrayList<String> applyUnitList) {
        this.applyUnitList = applyUnitList;
    }

    /**
     * @return the aplicationId
     */
    public Long getApplicationId() {
        return applicationId;
    }

    /**
     * @param aplicationId the aplicationId to set
     */
    public void setApplicationId(Long aplicationId) {
        this.applicationId = aplicationId;
    }
    
}
