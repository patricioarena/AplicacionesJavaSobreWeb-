package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.User;
import com.example.apiFrontend.services.HttpClientAsynchronous;
import com.example.apiFrontend.services.NotAuthorizationHeaderException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

    @GetMapping("/getAll")
    public String userList(@RequestHeader Map<String, String> headers, Model model) throws Exception {
        try {
            String url = "user/getAll";
            JSONObject respose = this.httpClientAsynchronous.GET(headers, url);
            String responseString = respose.get("data").toString();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            ArrayList<User> users = new Gson().fromJson(responseString, listType);

            System.out.println(responseString);
            model.addAttribute("titulo","Lista de Usuarios");
            model.addAttribute("users", users);

            return "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "users";
    }

}
