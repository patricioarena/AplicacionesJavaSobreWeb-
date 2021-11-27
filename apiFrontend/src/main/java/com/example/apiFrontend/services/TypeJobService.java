package com.example.apiFrontend.services;

import com.example.apiFrontend.models.Role;
import com.example.apiFrontend.models.TypeJob;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Service
public class TypeJobService {
    HttpClientAsynchronous httpClientAsynchronous;
    @Getter(AccessLevel.NONE)
    private ArrayList<TypeJob> jobs = null;

    @Autowired
    public TypeJobService(HttpClientAsynchronous httpClientAsynchronous) throws Exception {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    private void getRolesFromDb() throws Exception {
        String urlRole = "typeJob/getAll";
        JSONObject responseRole = this.httpClientAsynchronous.GET(urlRole);
        String responseStringRole = responseRole.get("data").toString();
        Type listTypeRole = new TypeToken<ArrayList<TypeJob>>() {}.getType();
        this.jobs = new Gson().fromJson(responseStringRole, listTypeRole);
    }

    public ArrayList<TypeJob> getTypesJobs() throws Exception {
        if (jobs == null) {
            getRolesFromDb();
        }
        return jobs;
    }
}
