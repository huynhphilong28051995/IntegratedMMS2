package mms2.administration.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author GOHENGCHI
 */
@Entity(name="Position")
public class PositionEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionID;
    @Column(unique = false)
    private String positionTitle;

    public PositionEntity() {
        setPositionID(System.nanoTime());
    }
    
    public void create(String positionTitle) {
        this.setPositionTitle(positionTitle);
    }
     
    public Long getPositionID() {
        return positionID;
    }

    public void setPositionID(Long positionID) {
        this.positionID = positionID;
    } 
    
    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    
}
