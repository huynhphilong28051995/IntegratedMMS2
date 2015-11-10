/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.customer.session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import mms2.customer.entity.CustomerEntity;
import mms2.customer.entity.GiftEntity;
import mms2.customer.entity.RedemptionHistoryEntity;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
@Local
public interface CustomerManagerBeanLocal {

    public CustomerEntity getCustomer(String email);

    public boolean verifyLogin(String email, String password);

    public String changePassword(String email, String password, String newPassword1, String newPassword2);
    
    public String changeAddress(String email, String address, String newaddress);
    
    public String changeNumber(String email, String oldnumber, String newnumber);
    
    public List<GiftEntity> getGifts(String country);
    
    public String redeemGift(Long giftID, String email);
    
    public ArrayList<SaleEntity> getPurchases(String email);
    
    public ArrayList<RedemptionHistoryEntity> getRedemptionHistory(String email);

}
