package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/values")
public class ValuesController {

    public ValuesController() {
    }

    // GET: api/values/default
    @GetMapping("/default")
    public List<String> Get() {
        return List.of("value1", "value2");
    }

    // GET api/values/5
    @GetMapping("/{id}")
    public String Get(@PathVariable String id) {
        return id;
    }

    // POST api/values/default
    @PostMapping("/default")
    public List<String> Post(@RequestBody String value) {
        return List.of("POST", value);
    }

    // PUT api/values/5
    @PutMapping("/{id}")
    public List<String> Put(@PathVariable String id, @RequestBody String value) {
        return List.of("PUT", value, id);
    }

    // DELETE api/values/5
    @DeleteMapping("/{id}")
    public List<String> Delete(@PathVariable String id) {
        return List.of("DELETE", id);
    }

}
