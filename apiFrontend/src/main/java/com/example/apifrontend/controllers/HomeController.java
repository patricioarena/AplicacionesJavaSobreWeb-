package com.example.apifrontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

// How to use Tomcat server in IntelliJ IDEA https://www.youtube.com/watch?v=29Xp3V3Ip08
// Spring MVC Start Code Java Config Version - Step by Step https://www.youtube.com/watch?v=KjQwrm4Jono

@ApiIgnore
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/example")
    public String welcome(){
        return "example";
    }

}
