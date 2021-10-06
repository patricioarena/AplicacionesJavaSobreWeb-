package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRequirementStatusService;
import com.example.demo.DataAccess.Models.RequirementStatus;
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

    public RequirementStatusController(IRequirementStatusService requirementStatusService){
        this._requirementStatusService = requirementStatusService;
    }

    @GetMapping(value = "/getAll")
    public List<RequirementStatus> getAll(){
        return _requirementStatusService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public RequirementStatus find(@PathVariable ObjectId id){
        return _requirementStatusService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<RequirementStatus> save(@RequestBody RequirementStatus requirementStatus){
        RequirementStatus obj = _requirementStatusService.save(requirementStatus);
        return new ResponseEntity<RequirementStatus>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<RequirementStatus> delete(@PathVariable ObjectId id){
        RequirementStatus requirementStatus = _requirementStatusService.get(id);
        if(requirementStatus != null){
            _requirementStatusService.delete(id);
        }else{
            return new ResponseEntity<RequirementStatus>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<RequirementStatus>(requirementStatus, HttpStatus.OK);
    }
}

