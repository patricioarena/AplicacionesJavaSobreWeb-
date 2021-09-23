package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}
