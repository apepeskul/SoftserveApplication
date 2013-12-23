package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.Order;

import com.springapp.mvc.repositories.OrderRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/data/order")

public class RESTOrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable("id") Long id){
        return orderRepository.findOne(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editOrder(Order order){
        orderRepository.save(order);

        return "/errors/200";
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String addOrder(Order order){
        orderRepository.save(order);

        return "/errors/200";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getAllOrders() throws JSONException {
        return orderRepository.findAll();

    }

    @RequestMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderRepository.delete(orderRepository.findOne(id));

        return "/errors/200";
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getPageOrder(@PathVariable ("page") int page,
                                            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<Order> orders = orderRepository.findAll(new PageRequest(page, size));

        return orders.getContent();
    }
}