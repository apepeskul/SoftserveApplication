package com.springapp.mvc.controllers;

import com.springapp.mvc.model.User;
import com.springapp.mvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class UserEditor extends PropertyEditorSupport {

    private UserRepository userRepository;
    UserEditor(UserRepository userRepository){
     this.userRepository=userRepository;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User user = userRepository.findOne(Long.parseLong(text));
        this.setValue(user);
    }

    @Override
    public String getAsText() {
        User user = (User) this.getValue();

        return Long.toString(user.getId());
    }
}
