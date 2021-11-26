package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Requirement;
import com.example.apiFrontend.models.RequirementForm;
import com.example.apiFrontend.models.TypeJob;
import com.example.apiFrontend.services.CommonsUtilities;
import com.example.apiFrontend.services.RequirementService;
import com.example.apiFrontend.services.TypeJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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
    public ModelAndView requirementFormSubmit(@ModelAttribute RequirementForm reqForm, Model model) throws Exception {

        Requirement temp = this.reqService.create(reqForm);

        ModelAndView mav = new ModelAndView("resultReqForm");
        ArrayList<TypeJob> typesJobs = this.typeJobService.getTypesJobs();
        AtomicReference<String> jobName = new AtomicReference<>("");

        typesJobs.forEach((typeJob) -> {
            if (typeJob.get_id().equals(temp.get_idTypeJob())) {
                jobName.set(typeJob.getJobName());
            }
        });

        mav.addObject("requirement", temp);
        mav.addObject("jobName", jobName);
        return mav;
    }

    // Metodo para maquetar
    @GetMapping("/requirement/resultReqForm")
    public ModelAndView resultReqForm() throws Exception {

        Requirement temp = this.reqService.findById("619c5c4ee58f0b5c34920e6f");

        ModelAndView mav = new ModelAndView("resultReqForm");
        ArrayList<TypeJob> typesJobs = this.typeJobService.getTypesJobs();

        AtomicReference<String> jobName = new AtomicReference<>("");
        typesJobs.forEach((typeJob) -> {
             if (typeJob.get_id().equals(temp.get_idTypeJob())) {
                 jobName.set(typeJob.getJobName());
             }
        });

        mav.addObject("requirement", temp);
        mav.addObject("jobName", jobName);
        return mav;

    }

}
