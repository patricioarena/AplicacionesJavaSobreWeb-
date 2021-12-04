package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Map;

@Data
@Getter
@Setter
public class Address {
    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String provState;
    private String country;
    @Nullable
    private Map<String,String> coordinates;

    public Address() {
    }

    public Address(String street, String number, String zipCode, String city, String provState, String country, Map<String,String> coordinates) {
        super();

        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.provState = provState;
        this.country = country;
        this.coordinates = coordinates;
    }
}
