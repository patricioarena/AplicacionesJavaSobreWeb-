package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;

@Data
@Getter
@Setter
public class UserFormRegister {

    private ArrayList<String> roles;
    @Nullable
    private ArrayList<String> rolesLabel;
    private String name;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;
    @Nullable
    private String typeDoc;
    @Nullable
    private int numberDoc;
    private String gender;
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String telephoneNumber;
    private double reputation;
    @Nullable
    private Address address;
    private boolean deleted;
}
