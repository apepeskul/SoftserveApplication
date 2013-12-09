package com.springapp.mvc.controllers;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.repositories.RoleRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 28.11.13
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/rest/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role searchRoleById(@PathVariable Long id) {
        return roleRepository.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> searchAllRole() throws JSONException {
        return roleRepository.findAll();
    }

    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public String createRole (@ModelAttribute("user")Role role){
        roleRepository.save(role);
        return "redirect:/";  //TODO: URL
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT)
    public String updateRole (@ModelAttribute("user")Role role){
        roleRepository.save(role);
        return "redirect:/";  //TODO: URL
    }

    @RequestMapping(value = "/delete/{roleId}")
    public String deleteRole(@PathVariable("roleId") Long roleId) {
        roleRepository.delete(roleRepository.findOne(roleId));
        return "redirect:/";  //TODO: URL
    }
    /*
    update
     */
    //TODO:     sort field
    //TODO:     desc asc

    @RequestMapping(value = "/page/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getPageRole(@PathVariable ("page") int page,
                                  @PathVariable("size") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "name");
        Page<Role> roles = roleRepository.findAll(new PageRequest(page, size));
        return roles.getContent();
    }

    //TODO: @param name
    @RequestMapping(value = "/page/starting/{page}/{size}", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getSomeRoleByName(
            @PathVariable ("page") int page,
            @PathVariable("size") int size){
        //Sort sort = new Sort(Sort.Direction.DESC, "name");

        Page<Role> roles = roleRepository.findByDescriptionStartingWith("Ad",
                new PageRequest(page, size));
        return roles.getContent();
    }
}
