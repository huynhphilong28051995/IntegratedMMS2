/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.message.sendsms;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author GOHENGCHI
 */
@Stateless
public class SmsSessionBean implements SmsSessionBeanLocal {

    @Resource(mappedName = "jms/queueSmsMessage")
    private Queue queueSmsMessage;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    
    
    
    @Override
    public void sendNewCustomerPassword(String smsRecipientNumber)
    {
        //String smsRecipientNumber = "+" + customer.getMobilePhoneCountryCode() + customer.getMobilePhoneNumber();
        String smsMessage = "Send Sms Test Successful";
        
        HashMap<String, String> messageData = new HashMap<>();
        messageData.put("smsRecipientNumber", smsRecipientNumber);
        messageData.put("smsMessage", smsMessage);

        sendJMSMessageToQueueSmsMessage(messageData);
    }
    
    
    
    private void sendJMSMessageToQueueSmsMessage(Map messageData) {
        context.createProducer().send(queueSmsMessage, messageData);
    }
}
