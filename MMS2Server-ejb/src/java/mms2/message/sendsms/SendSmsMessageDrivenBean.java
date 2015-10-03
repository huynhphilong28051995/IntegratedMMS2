package mms2.message.sendsms;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.OutputStream;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author GOHENGCHI
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queueSmsMessage"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SendSmsMessageDrivenBean implements MessageListener {
    
    public SendSmsMessageDrivenBean() {
    }
    
    @PostConstruct
    public void init()
    {
        System.err.println("SmsMessageBean: PostConstruct");        
    }
    
    @PreDestroy
    public void destroy()
    {
        System.err.println("SmsMessageBean: PreDestroy");        
    }
    
    @Override
    public void onMessage(Message message) {
        
        MapMessage mapMessage = null;

        
        
        try
        {
            if(message instanceof MapMessage)
            {                
                mapMessage = (MapMessage)message;
                
                String smsRecipientNumber = mapMessage.getString("smsRecipientNumber");
                String smsMessage = mapMessage.getString("smsMessage");
                
                System.err.println("Sending SMS: " + smsRecipientNumber + ": " + smsMessage);
                
                try
                {
                    String initString1 = "AT" + (char)13;
                    String initString2 = "AT+CMGF=1" + (char)13;
                    String cmdString1 = "AT+CMGS=" + smsRecipientNumber + (char)13;
                    String cmdString2 = smsMessage + (char)26;                                        
                    
                    System.err.println("initString1: " + initString1);
                    System.err.println("initString2: " + initString2);
                    System.err.println("cmdString1: " + cmdString1);
                    System.err.println("cmdString2: " + cmdString2);
                                        
                    CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier("COM3");
                    SerialPort serialPort = (SerialPort)commPortIdentifier.open("SMS", 2000);
                    serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    OutputStream outputStream = serialPort.getOutputStream();                    
                    
                    outputStream.write(initString1.getBytes());                    
                    outputStream.write(initString2.getBytes());                    
                    outputStream.write(cmdString1.getBytes());                    
                    outputStream.write(cmdString2.getBytes());
                    
                    Thread.sleep(2000);
                    
                    serialPort.close();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }                
                                
                System.err.println("Sent");
            }
            else
            {
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
