package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Greeting;
import com.example.apiFrontend.models.Requirement;
import com.example.apiFrontend.models.RequirementForm;
import com.example.apiFrontend.services.CommonsUtilities;
import com.example.apiFrontend.services.RequirementService;
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

    private final TypeJobService typeJobService;
    private final CommonsUtilities commonsUtilities;
    private final RequirementService reqService;

    @Autowired
    public RequirementController(RequirementService reqService, TypeJobService typeJobService, CommonsUtilities commonsUtilities){
        this.reqService = reqService;
        this.typeJobService = typeJobService;
        this.commonsUtilities = commonsUtilities;
    }

    @GetMapping("/requirement")
    public ModelAndView requirementForm(Model model) throws Exception {
//        model.addAttribute("greeting", new Greeting());
        ModelAndView mav = new ModelAndView("requirements");
        mav.addObject("requirementForm", new RequirementForm());
        mav.addObject("countries", this.commonsUtilities.getCountries());
        mav.addObject("typesJobs", this.typeJobService.getTypesJobs());
        return mav;
    }

    @PostMapping("/requirement/new")
    public String requirementFormSubmit(@ModelAttribute RequirementForm reqForm, Model model) throws Exception {
        Requirement temp = this.reqService.create(reqForm);
        model.addAttribute("requirement", temp);
        return "resultReqForm";
    }

    @GetMapping("/requirement/resultReqForm")
    public String resultReqForm(Model model) throws Exception {
        return "resultReqForm";
    }

}
