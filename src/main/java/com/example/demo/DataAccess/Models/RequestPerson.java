package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "requestPersonCollection")
public class RequestPerson extends AbstractUser {

    public RequestPerson(ObjectId _idRole, String name, String lastName, String birthDate, String typeDoc,
                       int numberDoc, String gender, String email, String telephoneNumber, float reputation,
                       Address address, Boolean deleted){
        super(_idRole, name, lastName, birthDate, typeDoc, numberDoc, gender, email,
                telephoneNumber, reputation, address, deleted);
    }
}
