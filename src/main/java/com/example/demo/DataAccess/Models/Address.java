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
@Document(collection = "adressCollection")
public class Address {
    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String provState;
    private String country;

    public Address() {
    }

    public Address(String street, String number, String zipCode, String city, String provState, String country) {
        super();

        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.provState = provState;
        this.country = country;
    }
}
