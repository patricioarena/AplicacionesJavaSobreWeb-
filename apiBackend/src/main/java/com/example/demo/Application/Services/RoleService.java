package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IRoleService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.DefaultRoles;
import com.example.demo.DataAccess.Models.AdminPerson;
import com.example.demo.DataAccess.Models.OfferPerson;
import com.example.demo.DataAccess.Models.Role;
import com.example.demo.DataAccess.Models.User;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.RoleRepository;
import com.example.demo.Domain.RoleDto;
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

    //Extraer logica a un metodo
    public List<RoleDto> getAllRoles() {
        List<RoleDto> returnList = new ArrayList<>();
        Iterable<Document> roles = commonResourcesRepository.getAll(Database.roleCollection); // usar otro metodo o query para traer todos los documentos

        roles.forEach(rol -> {
            RoleDto role = new RoleDto();
            var value = rol.getObjectId("_id");
            var name = rol.get("roleName");
            role.set_id(value.toString());
            role.setRoleName(name.toString());
//            System.out.println("" + rol);
            returnList.add(role);
//            System.out.println(role);
        });
        return returnList;
    }

    @Override
    public RoleDto get(String id) {
        Role user = this.roleRepository.findById(new ObjectId(id)).get();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, RoleDto.class);
    }

    @Override
    public List<RoleDto> createDefaultRoles() {
        DefaultRoles.getDefaultRolesList().parallelStream().forEach(role -> getRepository().save(role));
        return this.getAllRoles();
    }

    @Override
    public RoleDto saveNewRole(RoleDto roleDto) {
        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        role.set_id(new ObjectId(roleDto.get_id()));
        var temp1 = getRepository().save(role);
        return modelMapper.map(temp1, RoleDto.class);
    }

    public Integer setDeleted(String id) {
        return this.commonResourcesRepository.setDeleted(id, Database.roleCollection);
    }

    @Override
    public CrudRepository<Role, ObjectId> getRepository() {
        return roleRepository;
    }
}
