package com.springapp.mvc.utils;

import com.send.Sender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.springapp.mvc.repositories.UserRepository;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    private Sender sender = new Sender();

    public User getAuthenticatedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user;
        try {
            user = userRepository.findByLogin(username);
        } catch (NullPointerException e){
            return null;
        }

        return user;
    }

    public void sendingEmainCheck(User user){

        String message= "Dear " + user.getLogin() + ",<br>" +
                "In order to help maintain the security of your account, please verify your email address by clicking the following link:<br>" +
                "http://localhost:8080/validation/" + user.getHashCheck() +"<br>"+
                "Your email address will be used to assist you in changing your account credentials or regaining access " +
                "to your account, should you ever need help with those things." +
                "Thanks for helping us maintain the security of your account.<br>" +
                "<i>The Dev Support Team.</i>";
        sender.send("Welcome  to our service", message, user.getEmail());
    }

    public String toHash (String login) {
        login = "always wanna fly" + login;

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //TODO: exception Handling
        }
        md.update(login.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
