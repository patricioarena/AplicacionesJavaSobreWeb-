package com.example.demo.Application.IServices;

import com.example.demo.DataAccess.Models.Role;
import com.example.demo.Domain.RoleDto;
import com.example.demo.Domain.TypeJobDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IRoleService extends IGenericService<Role, ObjectId>{

    List<RoleDto> getAllRoles();
}
