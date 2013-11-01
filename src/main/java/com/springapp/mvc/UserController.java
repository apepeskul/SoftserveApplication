package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



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
        return "users";
    }

    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute User user,Role role, BindingResult result) {
        role = roleRepository.findById(role.getId());
        user.setRole(role);
        userRepository.save(user);

        return "redirect:/";
    }
    @RequestMapping (value = "/addRole/{userRole}")
    public void addRole (@PathVariable ("userRole") Long id,User user, BindingResult result){
            user.setRole(roleRepository.findById(id));

    }



    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {

        userRepository.delete(userRepository.findOne(userId));

        return "redirect:/";
    }
}