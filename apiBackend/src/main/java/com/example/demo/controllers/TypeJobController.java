package com.example.demo.controllers;

import com.example.demo.Application.IServices.ITypeJobService;
import com.example.demo.DataAccess.Models.TypeJob;
import com.example.demo.Domain.TypeJobDto;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/typeJob")
@CrossOrigin("*")
public class TypeJobController {

    private ITypeJobService _typeJobService;

    public TypeJobController(ITypeJobService typeJobService){
        this._typeJobService = typeJobService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<TypeJobDto>> getAll(){
        List<TypeJobDto> listTypejob = _typeJobService.getAllJobs();
        return new ResponseEntity<List<TypeJobDto>>(listTypejob, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<TypeJobDto> find(@PathVariable ObjectId id){
        TypeJobDto job = _typeJobService.getJobById(id);
        return new ResponseEntity<TypeJobDto>(job, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<TypeJobDto> save(@RequestBody TypeJob typeJob){
        TypeJobDto obj = _typeJobService.saveNewTypeJob(typeJob);
        return new ResponseEntity<TypeJobDto>(obj, HttpStatus.OK);
    }

/*    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<TypeJob> delete(@PathVariable ObjectId id){
        TypeJob typeJob = _typeJobService.get(id);
        if(typeJob != null){
            _typeJobService.delete(id);
        }else{
            return new ResponseEntity<TypeJob>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<TypeJob>(typeJob, HttpStatus.OK);
    }*/

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<TypeJobDto> delete(@PathVariable String id){
        var typeJobDto = _typeJobService.deleted(id);
        return new ResponseEntity<TypeJobDto>(typeJobDto, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyJob")
    public ResponseEntity<TypeJobDto> modifyJob(@RequestBody TypeJobDto typeJob){
        TypeJobDto typeJobDtoModify = _typeJobService.modifyJob(typeJob);
        return new ResponseEntity<TypeJobDto>(typeJobDtoModify, HttpStatus.OK);
    }
}
