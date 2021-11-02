package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Role;
import com.example.apiFrontend.models.User;
import com.example.apiFrontend.services.HttpClientAsynchronous;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApiIgnore
@Controller
public class UsersController {

    HttpClientAsynchronous httpClientAsynchronous;

    @Autowired
    public UsersController(HttpClientAsynchronous httpClientAsynchronous){
        this.httpClientAsynchronous = httpClientAsynchronous;
    }


/*    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> Get(@ApiIgnore @RequestHeader Map<String, String> headers) throws Exception {
        try {
            String url = "user/getAll";
            JSONObject respose = this.httpClientAsynchronous.GET(headers, url);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/base")
    public String Test(){
        return "base";
    }

    @GetMapping("users/getAll")
    public String userList(@RequestHeader Map<String, String> headers, Model model) throws Exception {
        try {
            String urlUsers = "user/getAll";
            JSONObject resposeUser = this.httpClientAsynchronous.GET(headers, urlUsers);
            String responseString = resposeUser.get("data").toString();

            String urlRole = "role/getAll";
            JSONObject responseRole = this.httpClientAsynchronous.GET(headers, urlRole);
            String responseStringRole = responseRole.get("data").toString();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            ArrayList<User> users = new Gson().fromJson(responseString, listType);

            Gson gsonRole = new Gson();
            Type listTypeRole = new TypeToken<ArrayList<Role>>() {}.getType();
            ArrayList<Role> roles = new Gson().fromJson(responseStringRole, listTypeRole);

            ArrayList<User> usersWithRoleName = new ArrayList<>();
            for (User aUser : users) {
                for (Role aRole : roles){
                    if (aUser.get_idRole().equals(aRole.get_id())) {
                        User aux = aUser;
                        aux.set_idRole(aRole.getRoleName());
                        usersWithRoleName.add(aux);
                    }
                }
            }

            System.out.println(usersWithRoleName);

            System.out.println(users);
            model.addAttribute("titulo","Lista de Usuarios");
            model.addAttribute("users", usersWithRoleName);

            return "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "users";
    }



}
