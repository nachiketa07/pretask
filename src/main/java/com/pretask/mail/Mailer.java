package com.pretask.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.pretask.mail.vo.MailVO;

import java.util.Properties;

@Component
public class Mailer {

    public  void sendMail(MailVO mailVO) throws MessagingException {
    	System.out.println("--in send Mail--");
        final String username = "myappjob20@gmail.com";
        final String password = "rzjlortdhzmljtyy";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        //try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailVO.getAddress_TO())
            );
            message.setSubject(mailVO.getSubject());
            message.setText("Dear "+ mailVO.getAddress_TO().split("@")[0]
                    + "\n\n "+mailVO.getBody());

            Transport.send(message);

            System.out.println("Done");

        /*} catch (MessagingException e) {
            e.printStackTrace();
        }*/
    }

}