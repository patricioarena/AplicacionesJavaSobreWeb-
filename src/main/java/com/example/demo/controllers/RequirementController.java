package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.DataAccess.Models.Requirement;
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
    public List<Requirement> getAll(){
        return _requirementService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Requirement find(@PathVariable ObjectId id){
        return _requirementService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Requirement> save(@RequestBody Requirement requirement){
        Requirement obj = _requirementService.save(requirement);
        return new ResponseEntity<Requirement>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Requirement> delete(@PathVariable ObjectId id){
        Requirement requirement = _requirementService.get(id);
        if(requirement != null){
            _requirementService.delete(id);
        }else{
            return new ResponseEntity<Requirement>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
    }

}
