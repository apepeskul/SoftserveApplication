package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.OrderDetails;
import com.springapp.mvc.repositories.OrderDetaitlsRepositrory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/detailss")
public class RESTOrderDetailsController {

    @Autowired
    private OrderDetaitlsRepositrory orderDetaitlsRepositrory;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OrderDetails getOrdersDetailsById(@PathVariable("id") Long id){
        return orderDetaitlsRepositrory.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDetails> getAllOrdersDetails(){
        return orderDetaitlsRepositrory.findAll();

    }

    @RequestMapping( value = "/delete/{id}")
    public String deleteOrderDetails(@PathVariable("id") Long id) {
        orderDetaitlsRepositrory.delete(orderDetaitlsRepositrory.findOne(id));

        return "redirect:/";  //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrderDetails(@ModelAttribute("orderDetails")OrderDetails orderDetails){
        orderDetaitlsRepositrory.save(orderDetails);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String updateOrderDetails(@ModelAttribute("orderDetails")OrderDetails orderDetails){
        orderDetaitlsRepositrory.save(orderDetails);

        return "redirect:/"; //TODO: URL
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDetails> getPageOrderDetails(@PathVariable ("page") int page,
                                            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<OrderDetails> orderDetailses = orderDetaitlsRepositrory.findAll(new PageRequest(page, size));
        return orderDetailses.getContent();
    }

}
