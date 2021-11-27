package com.example.apiFrontend.controllers;

import com.example.apiFrontend.models.Requirement;
import com.example.apiFrontend.models.forms.RequirementForm;
import com.example.apiFrontend.models.RequirementStatus;
import com.example.apiFrontend.models.TypeJob;
import com.example.apiFrontend.models.views.RequirementView;
import com.example.apiFrontend.services.CommonsUtilities;
import com.example.apiFrontend.services.RequirementService;
import com.example.apiFrontend.services.RequirementStatusService;
import com.example.apiFrontend.services.TypeJobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final RequirementStatusService requirementStatusService;

    @Autowired
    public RequirementController(
            RequirementService reqService,
            TypeJobService typeJobService,
            CommonsUtilities commonsUtilities,
            RequirementStatusService requirementStatusService
    ) {
        this.reqService = reqService;
        this.typeJobService = typeJobService;
        this.commonsUtilities = commonsUtilities;
        this.requirementStatusService = requirementStatusService;
    }

    @GetMapping("/requirement")
    public ModelAndView requirementForm(Model model) throws Exception {
        ModelAndView mav = new ModelAndView("requirements");
        mav.addObject("requirementForm", new RequirementForm());
        mav.addObject("countries", this.commonsUtilities.getCountries());
        mav.addObject("typesJobs", this.typeJobService.getTypesJobs());
        return mav;
    }

    @PostMapping("/requirement/new")
    public ModelAndView requirementFormSubmit(@ModelAttribute RequirementForm reqForm, Model model) throws Exception {

        Requirement temp = this.reqService.create(reqForm);
        ArrayList<TypeJob> typesJobs = this.typeJobService.getTypesJobs();

        ModelAndView mav = new ModelAndView("resultReqForm");
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

        Requirement temp = this.reqService.findByIdRequirement("619c5c4ee58f0b5c34920e6f");
        ArrayList<TypeJob> typesJobs = this.typeJobService.getTypesJobs();

        ModelAndView mav = new ModelAndView("resultReqForm");

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

    @GetMapping("/requirement/all/{idRequestPerson}")
    public ModelAndView allRequirementForOneId(@PathVariable String idRequestPerson) throws Exception {
        ModelAndView mav = new ModelAndView("allRequirementForOneId");
        ArrayList<Requirement> listReq = this.reqService.findByIdRequestPerson(idRequestPerson);
        ArrayList<RequirementView> listRequirementView = getListRequirementView(listReq);
        mav.addObject("listRequirement", listRequirementView);
        return mav;
    }

    @GetMapping("/requirement/all")
    public ModelAndView requirementAll() throws Exception {
        ModelAndView mav = new ModelAndView("allRequirement");
        ArrayList<Requirement> listReq = this.reqService.getAllRequirement();
        ArrayList<RequirementView> listRequirementView = getListRequirementView(listReq);
        mav.addObject("listRequirement", listRequirementView);
        return mav;
    }

    private ArrayList<RequirementView> getListRequirementView(ArrayList<Requirement> listReq) throws Exception {
        ArrayList<RequirementStatus> listReqStatus = this.requirementStatusService.getAllRequirementStatus();
        ArrayList<TypeJob> listTypeJobs = this.typeJobService.getTypesJobs();
        ArrayList<RequirementView> listRequirementView = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        listReq.forEach((requirement) -> {
            var req = modelMapper.map(requirement, RequirementView.class);
            req.setIdRequestPerson(requirement.get_idRequestPerson());

            listTypeJobs.forEach((typeJob) -> {
                if (requirement.get_idTypeJob().equals(typeJob.get_id())) {
                    req.setTypeJobName(typeJob.getJobName());
                }
            });

            listReqStatus.forEach((reqStatus) -> {
                if (requirement.get_idRequirementStatus().equals(reqStatus.get_id())) {
                    req.setRequirementStatusName(reqStatus.getStatus());
                }
            });

            listRequirementView.add(req);

        });
        return listRequirementView;
    }

}
