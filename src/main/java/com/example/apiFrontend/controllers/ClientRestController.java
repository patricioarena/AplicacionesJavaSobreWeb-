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

@ApiIgnore
@RestController
@RequestMapping(name = "", value = "api/ClientRest", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientRestController {

    private HttpClientAsynchronous httpClientAsynchronous;

    private String urlPokemon = "https://pokeapi.co/api/v2/pokemon";

    private String urlTestGet = "http://localhost:3000/getTest";
    private String urlTestGetById = "http://localhost:3000/getTest/12";
    private String urlTestPost = "http://localhost:3000/postTest";
    private String urlTestPut = "http://localhost:3000/putTest/12";
    private String urlTestDelete = "http://localhost:3000/deleteTest/12";

    @Autowired
    public ClientRestController(HttpClientAsynchronous httpClientAsynchronous) {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> Get(@ApiIgnore @RequestHeader Map<String, String> headers) throws Exception {
        try {
            JSONObject respose = this.httpClientAsynchronous.GET(headers, urlPokemon);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JSONObject> GetById(@ApiIgnore @RequestHeader Map<String, String> headers) throws Exception {
        try {
            JSONObject respose = this.httpClientAsynchronous.GET(headers, urlTestGet);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> Post(@ApiIgnore @RequestHeader Map<String, String> headers, @RequestBody Object data) throws Exception {
        try {
            JSONObject respose = this.httpClientAsynchronous.POST(headers,urlTestPost, data);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public ResponseEntity<JSONObject> Put(@ApiIgnore @RequestHeader Map<String, String> headers, @RequestBody Object data) throws Exception {
        try {
            JSONObject respose = this.httpClientAsynchronous.PUT(headers,urlTestPut, data);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<JSONObject> Delete(@ApiIgnore @RequestHeader Map<String, String> headers) throws Exception {
        try {
            JSONObject respose = this.httpClientAsynchronous.DELETE(headers, urlTestDelete);
            return new ResponseEntity<>(respose, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof NotAuthorizationHeaderException)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
