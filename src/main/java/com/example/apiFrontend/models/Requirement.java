package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Getter
@Setter
public class Requirement {

    @Nullable
    private String _id;
    private String _idRequestPerson;
    private String _idTypeJob;
    private String _idRequirementStatus;
    private Date date;
    private Address address;
    private String description;
    private String zone;
    private boolean deleted;
}
