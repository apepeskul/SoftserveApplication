package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.Price;
import com.springapp.mvc.repositories.PriceRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/data/price")
public class RESTPriceController {
    @Autowired
    private PriceRepository priceRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     @ResponseBody
     public Price getPrice(@PathVariable("id") Long id) {
        return priceRepository.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Price> searchAllPrice() throws JSONException {
        return priceRepository.findAll();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPrice(@ModelAttribute("price")Price price){
        priceRepository.save(price);

        return "/errors/200";
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String addPrice(@ModelAttribute("price")Price price){
        priceRepository.save(price);

        return "/errors/200";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String deletePrice(@PathVariable("priceId") Long priceId) {
        priceRepository.delete(priceRepository.findOne(priceId));

        return "/errors/200";
    }

    //TODO: @param name
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<Price> getPagePrice(@PathVariable ("page") int page,
                                            @PathVariable("size") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<Price> prices = priceRepository.findAll(new PageRequest(page, size));

        return prices.getContent();
    }

}
