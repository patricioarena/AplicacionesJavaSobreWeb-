package com.example.apifrontend.controllers;

import com.example.apifrontend.services.HttpClientAsynchronous;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/ClientRest")
public class ClientRestController {

    private HttpClientAsynchronous httpClientAsynchronous;

    private String urlTest1 = "https://pokeapi.co/api/v2/pokemon?limit=151";
    private String urlTest2 = "https://pokeapi.co/api/v2/pokemon/1";


    @Autowired
    public ClientRestController(HttpClientAsynchronous httpClientAsynchronous) {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    @RequestMapping(value = "", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public JSONObject Get() throws Exception {
        return this.httpClientAsynchronous.Get( urlTest2);
    }

}
