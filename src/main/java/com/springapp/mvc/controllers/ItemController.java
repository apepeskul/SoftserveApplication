package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Dimension;
import com.springapp.mvc.model.Item;
import com.springapp.mvc.model.Price;
import com.springapp.mvc.repositories.DimensionRepository;
import com.springapp.mvc.repositories.ItemRepositrory;
import com.springapp.mvc.repositories.PriceRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/item")

public class ItemController {

    @Autowired
    ItemRepositrory itemRepositrory;

    @Autowired
    DimensionRepository dimensionRepository;

    @Autowired
    PriceRepository priceRepository;

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public Item getItem(@PathVariable("id") Long id) throws JSONException {
       return itemRepositrory.findOne(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editItem(Item item){
        itemRepositrory.save(item);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public String addItem(Item item){
        itemRepositrory.save(item);

        return "redirect:/item"; //TODO: URL
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable("id") Long id) {
        itemRepositrory.delete(itemRepositrory.findOne(id));

        return "redirect:/items";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public List<Item> getAllItems() throws JSONException {
        return itemRepositrory.findAll();
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
        return priceRepository.findByItemId(itemRepositrory.findOne(id));
    }

    @RequestMapping(value = "/price/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editPrice(Price price){
        priceRepository.save(price);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/price/add")
    public String addPrice(@ModelAttribute Price price,  @RequestParam (value = "itm") Long itemId,
                           @RequestParam (value = "dm") Long dimensionId, BindingResult result) {
        price.setItemId(itemRepositrory.findOne(itemId));
        price.setDimensionId(dimensionRepository.findOne(dimensionId));
        priceRepository.save(price);

        return "redirect:/item"; //TODO: URL
    }

}
