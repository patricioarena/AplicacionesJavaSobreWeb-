package com.example.apifrontend.controllers;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("api/values")
public class ValuesController {

    public ValuesController() {
    }

    // GET: api/values/default
    @RequestMapping(value = "", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public List<String> Get() {
        return List.of("value1", "value2");
    }

    // GET api/values/5
    @RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public String Get(@PathVariable String id) {
        return id;
    }

    // POST api/values/default
    @RequestMapping(value = "", produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public List<String> Post(@RequestBody String value) {
        return List.of("POST", value);
    }

    // PUT api/values/5
    @RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.PUT)
    public List<String> Put(@PathVariable String id, @RequestBody String value) {
        return List.of("PUT", value, id);
    }

    // DELETE api/values/5
    @RequestMapping(value = "/{id}", produces = "application/json; charset=UTF-8", method = RequestMethod.DELETE)
    public List<String> Delete(@PathVariable String id) {
        return List.of("DELETE", id);
    }

}
