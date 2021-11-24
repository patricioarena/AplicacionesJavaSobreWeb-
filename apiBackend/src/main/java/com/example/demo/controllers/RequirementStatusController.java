package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRequirementStatusService;
import com.example.demo.DataAccess.Models.RequirementStatus;
import com.example.demo.Domain.Datawrapper;
import com.example.demo.Domain.RequirementDto;
import com.example.demo.Domain.RequirementStatusDto;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/requirementStatus")
@CrossOrigin("*")
public class RequirementStatusController {

    private IRequirementStatusService _requirementStatusService;

    public RequirementStatusController(IRequirementStatusService requirementStatusService) {
        this._requirementStatusService = requirementStatusService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Datawrapper<List<RequirementStatusDto>>>  getAll() {
        List<RequirementStatusDto> listRequirementStatus = _requirementStatusService.getAllRequirementStatus();
        Datawrapper<List<RequirementStatusDto>> response = new Datawrapper<>(listRequirementStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<RequirementStatusDto>>find(@PathVariable String id) {
        RequirementStatusDto rStatus = _requirementStatusService.getRequirementStatusById(id);
        Datawrapper<RequirementStatusDto> response = new Datawrapper<>(rStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<RequirementStatusDto>> save(@RequestBody RequirementStatus requirementStatus) {
        RequirementStatusDto rStatus = _requirementStatusService.saveNewRequirementStatus(requirementStatus);
        Datawrapper<RequirementStatusDto> response = new Datawrapper<>(rStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

