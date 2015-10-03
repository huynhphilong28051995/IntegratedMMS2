/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.leasing.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class LongTermApplicationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mallName;
    private String applicantName;
    private String applicantBusinessType;
    private ArrayList<String> applicantDescription = new ArrayList<String>();
    private String applicantAddress;
    private String applicantEmail;
    private String applicantTel;
    private double applicantBidRate;
    private ArrayList<String> applyUnitList  = new ArrayList<>();
    private Timestamp contractStart;
    private Timestamp contractEnd;
    private double contractDeposit;
    private ArrayList<String> contractDescription = new ArrayList<>();
    
    
    public LongTermApplicationEntity() {
    }

    public LongTermApplicationEntity(String mallName, String applicantName, 
            String applicantBusinessType,
            String applicantAddress, String applicantEmail, String applicantTel,
            double applicantBidRate) {
        this.mallName = mallName;
        this.applicantName = applicantName;
        this.applicantBusinessType = applicantBusinessType;
        this.applicantAddress = applicantAddress;
        this.applicantEmail = applicantEmail;
        this.applicantTel = applicantTel;
        this.applicantBidRate = applicantBidRate;
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
        if (!(object instanceof LongTermApplicationEntity)) {
            return false;
        }
        LongTermApplicationEntity other = (LongTermApplicationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.LongTermApplicationEntity[ id=" + id + " ]";
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
     * @return the applicantName
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * @param applicantName the applicantName to set
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * @return the applicantDescription
     */
    public ArrayList<String> getApplicantDescription() {
        return applicantDescription;
    }

    /**
     * @param applicantDescription the applicantDescription to set
     */
    public void setApplicantDescription(ArrayList<String> applicantDescription) {
        this.applicantDescription = applicantDescription;
    }

    /**
     * @return the applicantAddress
     */
    public String getApplicantAddress() {
        return applicantAddress;
    }

    /**
     * @param applicantAddress the applicantAddress to set
     */
    public void setApplicantAddress(String applicantAddress) {
        this.applicantAddress = applicantAddress;
    }

    /**
     * @return the applicantEmail
     */
    public String getApplicantEmail() {
        return applicantEmail;
    }

    /**
     * @param applicantEmail the applicantEmail to set
     */
    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    /**
     * @return the applicantTel
     */
    public String getApplicantTel() {
        return applicantTel;
    }

    /**
     * @param applicantTel the applicantTel to set
     */
    public void setApplicantTel(String applicantTel) {
        this.applicantTel = applicantTel;
    }

    /**
     * @return the applicantBidRate
     */
    public double getApplicantBidRate() {
        return applicantBidRate;
    }

    /**
     * @param applicantBidRate the applicantBidRate to set
     */
    public void setApplicantBidRate(double applicantBidRate) {
        this.applicantBidRate = applicantBidRate;
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
     * @return the contractStart
     */
    public Timestamp getContractStart() {
        return contractStart;
    }

    /**
     * @param contractStart the contractStart to set
     */
    public void setContractStart(Timestamp contractStart) {
        this.contractStart = contractStart;
    }

    /**
     * @return the contractEnd
     */
    public Timestamp getContractEnd() {
        return contractEnd;
    }

    /**
     * @param contractEnd the contractEnd to set
     */
    public void setContractEnd(Timestamp contractEnd) {
        this.contractEnd = contractEnd;
    }

    /**
     * @return the contractDeposit
     */
    public double getContractDeposit() {
        return contractDeposit;
    }

    /**
     * @param contractDeposit the contractDeposit to set
     */
    public void setContractDeposit(double contractDeposit) {
        this.contractDeposit = contractDeposit;
    }

    /**
     * @return the contractDescription
     */
    public ArrayList<String> getContractDescription() {
        return contractDescription;
    }

    /**
     * @param contractDescription the contractDescription to set
     */
    public void setContractDescription(ArrayList<String> contractDescription) {
        this.contractDescription = contractDescription;
    }

    /**
     * @return the applicantBusinessType
     */
    public String getApplicantBusinessType() {
        return applicantBusinessType;
    }

    /**
     * @param applicantBusinessType the applicantBusinessType to set
     */
    public void setApplicantBusinessType(String applicantBusinessType) {
        this.applicantBusinessType = applicantBusinessType;
    }
    
}
