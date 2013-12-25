package com.springapp.mvc.utils;

import org.apache.log4j.Logger;

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

    Logger logger = Logger.getLogger(this.getClass());

    public Sender() {
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream( "C:\\gitMaster17\\SoftserveApplication\\src\\main\\resources\\META-INF\\" +
                    "config.properties"),"UTF-8"));
        } catch(FileNotFoundException e) {
            logger.error("File config.properties isn't found " + e);
        } catch (IOException e) {
            logger.error("Cannot read the file config.properties " + e);
        }
        this.setUser(properties.getProperty("mail.smtp.user"));
        this.setPass(properties.getProperty("mail.smtp.password"));
    }

    public void send(String subject, String text, String toEmail) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getUser(), getPass());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getUser()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(text, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("The message wasn't sanding " + e);
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}