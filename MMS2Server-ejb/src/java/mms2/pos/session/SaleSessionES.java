/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.pos.session;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mms2.pos.entity.SaleEntity;

/**
 *
 * @author PhiLong
 */
@WebService
@Stateless
public class SaleSessionES {
    @PersistenceContext
    private EntityManager em;
    @WebMethod
    public String createSale(@WebParam(name="sale")SaleEntity sale){
        try{
            em.persist(sale);
            em.flush();
            return sale.getId().toString();
        }catch(Exception ex){
            System.err.println("SaleSessionWS_createSale throw exception");
            ex.printStackTrace();
            return "Error";
        }
    }
}
