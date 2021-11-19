package com.example.demo.Domain;

import com.example.demo.DataAccess.Models.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class RequirementDto {
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
