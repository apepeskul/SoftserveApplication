package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.OrderDetails;
import com.springapp.mvc.repositories.OrderDetaitlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/data/details")
public class RESTOrderDetailsController {

    @Autowired
    private OrderDetaitlsRepository orderDetaitlsRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OrderDetails getOrdersDetailsById(@PathVariable("id") Long id){
        return orderDetaitlsRepository.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDetails> getAllOrdersDetails(){
        return orderDetaitlsRepository.findAll();

    }

    @RequestMapping( value = "/delete/{id}")
    public String deleteOrderDetails(@PathVariable("id") Long id) {
        orderDetaitlsRepository.delete(orderDetaitlsRepository.findOne(id));

        return "/errors/200";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrderDetails(@ModelAttribute("orderDetails")OrderDetails orderDetails){
        orderDetaitlsRepository.save(orderDetails);

        return "/errors/200";
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String updateOrderDetails(@ModelAttribute("orderDetails")OrderDetails orderDetails){
        orderDetaitlsRepository.save(orderDetails);

        return "/errors/200";
    }

    //TODO: @param name
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDetails> getPageOrderDetails(@PathVariable ("page") int page,
                                            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<OrderDetails> orderDetailses = orderDetaitlsRepository.findAll(new PageRequest(page, size));

        return orderDetailses.getContent();
    }

}
