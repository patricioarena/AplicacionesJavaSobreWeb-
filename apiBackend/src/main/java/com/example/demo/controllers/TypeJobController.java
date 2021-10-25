package com.example.demo.controllers;

import com.example.demo.Application.IServices.ITypeJobService;
import com.example.demo.DataAccess.Models.TypeJob;
import com.example.demo.Domain.Datawrapper;
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
    public ResponseEntity<Datawrapper<List<TypeJobDto>>> getAll(){
        List<TypeJobDto> listTypejob = _typeJobService.getAllJobs();
        Datawrapper<List<TypeJobDto>> response = new Datawrapper<>(listTypejob);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<TypeJobDto>> find(@PathVariable ObjectId id){
        TypeJobDto job = _typeJobService.getJobById(id);
        Datawrapper<TypeJobDto> response = new Datawrapper<>(job);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<TypeJobDto>> save(@RequestBody TypeJob typeJob){
        TypeJobDto job = _typeJobService.saveNewTypeJob(typeJob);
        Datawrapper<TypeJobDto> response = new Datawrapper<>(job);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<Datawrapper<TypeJobDto>> delete(@PathVariable String id){
        TypeJobDto job = _typeJobService.deleted(id);
        Datawrapper<TypeJobDto> response = new Datawrapper<>(job);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/modifyJob")
    public ResponseEntity<Datawrapper<TypeJobDto>> modifyJob(@RequestBody TypeJobDto typeJob){
        TypeJobDto job = _typeJobService.modifyJob(typeJob);
        Datawrapper<TypeJobDto> response = new Datawrapper<>(job);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
