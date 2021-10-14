package com.example.demo.controllers;

import com.example.demo.Application.IServices.IAbstractUserService;
import com.example.demo.DataAccess.Models.AbstractUser;
import com.example.demo.DataAccess.Models.Address;
import com.example.demo.DataAccess.Models.AdminPerson;
import com.example.demo.DataAccess.Models.Comment;
import com.example.demo.Domain.AbstractUserDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/abstractUser")
@CrossOrigin("*")
public class AbstractUserController {

    private IAbstractUserService _abstractUserService;

    public AbstractUserController(IAbstractUserService abstractUserService){
        this._abstractUserService = abstractUserService;
    }

    @GetMapping(value = "/getAll")
    public List<AbstractUser> getAll(){

        return _abstractUserService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public AbstractUser find(@PathVariable ObjectId id){
        return _abstractUserService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<AbstractUser> save(@RequestBody AbstractUserDto abstractUser){
       // System.out.println(abstractUser);
        AbstractUser obj = _abstractUserService.saveNewUser(abstractUser);
        return new ResponseEntity<AbstractUser>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<AbstractUser> delete(@PathVariable ObjectId id){
        AbstractUser abstractUser = _abstractUserService.get(id);
        if(abstractUser != null){
            _abstractUserService.setDeleted(id);
        }else{
            return new ResponseEntity<AbstractUser>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<AbstractUser>(abstractUser, HttpStatus.OK);
    }

    @PutMapping(value = "/setDeleted")
    public void setDeleted(@RequestBody AbstractUser abstractUser){
        _abstractUserService.setDeleted(abstractUser);
    }

    @PutMapping(value = "/setDeleted/{id}")
    public void setDeleted(@PathVariable ObjectId id){
        _abstractUserService.setDeleted(id);
    }
}
