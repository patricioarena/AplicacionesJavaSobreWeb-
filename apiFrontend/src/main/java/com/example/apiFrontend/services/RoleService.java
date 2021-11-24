package com.example.apiFrontend.services;

import com.example.apiFrontend.models.Role;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Getter
@Service
public class RoleService {

    HttpClientAsynchronous httpClientAsynchronous;
    ArrayList<Role> roles = null;

    @Autowired
    public RoleService(HttpClientAsynchronous httpClientAsynchronous) throws Exception {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    private void getRolesFromDb() throws Exception {
        String urlRole = "role/getAll";
        JSONObject responseRole = this.httpClientAsynchronous.GET(urlRole);
        String responseStringRole = responseRole.get("data").toString();
        Type listTypeRole = new TypeToken<ArrayList<Role>>() {}.getType();
        this.roles = new Gson().fromJson(responseStringRole, listTypeRole);
    }

    public ArrayList<Role> getRoles() throws Exception {
        if (roles == null) {
            getRolesFromDb();
        }
        return roles;
    }
}
