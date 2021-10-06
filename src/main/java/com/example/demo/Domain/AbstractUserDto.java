package com.example.demo.Domain;

import com.example.demo.DataAccess.Models.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
public class AbstractUserDto {

    private String _idRole;
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
    private Boolean deleted;
}
