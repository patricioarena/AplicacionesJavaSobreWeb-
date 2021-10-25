package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.User;
import com.example.demo.Domain.UserDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IUserService extends IGenericService <User, ObjectId>{

//    void setDeleted(UserDto user);
//    void setDeleted(String id);
//    void setDeleted(ObjectId id);

    Integer setDeleted(String id);
    Integer updateUser(UserDto userDto);

    UserDto saveNewUser(UserDto abstractUser);
    List<UserDto> getAllUsers();
    UserDto get(String id);
}
