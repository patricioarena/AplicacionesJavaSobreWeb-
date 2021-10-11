package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRoleService;
import com.example.demo.DataAccess.Models.Role;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
@CrossOrigin("*")
public class RoleController {

    private IRoleService _roleService;

    public RoleController(IRoleService roleService){
        this._roleService = roleService;
    }

    @GetMapping(value = "/getAll")
    public List<Role> getAll(){
        return _roleService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Role find(@PathVariable ObjectId id){
        return _roleService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Role> save(@RequestBody Role role){
        Role obj = _roleService.save(role);
        return new ResponseEntity<Role>(obj, HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Role> delete(@PathVariable ObjectId id){
        Role role = _roleService.get(id);
        if(role != null){
            _roleService.delete(id);
        }else{
            return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }
}
