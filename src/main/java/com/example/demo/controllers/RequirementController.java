package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.Domain.Datawrapper;
import com.example.demo.Domain.RequirementDto;
import com.example.demo.Domain.TypeJobDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/requirement")
@CrossOrigin("*")
public class RequirementController {

    private IRequirementService _requirementService;

    @Autowired
    public RequirementController(IRequirementService requirementService) {
        this._requirementService = requirementService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Datawrapper<List<RequirementDto>>> getAll() {
        List<RequirementDto> listRequirement = _requirementService.getAllRequirements();
        Datawrapper<List<RequirementDto>> response = new Datawrapper<>(listRequirement);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/{idRequestPerson}")
    public ResponseEntity<Datawrapper<List<RequirementDto>>> getAllRequirementsByRequestPerson(@PathVariable String idRequestPerson) {
        List<RequirementDto> listRequirement = _requirementService.getAllRequirementsByRequestPerson(idRequestPerson);
        Datawrapper<List<RequirementDto>> response = new Datawrapper<>(listRequirement);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<RequirementDto>> find(@PathVariable String id) {
        RequirementDto requirement = _requirementService.getRequirementById(id);
        Datawrapper<RequirementDto> response = new Datawrapper<>(requirement);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<RequirementDto>> save(@RequestBody RequirementDto requirementDto) {
        RequirementDto requirement = _requirementService.saveNewRequirement(requirementDto);
        Datawrapper<RequirementDto> response = new Datawrapper<>(requirement);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Datawrapper<RequirementDto>> delete(@PathVariable String id) {
        RequirementDto requirementDto = _requirementService.deleted(id);
        Datawrapper<RequirementDto> response = new Datawrapper<>(requirementDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyRequirement")
    public ResponseEntity<Datawrapper<RequirementDto>> modifyRequirement(@RequestBody RequirementDto requirement) {
        RequirementDto requirementDtoModify = _requirementService.modifyRequirement(requirement);
        Datawrapper<RequirementDto> response = new Datawrapper<>(requirementDtoModify);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
