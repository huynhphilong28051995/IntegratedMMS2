/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author PhiLong
 */
@Entity
public class LevelEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mallName;
    private String levelCode;
    private ArrayList<String> unitPositionList = new ArrayList<String>();
    private ArrayList<String> unitPositionListPrototype = new ArrayList<String>();
    private String floorplanBackground;
    private boolean confirm = false;
    private boolean show= false;
    @OneToMany(cascade={CascadeType.ALL},mappedBy="level")
    private Collection<UnitEntity> units= new ArrayList<UnitEntity>();
    
    public LevelEntity(){
    }

    public LevelEntity(String mallName, String levelCode, String floorplanBackground) {
        this.mallName = mallName;
        this.levelCode = levelCode;
        this.floorplanBackground=floorplanBackground;
        unitPositionList.add("{}");
        unitPositionListPrototype.add("{}");
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
        if (!(object instanceof LevelEntity)) {
            return false;
        }
        LevelEntity other = (LevelEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.LevelEntity[ id=" + id + " ]";
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
     * @return the levelCode
     */
    public String getLevelCode() {
        return levelCode;
    }

    /**
     * @param levelCode the levelCode to set
     */
    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    /**
     * @return the unitPositionList
     */
    public ArrayList<String> getUnitPositionList() {
        return unitPositionList;
    }

    /**
     * @param unitPositionList the unitPositionList to set
     */
    public void setUnitPositionList(ArrayList<String> unitPositionList) {
        this.unitPositionList = unitPositionList;
    }

    /**
     * @return the units
     */
    public Collection<UnitEntity> getUnits() {
        return units;
    }


    /**
     * @return the floorplanBackground
     */
    public String getFloorplanBackground() {
        return floorplanBackground;
    }

    /**
     * @param floorplanBackground the floorplanBackground to set
     */
    public void setFloorplanBackground(String floorplanBackground) {
        this.floorplanBackground = floorplanBackground;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(Collection<UnitEntity> units) {
        this.units = units;
    }

    /**
     * @return the unitPositionListPrototype
     */
    public ArrayList<String> getUnitPositionListPrototype() {
        return unitPositionListPrototype;
    }

    /**
     * @param unitPositionListPrototype the unitPositionListPrototype to set
     */
    public void setUnitPositionListPrototype(ArrayList<String> unitPositionListPrototype) {
        this.unitPositionListPrototype = unitPositionListPrototype;
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
    }
}
