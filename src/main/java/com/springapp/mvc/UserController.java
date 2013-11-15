package com.springapp.mvc;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



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

    @RequestMapping(value = "/api/users", produces={"application/json; charset=UTF-8"})
    public
    @ResponseBody
    String listUsersJson(@RequestParam (value = "b") String termSearch,ModelMap model) throws JSONException {
        JSONArray userArray = new JSONArray();

        for (User user : userRepository.findAll(UserSpecs.loginIsLike(termSearch))) {
            JSONObject userJSON = new JSONObject();
            userJSON.put("id", user.getId());
            userJSON.put("firstName", user.getFirstName());
            userJSON.put("lastName", user.getLastName());
            userJSON.put("email", user.getEmail());
            userJSON.put("login", user.getLogin());
            userJSON.put("region", user.getRegion());
            userArray.put(userJSON);
        }
        return userArray.toString();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUsers(@RequestParam (value = "q") String termSearch, ModelMap model) {
        model.addAttribute("temprole", new Role());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("users", userRepository.findAll(UserSpecs.loginIsLike(termSearch)));
        return "users";}



    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute User user,Role role, BindingResult result) {
        role = roleRepository.findById(role.getId());
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