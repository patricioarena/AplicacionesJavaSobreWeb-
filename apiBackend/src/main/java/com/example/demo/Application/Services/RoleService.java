package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IRoleService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.DefaultRoles;
import com.example.demo.DataAccess.Models.*;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.RoleRepository;
import com.example.demo.Domain.RoleDto;
import com.example.demo.Domain.TypeJobDto;
import com.example.demo.Domain.UserDto;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: 25/10/2021 Ver lo que se pueda cambiar por mapper

@Service
@Configuration
public class RoleService extends GenericService<Role, ObjectId> implements IRoleService {

    private final RoleRepository roleRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, CommonResourcesRepository commonResourcesRepository) {
        this.roleRepository = roleRepository;
        this.commonResourcesRepository = commonResourcesRepository;
    }

    public List<RoleDto> getAllRoles() {
        List<RoleDto> returnList = new ArrayList<>();
        Iterable<Document> roles = this.commonResourcesRepository.getAll(Database.roleCollection);
        roles.forEach(rol -> {
            RoleDto role = new RoleDto();
            var value = rol.getObjectId("_id");
            var name = rol.get("roleName");
            role.set_id(value.toString());
            role.setRoleName(name.toString());
            returnList.add(role);
        });
        return returnList;
    }

    @Override
    public RoleDto get(String id) {
        Role user = this.getRepository().findById(new ObjectId(id)).get();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, RoleDto.class);
    }

    @Override
    public List<RoleDto> createDefaultRoles() {
        DefaultRoles.getDefaultRolesList().parallelStream().forEach(role -> this.getRepository().save(role));
        return this.getAllRoles();
    }

    @Override
    public RoleDto saveNewRole(RoleDto roleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        role.set_id(new ObjectId(roleDto.get_id()));
        var temp1 = this.getRepository().save(role);
        return modelMapper.map(temp1, RoleDto.class);
    }

    public RoleDto deleted(String id) {
        RoleDto typeJobDto = new RoleDto();
        var result = this.commonResourcesRepository.deleted(id, Database.roleCollection);
        return (result == 1) ? this.get(id): typeJobDto;
//        if (result == 1){
//           return this.get(id);
//        }else {
//            return typeJobDto;
//        }
    }

    @Override
    public CrudRepository<Role, ObjectId> getRepository() {
        return roleRepository;
    }
}
