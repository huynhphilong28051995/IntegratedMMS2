/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms.facility.session;

import java.util.ArrayList;
import javax.ejb.Local;
import mms.facility.entity.OutsourcingEntity;

/**
 *
 * @author linjiao_Zoe
 */
@Local
public interface OutsourcingManagerSessionLocal {

    public ArrayList<OutsourcingEntity> listOutsourcing(String mallName);

    public void deleteOutsourcing(Long outsourcingId);

    public OutsourcingEntity getOutsourcing(Long outsourcingId);

    public OutsourcingEntity updateStatus(Long outsourcingId, String status);
    
}
