package com.example.demo.dao;

import com.example.demo.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDAO {
    private String role;
    private String name;
    private String lastName;
    private String birthDate;
    private String typeDoc;
    private int numberDoc;
    private String gender;
    private String address;
    private String email;
    private String telephoneNumber;
    private float reputation;

}
