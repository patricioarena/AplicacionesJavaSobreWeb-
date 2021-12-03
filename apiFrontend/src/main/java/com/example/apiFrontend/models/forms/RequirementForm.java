package com.example.apiFrontend.models.forms;

import com.example.apiFrontend.models.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter
@Setter
public class RequirementForm {
    private String idRequestPerson;
    private String idTypeJob;
    private String idRequirementStatus;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private Address address;
    private String description;
    private String zone;
    private boolean deleted;
}
