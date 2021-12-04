package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "adminPersonCollection")
public class AdminPerson extends User {

    public AdminPerson(ObjectId _idRole, String name, String lastName, Date birthDate, String typeDoc,
                       int numberDoc, String gender, String email, String password, String telephoneNumber, float reputation,
                       Address address, boolean deleted){
        super(_idRole, name, lastName, birthDate, typeDoc, numberDoc, gender, email, password,
                telephoneNumber, reputation, address, deleted);
    }

    public AdminPerson(){}
}
