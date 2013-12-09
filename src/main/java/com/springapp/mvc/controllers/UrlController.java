package com.springapp.mvc.controllers;


import com.springapp.mvc.model.*;
import com.springapp.mvc.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.util.resources.LocaleData;

import java.util.Date;

@Controller
public class UrlController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepositrory itemRepository;
    @Autowired
    DimensionRepository dimensionRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RoleRepository roleRepository;
    @RequestMapping(value = "/orders",  method = RequestMethod.GET)
    public String listOrders(ModelMap model) {

        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", userRepository.findByLogin(userName));
       return "orders";


    }

    @RequestMapping(value = "/order",  method = RequestMethod.GET)
    public String showOrder(ModelMap model) {
        Order order = new Order();
        order.setCreationDate(new Date());
        //orderRepository.save(order);
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orderdetail", new OrderDetails());
        model.addAttribute("user", userRepository.findByLogin(userName));
        model.addAttribute("order", order);
        model.addAttribute("dimensions", dimensionRepository.findAll());
        model.addAttribute("merchs", userRepository.findByRole(roleRepository.findByDescription("Merchandiser")));
       /* String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", userRepository.findByLogin(userName));*/
        return "order";


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

    @RequestMapping(value = "/order/{id}",  method = RequestMethod.GET)
    public String listItems(ModelMap model, @PathVariable ("id") Long id) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("order", orderRepository.findOne(id));
        model.addAttribute("orderdetail", new OrderDetails());
        model.addAttribute("user", userRepository.findByLogin(userName));
        model.addAttribute("dimensions", dimensionRepository.findAll());
        model.addAttribute("merchs", userRepository.findByRole(roleRepository.findByDescription("Merchandiser")));

        return "order";


    }
}
