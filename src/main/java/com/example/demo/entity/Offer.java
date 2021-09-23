package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class Offer {
    private int _idOffer;
    private int _idOfferPerson;
    private Date date;
    private String description;
    private Float price;
}
