package com.example.apiFrontend.controllers;

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
@RequestMapping(name = "", value = "api/requirementStatus", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class RequirementStatusRestController {
    private HttpClientAsynchronous httpClientAsynchronous;

    @Autowired
    public RequirementStatusRestController(HttpClientAsynchronous httpClientAsynchronous) {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> Get(@ApiIgnore @RequestHeader Map<String, String> headers) throws Exception {
        try {
            String url = "requirementStatus/getAll";
            JSONObject respose = this.httpClientAsynchronous.GET(headers, url);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
