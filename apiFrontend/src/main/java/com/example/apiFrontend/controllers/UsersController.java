package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.*;
import com.example.apiFrontend.services.HttpClientAsynchronous;
import com.example.apiFrontend.services.RoleService;
import com.example.apiFrontend.services.UserService;
import com.example.apiFrontend.models.Address;
import com.example.apiFrontend.models.Role;
import com.example.apiFrontend.models.User;
import com.example.apiFrontend.models.forms.UserForm;
import com.example.apiFrontend.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;

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
                auxUser.setRoles(aux);
                usersWithRoleName.add(auxUser);
            }

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

    @GetMapping("users/register")
    public String registerForm(Model model) {
        model.addAttribute("register", new UserFormRegister());
        return "register";
    }

    @PostMapping("users/register/create")
    public ModelAndView registerSubmit(@ModelAttribute UserFormRegister userFormRegister, Model model) throws Exception {

        if(!userFormRegister.getEmail().equals(userFormRegister.getConfirmEmail())){
            ModelAndView mavRedirect = new ModelAndView("register");
            mavRedirect.addObject("userFormRegister", userFormRegister);
            return mavRedirect;
        }
        if(!userFormRegister.getPassword().equals(userFormRegister.getConfirmPassword())){
            ModelAndView mavRedirect = new ModelAndView("register");
            mavRedirect.addObject("userFormRegister", userFormRegister);
            return mavRedirect;
        }

        User user = this.userService.create(userFormRegister);
        ModelAndView mav = new ModelAndView("resultRegisterForm");
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/users/resultRegisterForm")
    public String resultRegisterForm(Model model) throws Exception{
        return "resultRegisterForm";
    }
}
