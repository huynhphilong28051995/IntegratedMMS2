package mms2.mail.sendmail;

import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateful;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author GOHENGCHI
 */
@Stateful
public class SendMail implements SendMailLocal {

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
    public int sendMail(String employeeEmailAddress, String employeefirstName, String employeeLastName){
        try
        {
            Properties props = System.getProperties();
              // -- Attaching to default Session, or we could start a new one --
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.smtp.host",smtpServ);
              props.put("mail.smtp.auth", "true" );
              Authenticator auth = new SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              // -- Create a new message --
              Message msg = new MimeMessage(session);
              // -- Set the FROM and TO fields --
              msg.setFrom(new InternetAddress(from));
              to = employeeEmailAddress;
              subject = "Merlion Mall Asia Welcomes You Onboard to an Endless Opportunities!";
              message = "Welcome, " + employeefirstName + " " + employeeLastName +"!\n\n"+
                      "MMA welcomes you to embark with a journey with us!\n"
                      + "Your account has been activated and ready to use.\n\n"
                      + "Before you are able to access the Employee Intranet, you will need to refer to follow the following instructions:\n\n"
                      + "     (1) Login to: http://localhost:8080/MMS2Server-war/administration/login with your Employee Email/Employee ID and Password\n"
                      + "     (2) You can find the password which have been text to your registered phone number and Employee Email\n"
                      + "     (3) You can also find the password enclosed in the welcome letter that has been handled to you by the relevant HR department\n"
                      + "     (4) Once you login, you will need to change your current default password to a new password\n"
                      + "     (5) Please also update your employee profile by clicking on 'User Settings' on the top right section of the portal\n"
                      + "     (6) For assistance, please contact the system administrator via the intranet employee directory or dial ext. 6271-2\n\n"
                      + "Thank you and have a pleasent stay at MMA\n"
                      + "\n\nThis is a System Generated Response, Please do not reply.\n\n"
                      + "Information in this email is confidential and may also be privileged. It is intended\n" +
                        "solely for the person to whom it is addressed. If you are not the intended recipient,\n" +
                        "please notify the sender, and please delete the message and any other record of it\n" +
                        "from your system immediately.";
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              msg.setSubject(subject);
              msg.setText(message);
              // -- Set some other header information --
              msg.setHeader("MyMail", "Mr. XYZ" );
              msg.setSentDate(new Date());
              // -- Send the message --
              Transport.send(msg);
              System.out.println("Message sent to "+to+" OK." );
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
                String username =  "huynhphilong28051995";           // specify your email id here (sender's email id)
                String password = "bonidom2805";                          // specify your password here
                return new PasswordAuthentication(username, password);
            }
      }
    
}
