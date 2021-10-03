package com.example.demo.DataAccess.Models;

import com.example.demo.Application.IServices.Find;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "usuarios")
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
}
