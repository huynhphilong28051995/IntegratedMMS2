/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mms2.leasing.session;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import mms2.leasing.entity.EventEntity;
import mms2.leasing.entity.TenantEntity;

/**
 *
 * @author PhiLong
 */
public class SendMail_AcceptEvent {
     private String to;
    private String from;
    private String message;
    private String subject;
    private String smtpServ;

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the smtpServ
     */
    public String getSmtpServ() {
        return smtpServ;
    }

    /**
     * @param smtpServ the smtpServ to set
     */
    public void setSmtpServ(String smtpServ) {
        this.smtpServ = smtpServ;
    }
    
    //Send Mail Function
    public int sendMail(EventEntity event){
        String email = event.getEmail();
        String hostName  = event.getHostName();
        String mallName = event.getMallName();
        String eventStart = event.getStartDate().toString();
        String eventEnd = event.getEndDate().toString();
        double amount = event.getContract().getAmount();
        try
        {
            Properties props = System.getProperties();
              // -- Attaching to default Session, or we could start a new one --
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.smtp.host","smtp.gmail.com");
              props.put("mail.smtp.auth", "true" );
              props.put("mail.smtp.ssl.enable", "true");
              Authenticator auth = new SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              // -- Create a new message --
              Message msg = new MimeMessage(session);
              // -- Set the FROM and TO fields --
              msg.setFrom(new InternetAddress("Java.Mail.CA@gmail.com"));
              subject = "Merlion Mall Asia Event Application Acceptance";
              message = "Hi, " +hostName+" !\n\n"+
                      "This mail is generated from "+mallName+"'s leasing system\n"
                      + "Your event application has been approved\n\n"
                      + "Your event information\n\n"
                      + "Start : "+eventStart+"\n\n"
                      + "End : "+eventEnd+"\n\n"
                      + "Pay amount : "+amount+"\n\n"
                      +"Thank you and have a pleasent stay at MMA\n"
                      + "\n\nThis is a System Generated Response, Please do not reply.\n\n"
                      + "Information in this email is confidential and may also be privileged. It is intended\n" +
                        "solely for the person to whom it is addressed. If you are not the intended recipient,\n" +
                        "please notify the sender, and please delete the message and any other record of it\n" +
                        "from your system immediately.";
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email,false));
              msg.setSubject(subject);
              msg.setText(message);
              // -- Set some other header information --
              msg.setHeader("LeaseRenewalReminder", hostName );
              msg.setSentDate(new Date());
              // -- Send the message --
              Transport.send(msg);
              System.out.println("Message sent to "+email+" OK." );
              return 0;
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
          System.out.println("Exception "+ex);
          return -1;
        }
  }

    // Also include an inner class that is used for authentication purposes

    private class SMTPAuthenticator extends javax.mail.Authenticator {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                String username =  "huynhphilong28051995@gmail.com";           // specify your email id here (sender's email id)
                String password = "bonidom2805";                          // specify your password here
                return new PasswordAuthentication(username, password);
            }
      }
    
}
