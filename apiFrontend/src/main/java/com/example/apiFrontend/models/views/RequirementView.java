package com.example.apiFrontend.models.views;

import com.example.apiFrontend.models.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@Getter
@Setter
public class RequirementView {

    @Nullable
    private String id;
    private String idRequestPerson;
    private String typeJobName;
    private String requirementStatusName;
    private Date date;
    private Address address;
    private String description;
    private String zone;
    private boolean deleted;
}
