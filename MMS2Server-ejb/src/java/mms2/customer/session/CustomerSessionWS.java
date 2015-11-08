/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.customer.session;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mms2.customer.entity.CustomerEntity;

/**
 *
 * @author PhiLong
 */
@Stateless
@WebService
public class CustomerSessionWS {
    @PersistenceContext
    private EntityManager em;
    
    @WebMethod
    public boolean createCustomer(@WebParam(name="email")String email,@WebParam(name="password") String password,
            @WebParam(name="firstName")String firstName,
            @WebParam(name="lastName")String lastName,@WebParam(name="address") String address, 
            @WebParam(name="telephone")String telephone){
      try{
          CustomerEntity customer= new CustomerEntity();
          customer.setEmail(email);
          customer.setPassword(password);
          customer.setFirstName(firstName);
          customer.setLastName(lastName);
          customer.setAddress(address);
          customer.setTelephone(telephone);
          customer.setPoints(0);
          em.persist(customer);
          return true;
      }catch(Exception ex){
          System.err.println("CustomerSessionWS_createCustomer throw exception");
          ex.printStackTrace();
          return false;
      }  
    }
}
