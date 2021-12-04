package com.example.demo.Domain;

import com.example.demo.DataAccess.Database;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
public class OfferDto {
    private String _id;
    private String _idOfferPerson;
    private Date date;
    private String description;
    private Float price;
}
