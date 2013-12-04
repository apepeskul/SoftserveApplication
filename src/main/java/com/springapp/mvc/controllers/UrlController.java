package com.springapp.mvc.controllers;


import com.springapp.mvc.model.Item;
import com.springapp.mvc.model.Price;
import com.springapp.mvc.model.User;
import com.springapp.mvc.repositories.DimensionRepository;
import com.springapp.mvc.repositories.ItemRepositrory;
import com.springapp.mvc.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepositrory itemRepository;
    @Autowired
    DimensionRepository dimensionRepository;
    @RequestMapping(value = "/orders",  method = RequestMethod.GET)
    public String listOrders(ModelMap model) {

        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", userRepository.findByLogin(userName));
       return "orders";


    }
    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        model.addAttribute("item", new Item());
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("dimensions", dimensionRepository.findAll() );
        model.addAttribute("price", new Price());


        return "item";


    }
    @RequestMapping(value = "/items",  method = RequestMethod.GET)
    public String listItems(ModelMap model) {
        model.addAttribute("item", new Item());


        return "items";


    }
}
