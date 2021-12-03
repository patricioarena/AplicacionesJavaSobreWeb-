package com.example.apiFrontend.services;

import com.example.apiFrontend.models.Requirement;
import com.example.apiFrontend.models.forms.RequirementForm;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Getter
@Service
public class RequirementService {

    private final RequirementStatusService requirementStatusService;
    HttpClientAsynchronous httpClientAsynchronous;

    @Autowired
    public RequirementService(HttpClientAsynchronous httpClientAsynchronous, RequirementStatusService requirementStatusService) throws Exception {
        this.httpClientAsynchronous = httpClientAsynchronous;
        this.requirementStatusService = requirementStatusService;
    }

    public Requirement create(RequirementForm model) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        model.setIdRequirementStatus(this.requirementStatusService.getAllRequirementStatus().get(0).get_id());
        model.setZone(model.getAddress().getZipCode());
        model.setIdRequestPerson("617638887e4fff79d6ec72a8");

        Requirement requirement = modelMapper.map(model, Requirement.class);
        requirement.set_idRequestPerson(model.getIdRequestPerson());
        requirement.set_idRequirementStatus(model.getIdRequirementStatus());
        requirement.set_idTypeJob(model.getIdTypeJob());

        String url= "requirement/save";
        JSONObject responseJson = this.httpClientAsynchronous.POST(url, requirement);
        String responseString = responseJson.get("data").toString();

        Type listType = new TypeToken<Requirement>() {}.getType();
        Requirement userUpdate = new Gson().fromJson(responseString, listType);

        return userUpdate;
    }


    public Requirement findByIdRequirement(String id) throws Exception {

        String url = "requirement/find/"+id;
        JSONObject responseJson= this.httpClientAsynchronous.GET(url);
        String responseString = responseJson.get("data").toString();

        Type listType = new TypeToken<Requirement>() {}.getType(); //TypeToken se utiliza para indicarle a Gson el tipo especifico al cual lo tiene que convertir
        return new Gson().fromJson(responseString, listType);

    }

    public ArrayList<Requirement> findByIdRequestPerson(String id) throws Exception {

        String url = "requirement/getAll/"+id;
        JSONObject responseJson = this.httpClientAsynchronous.GET(url);
        String responseString = responseJson.get("data").toString();

        Type listType = new TypeToken<ArrayList<Requirement>>() {}.getType();
        return new Gson().fromJson(responseString, listType);
    }

    public ArrayList<Requirement> getAllRequirement() throws Exception {
        String url = "requirement/getAll";
        JSONObject responseJson = this.httpClientAsynchronous.GET(url);
        String responseString = responseJson.get("data").toString();

        Type listType = new TypeToken<ArrayList<Requirement>>() {}.getType();
        return new Gson().fromJson(responseString, listType);
    }
}
