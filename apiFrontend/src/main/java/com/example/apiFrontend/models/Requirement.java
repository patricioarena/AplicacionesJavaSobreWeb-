package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter

public class Requirement {

    private String _id;
    private String _idRequestPerson;
    private String _idTypeJob;
    private String _idRequirementStatus;
    private Date date;
    private Address address;
    private String description;
    private String zone;
    private boolean deleted;

    public Requirement() {
    }

    public Requirement(String _idRequestPerson, String _idTypeJob, String _idRequirementStatus, Date date, Address address, String description, String zone, boolean deleted) {
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
