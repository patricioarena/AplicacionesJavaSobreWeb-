package com.example.apiFrontend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequirementStatus {

    private String id;
    private String status;

    public RequirementStatus() {
    }

    public RequirementStatus(String id, String status) {
        super();

        this.id = id;
        this.status = status;
    }
}
