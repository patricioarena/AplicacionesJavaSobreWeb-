package com.example.apiFrontend.services;

import com.example.apiFrontend.models.Address;
import com.example.apiFrontend.models.Role;
import com.example.apiFrontend.models.User;
import com.example.apiFrontend.models.UserFormRegister;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

@Getter
@Service
public class UserService {

    HttpClientAsynchronous httpClientAsynchronous;
    RoleService roleService;

    @Autowired
    public UserService(HttpClientAsynchronous httpClientAsynchronous, RoleService roleService) throws Exception {
        this.httpClientAsynchronous = httpClientAsynchronous;
        this.roleService = roleService;
    }

    public ArrayList<User> getAllUsers() throws Exception {

        String urlUsers = "user/getAll";
        JSONObject resposeUser = this.httpClientAsynchronous.GET(urlUsers);
        String responseString = resposeUser.get("data").toString();
        Type listType = new TypeToken<ArrayList<User>>() {}.getType();
        ArrayList<User> users = new Gson().fromJson(responseString, listType);

        return users;
    }

    public User findById(String id) throws Exception {

        String urlUserFind = "user/find/"+id;
        JSONObject resposeUser = this.httpClientAsynchronous.GET(urlUserFind);
        String responseString = resposeUser.get("data").toString();

        Type listType = new TypeToken<User>() {}.getType(); //TypeToken se utiliza para indicarle a Gson el tipo especifico al cual lo tiene que convertir
        User user = new Gson().fromJson(responseString, listType);

        return user;
    }

    public User update(User user) throws Exception {

        String urlUserUpdate = "user/update";
        JSONObject responseUser = this.httpClientAsynchronous.PUT(urlUserUpdate, user);
        String responseString = responseUser.get("data").toString();

        Type listType = new TypeToken<User>() {}.getType();
        User userUpdate = new Gson().fromJson(responseString, listType);

        return userUpdate;
    }

    public User create(UserFormRegister userFormRegister) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userFormRegister, User.class);

        user.setAddress(new Address());
        ArrayList<Role> roles = roleService.getRoles();

        String urlUserCreate = "user/save";
        JSONObject responseUser = this.httpClientAsynchronous.POST(urlUserCreate,user);
        String responseString = responseUser.get("data").toString();

        Type listType = new TypeToken<User>() {}.getType();
        User userCreate = new Gson().fromJson(responseString, listType);

        return userCreate;
    }
}
