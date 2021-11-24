package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Greeting;
import com.example.apiFrontend.services.CommonsUtilities;
import com.example.apiFrontend.services.TypeJobService;
import com.example.apiFrontend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class RequirementController {

    private final UserService userService;
    private final TypeJobService typeJobService;
    private final CommonsUtilities commonsUtilities;

    @Autowired
    public RequirementController(UserService userService, TypeJobService typeJobService, CommonsUtilities commonsUtilities){
        this.userService = userService;
        this.typeJobService = typeJobService;
        this.commonsUtilities = commonsUtilities;
    }

    @GetMapping("/requirement")
    public ModelAndView greetingForm(Model model) throws Exception {
//        model.addAttribute("greeting", new Greeting());
        ModelAndView mav = new ModelAndView("requirements");
        mav.addObject("greeting", new Greeting());
        mav.addObject("countries", this.commonsUtilities.getCountries());
        mav.addObject("typesJobs", this.typeJobService.getTypesJobs());

        return mav;
    }

    @PostMapping("/requirement/new")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }


}
