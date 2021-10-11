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
@Document(collection = "offerCollection")
public class Offer {

    private ObjectId _idOfferPerson;
    private Date date;
    private String description;
    private Float price;

    public Offer() {
    }

    public Offer(ObjectId _idOfferPerson, Date date, String description, Float price) {
        super();

        this._idOfferPerson = _idOfferPerson;
        this.date = date;
        this.description = description;
        this.price = price;
    }
}
