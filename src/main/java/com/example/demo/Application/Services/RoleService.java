package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IRoleService;
import com.example.demo.DataAccess.Models.Role;
import com.example.demo.DataAccess.Repository.RoleRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class RoleService extends GenericService<Role, ObjectId> implements IRoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public CrudRepository<Role, ObjectId> getRepository() {
        return roleRepository;
    }
}
