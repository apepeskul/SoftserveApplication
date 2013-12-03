package com.springapp.mvc.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.springapp.mvc.repositories.UserRepository;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

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
}
