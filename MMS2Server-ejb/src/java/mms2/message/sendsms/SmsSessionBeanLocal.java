/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.message.sendsms;

import javax.ejb.Local;

/**
 *
 * @author GOHENGCHI
 */
@Local
public interface SmsSessionBeanLocal {
    void sendNewCustomerPassword(String smsRecipientNumber);
}
