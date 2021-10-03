package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;

@Data
@Getter
@Setter
public class Offer {
    private ObjectId _idOffer;
    private ObjectId _idOfferPerson;
    private Date date;
    private String description;
    private Float price;
}
