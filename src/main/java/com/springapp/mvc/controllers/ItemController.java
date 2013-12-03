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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 25.11.13
 * Time: 6:23
 * To change this template use File | Settings | File Templates.
 */
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
    public String getItem(@PathVariable("id") Long id) throws JSONException {

        Item item = itemRepositrory.findOne(id);
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("id", item.getId());
            itemJSON.put("description", item.getDescription());
            itemJSON.put("name", item.getName());
            itemJSON.put("quantity", item.getQuantity());


        return itemJSON.toString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        model.addAttribute("item", new Item());
        model.addAttribute("items", itemRepositrory.findAll());
        model.addAttribute("dimensions", dimensionRepository.findAll() );
        model.addAttribute("price", new Price());


        return "item";


    }
    @RequestMapping(value = "/items",  method = RequestMethod.GET)
    public String listItems(ModelMap model) {
        model.addAttribute("item", new Item());


        return "items";


    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces={"application/json; charset=UTF-8"})
    public String editItem(Item item){
        itemRepositrory.save(item);
        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces={"application/json; charset=UTF-8"})
    public String addItem(Item item){
        itemRepositrory.save(item);
        return "redirect:/rest/item/new"; //TODO: URL
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteItem(@PathVariable("id") Long id) {
        itemRepositrory.delete(itemRepositrory.findOne(id));
        return "redirect:/rest/item/items";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    @ResponseBody
    public String getAllItems() throws JSONException{
        JSONArray itemArray = new JSONArray();
        for (Item item : itemRepositrory.findAll()) {
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("id", item.getId());
            itemJSON.put("description", item.getDescription());
            itemJSON.put("name", item.getName());
            itemJSON.put("quantity", item.getQuantity());

            itemArray.put(itemJSON);
        }
        return itemArray.toString();
    }

    @RequestMapping(value = "/dimension/{itemId}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String getDimension(@PathVariable("itemId") Long itemId) throws JSONException {
        JSONArray dimensionArray = new JSONArray();
        Dimension dimension = dimensionRepository.findOne(itemId);
            JSONObject dimensionJSON = new JSONObject();
            dimensionJSON.put("id", dimension.getDimensionId());
            dimensionJSON.put("name", dimension.getName());
            dimensionJSON.put("dimension", dimension.getDimensionId());
            dimensionJSON.put("multiplex", dimension.getMultiplex());
            dimensionArray.put(dimensionJSON);

        return dimensionArray.toString();
    }

    @RequestMapping(value = "/price/{id}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String getPrice(@PathVariable("id") Long id) throws JSONException {
        JSONArray priceArray = new JSONArray();
        Price price = priceRepository.findOne(id);
        JSONObject priceJSON = new JSONObject();
        priceJSON.put("id", price.getId());
        priceJSON.put("dimension", price.getDimensionId());
        priceJSON.put("itemId", price.getItemId());
        priceJSON.put("price", price.getPrice());
        priceArray.put(priceJSON);

        return priceArray.toString();
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
        return "redirect:/rest/item/new"; //TODO: URL
    }

}
