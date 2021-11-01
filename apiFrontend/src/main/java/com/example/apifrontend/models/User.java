package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {

    private String _id;
    private String _idRole;
    private String name;
    private String lastName;
    private String birthDate;
    private String typeDoc;
    private int numberDoc;
    private String gender;
    private String email;
    private String telephoneNumber;
    private double reputation;
    private Address address;
    private boolean deleted;

}
