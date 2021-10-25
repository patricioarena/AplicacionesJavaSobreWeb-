package com.example.demo.Domain;

import com.example.demo.DataAccess.Models.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
public class UserDto {

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
