package com.send;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {

    private String user;
    private String pass;
    private Properties properties;

    public Sender() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream( "C:\\GITvers\\SoftserveApplication\\src\\main\\resources\\" +
                    "META-INF\\config.properties"),"UTF-8"));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.user = properties.getProperty("mail.smtp.user");
        this.pass = properties.getProperty("mail.smtp.password");
    }

    public void send(String subject, String text, String toEmail){
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(text, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e); // TODO: exception Handling
        }
    }
}