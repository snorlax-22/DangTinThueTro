/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTTT.Maill;

/**
 *
 * @author huynhkhanh
 */
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import javax.mail.internet.AddressException;
public class Mail {
    public static void sendMail(String mailNhan, String mailSubject, String mailMsg){
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", 465);
 
        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dangtinnhatrocongnghephanmem@gmail.com", "@A123456");
            }
        });
 
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailNhan));
            message.setSubject(mailSubject);
            message.setText(mailMsg);
            // send message
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        sendMail("huynhquockhanh0907@gmail.com", "Hellllooo", "budwebwedfbhuihuiowdsfhfujiwe");
    }
    
}

