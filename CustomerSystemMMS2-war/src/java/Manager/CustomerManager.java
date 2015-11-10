/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import mms2.customer.entity.CustomerEntity;
import mms2.customer.entity.GiftEntity;
import mms2.customer.entity.RedemptionHistoryEntity;
import mms2.customer.session.CustomerManagerBeanLocal;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author AdminNUS
 */
public class CustomerManager {

    private CustomerManagerBeanLocal customerManagerBeanLocal;

    public CustomerManager(CustomerManagerBeanLocal customerManagerBeanLocal) {
        this.customerManagerBeanLocal = customerManagerBeanLocal;
    }

    public boolean verifyLogin(HttpServletRequest request) {
        String email = request.getParameter("email");
        request.getSession().setAttribute("email", email);
        String password = request.getParameter("password");
        return customerManagerBeanLocal.verifyLogin(email, password);
    }

    public CustomerEntity getCustomer(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        return customerManagerBeanLocal.getCustomer(email);
    }

    public String changePassword(String email, HttpServletRequest request) {
        String oldpassword = request.getParameter("oldpassword");
        String newpassword1 = request.getParameter("newpassword1");
        String newpassword2 = request.getParameter("newpassword2");
        return customerManagerBeanLocal.changePassword(email, oldpassword, newpassword1, newpassword2);
    }

    public String changeAddress(String email, HttpServletRequest request) {
        String address = request.getParameter("oldaddress");
        String newaddress = request.getParameter("newaddress");
        return customerManagerBeanLocal.changeAddress(email, address, newaddress);
    }

    public String changeNumber(String email, HttpServletRequest request) {
        String oldnumber = request.getParameter("oldnumber");
        String newnumber = request.getParameter("newnumber");
        return customerManagerBeanLocal.changeNumber(email, oldnumber, newnumber);
    }
    
    public List<GiftEntity> getGifts(String country) {
        return customerManagerBeanLocal.getGifts(country);
    }
    
    public String redeemGift(HttpServletRequest request){
        long giftID = Long.parseLong(request.getParameter("giftId"));
        String email = (String) request.getSession().getAttribute("email");
        return customerManagerBeanLocal.redeemGift(giftID, email);
    }
    
    public ArrayList<SaleEntity> getPurchases(String email) {
        return customerManagerBeanLocal.getPurchases(email);
    }
    
     public ArrayList<RedemptionHistoryEntity> getRedemptionHistory(String email) {
        return customerManagerBeanLocal.getRedemptionHistory(email);
    }
    

}
