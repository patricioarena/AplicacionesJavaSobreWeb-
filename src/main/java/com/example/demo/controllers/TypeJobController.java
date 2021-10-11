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
    public List<TypeJobDto> getAll(){

      //  return _typeJobService.getAll();
        return _typeJobService.getAllJobs();
    }

    @GetMapping(value = "/find/{id}")
    public TypeJob find(@PathVariable ObjectId id){
        return _typeJobService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<TypeJob> save(@RequestBody TypeJob typeJob){
        TypeJob obj = _typeJobService.save(typeJob);
        return new ResponseEntity<TypeJob>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<TypeJob> delete(@PathVariable ObjectId id){
        TypeJob typeJob = _typeJobService.get(id);
        if(typeJob != null){
            _typeJobService.delete(id);
        }else{
            return new ResponseEntity<TypeJob>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<TypeJob>(typeJob, HttpStatus.OK);
    }
}
