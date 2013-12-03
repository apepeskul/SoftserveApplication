package com.springapp.mvc.controllers;

import com.springapp.mvc.utils.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthenticationController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied(ModelMap model, HttpServletRequest request){
        String message = (String) request.getSession().getAttribute("message");
        model.addAttribute("message", message);
        return "403";
    }
}