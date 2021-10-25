package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IRoleService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.Role;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.DataAccess.Repository.RoleRepository;
import com.example.demo.Domain.RoleDto;
import org.bson.Document;
import org.bson.types.ObjectId;
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
    public RoleService(RoleRepository roleRepository, CommonResourcesRepository commonResourcesRepository){
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
    public CrudRepository<Role, ObjectId> getRepository() {
        return roleRepository;
    }
}
