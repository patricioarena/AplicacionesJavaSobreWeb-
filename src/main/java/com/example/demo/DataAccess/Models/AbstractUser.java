package com.example.demo.DataAccess.Models;

import com.example.demo.Application.IServices.Find;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "usuarios")
@JsonDeserialize(as = AdminPerson.class)
public abstract class AbstractUser {

    private ObjectId _idRole;
    private String name;
    private String lastName;
    private String birthDate;
    private String typeDoc;
    private int numberDoc;
    private String gender;
    private String email;
    private String telephoneNumber;
    private float reputation;
    private Address address;
    private boolean deleted;


    public AbstractUser(ObjectId _idRole, String name, String lastName, String birthDate, String typeDoc,
                        int numberDoc, String gender, String email, String telephoneNumber, float reputation,
                        Address address, boolean deleted){
        this._idRole = _idRole;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.typeDoc = typeDoc;
        this.numberDoc = numberDoc;
        this.gender = gender;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.reputation = reputation;
        this.address = address;
        this.deleted = deleted;

    }

    public AbstractUser() {

    }
}
