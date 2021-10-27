package com.example.apiFrontend.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.ui.Model;

// How to use Tomcat server in IntelliJ IDEA https://www.youtube.com/watch?v=29Xp3V3Ip08
// Spring MVC Start Code Java Config Version - Step by Step https://www.youtube.com/watch?v=KjQwrm4Jono

@ApiIgnore
@Controller
public class HomeController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }

}
