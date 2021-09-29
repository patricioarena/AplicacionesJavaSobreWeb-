package com.example.demo.DataAccess.Models;

import com.example.demo.Application.IServices.Find;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public abstract class AbstractUser {
    private int _idUser;
    private int _idRole;
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
