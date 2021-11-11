package com.example.demo.controllers;

import com.example.demo.Application.IServices.IUserService;
import com.example.demo.DataAccess.Models.User;
import com.example.demo.Domain.Datawrapper;
import com.example.demo.Domain.TypeJobDto;
import com.example.demo.Domain.UserDto;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {

    private IUserService _userService;

    public UserController(IUserService userService) {
        this._userService = userService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Datawrapper<List<UserDto>>> getAll() {
        List<UserDto> resultList = _userService.getAllUsers();
        Datawrapper<List<UserDto>> response = new Datawrapper<>(resultList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Datawrapper<UserDto>> find(@PathVariable String id) {
        UserDto resultUserDto = _userService.get(id);
        Datawrapper<UserDto> response = new Datawrapper<>(resultUserDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Datawrapper<UserDto>> save(@RequestBody UserDto userDto) {
        UserDto resultUserDto = _userService.saveNewUser(userDto);
        Datawrapper<UserDto> response = new Datawrapper<>(resultUserDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Datawrapper<UserDto>> update(@RequestBody UserDto userDto) {
        UserDto resultUpdate = _userService.updateUser(userDto);
        Datawrapper<UserDto> response = new Datawrapper<>(resultUpdate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Datawrapper<UserDto>> setDeleted(@PathVariable String id) {
        UserDto resultDelete = _userService.deleted(id);
        Datawrapper<UserDto> response = new Datawrapper<>(resultDelete);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
