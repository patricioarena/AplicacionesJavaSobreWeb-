package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRequirementService;
import com.example.demo.DataAccess.DbMongo.IDbMongoConfiguration;
import com.example.demo.DataAccess.Models.Requirement;
import com.example.demo.DataAccess.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/requirement")
@CrossOrigin("*")
public class RequirementController {

    private IRequirementService irequirementService;

    @Autowired
    public RequirementController(IRequirementService irequirementService) {
        this.irequirementService = irequirementService;
    }

    @GetMapping(value = "/getAll")
    public List<Requirement> getAll(){
        return irequirementService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Requirement find(@PathVariable Long id){
        return irequirementService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Requirement> save(@RequestBody Requirement requirement){
        Requirement obj = irequirementService.save(requirement);
        return new ResponseEntity<Requirement>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Requirement> delete(@PathVariable Long id){
        Requirement requirement = irequirementService.get(id);
        if(requirement != null){
            irequirementService.delete(id);
        }else{
            return new ResponseEntity<Requirement>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
    }

}
