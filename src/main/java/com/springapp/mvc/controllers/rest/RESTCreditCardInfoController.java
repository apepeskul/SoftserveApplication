package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.CreditCardInfo;
import com.springapp.mvc.repositories.CreditCardInfoRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/cardd")
public class RESTCreditCardInfoController {

    @Autowired
    private CreditCardInfoRepository creditCardInfoRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CreditCardInfo getCreditCardInfoById(@PathVariable("id") Long id){
        return creditCardInfoRepository.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<CreditCardInfo> getAllCreditCardInfo() throws JSONException {
        return creditCardInfoRepository.findAll();
    }

    @RequestMapping( value = "/delete/{id}")
    public String deleteCreditCardInfo(@PathVariable("id") Long id) {
        creditCardInfoRepository.delete(creditCardInfoRepository.findOne(id));

        return "redirect:/";  //TODO: URL
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCreditCardInfo(@ModelAttribute("creditCardInfo")CreditCardInfo creditCardInfo){
        creditCardInfoRepository.save(creditCardInfo);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String updateCreditCardInfo(@ModelAttribute("creditCardInfo")CreditCardInfo creditCardInfo){
        creditCardInfoRepository.save(creditCardInfo);

        return "redirect:/"; //TODO: URL
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<CreditCardInfo> getPageInfo(@PathVariable ("page") int page,
                                   @PathVariable("size") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<CreditCardInfo> infos = creditCardInfoRepository.findAll(new PageRequest(page, size));

        return infos.getContent();
    }

    //TODO: @param name
    @RequestMapping(value = "/page/starting/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<CreditCardInfo> getSomeInfoByName(
                                         @PathVariable ("page") int page,
                                         @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<CreditCardInfo> infos = creditCardInfoRepository.findByCreditCardNumberStartingWith("32",
                new PageRequest(page, size));

        return infos.getContent();
    }

}
