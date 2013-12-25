package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import com.springapp.mvc.model.UserSpecs;
import com.springapp.mvc.repositories.RoleRepository;
import com.springapp.mvc.repositories.UserRepository;
import com.springapp.mvc.utils.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private RoleRepository roleRepository;

    private UserService userService = new UserService();

    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
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

    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute User user, @RequestParam (value = "rid", required = false) Long roleId,
                          Role role, BindingResult result, HttpServletRequest request) {
        String userExistsError = userExistsError(user);
        if(userExistsError != null) {
            request.getSession().setAttribute("errorCause", userExistsError);
            return "redirect:/errors/409";
        }
        role = roleRepository.findById(roleId);
        user.setRole(role);
        userRepository.save(user);
        userService.sendingEmainCheck(user);

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

    @RequestMapping(value = "/errors/409")
    public String userExistsError(ModelMap model, HttpServletRequest request) {
        model.addAttribute("errorCause", request.getSession().getAttribute("errorCause"));

        return "/errors/409";
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


    @RequestMapping(value = "/login={login}/validation/{hash}")
    public String userValidation(@PathVariable("login") String login, @PathVariable("hash") String hashCheck) {
        try{
            User user = userRepository.findByLogin(login);
            if(user.getValidation()==false && userService.toHash(login).equals(hashCheck)){
                System.out.println("login = [" + login + "], hashCheck = [" + hashCheck + "]");
                user.setValidation(true);
                userRepository.save(user);
            } else{
                return "/errors/409";
            }
        } catch (RuntimeException e) {
            logger.error("Smth is wrong with the user "+ " " + e);
        }

        return "/errors/200";
    }

    //TODO: @param name
    //TODO:     sort field
    //TODO:     desc asc
    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getByCondition(
            @PathVariable ("page") int page,
            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<User> users = userRepository.findByLoginContainingAndEmailContainingAndFirstNameContaining("A", "", "",
                new PageRequest(page, size));

        return users.getContent();
    }

}