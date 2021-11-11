package com.example.demo.controllers;

import com.example.demo.Application.IServices.IRoleService;
import com.example.demo.DataAccess.Models.Role;
import com.example.demo.Domain.Datawrapper;
import com.example.demo.Domain.RoleDto;
import com.example.demo.Domain.TypeJobDto;
import com.example.demo.Domain.UserDto;
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
    public ResponseEntity<Datawrapper<List<RoleDto>>> getAll(){
        List<RoleDto> list = _roleService.getAllRoles();
        Datawrapper<List<RoleDto>> response = new Datawrapper<>(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<RoleDto>> find(@PathVariable String id){
        RoleDto roleDto = _roleService.get(id);
        Datawrapper<RoleDto> response = new Datawrapper<>(roleDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<RoleDto>> save(@RequestBody RoleDto roleDto){
        RoleDto resultRoleDto = _roleService.saveNewRole(roleDto);
        Datawrapper<RoleDto> response = new Datawrapper<>(resultRoleDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/createDefaultRoles")
    public ResponseEntity<Datawrapper<List<RoleDto>>> createDefaultRoles(){
        List<RoleDto> list = _roleService.createDefaultRoles();
        Datawrapper<List<RoleDto>> response = new Datawrapper<>(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Datawrapper<RoleDto>> delete(@PathVariable String id){
        RoleDto resultRoleDto = _roleService.deleted(id);
        Datawrapper<RoleDto> response = new Datawrapper<>(resultRoleDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
