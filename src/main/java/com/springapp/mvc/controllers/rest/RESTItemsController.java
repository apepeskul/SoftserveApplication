package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.Item;
import com.springapp.mvc.repositories.ItemRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/data/item")
public class RESTItemsController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Item getItemById(@PathVariable("id") Long id) throws JSONException {
        return itemRepository.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems() throws JSONException {
        return itemRepository.findAll();
    }

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteItem(@PathVariable("id") Long id) {
        itemRepository.delete(itemRepository.findOne(id));

        return "/errors/200";
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String addItem(@ModelAttribute("creditCardInfo")Item item){
        itemRepository.save(item);

        return "/errors/200";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateItem(@ModelAttribute("creditCardInfo")Item item){
        itemRepository.save(item);

        return "/errors/200";
    }

    //TODO: @param name
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
     @ResponseBody
     public List<Item> getPageItems(@PathVariable ("page") int page,
                                    @PathVariable("size") int size) throws JSONException {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<Item> items = itemRepository.findAll(new PageRequest(page, size));

        return items.getContent();
    }

}
