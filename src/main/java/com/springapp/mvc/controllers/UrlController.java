package com.springapp.mvc.controllers;

import com.springapp.mvc.model.*;
import com.springapp.mvc.repositories.*;

import com.springapp.mvc.security.CustomAuthenticationProvider;
import com.springapp.mvc.utils.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class UrlController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DimensionRepository dimensionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStartUrl(ModelMap model) {
        HashMap<String, String> pageForRole = new HashMap<String, String>();
        pageForRole.put("Admin", "admin");
        pageForRole.put("Customer", "orders");
        pageForRole.put("Merchandiser", "orders");
        pageForRole.put("Supervisor", "items");
        pageForRole.put("Anonymous", "login");
        pageForRole.put("God", "admin");

        String userRoleDescription;

        try {
            userRoleDescription = userService.getAuthenticatedUser().getRole().getDescription();
        } catch (NullPointerException e){
            userRoleDescription = "Admin";
        }
        return "redirect:/" + pageForRole.get(userRoleDescription);
    }

    @RequestMapping(value = "/orders",  method = RequestMethod.GET)
    public String listOrders(ModelMap model) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userRepository.findByLogin(userName));

       return "orders";
    }

    @RequestMapping(value = "order/rest/item/price/{itemId}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public List<Price> getPrices(@PathVariable("itemId") Long id) {
        return priceRepository.findByItemId(itemRepository.findOne(id));
    }

    @RequestMapping(value = "/order",  method = RequestMethod.GET)
    public String showOrder(ModelMap model) {
        Order order = new Order();
        order.setCreationDate(new Date());
        order.setTotalPrice(new BigDecimal(0));
        orderRepository.save(order);
        //orderRepository.save(order);
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orderdetail", new OrderDetails());
        model.addAttribute("user", userRepository.findByLogin(userName));
        model.addAttribute("order", order);
        model.addAttribute("dimensions", dimensionRepository.findAll());
        model.addAttribute("merchs", userRepository.findByRole(roleRepository.findByDescription("Merchandiser")));
       /* String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userRepository.findByLogin(userName));*/

        return "redirect:order/"+order.getId();
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
