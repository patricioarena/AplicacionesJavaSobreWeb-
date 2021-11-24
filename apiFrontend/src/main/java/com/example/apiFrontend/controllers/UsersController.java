package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Address;
import com.example.apiFrontend.models.Role;
import com.example.apiFrontend.models.User;
import com.example.apiFrontend.models.UserForm;
import com.example.apiFrontend.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApiIgnore
@Controller
public class UsersController {

    private final UserService userService;
    private final HttpClientAsynchronous httpClientAsynchronous;
    private final RoleService roleService;
    private final CommonsUtilities commonsUtilities;


    @Autowired
    public UsersController(HttpClientAsynchronous httpClientAsynchronous, UserService userService, RoleService roleService, CommonsUtilities commonsUtilities) {
        this.httpClientAsynchronous = httpClientAsynchronous;
        this.userService = userService;
        this.roleService = roleService;
        this.commonsUtilities = commonsUtilities;
    }


    @GetMapping("/base")
    public String Test() {
        return "base";
    }

    @GetMapping("users/getAll")
    public ModelAndView userList() throws Exception {
        ModelAndView mav = new ModelAndView("users");
        try {

            ArrayList<User> users = this.userService.getAllUsers();
            ArrayList<Role> roles = this.roleService.getRoles();

            ArrayList<User> usersWithRoleName = new ArrayList<>();

            for (User aUser : users) {

                ArrayList<String> aux = new ArrayList<>();

                for (Role aRole : roles) {
                    aUser.getRoles().forEach(role -> {
                        if (role.equals(aRole.get_id())) {
                            aux.add(aRole.getRoleName());
                        }
                    });
                }

                User auxUser = aUser;
                auxUser.setRolesLabel(aux);
                usersWithRoleName.add(auxUser);
            }

//            for (User aUser : users) {
//                for (Role aRole : roles){
//                    if (aUser.get_idRole().equals(aRole.get_id())) {
//                        User aux = aUser;
//                        aux.setRoleLabel(aRole.getRoleName());
//                        usersWithRoleName.add(aux);
//                    }
//                }
//            }

//            model.addAttribute("titulo", "Lista de Usuarios");
//            model.addAttribute("users", usersWithRoleName);

//            return "users";
//            mav.addObject("title", "List of users");
            mav.addObject("users", usersWithRoleName);
            mav.addObject("countries", this.commonsUtilities.getCountries());

            return mav;

        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "users";
        return mav;
    }

    @RequestMapping("users/getOne")
    @ResponseBody
    public User getOne(String Id) throws Exception {
        try {
            return this.userService.findById(Id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @RequestMapping(value = "users/update/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(@PathVariable String id, UserForm model) throws Exception {

        if (model.getId() == null) {
            return "redirect:/users/getAll";
        }

        ModelMapper modelMapper = new ModelMapper();
        Address adress = modelMapper.map(model, Address.class);
        User user = modelMapper.map(model, User.class);
        user.set_id(id);
//        user.set_idRole(idRole);
        user.setAddress(adress);

        var temp = this.userService.update(user);
        System.out.println(temp);
        return "redirect:/users/getAll";
    }
}
