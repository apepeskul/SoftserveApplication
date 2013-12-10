package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.User;
import com.springapp.mvc.repositories.UserRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/rest/userr")
public class RESTUserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User searchUserById(@PathVariable Long id){
        return userRepository.findOne(id);
    }

    @RequestMapping (value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers () throws JSONException {
        return userRepository.findAll();
    }

    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String createUser (@ModelAttribute("user") User user){
        userRepository.save(user);

        return "redirect:/";  //TODO: URL
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT)
    public String updateUser (@ModelAttribute("user")User user){
        userRepository.save(user);

        return "redirect:/";  //TODO: URL
    }

    @Transactional
    @RequestMapping(value = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userRepository.delete(userRepository.findOne(userId));

        return "redirect:/";  //TODO: URL
    }

    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getPageUser(@PathVariable ("page") int page,
                                  @PathVariable("size") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<User> users = userRepository.findAll(new PageRequest(page, size));

        return users.getContent();
    }

    //TODO: @param name
    @RequestMapping(value = "/page/starting/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getSomeUsersByName(
            @PathVariable ("page") int page,
            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");

        Page<User> users = userRepository.findByFirstNameStartingWith("Ad",
                new PageRequest(page, size));

        return users.getContent();
    }
}
