package com.example.apiFrontend.services;

import com.example.apiFrontend.models.RequirementStatus;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Getter
@Service
public class RequirementStatusService {

    HttpClientAsynchronous httpClientAsynchronous;
    @Getter(AccessLevel.NONE)
    private ArrayList<RequirementStatus> allStatus = null;

    @Autowired
    public RequirementStatusService(HttpClientAsynchronous httpClientAsynchronous) throws Exception {
        this.httpClientAsynchronous = httpClientAsynchronous;
    }

    private void getRequirementStatusFromDb() throws Exception {
        String urlRole = "requirementStatus/getAll";
        JSONObject responseRole = this.httpClientAsynchronous.GET(urlRole);
        String responseStringRole = responseRole.get("data").toString();
        Type listTypeRole = new TypeToken<ArrayList<RequirementStatus>>() {}.getType();
        this.allStatus = new Gson().fromJson(responseStringRole, listTypeRole);
    }

    public ArrayList<RequirementStatus> getAllRequirementStatus() throws Exception {
        if (allStatus == null) {
            getRequirementStatusFromDb();
        }
        return allStatus;
    }
}
