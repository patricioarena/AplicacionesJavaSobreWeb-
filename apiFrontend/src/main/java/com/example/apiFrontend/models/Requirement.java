package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class Requirement {
    private String _idRequestPerson;
    private String _idTypeJob;
    private String _idRequirementStatus;
    private Date date;
    private Address address;
    private String description;
    private String zone;
    private boolean deleted;
}
