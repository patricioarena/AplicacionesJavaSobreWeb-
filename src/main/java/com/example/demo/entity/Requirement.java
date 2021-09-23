package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
public class Requirement {
    private int _idRequirement;
    private int _idRequestPerson;
    private int _idTypeJob;
    private int _idRequirementStatus;
    private Date date;
    private String description;
    private String zone;
}
