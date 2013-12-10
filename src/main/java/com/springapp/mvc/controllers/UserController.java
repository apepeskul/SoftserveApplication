package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import com.springapp.mvc.model.UserSpecs;
import com.springapp.mvc.repositories.RoleRepository;
import com.springapp.mvc.repositories.UserRepository;
import com.springapp.mvc.utils.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    private UserService userService = new UserService();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
     //   model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());

        return "users";
    }

    @RequestMapping(value = "/datatables", produces={"application/json; charset=UTF-8"})
    public @ResponseBody List<User> listUsersDatatables() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public String editUser(@PathVariable ("userId") Long userId, ModelMap model) {
     //   model.addAttribute("temprole", new Role());
        model.addAttribute("edituser", userRepository.findOne(userId));
        model.addAttribute("roles", roleRepository.findAll());

        return "edit";
    }

    @RequestMapping(value = "/api/users", produces={"application/json; charset=UTF-8"})
    public @ResponseBody List<User> listUsersJson(@RequestParam (value = "b") String termSearch) {

        return userRepository.findAll(UserSpecs.loginIsLike(termSearch));
    }

    @RequestMapping (value = "users/{userId}", method = RequestMethod.GET)
    @ResponseBody User searchUserById  (@PathVariable ("userId") Long userId ){

        return userRepository.findOne(userId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUsers(@RequestParam (value = "q") String termSearch, ModelMap model) {
      //  model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll(UserSpecs.loginIsLike(termSearch)));

        return "users";
    }

    /*
    updated 10.12.13
     */
    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute User user, @RequestParam (value = "rid", required = false) Long roleId,
                          Role role, BindingResult result, HttpServletRequest request) {
        String userExistsError = userExistsError(user);
        if(userExistsError != null) {
            request.getSession().setAttribute("errorCause", userExistsError);
            return "redirect:/error";
        }
        role = roleRepository.findById(roleId);
        user.setRole(role);
        user.setHashCheck(userService.toHash(user.getLogin()));
        userRepository.save(user);
        userService.sendingEmainCheck(userRepository.findByLogin(user.getLogin()));

        return "redirect:/";
    }

    @RequestMapping(value = "/update")
    public String updateUser(@ModelAttribute User user, @RequestParam (value = "rid", required = false) Long roleId,
                          Role role, BindingResult result, HttpServletRequest request) {

        role = roleRepository.findById(roleId);
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/error")
    public String userExistsError(ModelMap model, HttpServletRequest request) {
        model.addAttribute("errorCause", request.getSession().getAttribute("errorCause"));

        return "/error";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userRepository.delete(userRepository.findOne(userId));

        return "redirect:/";
    }

    private String userExistsError(User user) {
        if(userRepository.findByLogin(user.getLogin()) != null) {
            return "login";
        } else if(userRepository.findByEmail(user.getEmail()) != null) {
            return "email";
        };

        return null;
    }

    /*
    Update 10.12.2013
     */
    @RequestMapping(value = "/validation/{hash}")
    public String userValidation(@PathVariable("hash") String hashCheck) {
        try{
            User user = userRepository.findByHashCheck(hashCheck);
            if(user.getValidation()==false){
                user.setValidation(true);
                userRepository.save(user);
            } else{
                return "/activated";//TODO: url
            }
        } catch (RuntimeException e) {
            e.printStackTrace(); //TODO: exception Handling
        }

        return "/activated";
    }
}