package com.example.demo.Application.Services;

import com.example.demo.Application.IServices.IUserService;
import com.example.demo.DataAccess.Database;
import com.example.demo.DataAccess.Models.*;
import com.example.demo.DataAccess.Repository.IUserRepository;
import com.example.demo.DataAccess.Repository.CommonResourcesRepository;
import com.example.demo.Domain.UserDto;
import com.google.gson.Gson;
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

@Service
@Configuration
public class UserService extends GenericService<User, ObjectId> implements IUserService {

    private IUserRepository userRepository;
    private final CommonResourcesRepository commonResourcesRepository;

    @Autowired
    public UserService(IUserRepository userRepository, CommonResourcesRepository commonResourcesRepository){
        this.userRepository = userRepository;
        this.commonResourcesRepository =commonResourcesRepository;
    }

//    @Override
//    public void setDeleted(UserDto user){
//        ModelMapper modelMapper = new ModelMapper();
//        User userAux = modelMapper.map(user, User.class);
//        user.setDeleted(true);
//        getRepository().save(userAux);
//    }
//
//    @Override
//    public void setDeleted(String id){
//        var get = getRepository();
//        Optional<User> obj = get.findById(new ObjectId(id));
//        if(obj.isPresent()){
//            User user = obj.get();
//            user.setDeleted(true);
//            getRepository().save(user);
//        }
//    }
//
//    // No se esta utilizando
//    @Override
//    public void setDeleted(ObjectId id){
//        var get = getRepository();
//        Optional<User> obj = get.findById(id);
//        if(obj.isPresent()){
//            User user = obj.get();
//            user.setDeleted(true);
//            getRepository().save(user);
//        }
//    }

    public Integer setDeleted(String id){
        return this.commonResourcesRepository.setDeleted(id, Database.userCollection);
    }

    public Integer updateUser(UserDto userDto){
        return this.commonResourcesRepository.updateFields(userDto, Database.userCollection);
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        ObjectId roleId = null;

        if (userDto.get_idRole() != null){
            roleId = new ObjectId(userDto.get_idRole());
        }else{
            roleId = new ObjectId();
        }

        UserDto userDtoReturn = null;

        switch(roleId.toString()){
            case "6171ffa57831007cb5105356":    // admin

                AdminPerson adminPerson = modelMapper.map(userDto, AdminPerson.class);
                adminPerson.set_idRole(roleId);
                var temp1 = getRepository().save(adminPerson);
                userDtoReturn = modelMapper.map(temp1, UserDto.class);
                break;

            case "6171ffed7831007cb5105357":    // requestPerson

                RequestPerson requestPerson = modelMapper.map(userDto, RequestPerson.class);
                requestPerson.set_idRole(roleId);
                var temp2 = getRepository().save(requestPerson);
                userDtoReturn = modelMapper.map(temp2, UserDto.class);
                break;

            case "617200147831007cb5105358":    // offerPerson

                OfferPerson offerPerson = modelMapper.map(userDto, OfferPerson.class);
                offerPerson.set_idRole(roleId);
                var temp3 = getRepository().save(offerPerson);
                userDtoReturn = modelMapper.map(temp3, UserDto.class);
                break;

            default:

                User user = modelMapper.map(userDto, User.class);
                user.set_idRole(null);
                var temp4 = getRepository().save(user);
                userDtoReturn = modelMapper.map(temp4, UserDto.class);
                break;

        }
        return userDtoReturn;
    }

    public List<UserDto> getAllUsers(){
        List<UserDto> returnList = new ArrayList<>();
        Iterable<User> iterableDocuments = this.userRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();

        iterableDocuments.forEach(user -> {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            returnList.add(userDto);
        });
        return returnList;
    }

    public UserDto get(String id){
        User user = this.userRepository.findById(new ObjectId(id)).get();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<User> getAll(){
        throw new java.lang.UnsupportedOperationException("Not supported yet. Use getAllUsers");
    }

    @Override
    public CrudRepository<User, ObjectId> getRepository() {
        return userRepository;
    }
}
