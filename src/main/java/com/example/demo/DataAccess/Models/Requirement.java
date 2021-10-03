package com.example.demo.DataAccess.Models;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "requirementCollection")
public class Requirement {

    @Id
    private Long _idRequirement;

    private Long _idRequestPerson;
    private Long _idTypeJob;
    private Long _idRequirementStatus;
    private Date date;
    private String description;
    private String zone;

    public Requirement() {

    }

    public Requirement(Long _idRequirement, Long _idRequestPerson, Long _idTypeJob, Long _idRequirementStatus, Date date, String description, String zone) {
        super();
        this._idRequirement = _idRequirement;
        this._idRequestPerson = _idRequestPerson;
        this._idTypeJob = _idTypeJob;
        this._idRequirementStatus = _idRequirementStatus;
        this.date = date;
        this.description = description;
        this.zone = zone;
    }

}
