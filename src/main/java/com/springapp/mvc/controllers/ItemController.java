package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Dimension;
import com.springapp.mvc.model.Item;
import com.springapp.mvc.model.Price;
import com.springapp.mvc.repositories.DimensionRepository;
import com.springapp.mvc.repositories.ItemRepository;
import com.springapp.mvc.repositories.PriceRepository;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/item")

public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    DimensionRepository dimensionRepository;

    @Autowired
    PriceRepository priceRepository;

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Item getItem(@PathVariable("id") Long id) throws JSONException {
       return itemRepository.findOne(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editItem(Item item){
        itemRepository.save(item);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public String addItem(Item item){
        itemRepository.save(item);

        return "redirect:/item"; //TODO: URL
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable("id") Long id) {
        itemRepository.delete(itemRepository.findOne(id));

        return "redirect:/items";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List<Item> getAllItems() throws JSONException {
        return itemRepository.findAll();
    }

    @RequestMapping(value = "/dimension/{itemId}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public Dimension getDimension(@PathVariable("itemId") Long itemId) throws JSONException {
        return dimensionRepository.findOne(itemId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Dimension> getAllDimension() throws JSONException {
        return dimensionRepository.findAll();
    }

    @RequestMapping(value = "/price/{itemId}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public List <Price> getPrices(@PathVariable("itemId") Long id) {
        return priceRepository.findByItemId(itemRepository.findOne(id));
    }

    @RequestMapping(value = "/price/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editPrice(Price price){
        priceRepository.save(price);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/price/add")
    public String addPrice(@ModelAttribute Price price,  @RequestParam (value = "itm") Long itemId,
                           @RequestParam (value = "dm") Long dimensionId, BindingResult result) {
        price.setItemId(itemRepository.findOne(itemId));
        price.setDimensionId(dimensionRepository.findOne(dimensionId));
        priceRepository.save(price);

        return "redirect:/item"; //TODO: URL
    }

}
