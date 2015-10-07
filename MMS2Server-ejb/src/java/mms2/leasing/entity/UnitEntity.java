/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.entity;

import java.io.Serializable;
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
public class UnitEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//id is to differentiate units between malls
    private String locationCode;// locationCode is to differentiate units within a mall
    private String category = "";
    private String categoryPrototype = "";
    private boolean hasTenant = false;
    private String mallName;
    private boolean openForPublicBiddingPrototype= false;
    private boolean openForPublicBidding = false;
    private boolean openForInternalBiddingPrototype = false;
    private boolean openForInternalBidding = false;
    private boolean deleteProposed = false;
    //this is a Many to One relationship
    private TenantEntity tenant;
    //this is a Many to One relationship
    private LevelEntity level;
    private boolean confirm = false;
    private boolean show =false;
    private boolean pushCart=false; //default is false because its a store unit
    private boolean kiosk = false;
    private boolean event = false;
    
    private boolean hasPendingTenant = false;
    
    
    public UnitEntity() {
    }

    public UnitEntity( LevelEntity level,String mallName, String locationCode, boolean pushCart, boolean kiosk, boolean event) {
        this.locationCode = locationCode;
        this.mallName = mallName;
        this.level = level;
        this.pushCart = pushCart;
        this.kiosk = kiosk;
        this.event = event;
    }

    public UnitEntity(LevelEntity level, String mallName, String locationCode) {
        this.locationCode = locationCode;
        this.mallName = mallName;
        this.level = level;
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
        if (!(object instanceof UnitEntity)) {
            return false;
        }
        UnitEntity other = (UnitEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.UnitEntity[ id=" + id + " ]";
    }

    /**
     * @return the locationCode
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * @param locationCode the locationCode to set
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the tenant
     */
    public TenantEntity getTenant() {
        return tenant;
    }

    /**
     * @param tenant the tenant to set
     */
    public void setTenant(TenantEntity tenant) {
        this.tenant = tenant;
    }

    /**
     * @return the hasTenant
     */
    public boolean isHasTenant() {
        return hasTenant;
    }

    /**
     * @param hasTenant the hasTenant to set
     */
    public void setHasTenant(boolean hasTenant) {
        this.hasTenant = hasTenant;
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
     * @return the level
     */
    public LevelEntity getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(LevelEntity level) {
        this.level = level;
    }

    /**
     * @return the pushCart
     */
    public boolean isPushCart() {
        return pushCart;
    }

    /**
     * @param pushCart the pushCart to set
     */
    public void setPushCart(boolean pushCart) {
        this.pushCart = pushCart;
    }

    /**
     * @return the kiosk
     */
    public boolean isKiosk() {
        return kiosk;
    }

    /**
     * @param kiosk the kiosk to set
     */
    public void setKiosk(boolean kiosk) {
        this.kiosk = kiosk;
    }

    /**
     * @return the event
     */
    public boolean isEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(boolean event) {
        this.event = event;
    }

    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the show
     */
    public boolean isShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(boolean show) {
        this.show = show;
    } /**
     * @return the categoryPrototype
     */
    public String getCategoryPrototype() {
        return categoryPrototype;
    }

    /**
     * @param categoryPrototype the categoryPrototype to set
     */
    public void setCategoryPrototype(String categoryPrototype) {
        this.categoryPrototype = categoryPrototype;
    }

  
    /**
     * @return the deleteProposed
     */
    public boolean isDeleteProposed() {
        return deleteProposed;
    }

    /**
     * @param deleteProposed the deleteProposed to set
     */
    public void setDeleteProposed(boolean deleteProposed) {
        this.deleteProposed = deleteProposed;
    }

    /**
     * @return the openForPublicBiddingPrototype
     */
    public boolean isOpenForPublicBiddingPrototype() {
        return openForPublicBiddingPrototype;
    }

    /**
     * @param openForPublicBiddingPrototype the openForPublicBiddingPrototype to set
     */
    public void setOpenForPublicBiddingPrototype(boolean openForPublicBiddingPrototype) {
        this.openForPublicBiddingPrototype = openForPublicBiddingPrototype;
    }

    /**
     * @return the openForPublicBidding
     */
    public boolean isOpenForPublicBidding() {
        return openForPublicBidding;
    }

    /**
     * @param openForPublicBidding the openForPublicBidding to set
     */
    public void setOpenForPublicBidding(boolean openForPublicBidding) {
        this.openForPublicBidding = openForPublicBidding;
    }

    /**
     * @return the openForInternalBiddingPrototype
     */
    public boolean isOpenForInternalBiddingPrototype() {
        return openForInternalBiddingPrototype;
    }

    /**
     * @param openForInternalBiddingPrototype the openForInternalBiddingPrototype to set
     */
    public void setOpenForInternalBiddingPrototype(boolean openForInternalBiddingPrototype) {
        this.openForInternalBiddingPrototype = openForInternalBiddingPrototype;
    }

    /**
     * @return the openForInternalBidding
     */
    public boolean isOpenForInternalBidding() {
        return openForInternalBidding;
    }

    /**
     * @param openForInternalBidding the openForInternalBidding to set
     */
    public void setOpenForInternalBidding(boolean openForInternalBidding) {
        this.openForInternalBidding = openForInternalBidding;
    }

    /**
     * @return the hasPendingTenant
     */
    public boolean isHasPendingTenant() {
        return hasPendingTenant;
    }

    /**
     * @param hasPendingTenant the hasPendingTenant to set
     */
    public void setHasPendingTenant(boolean hasPendingTenant) {
        this.hasPendingTenant = hasPendingTenant;
    }



}
