package com.example.apiFrontend.services;

import com.example.apiFrontend.models.Role;
import com.example.apiFrontend.models.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    HttpClientAsynchronous httpClientAsynchronous;
    ArrayList<Role> roles = null;

    @Autowired
    public UserService(HttpClientAsynchronous httpClientAsynchronous) throws Exception {
        this.httpClientAsynchronous = httpClientAsynchronous;

        String urlRole = "role/getAll";
        JSONObject responseRole = this.httpClientAsynchronous.GET(urlRole);
        String responseStringRole = responseRole.get("data").toString();
        Type listTypeRole = new TypeToken<ArrayList<Role>>() {}.getType();
        this.roles = new Gson().fromJson(responseStringRole, listTypeRole);

    }

    public User findById(String id) throws Exception {

        String urlUserFind = "user/find/"+id;
        JSONObject resposeUser = this.httpClientAsynchronous.GET(urlUserFind);
        String responseString = resposeUser.get("data").toString();

        Type listType = new TypeToken<User>() {}.getType();
        User user = new Gson().fromJson(responseString, listType);

        return user;
    }

    public void update(User user) {
        System.out.println("Hacer algo");
    }
}
