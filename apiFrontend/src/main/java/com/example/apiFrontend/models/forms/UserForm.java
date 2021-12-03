package com.example.apiFrontend.models.forms;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
public class UserForm {
    private String id;
    private ArrayList<String> roles;
    private String name;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;
    private String typeDoc;
    private int numberDoc;
    private String gender;
    private String email;
    private String telephoneNumber;
    private double reputation;
    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String provState;
    private String country;
    private boolean deleted;
}
