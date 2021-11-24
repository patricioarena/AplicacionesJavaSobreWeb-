package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
public class User {

    private String _id;
    private ArrayList<String> roles;
    @Nullable
    private ArrayList<String> rolesLabel;
    private String name;
    private String lastName;
    private Date birthDate;
    private String typeDoc;
    private int numberDoc;
    private String gender;
    private String email;
    private String password;
    private String telephoneNumber;
    private double reputation;
    private Address address;
    private boolean deleted;

}
