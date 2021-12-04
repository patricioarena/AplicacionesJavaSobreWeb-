package com.example.demo.DataAccess.Models;

import com.example.demo.DataAccess.Database;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = Database.requirementCollection)
public class Requirement {

    private ObjectId _id;
    private ObjectId _idRequestPerson;
    private ObjectId _idTypeJob;
    private ObjectId _idRequirementStatus;
    private Date date;
    private Address address;
    private String description;
    private String zone;
    private boolean deleted;

    public Requirement() {
    }

    public Requirement(ObjectId _idRequestPerson, ObjectId _idTypeJob, ObjectId _idRequirementStatus, Date date, Address address, String description, String zone, boolean deleted) {
        super();

        this._idRequestPerson = _idRequestPerson;
        this._idTypeJob = _idTypeJob;
        this._idRequirementStatus = _idRequirementStatus;
        this.date = date;
        this.address = address;
        this.description = description;
        this.zone = zone;
        this.deleted = deleted;
    }

}
