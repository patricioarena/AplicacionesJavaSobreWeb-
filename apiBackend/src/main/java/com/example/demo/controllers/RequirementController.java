package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.DataAccess.Models.Requirement;
import com.example.demo.Domain.RequirementDto;
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
    public ResponseEntity<List<RequirementDto>> getAll(){
        List<RequirementDto> listRequirement = _requirementService.getAllRequirements();
        return new ResponseEntity<List<RequirementDto>>(listRequirement, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<RequirementDto> find(@PathVariable ObjectId id){
        RequirementDto requirement = _requirementService.getRequirementById(id);
        return new ResponseEntity<RequirementDto>(requirement, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<RequirementDto> save(@RequestBody Requirement requirement){
        RequirementDto obj = _requirementService.saveNewRequirement(requirement);
        return new ResponseEntity<RequirementDto>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<RequirementDto> delete(@PathVariable String id){
        var requirementDto = _requirementService.deleted(id);
        return new ResponseEntity<RequirementDto>(requirementDto, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyRequirement")
    public ResponseEntity<RequirementDto> modifyRequirement(@RequestBody RequirementDto requirement){
        RequirementDto requirementDtoModify = _requirementService.modifyRequirement(requirement);
        return new ResponseEntity<RequirementDto>(requirementDtoModify, HttpStatus.OK);
    }

}
