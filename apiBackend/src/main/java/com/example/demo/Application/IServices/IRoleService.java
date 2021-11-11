package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.Role;
import com.example.demo.Domain.RoleDto;
import com.example.demo.Domain.TypeJobDto;
import com.example.demo.Domain.UserDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IRoleService extends IGenericService<Role, ObjectId>{

    List<RoleDto> getAllRoles();
    RoleDto get(String id);
    List<RoleDto> createDefaultRoles();
    RoleDto saveNewRole(RoleDto roleDto);
    RoleDto deleted(String id);
}
