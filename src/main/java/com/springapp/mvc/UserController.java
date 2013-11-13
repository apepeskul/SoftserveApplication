package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        //model.addAttribute("users", userRepository.findAll(UserSpecs.loginIsLike("adm")));
        return "users";
    }

    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute User user,Role role, BindingResult result) {
        role = roleRepository.findById(role.getId());
        user.setRole(role);
        userRepository.save(user);

        return "redirect:/";
    }

    @RequestMapping (value = "/search")
    public String search (@RequestParam (value = "q") String termSearch,Model model){
        model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll(UserSpecs.loginIsLike(termSearch)));
        model.addAttribute("roles", roleRepository.findAll());
        return "users";
    }



    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {

        userRepository.delete(userRepository.findOne(userId));

        return "redirect:/";
    }
}