package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.repositories.RoleRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/data/role")
public class RESTRoleController {

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

        return "/errors/200";
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT)
    public String updateRole (@ModelAttribute("user")Role role){
        roleRepository.save(role);

        return "/errors/200";
    }

    @RequestMapping(value = "/delete/{roleId}",  method = RequestMethod.DELETE)
    public String deleteRole(@PathVariable("roleId") Long roleId) {
        roleRepository.delete(roleRepository.findOne(roleId));

        return "/errors/200";
    }

    //TODO: @param name
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

}
