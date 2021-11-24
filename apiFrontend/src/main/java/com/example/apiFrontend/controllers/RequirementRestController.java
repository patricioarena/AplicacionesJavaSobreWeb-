package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Requirement;
import com.example.apiFrontend.services.HttpClientAsynchronous;
import com.example.apiFrontend.services.NotAuthorizationHeaderException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@RequestMapping(name = "", value = "api/requirement", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class RequirementRestController {
    private HttpClientAsynchronous httpClientAsynchronous;

    @Autowired
    public RequirementRestController(HttpClientAsynchronous httpClientAsynchronous) {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> Post(@ApiIgnore @RequestHeader Map<String, String> headers, @RequestBody Requirement data) throws Exception {
        try {
            String url = "requirement/save";
            JSONObject respose = this.httpClientAsynchronous.POST(headers,url, (Object) data);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
