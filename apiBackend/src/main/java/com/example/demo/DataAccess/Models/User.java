package com.example.demo.DataAccess.Models;
import com.example.demo.DataAccess.Database;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;


@Data
@Getter
@Setter
@Document(collection = Database.userCollection)
@JsonDeserialize(as = User.class)
public class User {

    private ObjectId _id;
    private ArrayList<ObjectId> roles;
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


    public User(ObjectId _idRole, String name, String lastName, Date birthDate, String typeDoc,
                int numberDoc, String gender, String email, String password, String telephoneNumber, float reputation,
                Address address, boolean deleted){
        this.roles = roles;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.typeDoc = typeDoc;
        this.numberDoc = numberDoc;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.reputation = reputation;
        this.address = address;
        this.deleted = deleted;

    }

    public User() {

    }
}
