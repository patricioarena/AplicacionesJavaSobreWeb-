package com.example.demo.DataAccess.Models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "requirementCollection")
public class Requirement {

    private ObjectId _id;
    private ObjectId _idRequestPerson;
    private ObjectId _idTypeJob;
    private ObjectId _idRequirementStatus;
    private Date date;
    private String description;
    private String zone;
    private boolean deleted;

    public Requirement() {
    }

    public Requirement(ObjectId _idRequestPerson, ObjectId _idTypeJob, ObjectId _idRequirementStatus, Date date, String description, String zone, boolean deleted) {
        super();

        this._idRequestPerson = _idRequestPerson;
        this._idTypeJob = _idTypeJob;
        this._idRequirementStatus = _idRequirementStatus;
        this.date = date;
        this.description = description;
        this.zone = zone;
        this.deleted = deleted;
    }

}
