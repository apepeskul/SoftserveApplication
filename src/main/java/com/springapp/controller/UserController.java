package com.springapp.controller;


import com.springapp.criteria.UserSpecs;
import com.springapp.entity.Role;
import com.springapp.entity.User;
import com.springapp.repository.RoleRepository;
import com.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
     //   model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());

        return "users";


    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable ("userId") Long userId, ModelMap model) {
     //   model.addAttribute("temprole", new Role());
        model.addAttribute("edituser", userRepository.findOne(userId));
        model.addAttribute("roles", roleRepository.findAll());

        return "edit";


    }

    @RequestMapping(value = "/api/users", produces={"application/json; charset=UTF-8"})
    public
    @ResponseBody
    List<User> listUsersJson(@RequestParam (value = "b") String termSearch) {
        return userRepository.findAll(UserSpecs.loginIsLike(termSearch));
    }

    @RequestMapping (value = "users/{userId}", method = RequestMethod.GET)

        @ResponseBody User searchUserById  (@PathVariable ("userId") Long userId ){

        return userRepository.findById(userId);


    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUsers(@RequestParam (value = "q") String termSearch, ModelMap model) {
      //  model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll(UserSpecs.loginIsLike(termSearch)));
        return "users";}



    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute User user, @RequestParam (value = "rid", required = false) Long roleId, Role role, BindingResult result) {
        role = roleRepository.findById(roleId);
        user.setRole(role);
        userRepository.save(user);


        return "redirect:/";

    }



    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {

        userRepository.delete(userRepository.findOne(userId));

        return "redirect:/";
    }
}