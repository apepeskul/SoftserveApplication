package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.Dimension;
import com.springapp.mvc.repositories.DimensionRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Representational State Transfer

@Controller
@RequestMapping(value = "/rest/dimensionn")
public class RESTDimensionController {
    @Autowired
    private DimensionRepository dimensionRepository;

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public Dimension getDimension(@PathVariable("itemId") Long itemId) {
        return dimensionRepository.findOne(itemId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Dimension> getAllDimension() throws JSONException {
        return dimensionRepository.findAll();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String editDimension(@ModelAttribute("dimension")Dimension dimension){
        dimensionRepository.save(dimension);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addDimension(@ModelAttribute("dimension")Dimension dimension){
        dimensionRepository.save(dimension);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping("/delete/{id}")
    public String deleteDimension(@PathVariable("id") Long id) {
        dimensionRepository.delete(dimensionRepository.findOne(id));

        return "redirect:/"; //TODO: URL
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<Dimension> getPageDimension(@PathVariable ("page") int page,
                                   @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<Dimension> dimensions = dimensionRepository.findAll(new PageRequest(page, size));
        return dimensions.getContent();
    }

    //TODO: @param name
    @RequestMapping(value = "/page/starting/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<Dimension> getSomeDimensionByName(//@RequestParam (value = "q") String termSearch,
                                         @PathVariable ("page") int page,
                                         @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<Dimension> dimensions = dimensionRepository.findByNameStartingWith("qw" ,new PageRequest(page, size));
        return dimensions.getContent();
    }
}
