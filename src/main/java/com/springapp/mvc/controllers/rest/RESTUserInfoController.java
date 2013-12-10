package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.UserInfo;
import com.springapp.mvc.repositories.UserInfoRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/infoo")
public class RESTUserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfo (@PathVariable ("id") Long id) {
        return userInfoRepository.findOne(id);
    }

    @RequestMapping (value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> getAllInfo () throws JSONException {
        return userInfoRepository.findAll();
    }

    @RequestMapping (value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String addUserInfo (@ModelAttribute("userInfo")UserInfo userInfo, String test){
        userInfoRepository.save(userInfo);

        return "redirect:/"; //TODO: URL
    }


    @RequestMapping (value = "/update}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUserInfo (@ModelAttribute("userInfo")UserInfo userInfo) {
        userInfoRepository.save(userInfo);

        return "redirect:/"; //TODO: URL
    }

    @RequestMapping(value = "/delete/{userId}")
    public String deleteUserInfo(@PathVariable("userId") Long userId) {
        userInfoRepository.delete(userInfoRepository.findOne(userId));

        return "redirect:/";  //TODO: URL
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> getPageUserInfo(@PathVariable ("page") int page,
                                  @PathVariable("size") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<UserInfo> userInfos = userInfoRepository.findAll(new PageRequest(page, size));

        return userInfos.getContent();
    }

}
