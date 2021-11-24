package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Greeting;
import com.example.apiFrontend.services.TypeJobService;
import com.example.apiFrontend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class RequirementController {

    private final UserService userService;
    private final TypeJobService typeJobService;

    @Autowired
    public RequirementController(UserService userService, TypeJobService typeJobService){
        this.userService = userService;
        this.typeJobService = typeJobService;
    }

    @GetMapping("/requirement")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "requirements";
    }

    @PostMapping("/requirement/new")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }


}
